package attachFileFromRecord;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Uploadedbyfilter extends AttachFromRecordNavigation {

	// ---------- Locators ----------

	private final By uploadedByTrigger  = By.xpath(
			"//select[contains(@id,'uploadedBy')]/following-sibling::span[contains(@class,'select2-container')]"
			+ "//span[contains(@class,'select2-selection--multiple')]");
	private final By dropdownPanel      = By.xpath("//span[@class='select2-dropdown select2-dropdown--below']");
	private final By selectAllLink      = By.id("select_all");
	private final By unselectAllLink    = By.id("unselect_all");
	private final By fileListing        = By.xpath("//div[@class='attach-file-list-container']");
	private final By allFileRowsLocator = By.xpath(".//attach-file-list-item[@class='attach-file-list-item']");
	private final By uploaderInRow      = By.xpath(".//span[@class='meta-item'][3]");
	private final By emptyStateTitle    = By.xpath("//h5[@class='empty-state-title']");
	private final By totalFilesText     = By.xpath("//*[contains(text(),'Total') and contains(text(),'files')]");

	// Select2 "chips" for whichever uploaders are currently selected. Zero chips
	// means no filter applied (all files shown) — checking this instead of
	// trigger text, since "All" is only a placeholder attribute, not visible text.
	private final By selectedChips = By.xpath(
			"//select[contains(@id,'uploadedBy')]/following-sibling::span[contains(@class,'select2-container')]"
			+ "//li[contains(@class,'select2-selection__choice')]");

	private WebDriverWait getWait() {
		return new WebDriverWait(driver, Duration.ofSeconds(20));
	}

	// The underlying <select> has real <option> elements (confirmed via DevTools:
	// "Kinjal Mogha", "Pinki Mishra", "Vanita Patel", "--Logged in user--").
	// Select2 renders each as a clickable <li class="select2-results__option">
	// in its floating results panel — NOT a literal <input type="checkbox">;
	// the checkbox look is just CSS styling on that <li>.
	private By checkboxForUser(String name) {
		return By.xpath("//li[contains(@class,'select2-results__option')][normalize-space()='" + name + "']");
	}

	// ---------- Dropdown helpers ----------

	private void openDropdown() {
		getWait().until(ExpectedConditions.elementToBeClickable(uploadedByTrigger)).click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(dropdownPanel));
		System.out.println("Opened 'Uploaded by' dropdown");
	}

	private void closeDropdown() {
		driver.findElement(uploadedByTrigger).click();
		System.out.println("Closed 'Uploaded by' dropdown");
	}

	// Opens the dropdown, runs the given action, and guarantees the dropdown
	// gets closed afterwards even if the action throws — otherwise a failed
	// click leaves the panel open and the next test's openDropdown() ends up
	// toggling it CLOSED instead of open (causing a cascading timeout).
	private void withDropdownOpen(Runnable action) {
		openDropdown();
		try {
			action.run();
		} finally {
			closeDropdown();
		}
	}

	private void waitForListToSettle() {
		getWait().until(ExpectedConditions.or(
				ExpectedConditions.visibilityOfElementLocated(allFileRowsLocator),
				ExpectedConditions.visibilityOfElementLocated(emptyStateTitle)
		));
	}

	private List<WebElement> getVisibleFileRows() {
		return driver.findElements(allFileRowsLocator);
	}

	private int getTotalFilesFromCounter() {
		String text = driver.findElement(totalFilesText).getText(); // e.g. "Total 7 files"
		return Integer.parseInt(text.replaceAll("[^0-9]", ""));
	}

	// ---------- Scenario 1: Default state shows all files ----------
	public void verifyDefaultUploadedByShowsAllFiles() {
		Assert.assertEquals(driver.findElements(selectedChips).size(), 0,
				"Default 'Uploaded by' filter should have no uploader selected (shows all)");

		waitForListToSettle();
		List<WebElement> rows = getVisibleFileRows();
		int total = getTotalFilesFromCounter();
		Assert.assertEquals(rows.size(), total, "Visible rows should match total file count by default");
		System.out.println("Default state verified: " + rows.size() + " files shown, matches total: " + total);
	}

	// ---------- Scenario 2: Single uploader selection ----------
	public void filterBySingleUploader(String uploaderName) {
		withDropdownOpen(() -> {
			driver.findElement(unselectAllLink).click();
			driver.findElement(checkboxForUser(uploaderName)).click();
		});
		waitForListToSettle();

		List<WebElement> rows = getVisibleFileRows();
		Assert.assertFalse(rows.isEmpty(), "Expected at least one file for uploader: " + uploaderName);

		for (WebElement row : rows) {
			String uploader = row.findElement(uploaderInRow).getText().trim();
			Assert.assertEquals(uploader, uploaderName,
					"Found a file not uploaded by " + uploaderName + " -> " + uploader);
		}
		System.out.println("Verified all " + rows.size() + " visible files were uploaded by: " + uploaderName);
	}

	// ---------- Scenario 3: Uploader with zero files shows empty state ----------
	public void filterByUploaderWithNoFiles(String uploaderName) {
		withDropdownOpen(() -> {
			driver.findElement(unselectAllLink).click();
			driver.findElement(checkboxForUser(uploaderName)).click();
		});
		waitForListToSettle();

		Assert.assertFalse(driver.findElements(emptyStateTitle).isEmpty(),
				"Expected empty state for uploader with no files: " + uploaderName);
		String title = driver.findElement(emptyStateTitle).getText().trim();
		Assert.assertEquals(title, "No files found", "Wrong empty-state title");
		System.out.println("Verified empty state correctly shown for uploader with no files: " + uploaderName);
	}

	// ---------- Scenario 4: Multiple uploaders selected (union of files) ----------
	public void filterByMultipleUploaders(List<String> uploaderNames) {
		withDropdownOpen(() -> {
			driver.findElement(unselectAllLink).click();
			for (String name : uploaderNames) {
				driver.findElement(checkboxForUser(name)).click();
			}
		});
		waitForListToSettle();

		List<WebElement> rows = getVisibleFileRows();
		Assert.assertFalse(rows.isEmpty(), "Expected at least one file for uploaders: " + uploaderNames);

		for (WebElement row : rows) {
			String uploader = row.findElement(uploaderInRow).getText().trim();
			Assert.assertTrue(uploaderNames.contains(uploader),
					"Found a file uploaded by someone outside the selected list -> " + uploader);
		}
		System.out.println("Verified all " + rows.size() + " visible files were uploaded by one of: " + uploaderNames);
	}

	// ---------- Scenario 5: 'Select All' shows every file ----------
	public void clickSelectAllAndVerify() {
		withDropdownOpen(() -> driver.findElement(selectAllLink).click());
		waitForListToSettle();

		List<WebElement> rows = getVisibleFileRows();
		int total = getTotalFilesFromCounter();
		Assert.assertEquals(rows.size(), total, "'Select All' should show every file");
		System.out.println("Verified 'Select All' shows all " + total + " files");
	}

	// ---------- Scenario 6: 'Unselect All' reverts to showing all files ----------
	// Confirmed by actual test run: unselecting every uploader does NOT show the
	// empty state — it falls back to "no filter" (same as default), since no
	// uploader selected means nothing to filter out.
	public void clickUnselectAllAndVerify() {
		withDropdownOpen(() -> driver.findElement(unselectAllLink).click());
		waitForListToSettle();

		List<WebElement> rows = getVisibleFileRows();
		int total = getTotalFilesFromCounter();
		Assert.assertEquals(rows.size(), total, "'Unselect All' should fall back to showing every file");
		System.out.println("Verified 'Unselect All' shows all " + total + " files");
	}

	// ---------- Scenario 7: A selection chip appears after selecting an uploader ----------
	public void verifyTriggerLabelUpdates(String uploaderName) {
		withDropdownOpen(() -> {
			driver.findElement(unselectAllLink).click();
			driver.findElement(checkboxForUser(uploaderName)).click();
		});

		int chipCount = driver.findElements(selectedChips).size();
		Assert.assertTrue(chipCount > 0, "Expected a selection chip after selecting: " + uploaderName);
		System.out.println("Selection chip(s) present after selecting " + uploaderName + ": " + chipCount);
	}

	// ---------- Scenario 8: Selection persists when dropdown is reopened ----------
	public void verifySelectionPersistsOnReopen(String uploaderName) {
		withDropdownOpen(() -> {
			driver.findElement(unselectAllLink).click();
			driver.findElement(checkboxForUser(uploaderName)).click();
		});

		withDropdownOpen(() -> {
			WebElement option = driver.findElement(checkboxForUser(uploaderName));
			// select2-results__option isn't a real checkbox — Select2 marks
			// selection state via aria-selected, not the isSelected() API.
			Assert.assertEquals(option.getAttribute("aria-selected"), "true",
					"Option for " + uploaderName + " should remain marked selected on reopen");
			System.out.println("Verified selection for " + uploaderName + " persisted after reopening dropdown");
		});
	}

	// ---------- Scenario 9: File counter matches visible rows ----------
	public void verifyFileCountMatchesVisibleRows() {
		waitForListToSettle();
		List<WebElement> rows = getVisibleFileRows();
		int counterValue = getTotalFilesFromCounter();
		Assert.assertEquals(rows.size(), counterValue,
				"Displayed file count (" + rows.size() + ") does not match counter text (" + counterValue + ")");
		System.out.println("Verified file counter matches visible rows: " + counterValue);
	}

}
