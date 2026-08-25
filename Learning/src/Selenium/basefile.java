package Selenium;

public class basefile {

}
package Base;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.io.IOException;
import java.time.Duration;

public class base {
	
//	public WebDriver driver;
//    public WebDriverWait wait;

    // ✅ CORRECT - each thread gets its own driver
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driver.get();
    }

	
//	public WebDriver getDriver() {
//        return driver.get();
//    }
//
//    public WebDriverWait getWait() {
//        return wait.get();
//    }
	 
	 // shared lock object — same for all instances/threads
//	private static final Object LOGIN_LOCK = new Object();


    public void setup() {
    	
    	if (driver != null) {
            System.out.println("Driver already exists — reusing existing browser session");
            return;   // ✅ skip opening a new browser entirely
        }
        String browser = "chrome"; // ✅ Switch between "chrome" or "edge"
//        WebDriver webDriver = null;

        if (browser.equalsIgnoreCase("chrome")) {

            // ✅ Chrome Setup
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-notifications");
            options.addArguments("--disable-popup-blocking");
            driver = new ChromeDriver(options);
//            webDriver = new ChromeDriver(options);
            System.out.println("Chrome browser launched successfully");

        } else if (browser.equalsIgnoreCase("edge")) {

            // ✅ Edge Setup
            EdgeOptions options = new EdgeOptions();
            options.addArguments("--disable-notifications");
            options.addArguments("--disable-popup-blocking");
            driver = new EdgeDriver(options);
//            webDriver = new EdgeDriver(options);
            System.out.println("Edge browser launched successfully");

        } else {
            System.out.println("❌ Invalid browser name! Use 'chrome' or 'edge'");
        }

        driver.manage().window().maximize();
        driver.get("https://vanita.salesmate.io/login/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
//        webDriver.manage().window().maximize();
//        webDriver.get("https://vanita.salesmate.io/login/");
//        driver.set(webDriver);
//        wait.set(new WebDriverWait(getDriver(), Duration.ofSeconds(10)));
    }
    

//    public void synchronizedLogin(Runnable loginSteps) {
//        synchronized (LOGIN_LOCK) {
//            loginSteps.run();
//        }
//    }

    public void tearDown() {                             // quits browser
        if (driver != null) {
            driver.quit();
            driver = null;  
            System.out.println("Browser closed successfully");
            
//    	if (getDriver() != null) {
//            getDriver().quit();
//            driver.remove();   // ✅ IMPORTANT: clears thread after use (prevents memory leak)
//            wait.remove();     // ✅ IMPORTANT: clears thread after use
//            System.out.println("Browser closed successfully");
        }
    }
}