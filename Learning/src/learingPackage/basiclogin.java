package learingPackage;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class basiclogin {
	public static void main(String[]args) {
		
		WebDriver driver =new ChromeDriver();
	    driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.get("https://dev33.salesmate.io/");
		driver.findElement(By.id("email")).sendKeys("vanita.patel@rapidops.com");
		driver.findElement(By.id("password")).sendKeys("Vanita@123");
		driver.findElement(By.className("btn-primary")).click();
//		wait.until(ExpectedConditions.urlToBe("test"));
		driver.quit();
	}
}
	