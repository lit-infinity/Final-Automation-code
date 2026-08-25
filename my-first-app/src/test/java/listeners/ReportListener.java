package listeners;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReportListener implements ITestListener {

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("[ReportListener] onTestSuccess fired for: " + result.getName());
        writeResult(result, "PASS", "");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("[ReportListener] onTestFailure fired for: " + result.getName());
        String screenshotPath = captureScreenshot(result);
        writeResult(result, "FAIL", screenshotPath);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("[ReportListener] onTestSkipped fired for: " + result.getName());
        writeResult(result, "SKIPPED", "");
    }

    private void writeResult(ITestResult result, String status, String screenshotPath) {
        String className = result.getTestClass().getRealClass().getSimpleName();

        File dir = new File("target");
        if (!dir.exists()) {
            boolean created = dir.mkdirs();
            System.out.println("[ReportListener] target folder created: " + created);
        }

        String fileName = "target/" + className + "_TestReport.csv";
        System.out.println("[ReportListener] Writing to: " + new File(fileName).getAbsolutePath());

        boolean fileExists = new File(fileName).exists();

        // Dynamically reads whatever was passed into the test method (e.g. from a
        // @DataProvider). Empty for plain @Test methods with no parameters.
        String inputData = getInputDataAsString(result);

        try (FileWriter fw = new FileWriter(fileName, true)) {
            if (!fileExists) {
                fw.write("TestCaseName,InputData,Status,Timestamp,ScreenshotPath\n");
            }
            String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            fw.write(escapeCsv(result.getName()) + ","
                    + escapeCsv(inputData) + ","
                    + status + ","
                    + timestamp + ","
                    + escapeCsv(screenshotPath) + "\n");
            fw.flush();
        } catch (IOException e) {
            System.out.println("[ReportListener] ERROR writing file:");
            e.printStackTrace();
        }
    }

    // ---------------- Screenshot logic ----------------

    /**
     * Captures a screenshot for a failed test, IF a WebDriver instance can be
     * found on the test class (via reflection). Returns the saved file's
     * absolute path, or "" if no driver was found / capture failed.
     */
    private String captureScreenshot(ITestResult result) {
        WebDriver driver = findDriver(result.getInstance());

        if (driver == null) {
            System.out.println("[ReportListener] No WebDriver found on test instance - skipping screenshot for: "
                    + result.getName());
            return "";
        }

        try {
            String className = result.getTestClass().getRealClass().getSimpleName();

            File screenshotDir = new File("target/screenshots/" + className);
            if (!screenshotDir.exists()) {
                screenshotDir.mkdirs();
            }

            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            // result.getName() is the @Test method name (e.g. searchWithInvalidData)
            String fileNameOnly = result.getName() + "_" + timestamp + ".png";

            File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Path destination = Paths.get(screenshotDir.getPath(), fileNameOnly);

            Files.copy(sourceFile.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);

            System.out.println("[ReportListener] Screenshot saved: " + destination.toAbsolutePath());
            return destination.toAbsolutePath().toString();

        } catch (Exception e) {
            System.out.println("[ReportListener] Failed to capture screenshot for: " + result.getName());
            e.printStackTrace();
            return "";
        }
    }

    /**
     * Walks up the test class's hierarchy looking for any field whose type is
     * (or extends) WebDriver, and returns its current value. This is what
     * makes screenshot capture work for EVERY test class automatically:
     * no interface to implement, no field name convention required, no
     * change needed in Search.java / base navigation classes / test classes.
     */
    private WebDriver findDriver(Object testInstance) {
        if (testInstance == null) {
            return null;
        }

        Class<?> clazz = testInstance.getClass();
        while (clazz != null) {
            for (Field field : clazz.getDeclaredFields()) {
                if (WebDriver.class.isAssignableFrom(field.getType())) {
                    try {
                        field.setAccessible(true);
                        Object value = field.get(testInstance);
                        if (value instanceof WebDriver) {
                            return (WebDriver) value;
                        }
                    } catch (IllegalAccessException e) {
                        System.out.println("[ReportListener] Could not access driver field: " + e.getMessage());
                    }
                }
            }
            clazz = clazz.getSuperclass();
        }
        return null;
    }

    // ---------------- existing helpers ----------------

    private String getInputDataAsString(ITestResult result) {
        Object[] params = result.getParameters();
        if (params == null || params.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < params.length; i++) {
            sb.append(params[i] == null ? "null" : params[i].toString());
            if (i < params.length - 1) {
                sb.append(" | ");
            }
        }
        return sb.toString();
    }

    private String escapeCsv(String value) {
        if (value == null) {
            return "\"\"";
        }
        String escaped = value.replace("\"", "\"\"");
        return "\"" + escaped + "\"";
    }
}