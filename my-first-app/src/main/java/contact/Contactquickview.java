package contact;

import java.awt.RenderingHints.Key;
import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Login.Login;

public class Contactquickview extends Login {

    private final By Contactmodule  = By.xpath("//a[.//span[text()='Contacts']]");
    private final By Contacteyeicon = By.xpath("(//button[@title='Preview'])[1]");
    private final By Firstname      = By.id("firstName");
    private final By ContactName	= By.xpath("(//a[@title='Click to view details'])[1]");
    private final By Cancel			= By.xpath("//button[@class='btn btn-sm btn-default pull-left']");	
    private final By Jobtitle		= By.xpath("//input[@id=\"designation\"]");
    private final By Ownerdropdwon	= By.xpath("//span[contains(@aria-labelledby,'select2-owner')]");	
    private final By Ownervalue		= By.xpath("//ul[contains(@id,'select2-owner')]//span[@title='Saloni Nayak']");
    private final By Phone			= By.id("phone");	
    private final By Email			= By.id("email");	
	private final By Typedropdwon 	= By.xpath("//span[contains(@id,'select2-type_')]");
	private final By Typevalue		= By.xpath("//ul[@class='select2-results__options']//span[@title='Customer']");
	private final By Datefield		= By.xpath("//input[@id='dateCustomField1']");
	private final By Emailcheckbox 	= By.xpath("//input[@id='emailOptOut']");
    private final By Update			= By.xpath("//button[@id=\"btnSubmit\"]");
    private final By Updatemsg		= By.xpath("//span[@class='noty_text']");
    
    
//    for daynamic name
    Random rand = new Random();
	int num = rand.nextInt(1000);
	
    String updatedFirstName ="vanita" + num;
    String updatedEmail		="vanitapatel"+num+"@gmail.com";
    String updatePhone		= "+91966453"+num;

    protected WebDriverWait wait;

    public void Contactlisting() throws InterruptedException {
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.findElement(Contactmodule).click();
		System.out.println("Clicked contact module");

		    // Wait until New button is visible and clickable
		wait.until(ExpectedConditions.elementToBeClickable(ContactName));
		System.out.println("Contact module loaded successfully - firstname is visible");

    	}
    	

    public void quickview() throws InterruptedException {

        WebElement contactName = driver.findElement(ContactName);
        Thread.sleep(500);
        System.out.println("Contact found: " + contactName.getText());

        // Hover on contact name
        Actions actions = new Actions(driver);
        actions.moveToElement(contactName).perform();
        Thread.sleep(500);
        System.out.println("Hovered on contact name");

        // Wait for eye icon
        wait.until(ExpectedConditions.visibilityOfElementLocated(Contacteyeicon));
        System.out.println("Eye icon visible");

        // Click eye icon
        driver.findElement(Contacteyeicon).click();;
        System.out.println("Clicked eye icon");

        // Verify quick view
        wait.until(ExpectedConditions.visibilityOfElementLocated(Firstname));
        Thread.sleep(2000);
        System.out.println("Quick view opened successfully");
        
//        driver.findElement(Cancel).click();
//        System.out.println("quickview is closed successfully");
        
        
    }
    
    public void UpdateContact() throws InterruptedException {

        // ✅ Fix 1 - generate random number fresh each time
//        Random rand = new Random();
//        int num = rand.nextInt(1000);

        // ✅ Fix 2 - wait for firstname field before interacting
        wait.until(ExpectedConditions.visibilityOfElementLocated(Firstname));

        // Clear and type new firstname
        WebElement firstname = driver.findElement(Firstname);
        firstname.sendKeys(Keys.CONTROL + "a");  // Select all text
        firstname.sendKeys(Keys.DELETE);          // Delete selected text
        firstname.clear();
        firstname.sendKeys(updatedFirstName);
        System.out.println("Entered name: vanita" + num);

        Thread.sleep(500);
        
//      clear and update the Job Title
        WebElement jobtitle = driver.findElement(Jobtitle);
        jobtitle.sendKeys(Keys.CONTROL + "a");  // Select all text
        jobtitle.sendKeys(Keys.DELETE);          // Delete selected text
        jobtitle.clear();
        jobtitle.sendKeys("QA");
        System.out.println("updated jobtitle");    
        
//      update the Contact Owner
        
        driver.findElement(Ownerdropdwon).click();
	    System.out.println("Owner dropdown is clicked");

	    WebElement ownerValue = wait.until(ExpectedConditions.visibilityOfElementLocated(Ownervalue ));

	    System.out.println("Found the owner value");

	    String ownerText = ownerValue.getText();  // ✅ get text BEFORE click
	    ownerValue.click();
	    System.out.println("Option is clicked: " + ownerText);
	    
//		update the Phone
	    
	    WebElement phone =driver.findElement(Phone);
	    phone.sendKeys(Keys.CONTROL + "a");
	    phone.sendKeys(Keys.DELETE);
	    phone.clear();
	    phone.sendKeys(updatePhone);
	    System.out.println("phone updated");
	    
	    
//		Update Email
	    
	    WebElement email =driver.findElement(Email);
	    email.sendKeys(Keys.CONTROL + "a");
	    email.sendKeys(Keys.DELETE);
	    email.clear();
	    email.sendKeys(updatedEmail);
	    System.out.println("Email updated");
	    Thread.sleep(2000);
	    
//      Update the type
	    
	 // Step 1 - Click dropdown
	    wait.until(ExpectedConditions.elementToBeClickable(Typedropdwon));
	    driver.findElement(Typedropdwon).click();
	    System.out.println("Type dropdown clicked");
	    Thread.sleep(500); // ✅ Wait for options to appear

	    // Step 2 - Wait for option to be visible
	    WebElement typeValue = wait.until(
	        ExpectedConditions.visibilityOfElementLocated(Typevalue));
	    System.out.println("Type option found"); // ✅ Fixed print message

	    // Step 3 - Get text before clicking
	    String typeText = typeValue.getText();

	    // Step 4 - Click the option
	    typeValue.click();
	    System.out.println("Option selected: " + typeText); // ✅ Fixed print message
	    
	    
//      Update the datefield
	    
	    driver.findElement(Datefield).sendKeys("Mar 25, 2026");
	    System.out.println("date field set");
	    Thread.sleep(1000);
	    
	    
//      Email optout checkbox
	    WebElement checkbox = driver.findElement(Emailcheckbox);
	    
	    boolean isChecked = checkbox.isSelected();
	    System.out.println("is checkbox checked?: " + isChecked);
	    
	    if(!isChecked) {


		 // Scroll into view first
		 ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkbox);
		 Thread.sleep(1000);

		 // Trigger Angular click using JS event dispatch
		 ((JavascriptExecutor) driver).executeScript(
		     "arguments[0].click(); " +
		     "arguments[0].dispatchEvent(new Event('change', { bubbles: true })); " +
		     "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));",
		     checkbox
		 );
		 Thread.sleep(500);
		 System.out.println("Email checkbox is clicked");
	    }
	    
	    else {
	        System.out.println("Email checkbox is already checked - skipping click");

	    }


        
        

        // ✅ Fix 3 - Save the contact after update
        wait.until(ExpectedConditions.elementToBeClickable(Update));
        driver.findElement(Update).click();
        System.out.println("Clicked Save button");
        
//        check the updatebutton
        wait.until(ExpectedConditions.visibilityOfElementLocated(Updatemsg));
        WebElement Message =driver.findElement(Updatemsg);        
        String actualMessage = Message.getText().trim().toLowerCase();
        System.out.println("Actual message: " + actualMessage);
        Assert.assertTrue(
            actualMessage.contains("updated successfully"),
            "Expected 'updated successfully' but got: " + actualMessage
        );
        Thread.sleep(2000);
        // ✅ Fix 4 - corrected typo
        System.out.println("Contact updated successfully");
    }
    
//    Checking the Timeline entry
    
    public void verifyTimelineUpdates() throws InterruptedException {

        System.out.println("=== Verifying Timeline with Actual Values ===");
        Thread.sleep(2000);
        
//        Navigate to the timeline tab
        driver.findElement(By.xpath("//a[normalize-space(text())='Timeline']")).click();

        // ✅ XPath - finds NEW value after arrow in First Name Updated row
        // Structure: oldValue → [arrow] → newValue
        By FirstNameNewValue = By.xpath(
            "//span[text()='First Name Updated']" +
            "/ancestor::div[contains(@class,'timeline-content')]" +
            "//span[contains(@class,'icon-arrow-2-right')]" +
            "/following-sibling::span[1]"
        );

        // Wait for timeline entry
        WebElement firstNameValue = wait.until(
            ExpectedConditions.visibilityOfElementLocated(FirstNameNewValue));

        // Get actual text from timeline
        String timelineFirstName = firstNameValue.getText().trim();
        System.out.println("Timeline shows new First Name: " + timelineFirstName);
        System.out.println("Expected First Name:           " + updatedFirstName);

        // ✅ Assert actual updated value matches timeline value
        Assert.assertEquals(
            timelineFirstName,
            updatedFirstName,
            "❌ First Name mismatch! Timeline: " + timelineFirstName +
            " | Expected: " + updatedFirstName
        );
        System.out.println("✅ First Name verified: " + updatedFirstName);
        
     // ✅ 2 - Email
        String timelineEmail = driver.findElement(By.xpath(
            "//span[text()='Email Updated']" +
            "/ancestor::div[contains(@class,'timeline-content')]" +
            "//span[contains(@class,'icon-arrow-2-right')]" +
            "/following-sibling::span[1]"
        )).getText().trim();

        Assert.assertEquals(timelineEmail, updatedEmail,
            "❌ Email mismatch!");
        System.out.println("✅ Email: " + timelineEmail);

        // ✅ 3 - Phone
        String timelinePhone = driver.findElement(By.xpath(
            "//span[text()='Phone Updated']" +
            "/ancestor::div[contains(@class,'timeline-content')]" +
            "//span[contains(@class,'icon-arrow-2-right')]" +
            "/following-sibling::span[1]"
        )).getText().trim();

        Assert.assertEquals(timelinePhone, updatePhone,
            "❌ Phone mismatch!");
        System.out.println("✅ Phone: " + timelinePhone);
        
    }
}
