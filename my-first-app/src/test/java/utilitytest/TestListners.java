package utilitytest;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import Base.ScreenshotUtil;
import Base.base;


public class TestListners implements ITestListener {
	
	 @Override
	    public void onTestStart(ITestResult result) {
	        System.out.println("Test Started: " + result.getName());
	    }

	    @Override
	    public void onTestSuccess(ITestResult result) {
	        System.out.println("Test Passed: " + result.getName());
	    }

	    @Override
	    public void onTestFailure(ITestResult result) {
	        System.out.println("Test Failed: " + result.getName());

	        // ✅ Get driver from the test class
	        Object testClass = result.getInstance();
	        WebDriver driver = ((base) testClass).driver;

	        // ✅ Take screenshot at exact failure point
	        ScreenshotUtil.takeScreenshot(driver, result.getName());
	    }

	    @Override
	    public void onTestSkipped(ITestResult result) {
	        System.out.println("Test Skipped: " + result.getName());
	    }

	    @Override
	    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	    }

	    @Override
	    public void onStart(ITestContext context) {
	        System.out.println("Suite Started: " + context.getName());
	    }

	    @Override
	    public void onFinish(ITestContext context) {
	        System.out.println("Suite Finished: " + context.getName());
	    }
}
