package learingPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Locators {
	
	public static void main(String[]args) {
		WebDriver driver = new ChromeDriver();
		driver.get("https://dev33.salesmate.io/login/");
		
        // Locate element using ID
		
        driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("vanita.patel@salesmate.io");
		
        driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("Samehere@123");
		driver.findElement(By.className("btn-primary")).click();
		

	}

}
