package learingPackage;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class driverinitialization {
	
	
		
	@Test(priority =1)	
	public void test() throws InterruptedException {
		
		
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		driver.manage().window().maximize();
		driver.get("https://google.com");
		driver.navigate().to("https://dev33.salesmate.io/");
		Thread.sleep(20);
//		driver.navigate().back();
//		driver.navigate().forward();
//		driver.navigate().refresh();
//		
//		String url =driver.getCurrentUrl();
//		System.out.println("current url" +url);
//		
//		System.out.print("coocket"+driver.manage().getCookies()); 
		
		driver.findElement(By.id("email")).sendKeys("vanita.patel@salesmate.io");
		driver.findElement(By.id("password")).sendKeys("Samehere@123");
	
		
		driver.findElement(By.tagName("button"));
		System.out.print("found the tage name");
		
		driver.findElement(By.linkText("Forgot Password?"));
		driver.findElement(By.partialLinkText("Sign up"));
		System.out.println("found the link name");
		


		driver.findElement(
			    By.cssSelector("button[type='submit'][name='btnSubmit']")
			).click();
//		driver.findElement(By.name("btnSubmit")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.urlToBe("https://dev33.salesmate.io/#/app/onboarding"));
		String Actual = driver.getCurrentUrl();
		String Expected = "https://dev33.salesmate.io/#/app/onboarding";
		Assert.assertTrue(Actual.contains(Expected), "wrong url found" );
		

		
		driver.close();
		
	
	
	}

}
