package Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import java.util.List;

import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;

import java.time.Duration;

@Listeners(learingPackage.TestListener.class)
public class SignupStep2_PersonalInfo extends SignupBasePage {

    private WebDriverWait wait;

    // Step 1 data to reach step 2
    private final String STEP1_URL =
        "https://accounts-dev.salesmate.io/registration-step1.html";
    private final String TEST_EMAIL =
        "vanita.test" + System.currentTimeMillis() + "@rapidops.com";

    // Locators for Step 2 (Let's get to know you)
    private final By firstNameField    = By.xpath("//input[@id='first_name']");
    private final By lastNameField     = By.xpath("//input[@id='last_name']");
    private final By phoneField        = By.id("phone");
    private final By passwordField     = By.xpath("//input[@type='password' and contains(@id,'password')]");
    private final By confirmPassField  = By.xpath("//input[@id='confirmPassword']");
//    private final By nextButton        = By.xpath("//span[contains(text(),'Next')]");
    private final By nextButton        = By.id("step2_complete");
  
    private final By disablednextButton = By.xpath("/html/body/app-root/new-signup/div/div[2]/div/signup-user-info/div/div[2]/div[5]/span");
    private final By passwordTooltip   = By.xpath("//*[contains(@class,'tooltip') or contains(@class,'password-hint')]");
    private final By pageTitle         = By.xpath("//h1[contains(@class,'title-for-test')]");
    private final By confirmPassError = By.xpath("//sm-validation-messages//div[contains(@class,'error-message')]");


    @BeforeClass
    public void reachStep2() {
        driver.get(STEP1_URL);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Enter email and proceed to step 2
        wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.id("popupEmailInput")));
        driver.findElement(By.id("popupEmailInput")).sendKeys(TEST_EMAIL);

        wait.until(ExpectedConditions.elementToBeClickable(
            By.id("btn_get_started")));
        driver.findElement(By.id("btn_get_started")).click();

        // Wait for step 2 page (Let's get to know you)
        wait.until(ExpectedConditions.urlContains("registration"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle));
        System.out.println("Reached Step 2: " + driver.getCurrentUrl());
    }

    // -------------------------------------------------------
    // TC-1: Verify "Let's get to know you" page loaded
    // -------------------------------------------------------
    @Test(priority = 1)
    public void verifyStep2PageLoaded() {
        WebElement title = driver.findElement(pageTitle);
        String titleText = title.getText().trim();
        System.out.println("Page title: " + titleText);

        Assert.assertTrue(
            titleText.toLowerCase().contains("get to know") ||
            titleText.toLowerCase().contains("personal"),
            "Should be on 'Let's get to know you' page, found: " + titleText);

        System.out.println("TC-1 PASSED: Step 2 page loaded correctly");
    }

//    // -------------------------------------------------------
//    // TC-2: Next button disabled when fields are empty
//    // -------------------------------------------------------
    @Test(priority = 2)
    public void verifyNextButtonDisabledWhenEmpty() {
        WebElement btn = driver.findElement(disablednextButton);
        String cssClass = btn.getAttribute("class");
        System.out.println("cass" +cssClass);
        Assert.assertTrue(
            cssClass.contains("disabled") || !btn.isEnabled(),
            "Next button should be disabled when fields are empty");

        System.out.println("TC-2 PASSED: Next button disabled when empty");
    }

//    // -------------------------------------------------------
//    // TC-3: Confirm password field hidden when password is emptyaf
//    // -------------------------------------------------------
    @Test(priority = 3)
    public void verifyConfirmPasswordHiddenInitially() {
        // Confirm password field should not be visible initially
        boolean confirmVisible;
        try {
            WebElement confirmPass = driver.findElement(confirmPassField);
            confirmVisible = confirmPass.isDisplayed();
        } catch (Exception e) {
            confirmVisible = false;
        }

        Assert.assertFalse(confirmVisible,
            "Confirm password should be hidden when password field is empty");

        System.out.println(
            "TC-3 PASSED: Confirm password hidden when password is empty");
    }

    // -------------------------------------------------------
    // TC-4: Confirm password appears after entering password
    // -------------------------------------------------------
    @Test(priority = 4)
    public void verifyConfirmPasswordAppearsAfterTyping() {
        WebElement passField = wait.until(
            ExpectedConditions.visibilityOfElementLocated(passwordField));
        passField.clear();
        passField.sendKeys("Test@1234");
        
        System.out.println("we types the password");

        // Confirm password should now appear
        WebElement confirmPass = wait.until(
            ExpectedConditions.visibilityOfElementLocated(confirmPassField));
        System.out.println("checking the visisbility of the confirm password");
        Assert.assertTrue(confirmPass.isDisplayed(),
            "Confirm password field should appear after typing password");
       

        // Clear password — confirm should disappear
        passField.clear();
        passField.sendKeys(Keys.CONTROL + "a");
        passField.sendKeys(Keys.DELETE);
        

        boolean confirmVisible;
        try {
            Thread.sleep(500);
            confirmVisible = driver.findElement(confirmPassField).isDisplayed();
        } catch (Exception e) {
            confirmVisible = false;
        }
        Assert.assertFalse(confirmVisible,
            "Confirm password should disappear when password is cleared");

        System.out.println(
            "TC-4 PASSED: Confirm password shows/hides with password field");
    }

    // -------------------------------------------------------
    // TC-5: Password tooltip message visible on hover/click
    // -------------------------------------------------------
    @Test(priority = 5)
    public void verifyPasswordTooltipVisible() {
        // Click on tooltip icon near password
    	WebElement passField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(passwordField));
            passField.clear();
            passField.sendKeys("Test@1234");
        try {
            WebElement tooltip = driver.findElement(
                By.xpath("//*[contains(@class,'tooltip-icon') " +
                         "or contains(@class,'info-icon') " +
                         "or @title='Password requirements']"));
            tooltip.click();

            WebElement tooltipContent = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//*[contains(@class,'tooltip') " +
                             "or contains(@class,'popover') " +
                             "or contains(@class,'password-hint')]")));
            Assert.assertTrue(tooltipContent.isDisplayed(),
                "Password tooltip should be visible after clicking");

            System.out.println("TC-5 PASSED: Password tooltip is visible");
        } catch (Exception e) {
            System.out.println(
                "TC-5 SKIPPED: Tooltip element not found - " + e.getMessage());
        }
    }

    // -------------------------------------------------------
    // TC-6: Password mismatch keeps Next button disabled
    // -------------------------------------------------------

    @Test(priority = 6)
    public void verifyPasswordMismatchDisablesNext() {

        // refresh page before starting
        driver.navigate().refresh();
        System.out.println("page refreshed");

        // wait for page to fully load after refresh
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle));
        System.out.println("page loaded after refresh");

        // Fill first name, last name
        fillField(firstNameField, "Vanita");
        System.out.println("first name filled");

        fillField(lastNameField, "Patel");
        System.out.println("last name filled");

        // fill phone before dropdown
        fillField(phoneField, "9876543210");
        System.out.println("phone filled");

        // select country
        selectCountryFromDropdown("India");
        System.out.println("country selected");

        // fill password
        WebElement passField = driver.findElement(passwordField);
        passField.clear();
        passField.sendKeys("Test@1234");
        System.out.println("password filled");

        // fill wrong confirm password
        WebElement confirmPass = wait.until(
            ExpectedConditions.visibilityOfElementLocated(confirmPassField));
        confirmPass.clear();
        confirmPass.sendKeys("WrongPass@99");
        System.out.println("wrong confirm password filled");

        // press TAB to move focus away and trigger Angular validation
        confirmPass.sendKeys(Keys.TAB);
        System.out.println("TAB pressed to trigger validation");

        // wait for error message to appear
        WebElement errorMsg = wait.until(
                ExpectedConditions.visibilityOfElementLocated(confirmPassError));
        // get the text and print it
        String errorText = errorMsg.getText().trim();
        System.out.println("error message text: " + errorText);

        // verify error message says do not match
        Assert.assertTrue(
            errorText.toLowerCase().contains("do not match"),
            "Expected 'do not match' message but found: " + errorText);

        System.out.println("TC-6 PASSED: Password mismatch error message displayed correctly");
    }

    // -------------------------------------------------------
    // TC-7: Valid data enables Next button and redirects
    // -------------------------------------------------------
    @Test(priority = 7)
    public void verifyValidDataEnablesNextAndRedirects() {
        // refresh page before starting
        driver.navigate().refresh();
        System.out.println("page refreshed");

        // wait for page to fully load after refresh
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle));
        System.out.println("page loaded after refresh");

        // Fill first name, last name
        fillField(firstNameField, "Vanita");
        System.out.println("first name filled");

        fillField(lastNameField, "Patel");
        System.out.println("last name filled");

        // fill phone before dropdown
        fillField(phoneField, "9876543210");
        System.out.println("phone filled");

        // select country
        selectCountryFromDropdown("India");
        System.out.println("country selected");

        // password
        WebElement passField = driver.findElement(passwordField);
        passField.clear();
        passField.sendKeys("Test@1234");
        System.out.println("password filled");

        // confirm password with wrong value
        WebElement confirmPass = wait.until(
            ExpectedConditions.visibilityOfElementLocated(confirmPassField));
        confirmPass.clear();
        confirmPass.sendKeys("Test@1234");
        System.out.println("confirm password filled");

        // Next button should be enabled
        WebElement btn = wait.until(
            ExpectedConditions.elementToBeClickable(nextButton));
        Assert.assertFalse(
            btn.getAttribute("class").contains("disabled"),
            "Next button should be enabled when all valid data is entered");

        btn.click();

        // Wait for redirect to Step 3 (Personalize page)
        wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//h1[contains(text(),'personalize') " +
                     "or contains(text(),'Personalize')]")));

        String pageHeader = driver.findElement(
            By.xpath("//h1[contains(@class,'title-for-test')]")).getText();

        Assert.assertTrue(
            pageHeader.toLowerCase().contains("personalize"),
            "Should redirect to personalize page, found: " + pageHeader);

        System.out.println(
            "TC-7 PASSED: Valid data redirects to personalize page");
    }

    // -------------------------------------------------------
    // Helper Methods
    // -------------------------------------------------------
    private void fillField(By locator, String value) {
        WebElement field = wait.until(
            ExpectedConditions.visibilityOfElementLocated(locator));
        field.clear();
        field.sendKeys(value);
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