package contact;


import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Login.Login;
public class createcontact extends Login {
	
//	Locators
	
	private final By Contactmodule  	= By.xpath("//a[@href='#/app/contacts/list']");
	private final By Newbutton	    	= By.xpath("//button[normalize-space(text())='New']");
	private final By Firstname 			= By.id("firstName");
	private final By Lastname 			= By.id("lastName");
	private final By Email 				= By.id("email");
	private final By Phone 				= By.id("phone");
	private final By Typedropdwon 		= By.xpath("(//span[contains(@class,'select2-selection--single')]//span[@class='select2-selection__placeholder'][text()='Select'])[1]");
	private final By Typeoptions		= By.xpath("//ul[@class ='select2-results__options']/li");
	private final By Submitbutton 		= By.id("btnSubmit");
	private final By Suucessmessage		= By.xpath("//span[text()='Created successfully.']");
	private final By Typesearchbox 	    = By.xpath("//input[@class='select2-search__field']");
	private final By Timezonedropdwon 	= By.xpath("//span[contains(@id,'select2-timezone')]");
	private final By Timezonesearchbox	= By.xpath("//input[@role='textbox']");
	private final By Timezoneoptions	= By.xpath("//ul[@class='select2-results__options']/li");
	private final By Firstnamerequired 	= By.xpath("(//div[contains(text(),'First Name or Last Name is required')])[1]");
	private final By Lastnamerequired	= By.xpath("(//div[contains(text(),'First Name or Last Name is required')])[2]");
	private final By Emailrequired		= By.xpath("//div[contains(text(),' Email is required ')]");
	private final By Emailvalidation 	= By.xpath("//div[contains (text(),'Email must be a valid email')]");
	private final By Cancelbutton 		= By.xpath("//button[@class ='btn btn-default']");
	private final By crossicon			= By.xpath("(//a[@class ='icon btn-icon chrome-back-btn'])[2]");
	private final By Ownerdropdwon 		= By.xpath("//span[contains(@aria-labelledby,'select2-owner')]");
	private final By ownersearchbox 	= By.xpath("//input[@class='select2-search__field']");
	private final By Owneroptions		= By.xpath("//ul[contains(@class,'select2-results__options')]//span[@title]");
	private final By Emailoptout		= By.xpath("//span[contains(text(),'Email Opt Out')]/parent::label");
	
//	(//label[@class='i-checks'])[1]
	
//	For dynamic name and email
	Random rand = new Random();
	int num = rand.nextInt(1000);

    
	public void contactNavigation() {
		driver.findElement(Contactmodule).click();
		System.out.println("Clicked contact module");

		    // Wait until New button is visible and clickable
		wait.until(ExpectedConditions.elementToBeClickable(Newbutton));
		System.out.println("Contact module loaded successfully - New button is visible");

	}
	
	public void validationcheck() throws InterruptedException{
		
		// Initialize wait
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

	    // Wait until New button is visible and clickable
	    wait.until(ExpectedConditions.elementToBeClickable(Newbutton));
	    System.out.println("Contact module loaded successfully - New button is visible");

	    // Click on New button
	    driver.findElement(Newbutton).click();
	    System.out.println("clicked on the new button");
	    
	    Thread.sleep(2000);
	    
//	    Click on the save button without any data
	    driver.findElement(Submitbutton).click();
	    
//	    check for the validation apper in the firstname, last name and email.
	    
	    //firname validation check
	    String Actual_firstname_message= driver.findElement(Firstnamerequired).getText();
	    System.out.println("validation for firstname "+ Actual_firstname_message);
	    Assert.assertTrue(Actual_firstname_message.contains("required"), "First Name validation message not displayed!");
	    
        //last name validation check	    
	    String Actual_lasttname_message= driver.findElement(Lastnamerequired).getText();
	    System.out.println("validation for lastname "+ Actual_lasttname_message);
	    Assert.assertTrue(Actual_lasttname_message.contains("required"), "Last name validation message is not displayed");
	    
	    //Email required
	    String Actual_email_message = driver.findElement(Emailrequired).getText();
	    System.out.println("validation for email "+ Actual_email_message);
	    Assert.assertTrue(Actual_email_message.contains("required"), "Email validation is not displayed");
	    
	    System.out.println("All validation are passed");
	}
	
	public void Invalidemail() {
		
		driver.findElement(Email).sendKeys("vanita.patel");
		String Actual_Emailvalidationmessage = driver.findElement(Emailvalidation).getText();
		
		System.out.println("Valid email message "+Actual_Emailvalidationmessage);
		
		Assert.assertTrue(Actual_Emailvalidationmessage.contains("Email must be a valid email"), "validation for invalid email is failed");
			
		
	}
	
	public void ContactCreation() throws Exception {

	    // Initialize wait
//	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
//
//	    // Wait until New button is visible and clickable
//	    wait.until(ExpectedConditions.elementToBeClickable(Newbutton));
//	    System.out.println("Contact module loaded successfully - New button is visible");
//
	    // Click on New button
	    driver.findElement(Newbutton).click();
	    System.out.println("clicked on the new button");

	    // Wait for sidebar and Firstname field visibility
	    wait.until(ExpectedConditions.visibilityOfElementLocated(Firstname));
	    System.out.println("Sidebar opened successfully");

	    // Fill the form
	    driver.findElement(Firstname).sendKeys("test"+num);
	    driver.findElement(Lastname).sendKeys("User");
	    driver.findElement(Phone).sendKeys("+919664530127");
	    driver.findElement(Email).sendKeys("test"+num+"@gmail.com");
	    System.out.println("value enter till email");
	    
//	    click on the ownerdropdown
	    
	    driver.findElement(Ownerdropdwon).click();
	    System.out.println("Owner dropdown is clicked");

	    WebElement ownerValue = wait.until(ExpectedConditions.visibilityOfElementLocated(
	        By.xpath("//ul[contains(@id,'select2-owner')]//span[@title='Saloni Nayak']")
	    ));

	    System.out.println("Found the owner value");

	    String ownerText = ownerValue.getText();  // ✅ get text BEFORE click
	    ownerValue.click();
	    System.out.println("Option is clicked: " + ownerText);
	    
	    
	    
//	    click on the type dropdwon
	    driver.findElement(Typedropdwon).click();
	    driver.findElement(Typesearchbox).sendKeys("Prospect"); //search the option in the dropdwon
	    
	    wait.until(ExpectedConditions.visibilityOfElementLocated(Typeoptions)); //wait for the option to visible after search
	    List<WebElement> Options =driver.findElements(Typeoptions); //getting all options after search
//	    Thread.sleep(2000);
	    
	    
	    boolean optionfound =false;
	    
	    for(WebElement opt:Options) {
	    	String optText1 = opt.getText().trim();
	    	System.out.println("option displayed "+ optText1);
	    	if(optText1.equals("Prospect")){
	    		optionfound= true;
	    		opt.click();
	    		System.out.println("option is selected");
	    		break;
	    	}
	    }
	    
	    if(!optionfound) {
	    	System.out.println("option is not founds");
	    }
	    
	 // Step 1: Click dropdown and wait for search box to appear
	    driver.findElement(Timezonedropdwon).click();
	    System.out.println("Timezone dropdown clicked");

	    // Step 2: Wait for search box, then type
	    wait.until(ExpectedConditions.visibilityOfElementLocated(Timezonesearchbox));
	    driver.findElement(Timezonesearchbox).sendKeys("Asia/Kolkata"); // ✅ Capital K
	    System.out.println("Searched timezone");

	    // Step 3: Wait for options to load
	    wait.until(ExpectedConditions.visibilityOfElementLocated(Timezoneoptions));
	    wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(Timezoneoptions, 0));

	    // Step 4: Loop and match correctly
	    List<WebElement> Toptions = driver.findElements(Timezoneoptions);
	    boolean found = false;

	    for (WebElement opt : Toptions) {
	        String optText = opt.getText().trim();
	        System.out.println("Option found: " + optText); // debug log

	        if (optText.equals("Asia/Kolkata")) { // ✅ Capital K
	            found = true;
	            opt.click();
	            System.out.println("Timezone selected: " + optText);
	            break;
	        }
	    }

	    if (!found) {
	        System.out.println("❌ Timezone 'Asia/Kolkata' not found in dropdown options!");
	    }
	    
	    
//	    Email checkbox selection
	    
	  WebElement checkbox = driver.findElement(By.xpath("//input[@name='emailOptOut']"));

	 // Scroll into view first
	 ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkbox);
	 Thread.sleep(500);

	 // Trigger Angular click using JS event dispatch
	 ((JavascriptExecutor) driver).executeScript(
	     "arguments[0].click(); " +
	     "arguments[0].dispatchEvent(new Event('change', { bubbles: true })); " +
	     "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));",
	     checkbox
	 );
	 Thread.sleep(500);
	 System.out.println("Email checkbox is clicked");
	    
	    
//	     Submit form
//	    driver.findElement(Submitbutton).click();
//	    Thread.sleep(3000);
//
//	    // Wait for success message
//	    wait.until(ExpectedConditions.visibilityOfElementLocated(Suucessmessage));
//
//	    // Get actual message
//	    String Actual = driver.findElement(Suucessmessage).getText();
//	    System.out.println("Actual Success message " + Actual);
//
//	    // Assertion
//	    Assert.assertTrue(Actual.contains("Created successfully"));
//
//	    System.out.println("Contact is created successfully");
	}
	
	public void verifyAndSelectDropdown() {

	    // Wait until New button is visible and clickable
	    wait.until(ExpectedConditions.elementToBeClickable(Newbutton));
	    System.out.println("Contact module loaded successfully - New button is visible");

	    // Click on New button
	    driver.findElement(Newbutton).click();
	    System.out.println("clicked on the new button");

	    // Wait for sidebar
	    wait.until(ExpectedConditions.visibilityOfElementLocated(Typedropdwon));
	    System.out.println("Sidebar opened successfully");

	    // Click on the type dropdown
	    driver.findElement(Typedropdwon).click();
	    System.out.println("Dropdwon clicked successfully");

	    // Wait for all options visibility
	    wait.until(ExpectedConditions.visibilityOfElementLocated(Typeoptions));

	    List<WebElement> Options = driver.findElements(Typeoptions);

	    // Creating ArrayList to store all options
	    ArrayList<String> Actual = new ArrayList<String>();

	    for (WebElement Alloptions : Options) {
	        Actual.add(Alloptions.getText());
	    }

	    System.out.println("getting all options of Actual " + Actual);

	    ArrayList<String> Expected = new ArrayList<String>(Arrays.asList(
	            "Select", "Marketing Qualified Lead", "Prospect", "Lead", "Customer", "Partner", "Tester"
	    ));

	    System.out.println("getting all options of Expected " + Expected);

	    // Checking all options match or not
	    Assert.assertEquals(Actual, Expected);

	    // Checking size matches or not
	    System.out.println("Actual size is " + Actual.size() + " Expected Size is " + Expected.size());
	    Assert.assertEquals(Actual.size(), Expected.size());
	}
	
	public void Closesidebar() throws InterruptedException {
		
	    // Wait until New button is visible and clickable
//	    wait.until(ExpectedConditions.elementToBeClickable(Newbutton));
//	    System.out.println("Contact module loaded successfully - New button is visible");

	    // Click on New button
	    driver.findElement(Newbutton).click();
	    System.out.println("clicked on the new button");
	    
	    Thread.sleep(2000);
		
//		wait untill cancel button is displayed
		wait.until(ExpectedConditions.visibilityOfElementLocated(Cancelbutton));
		
		System.out.println("running the close sidebar");
	
		//click on the cancel button
		driver.findElement(Cancelbutton).click();
		System.out.println("clicked on the cancel button and sidebar is closed");
		
		// Wait until the Cancel button (sidebar) is invisible
		boolean isSidebarClosed = wait.until(
		    ExpectedConditions.invisibilityOfElementLocated(Cancelbutton)
		);

		// Assert using the boolean returned by the wait condition
		Assert.assertTrue(isSidebarClosed, "Sidebar didn't close");
		System.out.println("Sidebar is closed successfully");
		
	}
	
	public void ownerdropdown() throws InterruptedException {
		
		wait.until(ExpectedConditions.elementToBeClickable(Newbutton));
		// Click on New button
	    driver.findElement(Newbutton).click();
	    System.out.println("clicked on the new button for owner dropdwon");
	    
		wait.until(ExpectedConditions.elementToBeClickable(Ownerdropdwon));
	    driver.findElement(Ownerdropdwon).click();
	    System.out.println("Clicked owner dropdwon");
	    
//	    Searching the owner
	    
	    driver.findElement(ownersearchbox).sendKeys("Saloni Nayak");
	    	    
//	    getting the search result
	    
	    List<WebElement> owneroptions = driver.findElements(Owneroptions); 
	    ArrayList<String> capowner = new ArrayList<String>();

	    
//	    getting the correct option using loop and condition
	    
	    boolean Result =false;
	    
	    for(WebElement opt:owneroptions) {
	    	capowner.add(opt.getText());
	    	if(opt.getText().trim().contains("Saloni Nayak")) {
	    		Result =true;
	    		opt.click();
	    		System.out.println("owner is found and clicked");
	    		break;
	    	}
	    	
	    	if(!Result) {
	    		System.out.println("searched owner not found");
	    	}
	    }
	    System.out.println("found owner list"+capowner);
	         
	   

	    
	}
		

}
