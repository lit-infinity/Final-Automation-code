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
import org.openqa.selenium.JavascriptExecutor;


@Listeners(learingPackage.TestListener.class)
public class SignupStep4_VerifyEmail extends SignupBasePage {

    private WebDriverWait wait;

    private final String STEP1_URL =
        "https://accounts-dev.salesmate.io/registration-step1.html";
    private final String TEST_EMAIL =
        "otp.test" + System.currentTimeMillis() + "@rapidops.com";

    // Locators
    private final By pageTitle      = By.xpath("//h1[contains(@class,'title-for-test')]");
    private final By otpInputs      = By.xpath("//div[contains(@class,'otp-inputs')]//input");
    private final By verifyButton   = By.id("otp_verified");
    private final By backButton     = By.id("step4_back");
    private final By resendLink     = By.id("step4_resend_email");
    private final By errorMessage   = By.xpath(
        "//div[contains(@class,'error-message') and " +
        "contains(@class,'text-danger')]");

    @BeforeClass
    public void reachStep4() throws InterruptedException {
        driver.get(STEP1_URL);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Step 1
        wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.id("popupEmailInput")));
        driver.findElement(By.id("popupEmailInput")).sendKeys(TEST_EMAIL);
        driver.findElement(By.id("btn_get_started")).click();

        // Step 2
        wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//h1[contains(@class,'title-for-test')]")));
        fillStep2();

        // Step 3
        wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//h1[contains(text(),'personalize') " +
                     "or contains(text(),'Personalize')]")));
        fillStep3();

        // Step 4 - OTP page
        wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//h1[contains(text(),'verify') " +
                     "or contains(text(),'Verify')]")));

        System.out.println("Reached Step 4: " + driver.getCurrentUrl());
    }

    // -------------------------------------------------------
    // TC-1: Verify email verification page loaded
    // -------------------------------------------------------
    @Test(priority = 1)
    public void verifyStep4PageLoaded() {
        WebElement title = driver.findElement(pageTitle);
        String titleText = title.getText().trim();
        System.out.println("Step 4 title: " + titleText);

        Assert.assertTrue(
            titleText.toLowerCase().contains("verify"),
            "Should be on verify email page, found: " + titleText);

        // Verify email address shown in subtitle
        WebElement subtitle = driver.findElement(
            By.xpath("//div[contains(@class,'subtitle-for-test')]"));
        Assert.assertTrue(
            subtitle.getText().contains(TEST_EMAIL),
            "Subtitle should show the registered email");

        System.out.println("TC-1 PASSED: Verify email page loaded");
    }

    // -------------------------------------------------------
    // TC-2: Verify button disabled when OTP fields are empty
    // -------------------------------------------------------
    @Test(priority = 2)
    public void verifyButtonDisabledWhenOTPEmpty() {
        // Target the wrapping <span class="disabled"> that contains the Verify button
        WebElement disabledSpan = wait.until(
            ExpectedConditions.presenceOfElementLocated(
                By.xpath("//span[contains(@class,'disabled') and .//sm-button]")));

        String spanClass = disabledSpan.getAttribute("class");
        System.out.println("Disabled span class: " + spanClass);

        Assert.assertTrue(
            spanClass.contains("disabled"),
            "Verify button wrapper span should have 'disabled' class when OTP is empty. " +
            "Got class: " + spanClass);

        System.out.println(
            "TC-2 PASSED: Verify button disabled when OTP is empty");
    }

    // -------------------------------------------------------
    // TC-3: OTP field accepts only 1 digit per box (6 boxes)
    // -------------------------------------------------------
    @Test(priority = 3)
    public void verifyOTPFieldsStructure() {
        List<WebElement> inputs = driver.findElements(otpInputs);
        Assert.assertEquals(inputs.size(), 6,
            "There should be exactly 6 OTP input fields");

        for (WebElement input : inputs) {
            String maxLength = input.getAttribute("maxlength");
            Assert.assertEquals(maxLength, "1",
                "Each OTP field should accept only 1 character");
        }

        System.out.println(
            "TC-3 PASSED: 6 OTP fields each accepting 1 digit");
    }

    // -------------------------------------------------------
    // TC-4: Invalid OTP shows error message
    // -------------------------------------------------------
    @Test(priority = 4)
    public void verifyInvalidOTPShowsError() {
        // Enter invalid 6-digit OTP
        List<WebElement> inputs = driver.findElements(otpInputs);
        String[] invalidOTP = {"1", "2", "3", "4", "5", "6"};
        for (int i = 0; i < inputs.size(); i++) {
            inputs.get(i).clear();
            inputs.get(i).sendKeys(invalidOTP[i]);
        }

        // Click Verify button
        WebElement verifyBtn = wait.until(
            ExpectedConditions.elementToBeClickable(verifyButton));
        verifyBtn.click();

        // Wait for error message
        WebElement error = wait.until(
            ExpectedConditions.visibilityOfElementLocated(errorMessage));

        String errorText = error.getText().trim();
        System.out.println("OTP Error: " + errorText);

        Assert.assertTrue(
            errorText.contains("Invalid verification code") ||
            errorText.contains("invalid") ||
            errorText.contains("Invalid"),
            "Should show invalid OTP error, got: " + errorText);

        System.out.println("TC-4 PASSED: Invalid OTP shows error message");
    }

    // -------------------------------------------------------
    // TC-5: Resend email shows success message
    // -------------------------------------------------------
    @Test(priority = 5)
    public void verifyResendEmailShowsSuccess() {
        // Click Resend Email link
        WebElement resend = wait.until(
            ExpectedConditions.elementToBeClickable(resendLink));
        resend.click();
        System.out.println("Resend link clicked");

        // Wait for the noty success notification container to appear
        WebElement successContainer = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//li[contains(@class,'noty_container_type_success')]" +
                         "//span[contains(@class,'noty_text')]")));

        String successText = successContainer.getText().trim();
        System.out.println("Resend success message: " + successText);

        Assert.assertTrue(
            successText.contains("Email has been sent") ||
            successText.contains("email has been sent") ||
            successText.contains("Please check your mail"),
            "Should show email sent success message, got: " + successText);

        System.out.println("TC-5 PASSED: Resend email shows success message");
    }
    // -------------------------------------------------------
    // TC-6: Back button goes to previous page (Personalize)
    // -------------------------------------------------------
    @Test(priority = 6)
    public void verifyBackButtonNavigation() {
        WebElement backBtn = wait.until(
            ExpectedConditions.elementToBeClickable(backButton));
        backBtn.click();

        // Should go back to Step 3 (personalize page)
        WebElement prevTitle = wait.until(
            ExpectedConditions.visibilityOfElementLocated(pageTitle));

        String prevTitleText = prevTitle.getText().trim();
        System.out.println("Previous page title: " + prevTitleText);

        Assert.assertTrue(
            prevTitleText.toLowerCase().contains("personalize") ||
            prevTitleText.toLowerCase().contains("experience"),
            "Back should go to personalize page, found: " + prevTitleText);

        System.out.println(
            "TC-6 PASSED: Back button navigates to personalize page");
    }

    // -------------------------------------------------------
    // Helpers to build up state through steps 1-3
    // -------------------------------------------------------
    private void fillStep2() throws InterruptedException {
    	wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("first_name")));
            driver.findElement(By.id("first_name")).sendKeys("Vanita");

            // Last Name
            driver.findElement(By.id("last_name")).sendKeys("Patel");

            // Country dropdown
            selectDropdown("India");
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
            	    By.xpath("//button[@name='step2_complete' or @id='step2_complete']")))
            	    .click();
    }

    private void fillStep3() {
        // Select Industry
        selectDropdown("Automobile");

        // Company name
        wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.id("company_name")));
        driver.findElement(By.id("company_name")).sendKeys("RapidOps");

        // Domain
        String domainName = "rapidops" + System.currentTimeMillis();
        driver.findElement(By.id("linkname")).sendKeys(domainName);

        // Click Next
        WebElement nextBtn = wait.until(
                ExpectedConditions.elementToBeClickable(
                    By.id("step3_complete")));
            nextBtn.click();
    }
    private void selectDropdown(String value) {
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
            searchBox.sendKeys(value);

            // wait for all results to load
            List<WebElement> allOptions = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(
                    By.xpath("//li[contains(@class,'select2-results__option')]")));

            // loop and find exact match
            for(WebElement option : allOptions) {
                System.out.println("Option found: " + option.getText());
                if(option.getText().trim().equals(value)) {
                    option.click();
                    System.out.println("Successfully selected: " + value);
                    break;
                }
            }

        } catch (Exception e) {
            System.out.println("Country selection skipped: " + e.getMessage());
        }
    }
}