package Base;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtil{

    
    public static String takeScreenshot(WebDriver driver,String testName) {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);

            String timestamp = String.valueOf(System.currentTimeMillis());
            String fileName  = testName + "_" + timestamp + ".png";
            String path = System.getProperty("user.dir") 
                          + "/screenshots/" + fileName;

            File destDir = new File(
                System.getProperty("user.dir") + "/screenshots"
            );
            if (!destDir.exists()) {
                destDir.mkdirs(); // auto-create folder if missing
            }

            FileUtils.copyFile(source, new File(path));
            System.out.println("Screenshot saved: " + path);

            return path; // ← must return the path

        } catch (IOException e) {
            System.out.println("Screenshot failed: " + e.getMessage());
            return null; // ← return null on error
        }
    }
}