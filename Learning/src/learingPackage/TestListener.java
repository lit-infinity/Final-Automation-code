package learingPackage;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TestListener implements ITestListener {

    private static FileWriter writer;

    @Override
    public void onStart(ITestContext context) {
        // Create reports folder if it doesn't exist
        try {
            File reportsFolder = new File("reports");
            if (!reportsFolder.exists()) {
                reportsFolder.mkdir();
            }
            // Overwrite CSV file on each test run
            writer = new FileWriter("reports/test-results.csv");
            writer.append("Test Name,Status\n"); // header
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println(result.getName() + " PASSED");
        writeResult(result.getName(), "PASSED");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println(result.getName() + " FAILED");
        writeResult(result.getName(), "FAILED");
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("All tests finished!");
        try {
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeResult(String testName, String status) {
        try {
            writer.append(testName + "," + status + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}