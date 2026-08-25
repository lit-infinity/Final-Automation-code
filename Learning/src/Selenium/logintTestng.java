package Selenium;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import org.testng.annotations.Listeners;
import org.openqa.selenium.WebElement;


@Listeners(learingPackage.TestListener.class)
public class logintTestng {

    WebDriver driver;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://dev33.salesmate.io/login/");
    }
    
    @Test(priority = 1)
   
    public void invalidlogin() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	driver.findElement(By.id("email")).sendKeys("vanita.patel"); 
    	driver.findElement(By.className("btn-primary")).click();
    	WebElement InvalidEmail =wait.until(ExpectedConditions.visibilityOfElementLocated(
    			By.xpath("//div[@class='error-message text-danger']")));
    	System.out.println("Actual error message" + InvalidEmail.getText().trim() );
    	Assert.assertEquals(InvalidEmail.getText().trim(), "Email is invalid");
    	
    	System.out.println("Exicuting 1 testcase");
    	
    	
    }
   
	@Test(priority = 2)
	public void emptyLoginValidation() {
		driver.navigate().refresh();
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		driver.findElement(By.className("btn-primary")).click();
		
		WebElement Emailerror =wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[contains(text(),'Email is required')]")));
		WebElement Passworderror =wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[contains(text(),'Password is required')]")));
		
		System.out.println("i m printing the email message"+Emailerror.getText().trim());
		System.out.println("i m printing the password message"+ Passworderror.getText().trim());
		
		Assert.assertEquals(Emailerror.getText().trim(), "Email is required");
		Assert.assertEquals(Passworderror.getText().trim(), "Password is required");
		System.out.println("Exicuting 3 testcase");

	}
	
    
	@Test(priority = 3)
	public void invalidLogin() {
		driver.navigate().refresh();
	    driver.findElement(By.id("email")).sendKeys("vanita.patel@salesmate.io");
	    driver.findElement(By.id("password")).sendKeys("Samehere@1234");
	    driver.findElement(By.className("btn-primary")).click();
	
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));	    
	    wait.until(ExpectedConditions.textToBePresentInElementLocated(
	    		By.id("noty_topCenter_layout_container"),
	    	    "Email or password seems to be wrong, please try again with valid credentials."
	    	));
	    
	    String actual = driver.findElement(By.id("noty_topCenter_layout_container")).getText();	
	    String expected = "Email or password seems to be wrong, please try again with valid credentials.";
	    
	    System.out.println("Actual Error Message: " + actual);
	    Assert.assertEquals(actual,expected );
		System.out.println("Exicuting last testcase");

	}
	
	@Test(priority =4)
	public void verifyRememberMetext() {
		WebElement Remebermetext = driver.findElement(By.className("text-ellipsis"));
		Assert.assertTrue(Remebermetext.isDisplayed());
		Assert.assertEquals(Remebermetext.getText().trim(), "Remember Me");
	}
	
	@Test(priority = 5)
	public void verifyRememberMeCheckbox() {

	    // Actual checkbox (for verification)
	    WebElement checkbox = driver.findElement(By.id("rememberMeCheckboxField"));

	    // Label (for clicking)
	    WebElement label = driver.findElement(By.xpath("//label[@class='i-checks']"));

	    System.out.println("element is found");

	    // Default state
	    Assert.assertFalse(checkbox.isSelected());

	    // Click label
	    label.click();

	    // Verify checkbox state
	    System.out.println("Checkbox state: " + checkbox.isSelected());

	    Assert.assertTrue(checkbox.isSelected());
	}
	

    @Test(priority = 6)
    public void validLogin() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	
    	driver.navigate().refresh();

    	WebElement email = driver.findElement(By.id("email"));
    	email.clear();
    	email.sendKeys("vanita.patel@salesmate.io");

    	WebElement password = driver.findElement(By.id("password"));
    	password.clear();
    	password.sendKeys("Samehere@123");
        driver.findElement(By.className("btn-primary")).click();

        // Wait until redirected to onboarding page
        wait.until(ExpectedConditions.urlToBe("https://dev33.salesmate.io/#/app/onboarding"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[@class='s-m-b-md font-bold']")));
        System.out.println("Onboarding page fully loaded!");


        String expectedUrl = "https://dev33.salesmate.io/#/app/onboarding";
        String actualUrl = driver.getCurrentUrl();

        System.out.println("Actual URL: " + actualUrl);

        Assert.assertEquals(actualUrl, expectedUrl);
    }



    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}