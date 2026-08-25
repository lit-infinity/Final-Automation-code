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

@Listeners(learingPackage.TestListener.class)
public class SignupStep1_EmailPage extends SignupBasePage {

    private WebDriverWait wait;

    // URLs
    private final String LOGIN_URL  = "https://dev33.salesmate.io/login/";
    private final String STEP1_URL  = "https://dev33.salesmate.io/registration-step1.html";

    // Emails
    private final String REGISTERED_EMAIL   = "vanita.patel@salesmate.io";
    private final String UNREGISTERED_EMAIL = "vanita.test" + System.currentTimeMillis() + "@rapidops.com";

    // Locators - Login Page
    private final By signUpLink = By.xpath("/html/body/app-root/login-wrapper/div[1]/sm-login/form/div/div[1]/div[2]/div/a");


    // Locators - Step 1 Page
    private final By emailInput       = By.id("popupEmailInput");
    private final By getStartedBtn    = By.id("btn_get_started");
    private final By emailErrorMsg    = By.id("err_message");
    private final By errorMsgHolder   = By.id("api_err_message");
    private final By signInLink       = By.id("signin_from_signup");
    private final By freeTrialText    = By.xpath("//h1[@data-translate-key='SIGN_UP_FOR_FREE_TRIAL']");

    @BeforeClass
    public void navigateToLoginPage() {
        // Always start from login page
        driver.get(LOGIN_URL);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        System.out.println("Started at login page: " + driver.getCurrentUrl());
    }

    // -------------------------------------------------------
    // TC-1: Click Sign Up on login page → redirect to STEP1_URL
    // -------------------------------------------------------
    @Test(priority = 1)
    public void verifySignUpRedirectsToRegistrationPage() {

        // Wait for Sign Up link to be visible on login page
        WebElement signUp = wait.until(
            ExpectedConditions.elementToBeClickable(signUpLink));

        System.out.println("Sign Up link found: " + signUp.getText().trim());

        // Click Sign Up
        signUp.click();

        // Verify redirected to STEP1_URL
        wait.until(ExpectedConditions.urlToBe(STEP1_URL));

        String currentUrl = driver.getCurrentUrl();
        System.out.println("Redirected to: " + currentUrl);

        Assert.assertEquals(currentUrl, STEP1_URL,
            "Clicking Sign Up should redirect to registration step 1 page");

        System.out.println("TC-1 PASSED: Sign Up redirects to registration page");
    }

    // -------------------------------------------------------
    // TC-2: Verify page loads with 15 days free trial text
    // -------------------------------------------------------
    @Test(priority = 2, dependsOnMethods = {"verifySignUpRedirectsToRegistrationPage"})
    public void verifyFreeTrialPageLoaded() {

        // Verify free trial text is visible
        WebElement trialText = wait.until(
            ExpectedConditions.visibilityOfElementLocated(freeTrialText));

        Assert.assertTrue(trialText.isDisplayed(),
            "15 days free trial text should be visible on the page");

        // Verify email input is present
        WebElement emailField = wait.until(
            ExpectedConditions.visibilityOfElementLocated(emailInput));

        Assert.assertTrue(emailField.isDisplayed(),
            "Email input field should be visible");

        System.out.println("TC-2 PASSED: Free trial page loaded with correct content");
    }
//
//    // -------------------------------------------------------
//    // TC-3: Get Started button disabled when email is empty
//    // -------------------------------------------------------
    @Test(priority = 3, dependsOnMethods = {"verifyFreeTrialPageLoaded"})
    public void verifyGetStartedButtonDisabledWhenEmpty() {

        // Make sure email is empty
        driver.findElement(emailInput).clear();

        WebElement btn = driver.findElement(getStartedBtn);
        String cssClass = btn.getAttribute("class");

        Assert.assertTrue(cssClass.contains("disabled"),
            "Get Started button should be disabled when email field is empty");

        System.out.println("TC-3 PASSED: Get Started button disabled when email is empty");
    }
//
//    // -------------------------------------------------------
//    // TC-4: Invalid email format shows validation error
//    // -------------------------------------------------------
    @Test(priority = 4, dependsOnMethods = {"verifyGetStartedButtonDisabledWhenEmpty"})
    public void verifyInvalidEmailShowsValidationError() {

        // Enter invalid email
        WebElement emailField = driver.findElement(emailInput);
        emailField.clear();
        emailField.sendKeys("invalidemail");

        // Click Get Started
        driver.findElement(getStartedBtn).click();

        // Wait for inline error message
        WebElement error = wait.until(
            ExpectedConditions.visibilityOfElementLocated(emailErrorMsg));

        String actualError = error.getText().trim();
        System.out.println("Actual validation error: " + actualError);

        Assert.assertTrue(
            actualError.contains("Please enter valid email address"),
            "Should show 'Please enter valid email address', but got: " + actualError);

        System.out.println("TC-4 PASSED: Invalid email shows correct validation message");
    }
//
//    // -------------------------------------------------------
//    // TC-5: Already registered email shows API error message
//    // -------------------------------------------------------
    @Test(priority = 5)
    public void verifyAlreadyRegisteredEmailShowsError() {

        // Refresh to clear previous state
        driver.navigate().refresh();
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput));

        // Enter already registered email
        WebElement emailField = driver.findElement(emailInput);
//        emailField.clear();
        emailField.sendKeys(REGISTERED_EMAIL);

        // Wait for Get Started button to become clickable
        wait.until(ExpectedConditions.elementToBeClickable(getStartedBtn));
        driver.findElement(getStartedBtn).click();

        // Wait for API error holder to be visible
        WebElement errorHolder = wait.until(
            ExpectedConditions.visibilityOfElementLocated(errorMsgHolder));

        String actualError = errorHolder.getText().trim();
        System.out.println("API error message: " + actualError);

        Assert.assertTrue(
            actualError.contains("User with this email is already registered"),
            "Should show already registered error, but got: " + actualError);

        System.out.println("TC-5 PASSED: Already registered email shows correct error");
    }
    
 // -------------------------------------------------------
    // TC-6: Sign In link on Step 1 page redirects to login page
    // -------------------------------------------------------
    @Test(priority = 6)
    public void verifySignInLinkRedirectsToLoginPage() {

        // Refresh to clear state
        driver.navigate().refresh();
        wait.until(ExpectedConditions.visibilityOfElementLocated(signInLink));

        // Click Sign In link
        WebElement signIn = driver.findElement(signInLink);
        System.out.println("Sign In link text: " + signIn.getText().trim());
        signIn.click();

        // Verify redirected to login page
        wait.until(ExpectedConditions.urlContains("login"));
        String currentUrl = driver.getCurrentUrl();

        Assert.assertTrue(currentUrl.contains("login"),
            "Clicking Sign In should redirect to login page, but URL was: " + currentUrl);

        System.out.println("Redirected to: " + currentUrl);
        System.out.println("TC-6 PASSED: Sign In link redirects to login page");

        // Navigate back to STEP1_URL for next test
        driver.get(STEP1_URL);
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput));
    }

     // -------------------------------------------------------
    // TC-7: Valid unregistered email redirects to next step
   // -------------------------------------------------------
    @Test(priority = 7)
    public void verifyValidEmailRedirectsToNextStep() {

        // Refresh to clear state
        driver.navigate().refresh();
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput));

        // Enter valid unregistered email
        WebElement emailField = driver.findElement(emailInput);
        emailField.clear();
        emailField.sendKeys(UNREGISTERED_EMAIL);
        System.out.println("Using email: " + UNREGISTERED_EMAIL);

        // Wait for Get Started button to be clickable and click
        wait.until(ExpectedConditions.elementToBeClickable(getStartedBtn));
        driver.findElement(getStartedBtn).click();

        // Verify redirected to registration page (Let's get to know you)
        wait.until(ExpectedConditions.urlContains("registration/#"));
        String currentUrl = driver.getCurrentUrl();

        Assert.assertTrue(
            currentUrl.contains("registration/#"),
            "Valid email should redirect to registration flow, but URL was: " + currentUrl);

        // Also verify by checking page heading
        WebElement pageHeading = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h1[@class='font-bold m-b s-m-b-md title-for-test']")));
        
        String actualHeading = pageHeading.getText().trim();

        System.out.println("Next page heading: " + pageHeading.getText().trim());
        
        Assert.assertEquals(actualHeading, "Let's get to know you",
                "Page heading should be 'Let's get to know you' but found: " + actualHeading);

        System.out.println("Redirected to: " + currentUrl);
        System.out.println("TC-7 PASSED: Valid email redirects to 'Let's get to know you' page");
    }
    
    
  
  }
