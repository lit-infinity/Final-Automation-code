package learingPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.JavascriptExecutor;
import java.time.Duration;

public class TimeoutsExample {

    public static void main(String[] args) {

        WebDriver driver =new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
        
        driver.get("https://www.google.com");
        driver.findElement(By.name("q")).sendKeys("Selenium Webdriver");
        
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeAsyncScript("alert('this is testing script')");
        
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        driver.switchTo().alert().accept();
        driver.quit();


     
    }
}
