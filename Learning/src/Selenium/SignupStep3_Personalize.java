package Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.Keys;


@Listeners(learingPackage.TestListener.class)
public class SignupStep3_Personalize extends SignupBasePage {

    private WebDriverWait wait;

    private final String STEP1_URL =
        "https://accounts-dev.salesmate.io/registration-step1.html";
    private final String TEST_EMAIL =
        "personalize.test" + System.currentTimeMillis() + "@rapidops.com";

    // Locators
    private final By pageTitle      	= By.xpath("//h1[contains(@class,'title-for-test')]");
    private final By nextButton    		= By.id("step3_complete");
    private final By backButton     	= By.xpath("//button[@name='step3_back' or @id='step3_back']");
    private final By Industrydropdwon 	= By.xpath("//select[@name='industry']/following-sibling::" +
            										"span[@class='select2 select2-container " +
            										 "select2-container--default']");
    private final By SearchboxIndustry  =By.xpath("//input[@class='select2-search__field']");
    private final By companyField   	= By.id("company_name");
    private final By domainField    	= By.id("linkname");
    
    @BeforeClass
    public void reachStep3() throws InterruptedException {
        driver.get(STEP1_URL);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Step 1: Enter email
        wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.id("popupEmailInput")));
        driver.findElement(By.id("popupEmailInput")).sendKeys(TEST_EMAIL);
        driver.findElement(By.id("btn_get_started")).click();

        // Step 2: Fill personal info
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle));
        fillPersonalInfoAndProceed();

        // Wait for Step 3 (Personalize page)
        wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//h1[contains(text(),'personalize') " +
                     "or contains(text(),'Personalize')]")));

        System.out.println("Reached Step 3: " + driver.getCurrentUrl());
    }

    // -------------------------------------------------------
    // TC-1: Verify "Let's personalize your experience" page
    // -------------------------------------------------------
    @Test(priority = 1)
    public void verifyStep3PageLoaded() {
        WebElement title = driver.findElement(pageTitle);
        String titleText = title.getText().trim();
        System.out.println("Step 3 page title: " + titleText);

        Assert.assertTrue(
            titleText.toLowerCase().contains("personalize"),
            "Should be on personalize page, found: " + titleText);

        System.out.println("TC-1 PASSED: Step 3 page loaded correctly");
    }

    // -------------------------------------------------------
    // TC-2: Next button disabled when fields are empty
    // -------------------------------------------------------
    @Test(priority = 2,enabled = false)
    public void verifyNextButtonDisabledInitially() {

        // Directly find the parent span that wraps the Next button
        // This is more reliable than navigating up from button
        WebElement parentSpan = driver.findElement(
            By.xpath("//span[.//button[@id='step3_complete']]"));

        // Get its class
        String parentSpanClass = parentSpan.getAttribute("class");
        System.out.println("Parent span class: " + parentSpanClass);

        // Verify it contains disabled
        Assert.assertTrue(
            parentSpanClass.contains("disabled"),
            "Next button parent span should have 'disabled' class " +
            "but found: " + parentSpanClass);

        System.out.println("TC-2 PASSED: Next disabled when fields are empty");
    }
        
    

    // -------------------------------------------------------
    // TC-3: Industry dropdown is searchable
    // -------------------------------------------------------
    @Test(priority = 3,enabled = false)
    public void verifyIndustryDropdownIsSearchable() {
        try {
            // Open industry dropdown
            WebElement industryDropdown = driver.findElement(Industrydropdwon);
            industryDropdown.click();

            // Search in dropdown
            WebElement searchBox = wait.until(
                ExpectedConditions.visibilityOfElementLocated(SearchboxIndustry));
            searchBox.sendKeys("AutoMobile");

            // Verify results appear
            WebElement result = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//li[contains(@class,'select2-results__option')]")));
            Assert.assertTrue(result.isDisplayed(),
                "Search results should appear in industry dropdown");

            // Select Automobile
            driver.findElement(
                By.xpath("//li[contains(text(),'Automobile')]")).click();

            System.out.println(
                "TC-3 PASSED: Industry dropdown is searchable and selectable");
        } catch (Exception e) {
            System.out.println("TC-3 NOTE: " + e.getMessage());
        }
    }

    // -------------------------------------------------------
    // TC-4: Next button still disabled without company name
    // -------------------------------------------------------
    @Test(priority = 4, enabled = false)
    public void verifyNextDisabledWithoutCompanyName() {
        // Industry is selected (from TC-3), but company + domain empty
    	// Directly find the parent span that wraps the Next button
        // This is more reliable than navigating up from button
        WebElement parentSpan = driver.findElement(
            By.xpath("//span[.//button[@id='step3_complete']]"));

        // Get its class
        String parentSpanClass = parentSpan.getAttribute("class");
        System.out.println("Parent span class: " + parentSpanClass);

        // Verify it contains disabled
        Assert.assertTrue(
            parentSpanClass.contains("disabled"),
            "Next button parent span should have 'disabled' class " +
            "but found: " + parentSpanClass);

        System.out.println(
            "TC-4 PASSED: Next disabled without company name");
    }
    
    // -------------------------------------------------------
    // TC-5: Enter the invalid email and error message displayed
    // -------------------------------------------------------
    
    @Test(priority = 5,enabled = false)
    
    public void verifyInvalidDomainShowsError() {
    	WebElement domain = wait.until(
                ExpectedConditions.visibilityOfElementLocated(domainField));
        domain.clear();
        domain.sendKeys("test-test");
        
        WebElement err_msg =wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'text-danger')]")));
        String Actual_err = err_msg.getText();
        System.out.println("here actual error displayed "+Actual_err);
        Assert.assertEquals(Actual_err, "Special characters are not allowed in Domain name");
        
        System.out.println( "TC-5 PASSED: Enter the invalid email and error message displayed");             	
    }
    
   @Test(priority = 6,enabled = false)
    
    public void verifyDomainLengthValidation() {
	   
	   WebElement company = wait.until(
	            ExpectedConditions.visibilityOfElementLocated(companyField));
	        company.clear();
	        company.sendKeys("RapidOps Inc");
	        
    	WebElement domain = wait.until(
                ExpectedConditions.visibilityOfElementLocated(domainField));
    	domain.sendKeys(Keys.CONTROL + "a", Keys.DELETE);
        domain.sendKeys("te");
        
        WebElement errorMsg =wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='error-message text-danger']")));
        String actualError   = errorMsg.getText().trim();
        String expectedError = "Domain name should be greater than 2 characters";

        System.out.println("Expected error: " + expectedError);
        System.out.println("Actual error  : " + actualError);

        // Verify error message is displayed
        Assert.assertTrue(errorMsg.isDisplayed(),
            "Error message should be displayed for domain less than 2 characters");

        // Verify exact error message text
        Assert.assertEquals(actualError, expectedError,
            "Error message text should match");

        System.out.println("TC-6 PASSED: Domain length validation works correctly");
            
    	
    }
   
   @Test(priority = 7,enabled = false)
   
   public void verifyAlreadyRegisteredDomainShowsError() {
	        
   	WebElement domain = wait.until(
               ExpectedConditions.visibilityOfElementLocated(domainField));
   	domain.sendKeys(Keys.CONTROL + "a", Keys.DELETE);
    domain.sendKeys("rapidopstest");
       
       WebElement errorMsg =wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='error-message text-danger domain-error-for-test']")));
       String actualError   = errorMsg.getText().trim();
       String expectedError = "Domain is already taken. Please enter different value";

       System.out.println("Expected error: " + expectedError);
       System.out.println("Actual error  : " + actualError);

       // Verify error message is displayed
       Assert.assertTrue(errorMsg.isDisplayed(),
           "Error message should be displayed for alredy resgistred email");

       // Verify exact error message text
       Assert.assertEquals(actualError, expectedError,
           "Error message text should match");

       System.out.println("TC-7 PASSED: Domain registred work correctly");
           
   	
   }


    // -------------------------------------------------------
    // TC-8: Fill all fields and Next button becomes enabled
    // -------------------------------------------------------
    @Test(priority = 8,enabled = false)
    public void verifyNextEnabledWithAllFields() {
        // Fill company name
        WebElement company = wait.until(
            ExpectedConditions.visibilityOfElementLocated(companyField));
        company.sendKeys(Keys.CONTROL + "a", Keys.DELETE);
        company.sendKeys("RapidOps Inc");

        // Fill domain
        WebElement domain = wait.until(
            ExpectedConditions.visibilityOfElementLocated(domainField));
        domain.sendKeys(Keys.CONTROL + "a", Keys.DELETE);
        String domainname = "rapidops" + System.currentTimeMillis();
        domain.sendKeys(domainname);

        // Next button should be enabled now
        WebElement nextBtn = driver.findElement(nextButton);
        String parentClass = nextBtn.findElement(
            By.xpath("..")).getAttribute("class");

        Assert.assertFalse(
            parentClass.contains("disabled"),
            "Next button should be enabled when all fields are filled");

        System.out.println(
            "TC-8 PASSED: Next button enabled with all valid data");
    }

    // -------------------------------------------------------
    // TC-9: Clicking Next redirects to Verify Email page
    // -------------------------------------------------------
    @Test(priority = 9, dependsOnMethods = {"verifyNextEnabledWithAllFields"},enabled = false)
    public void verifyNextRedirectsToVerifyEmail()throws InterruptedException {
    	Thread.sleep(2000);
        WebElement nextBtn = wait.until(
            ExpectedConditions.elementToBeClickable(nextButton));
        nextBtn.click();

        // Wait for verify email page (identified by heading, not URL)
        WebElement verifyTitle = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h1[contains(text(),'verify') " +
                         "or contains(text(),'Verify')]")));

        Assert.assertTrue(verifyTitle.isDisplayed(),
            "Should redirect to email verification page");

        System.out.println("Current URL: " + driver.getCurrentUrl());
        System.out.println(
            "TC-9 PASSED: Redirected to email verification page");
    }

    // -------------------------------------------------------
    // TC-10: Back button goes to previous page
    // -------------------------------------------------------
    @Test(priority = 10,enabled = false)
    public void verifyBackButton() {
        // If we got to verify page, go back
        try {
            WebElement backOnVerify = driver.findElement(
                By.xpath("//button[@id='step4_back' or @name='step4_back']"));
            if (backOnVerify.isDisplayed()) {
                backOnVerify.click();
            }
        } catch (Exception e) {
            // Already on step 3
        }

        wait.until(ExpectedConditions.visibilityOfElementLocated(backButton));
        WebElement backBtn = driver.findElement(backButton);
        backBtn.click();

        // Should go back to personal info page
        wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//h1[contains(text(),'know') " +
                     "or contains(text(),'Know')]")));

        WebElement prevPageTitle = driver.findElement(
            By.xpath("//h1[contains(@class,'title-for-test')]"));

        Assert.assertTrue(
            prevPageTitle.getText().toLowerCase().contains("know") ||
            prevPageTitle.getText().toLowerCase().contains("personal"),
            "Back should navigate to personal info page");

        System.out.println("TC-10 PASSED: Back button works correctly");
    }

    // -------------------------------------------------------
    // Helper: Fill step 2 and click Next to reach step 3
    // -------------------------------------------------------
    private void fillPersonalInfoAndProceed() throws InterruptedException {
        // First Name
        wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.id("first_name")));
        driver.findElement(By.id("first_name")).sendKeys("Vanita");

        // Last Name
        driver.findElement(By.id("last_name")).sendKeys("Patel");

        // Country dropdown
        selectCountryFromDropdown("India");
        Thread.sleep(500);

        // Mobile
        WebElement phone = driver.findElement(
            By.xpath("//input[contains(@class,'phone-number') " +
                     "or @type='tel']"));
        phone.sendKeys("9876543210");

        // Password
        WebElement pass = driver.findElement(
            By.xpath("//input[@type='password']"));
        pass.sendKeys("Test@1234");

        // Confirm Password
        Thread.sleep(500);
        WebElement confirmPass = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@type='password' and " +
                         "contains(@id,'confirm')]")));
        confirmPass.sendKeys("Test@1234");

        // Click Next
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@name='step2_next' or @id='step2_next']")))
                .click();
    }
    private void selectCountryFromDropdown(String countryName) {
        try {
            // open dropdown
            WebElement dropdownContainer = driver.findElement(
                By.xpath("(//span[@class='select2-selection " +
                         "select2-selection--single'])[1]"));
            dropdownContainer.click();

            // type country name
            WebElement searchBox = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//input[@class='select2-search__field']")));
            searchBox.sendKeys(countryName);

            // wait for all results to load
            List<WebElement> allOptions = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(
                    By.xpath("//li[contains(@class,'select2-results__option')]")));

            // loop and find exact match
            for(WebElement option : allOptions) {
                System.out.println("Option found: " + option.getText());
                if(option.getText().trim().equals(countryName)) {
                    option.click();
                    System.out.println("Successfully selected: " + countryName);
                    break;
                }
            }

        } catch (Exception e) {
            System.out.println("Country selection skipped: " + e.getMessage());
        }
    }
}

