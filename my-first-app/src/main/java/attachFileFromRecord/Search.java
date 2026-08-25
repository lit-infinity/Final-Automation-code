package attachFileFromRecord;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


public class Search extends AttachFromRecordNavigation {

	private final By seachbox      = By.xpath("//list-quick-search//input[@placeholder='Search']");
	private final By resultItems   = By.xpath(".//attach-file-list-item[@class='attach-file-list-item']");
	private final By noMatchingMsg = By.xpath("//h5[@class='empty-state-title']");
	private final By crossicon     = By.xpath("//a[contains(@class,'close-icon')]");

	private WebDriverWait getWait() {
		return new WebDriverWait(driver, Duration.ofSeconds(20));
	}

	/**
	 * Common step: type the search term into the search box and wait for
	 * EITHER results OR the "No matching files" empty-state to appear.
	 * Shared by both the valid-data and invalid-data flows so the
	 * waiting/typing logic isn't duplicated.
	 */
	private void typeSearchTerm(String searchTerm) throws InterruptedException {
		WebDriverWait wait = getWait();
		wait.until(ExpectedConditions.visibilityOfElementLocated(seachbox));

		WebElement box = driver.findElement(seachbox);
		box.clear();
		box.sendKeys(searchTerm);
		System.out.println("Searched for: " + searchTerm);
		Thread.sleep(2000); // consider replacing with an explicit wait on the UI settling, if possible

		wait.until(ExpectedConditions.or(
				ExpectedConditions.visibilityOfElementLocated(resultItems),
				ExpectedConditions.visibilityOfElementLocated(noMatchingMsg)
		));
	}

	/**
	 * Clears the search box via the close/cross icon (if present) and waits
	 * for the full, unfiltered list to reload. Called once per search,
	 * after validation — not inside the per-result loop.
	 */
	private void clearSearch() {
		List<WebElement> close = driver.findElements(crossicon);
		if (!close.isEmpty() && close.get(0).isDisplayed()) {
			close.get(0).click();
			System.out.println("Search cleared");

			getWait().until(ExpectedConditions.visibilityOfElementLocated(resultItems));
			System.out.println("All files reloaded after clear");
		}
	}

	/**
	 * Search using a VALID term — i.e. a term expected to return at least
	 * one matching result. Fails the test if "No matching files" shows up,
	 * or if no displayed result actually contains the search term.
	 *
	 * @param searchTerm the valid search term to type into the search box
	 */
	public void searchValidData(String searchTerm) throws InterruptedException {

		typeSearchTerm(searchTerm);

		List<WebElement> noMatchElement = driver.findElements(noMatchingMsg);
		if (!noMatchElement.isEmpty() && noMatchElement.get(0).isDisplayed()) {
			Assert.fail("Expected matching results for valid search term '" + searchTerm
					+ "' but 'No matching files' message was shown instead");
		}

		List<WebElement> results = driver.findElements(resultItems);
		Assert.assertTrue(results.size() > 0,
				"No results displayed after searching valid term: " + searchTerm);
		System.out.println("Total results displayed: " + results.size());
		System.out.println("-----------------------------");

		boolean anyResultMatched = false;

		for (int i = 0; i < results.size(); i++) {
			String resultText = results.get(i).getText().toLowerCase();

			if (resultText.contains(searchTerm.toLowerCase())) {
				anyResultMatched = true;
				System.out.println("✅ Result " + (i + 1) + " CONTAINS '" + searchTerm + "' -> " + resultText);
			} else {
				System.out.println("❌ Result " + (i + 1) + " does NOT contain '" + searchTerm + "' -> " + resultText);
			}
		}

		// Checked once, after the full loop — not per-iteration.
		Assert.assertTrue(anyResultMatched,
				"❌ FAILED - None of the results contain the search term: " + searchTerm);
		System.out.println("✅ Validation PASSED - At least one result contains: " + searchTerm);

		clearSearch();
		Thread.sleep(3000);
	}

	/**
	 * Search using an INVALID term — i.e. a term expected to return NO
	 * results. Fails the test if any results show up instead of the
	 * "No matching files" empty-state.
	 *
	 * @param searchTerm the invalid search term to type into the search box
	 */
	public void searchInvalidData(String searchTerm) throws InterruptedException {

		typeSearchTerm(searchTerm);

		List<WebElement> noMatchElement = driver.findElements(noMatchingMsg);

		if (!noMatchElement.isEmpty() && noMatchElement.get(0).isDisplayed()) {

			System.out.println("❌ No matching files found for: " + searchTerm);

			Assert.assertTrue(noMatchElement.get(0).isDisplayed(),
					"No matching files message should be visible");
			Assert.assertEquals(noMatchElement.get(0).getText(), "No matching files",
					"No result message text mismatch");

			System.out.println("✅ Validated: 'No matching files' message is correctly displayed");

		} else {
			List<WebElement> results = driver.findElements(resultItems);
			Assert.fail("Expected 'No matching files' for invalid search term '" + searchTerm
					+ "' but got " + results.size() + " result(s) instead");
		}

		clearSearch();
	}

} // class