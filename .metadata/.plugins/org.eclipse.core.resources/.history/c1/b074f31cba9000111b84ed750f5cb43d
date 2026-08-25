package testAttachFileFromRecord;

import java.util.Arrays;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import attachFileFromRecord.Uploadedbyfilter;
import listeners.ReportListener;

@Listeners(ReportListener.class)
public class testuploadedbyfilter extends Uploadedbyfilter {

	// Names as shown in the "Uploaded by" dropdown for this record.
	// Adjust these if the actual uploaders on the test record differ.
	private static final String UPLOADER_WITH_FILES    = "Vanita Patel";
	private static final String UPLOADER_WITHOUT_FILES = "Kinjal Mogha";
	private static final String SECOND_UPLOADER        = "Pinki Mishra";

	@BeforeClass(alwaysRun = true)
	public void init() throws Exception {
		System.out.println(">>> testuploadedbyfilter INIT ENTERED <<<");
		setup();
		loginOnce();
		navigateToFileListingOnce();
	}

	/**
	 * Scenario 1: on opening the popup, filter should show "All" and every file should be visible.
	 */
	@Test(priority = 1, groups = { "smoke" }, enabled = true)
	public void testDefaultFilterShowsAllFiles() {
		verifyDefaultUploadedByShowsAllFiles();
	}

	/**
	 * Scenario 2: selecting a single uploader should show only files uploaded by that person.
	 */
	@Test(priority = 2, groups = { "smoke" }, enabled = true)
	public void testFilterBySingleUploader() {
		filterBySingleUploader(UPLOADER_WITH_FILES);
	}

	/**
	 * Scenario 3: selecting an uploader with no files should show the "No files found" empty state.
	 */
	@Test(priority = 3, enabled = true)
	public void testFilterByUploaderWithNoFiles() {
		filterByUploaderWithNoFiles(UPLOADER_WITHOUT_FILES);
	}

	/**
	 * Scenario 4: selecting multiple uploaders should show the union of their files only.
	 */
	@Test(priority = 4, enabled = true)
	public void testFilterByMultipleUploaders() {
		filterByMultipleUploaders(Arrays.asList(UPLOADER_WITH_FILES, SECOND_UPLOADER));
	}

	/**
	 * Scenario 5: clicking "Select All" should check every uploader and show all files again.
	 */
	@Test(priority = 5, groups = { "smoke" }, enabled = true)
	public void testSelectAllShowsAllFiles() {
		clickSelectAllAndVerify();
	}

	/**
	 * Scenario 6: clicking "Unselect All" should clear every checkbox.
	 * NOTE: confirm actual expected behavior on the real app before trusting this assertion.
	 */
	@Test(priority = 6, enabled = true)
	public void testUnselectAllClearsFilter() {
		clickUnselectAllAndVerify();
	}

	/**
	 * Scenario 7: after selecting an uploader, the trigger box text should update
	 * and no longer just say "All".
	 */
	@Test(priority = 7, enabled = true)
	public void testTriggerLabelUpdatesOnSelection() {
		verifyTriggerLabelUpdates(UPLOADER_WITH_FILES);
	}

	/**
	 * Scenario 8: selecting an uploader, closing the dropdown, then reopening it
	 * should show that checkbox still checked.
	 */
	@Test(priority = 8, enabled = true)
	public void testSelectionPersistsOnReopen() {
		verifySelectionPersistsOnReopen(UPLOADER_WITH_FILES);
	}

	/**
	 * Scenario 9: the "Total X files" counter should always match the number of visible rows.
	 */
	@Test(priority = 9, groups = { "smoke" }, enabled = true)
	public void testFileCounterMatchesVisibleRows() {
		verifyFileCountMatchesVisibleRows();
	}

	@AfterClass(alwaysRun = true)
	public void teardown() {
		tearDown();
	}

}
