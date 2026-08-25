package attachFileFromRecord;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Uploadedbyfilter extends AttachFromRecordNavigation {

	// ---------- Locators ----------

	private final By Uploadedbydropdwon  	= By.xpath("(//input[@class='select2-search__field'])[2]");
	private final By Unselectall         	= By.id("unselect_all");
	private final By Selectall 				= By.id("select_all");
	private final By Totalfilescounttext 	= By.xpath("(//span[@class = 'font-size-semi-sm m-r-md'])[2]");
	private final By FileListing     		= By.xpath("//div[@class='attach-file-list-container']");
	private final By Allfilerows     	 	= By.xpath(".//attach-file-list-item[@class='attach-file-list-item']");
	private final By Filename        	 	= By.xpath(".//div[@class='file-name']");
	private final By Uploader        	 	= By.xpath(".//span[@class='meta-item'][3]");
	private final By Date                	= By.xpath(".//span[@class='meta-item'][1]");
	private final By Size                	= By.xpath(".//span[@class='meta-item'][2]");
	private final By emptyStateContainer 	= By.xpath("//div[@class='empty-state w-full']");
	private final By emptyStateIcon      	= By.xpath("//div[@class='empty-state-icon']");
	private final By emptyStateTitle    	= By.xpath("//h5[@class='empty-state-title']");
	private final By emptyStateSubText   	= By.xpath("//div[contains(text(),'Upload a file to this record')]");

	private WebDriverWait getWait() {
		return new WebDriverWait(driver, Duration.ofSeconds(20));
	}

	// Builds the option locator for whichever uploader name is passed in,
	// instead of relying on a fixed locator per name.
	private By uploaderOption(String uploaderName) {
		return By.xpath("//li[contains(@class,'select2-results__option')][.//span[@title='" + uploaderName + "']]");
	}

	private int getTotalFilesFromCounter() {
		String text = driver.findElement(Totalfilescounttext).getText(); // e.g. "Total 7 files"
		return Integer.parseInt(text.replaceAll("[^0-9]", ""));
	}

	// ---------- Dropdown lifecycle ----------

	private void openDropdown() {
		getWait().until(ExpectedConditions.elementToBeClickable(Uploadedbydropdwon)).click();
		System.out.println("Opened 'Uploaded by' dropdown");
	}

	// Escape closes the select2 panel without depending on clicking the
	// search input again (which just keeps focus/reopens rather than closing).
	private void closeDropdown() {
		driver.findElement(Uploadedbydropdwon).sendKeys(Keys.ESCAPE);
		System.out.println("Closed 'Uploaded by' dropdown");
	}

	// Opens the dropdown, runs the given action, and ALWAYS closes the dropdown
	// afterwards - even if the action throws - so the next test method never
	// inherits a half-open/broken dropdown state left behind by this one.
	private void withDropdownOpen(Runnable action) {
		openDropdown();
		try {
			action.run();
		} finally {
			closeDropdown();
		}
	}

	// ---------- Scenarios ----------

	public void filterBySingleUploader(String expectedUploader) throws InterruptedException {

		withDropdownOpen(() -> {
			driver.findElement(uploaderOption(expectedUploader)).click();
			System.out.println("Selected '" + expectedUploader + "' from Uploaded by filter");
		});

		getWait().until(ExpectedConditions.visibilityOfElementLocated(Allfilerows));

		List<WebElement> rows = driver.findElement(FileListing).findElements(Allfilerows);
		Assert.assertTrue(!rows.isEmpty(), "No file rows found after filtering by uploader: " + expectedUploader);
		System.out.println("Total rows after filter: " + rows.size());

		for (WebElement row : rows) {
			String fileName = row.findElement(Filename).getText().trim();
			String uploader = row.findElement(Uploader).getText().trim();

			Assert.assertEquals(uploader, expectedUploader,
					"File '" + fileName + "' was uploaded by '" + uploader
					+ "' but expected only files from '" + expectedUploader + "'");
			System.out.println("Verified '" + fileName + "' uploaded by " + uploader);
		}

		System.out.println("All " + rows.size() + " file(s) verified as uploaded by: " + expectedUploader);
		System.out.println("----------first testcase passed-----------------");
	}

	public void Unselectall() throws InterruptedException {

		withDropdownOpen(() -> {
			driver.findElement(Unselectall).click();
			System.out.println("'Unselect all' clicked");
		});

		getWait().until(ExpectedConditions.visibilityOfElementLocated(Allfilerows));

		List<WebElement> allFileRows = driver.findElement(FileListing).findElements(Allfilerows);
		System.out.println("orignal Total files found: " + allFileRows.size());
		Assert.assertTrue(allFileRows.size() > 0, "No file rows found!");

		for (WebElement row : allFileRows) {

			// Verify row data
			String fileName = row.findElement(Filename).getText().trim();
			String date     = row.findElement(Date).getText().trim();
			String size     = row.findElement(Size).getText().trim();
			String uploader = row.findElement(Uploader).getText().trim();

			Assert.assertFalse(fileName.isEmpty(), "File name empty");
			Assert.assertFalse(date.isEmpty(),     "Date empty");
			Assert.assertFalse(size.isEmpty(),     "Size empty");
			Assert.assertFalse(uploader.isEmpty(), "Uploader empty");
			System.out.println("Row verified: " + fileName + " | " + date + " | " + size + " | " + uploader);
		}

		System.out.println("---------------second testcase passed--------------------");
	}

	public void filterByUploaderWithNoFiles(String uploaderName) {

		withDropdownOpen(() -> {
			driver.findElement(uploaderOption(uploaderName)).click();
			System.out.println("Selected '" + uploaderName + "' from Uploaded by filter");
		});

		getWait().until(ExpectedConditions.visibilityOfElementLocated(emptyStateContainer));
		Assert.assertTrue(driver.findElement(emptyStateContainer).isDisplayed(), "Empty container not visible");
		Assert.assertTrue(driver.findElement(emptyStateIcon).isDisplayed(), "Empty icon not visible");

		String title = driver.findElement(emptyStateTitle).getText().trim();
		Assert.assertEquals(title, "No files found", "Wrong title");
		System.out.println("Empty state title: " + title);

		String subtext = driver.findElement(emptyStateSubText).getText().trim();
		Assert.assertEquals(subtext, "Upload a file to this record to see it here.", "Wrong subtext");
		System.out.println("Empty state verified successfully");
		System.out.println("---------------third testcase passed-----------------");
	}

	public void filterByMultipleUploaders(List<String> uploaderNames) throws InterruptedException {

		withDropdownOpen(() -> {
			driver.findElement(Unselectall).click();
			for (String name : uploaderNames) {
				getWait().until(ExpectedConditions.elementToBeClickable(uploaderOption(name))).click();
			}
		});

		getWait().until(ExpectedConditions.or(
				ExpectedConditions.visibilityOfElementLocated(Allfilerows),
				ExpectedConditions.visibilityOfElementLocated(emptyStateTitle)
		));
		List<WebElement> allFileRows = driver.findElement(FileListing).findElements(Allfilerows);
		Assert.assertFalse(allFileRows.isEmpty(), "Expected at least one file for uploaders: " + uploaderNames);

		for (WebElement row : allFileRows) {
			String uploader = row.findElement(Uploader).getText().trim();
			Assert.assertTrue(uploaderNames.contains(uploader),
					"Found a file uploaded by someone outside the selected list -> " + uploader);
		}
		System.out.println("Verified all " + allFileRows.size() + " visible files were uploaded by one of: " + uploaderNames);
		System.out.println("---------------forth testcase passed-----------------");
	}

	public void clickSelectAllAndVerify() {

		withDropdownOpen(() -> {
			driver.findElement(Unselectall).click();
			driver.findElement(Selectall).click();
		});

		getWait().until(ExpectedConditions.or(
				ExpectedConditions.visibilityOfElementLocated(Allfilerows),
				ExpectedConditions.visibilityOfElementLocated(emptyStateTitle)
		));

		List<WebElement> allFileRows = driver.findElement(FileListing).findElements(Allfilerows);
		int total = getTotalFilesFromCounter();
		Assert.assertEquals(allFileRows.size(), total, "'Select All' should show every file");
		System.out.println("Verified 'Select All' shows all " + total + " files");
		System.out.println("---------------fifth testcase passed-----------------");
	}

}
