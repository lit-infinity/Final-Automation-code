package Login;

import java.time.Duration;

import java.util.concurrent.ExecutionException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Base.base;

public class Login extends base  {

	// Default account. Subclasses can override these before calling loginOnce()
	// so parallel test classes log in with separate accounts instead of colliding.
	protected String loginEmail = "vanita231966@outlook.com";
	protected String loginPassword = "Vanita@123";


	public void invalidLogin(String email, String password) {
		driver.navigate().refresh();
		
	    driver.findElement(By.id("email")).sendKeys(email);
	    driver.findElement(By.id("password")).sendKeys(password);
	    driver.findElement(By.className("btn-primary")).click();
		
//		getDriver().findElement(By.id("email")).sendKeys(email);
//		getDriver().findElement(By.id("password")).sendKeys(password);
//		getDriver().findElement(By.className("btn-primary")).click();
	
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));	    
	    wait.until(ExpectedConditions.textToBePresentInElementLocated(
	    		By.id("noty_topCenter_layout_container"),
	    	    "Email or password seems to be wrong, please try again with valid credentials."
	    	));
		
//		getWait().until(ExpectedConditions.textToBePresentInElementLocated(
//		By.id("noty_topCenter_layout_container"),
//	    "Email or password seems to be wrong, please try again with valid credentials."
//	));

	    
	    String actual = driver.findElement(By.id("noty_topCenter_layout_container")).getText();	
//		String actual = getDriver().findElement(By.id("noty_topCenter_layout_container")).getText();	
	    String expected = "Email or password seems to be wrong, please try again with valid credentials.";
	    
	    System.out.println("Actual Error Message: " + actual);
	    Assert.assertEquals(actual,expected );
		System.out.println("Exicuting last testcase");

	}
	
	
	
	
//	login with valid credentials
	
	
	public void validLogin() throws Exception {
		

    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	
    	driver.navigate().refresh();

    	WebElement email = driver.findElement(By.id("email"));
    	email.clear();
    	email.sendKeys(loginEmail);

    	WebElement passwordfield = driver.findElement(By.id("password"));
    	passwordfield.clear();
    	passwordfield.sendKeys(loginPassword);
        driver.findElement(By.className("btn-primary")).click();
        
        Thread.sleep(500);

        // Wait until redirected to onboarding page
        wait.until(ExpectedConditions.urlToBe("https://vanita.salesmate.io/#/app/onboarding"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[@class='s-m-b-md font-bold']")));
        System.out.println("Onboarding page fully loaded!");


        String expectedUrl = "https://vanita.salesmate.io/#/app/onboarding";
        String actualUrl = driver.getCurrentUrl();

        System.out.println("Actual URL: " + actualUrl);

        Assert.assertEquals(actualUrl, expectedUrl);
    }

}
