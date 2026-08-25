package learingPackage;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class practice {
	@Test(priority = 1)
	public void testing() {
		WebDriver driver  = new ChromeDriver();
		driver.get("https://dev33.salesmate.io/");
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
		driver.findElement(By.id("email")).sendKeys("vanita.patel@salesmate.io");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
		driver.findElement(By.id("password")).sendKeys("Samehere@123");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnSubmit")));
		driver.findElement(By.id("btnSubmit")).click();
		wait.until(ExpectedConditions.urlToBe("https://dev33.salesmate.io/#/app/onboarding"));
		String Actual = driver.getCurrentUrl();
		String Expected = "https://dev33.salesmate.io/#/app/onboarding";
		Assert.assertTrue(Actual.contains(Expected), "url not found");
//		WebElement msg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("success message")));
//		Assert.assertEquals(msg.getText(), "success", "login failed");
		driver.quit();

		
		
		
	}
	
	

}
