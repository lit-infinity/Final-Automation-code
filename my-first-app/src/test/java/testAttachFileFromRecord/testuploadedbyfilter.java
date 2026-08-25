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
	private static final String SECOND_UPLOADER    = "Vanita Patel";
	private static final String UPLOADER_WITH_FILES = "Kinjal Mogha";
	private static final String UPLOADER_WITHOUT_FILES = "Pinki Mishra";

	@BeforeClass(alwaysRun = true)
	public void init() throws Exception {
		System.out.println(">>> testuploadedbyfilter INIT ENTERED <<<");
		setup();
		loginOnce();
		navigateToFileListingOnce();
	}

	/**
	 * Scenario 1: on opening the popup, filter should show "All" and every file should be visible.
	 * @throws InterruptedException 
	 */
	@Test(priority = 1, groups = { "smoke" }, enabled = true)
	public void singleuploader() throws InterruptedException {
		filterBySingleUploader(UPLOADER_WITH_FILES);
	}
	
	@Test(priority = 2, enabled = true, dependsOnMethods = {"singleuploader"})
	public void Origanalfilestate() throws InterruptedException {
		Unselectall();
	}
	
	@Test(priority = 3, groups = { "smoke" }, enabled = true)
	public void Emptystate(){
		filterByUploaderWithNoFiles(UPLOADER_WITHOUT_FILES);
	}
	
	@Test(priority = 4, enabled = true, groups = { "smoke" })
	public void testFilterByMultipleUploaders() throws InterruptedException {
		filterByMultipleUploaders(Arrays.asList(UPLOADER_WITH_FILES, SECOND_UPLOADER));
	}
	
	@Test(priority = 5, groups = { "smoke" }, enabled = true)
	public void testSelectAllShowsAllFiles() {
		clickSelectAllAndVerify();
	}


	@AfterClass(alwaysRun = true)
	public void teardown() {
		tearDown();
	}

}
