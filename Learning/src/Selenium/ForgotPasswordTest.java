package Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.time.Duration;


@Listeners(learingPackage.TestListener.class)
public class ForgotPasswordTest {

    WebDriver driver;

    // The login page URL (same as your login test)
    private static final String LOGIN_URL      = "https://dev33.salesmate.io/login/";
    // The forgot password page URL (where you land after clicking the link)
    private static final String FORGOT_PWD_URL = "https://dev33.salesmate.io/forgotpassword.html";

    // A real registered email to test the success flow
    private static final String VALID_EMAIL      = "vanita.patel@salesmate.io";
    // A fake email that is NOT registered in the system
    private static final String UNREGISTERED_EMAIL = "notregistered@example.com";


    // ============================================================
    // SETUP — runs ONCE before all tests in this class
    // ============================================================

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();              // open Chrome browser
        driver.manage().window().maximize();       // maximize window
        driver.get(LOGIN_URL);                     // open the Salesmate login page
        System.out.println("Browser opened and navigated to: " + LOGIN_URL);
    }


    // ============================================================
    // TC1 — Verify "Forgot Password" link is VISIBLE on login page
    //
    // Why this test?
    //   We must confirm the link exists before we click it.
    //   If this fails, all other tests will also fail.
    // ============================================================

    @Test(priority = 1)
    public void verifyForgotPasswordLinkIsVisible() {
        System.out.println("\n--- TC1: Verify Forgot Password link is visible ---");

        // Find the "Forgot Password?" link on the login page by its link text
        WebElement forgotPasswordLink = driver.findElement(By.id("forgetPassword"));

        // isDisplayed() returns true if element is visible on screen
        Assert.assertTrue(forgotPasswordLink.isDisplayed(),
                "Forgot Password link should be visible on the login page");

        System.out.println("Forgot Password link text: " + forgotPasswordLink.getText());
        System.out.println("PASS: Forgot Password link is visible");
    }


    // ============================================================
    // TC2 — Verify clicking "Forgot Password" navigates to the correct page
    //
    // Why this test?
    //   Clicking the link should take user to the forgot password page.
    //   We verify the URL and check the page heading is correct.
    // ============================================================

    @Test(priority = 2)
    public void verifyForgotPasswordPageNavigation() {
        System.out.println("\n--- TC2: Verify navigation to Forgot Password page ---");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Click the "Forgot Password?" link
        WebElement forgotPasswordLink = driver.findElement(By.id("forgetPassword"));
        forgotPasswordLink.click();
        System.out.println("Clicked Forgot Password link");

        // Wait until the URL changes to the forgot password page
        wait.until(ExpectedConditions.urlToBe(FORGOT_PWD_URL));

        String currentUrl = driver.getCurrentUrl();
        System.out.println("Current URL after click: " + currentUrl);

        // Verify we landed on the forgot password page
        Assert.assertTrue(currentUrl.equals(FORGOT_PWD_URL),
                "URL should contain 'forgot-password' after clicking the link");

        // Also verify the page title / heading is correct
        WebElement pageHeading = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//h2 | //h3 | //h1")));
        System.out.println("Page heading: " + pageHeading.getText());

        System.out.println("PASS: Navigation to Forgot Password page works");
    }
    
 // ============================================================
    // TC3 — click on the back to login--redirected to the login page
    //
    // Why this test?
    //   User should be redirected to the login page .
    // ============================================================

    @Test(priority = 3)
    public void verifybacktologifromforgot() {
        System.out.println("\n--- TC3: back to login from the forgot password ---");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Make sure we are on the forgot password page
        driver.get(FORGOT_PWD_URL);

        WebElement backtologinButton = driver.findElement(By.xpath("//a[@class='return-to-login-btn return-to-login-forgot']"));
        backtologinButton.click();
        System.out.println("Clicked back to login button");

//        Wait for the URL to go back to the login page
        wait.until(ExpectedConditions.urlToBe(LOGIN_URL));

        String currentUrl = driver.getCurrentUrl();
        System.out.println("URL after clicking Back to Login: " + currentUrl);

        // Verify we are back on the login page
        Assert.assertTrue(currentUrl.equals(LOGIN_URL),
                "URL should contain 'login' after clicking Back to Login");

        // Also verify the login form is visible again (email field should be there)
        WebElement emailField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        Assert.assertTrue(emailField.isDisplayed(),
                "Email field on login page should be visible after navigating back");

        System.out.println("PASS: Back to Login navigation from forgot password works correctly");
    }

    // ============================================================
    // TC4 — Submit with EMPTY email → validation error appears
    //
    // Why this test?
    //   User should not be allowed to submit without entering any email.
    //   The form should show a "Email is required" validation message.
    // ============================================================

    @Test(priority = 3)
    public void verifyEmptyEmailValidation() throws InterruptedException {
        System.out.println("\n--- TC4: Empty email validation ---");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Make sure we are on the forgot password page
        driver.get(FORGOT_PWD_URL);

        // Find the email input field and make sure it's empty
        WebElement emailField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("email-input-wrapper")));
        System.out.println("email is visible");
        // Click the submit / Send Reset Email button WITHOUT entering an email
        WebElement submitButton = driver.findElement(By.id("btn_reset_password"));
        submitButton.click();
        System.out.println("Clicked submit with empty email");
        Thread.sleep(20);

        // Wait for the validation error message to appear
        WebElement errorMessage = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[@id='error_messages_holder']")));
      
        String errorText = errorMessage.getText().trim();
        System.out.println("Validation error message: " + errorText);

        // Error message must not be empty (some error must show)
        Assert.assertFalse(errorText.isEmpty(),
                "Please enter valid email address\n");

        System.out.println("PASS: Empty email shows validation error");
    }


    // ============================================================
    // TC4 — Submit with INVALID email format → validation error appears
    //
    // Why this test?
    //   User should enter a properly formatted email like user@domain.com
    //   If format is wrong (e.g. "abc", "abc@", "@domain"), error must show.
    // ============================================================

    @Test(priority = 5)
    public void verifyInvalidEmailFormatValidation() throws InterruptedException {
        System.out.println("\n--- TC5: Invalid email format validation ---");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Navigate fresh to the forgot password page
        driver.get(FORGOT_PWD_URL);

        // Find email field and type an invalid format
        WebElement emailField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        emailField.sendKeys("invalidemail");    // NOT a valid email format — no @ symbol
        System.out.println("Typed invalid email format: 'invalidemail'");

        // Click submit
        WebElement submitButton = driver.findElement(By.id("btn_reset_password"));
        submitButton.click();
        System.out.println("Clicked submit with invalid email format");
        
        Thread.sleep(20);

        // Wait for validation error
        WebElement errorMessage = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("error_messages_holder")));

        String errorText = errorMessage.getText().trim();
        System.out.println("Validation error: " + errorText);

        Assert.assertFalse(errorText.isEmpty(),
                "An error message should appear for invalid email format");

        System.out.println("PASS: Invalid email format shows validation error");
    }


    // ============================================================
    // TC6 — Submit with VALID REGISTERED email → success message shown
    //
    // Why this test?
    //   When a real registered email is entered and submitted,
    //   Salesmate sends a reset email and shows a confirmation message.
    //   We verify that success message is displayed.
    //
    // NOTE: This does NOT test actual email delivery.
    //       It only tests what appears on the screen after submission.
    // ============================================================

    @Test(priority = 6)
    public void verifyValidEmailSubmission() {
        System.out.println("\n--- TC6: Valid registered email submission ---");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Navigate fresh to the forgot password page
        driver.get(FORGOT_PWD_URL);

        // Enter a valid registered email
        WebElement emailField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        emailField.sendKeys(VALID_EMAIL);    // "vanita.patel@salesmate.io"
        System.out.println("Entered valid registered email: " + VALID_EMAIL);

        // Click submit button
        WebElement submitButton = driver.findElement(By.id("btn_reset_password"));
        submitButton.click();
        System.out.println("Clicked submit with valid email format");

        // Wait for success message — Salesmate shows a confirmation after submission
        // The success message typically contains "sent" or "email" or "check"
        WebElement confirmationText = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                		By.xpath("//p[span[@data-translate-key='WE_HAVE_SENT'] and span[@id='email_placeholder'] and span[@data-translate-key='RESET_INSTRUCTION']]")));



        String actualText = confirmationText.getText().trim();
        System.out.println("Confirmation text: " + actualText);

        // Step 5: Build expected text using the same email that was entered
        String expectedText = "We have sent " + VALID_EMAIL + " an email with reset instructions.";

        Assert.assertEquals(actualText, expectedText,
                "Confirmation message should contain the entered email: " + VALID_EMAIL);
    }


    // ============================================================
    // TC7 — Submit with UNREGISTERED email → appropriate message shown
    //
    // Why this test?
    //   When user enters an email that does NOT exist in Salesmate,
    //   the app may show "email not found" OR for security reasons
    //   it may still show the same success message (to prevent email harvesting).
    //   Either way, we verify the page responds and doesn't crash.
    // ============================================================

    @Test(priority = 7)
    public void verifyUnregisteredEmailSubmission() {
        System.out.println("\n--- TC7: Unregistered email submission ---");
//        / Navigate fresh to the forgot password page
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        driver.get(FORGOT_PWD_URL);

        // Enter a valid registered email
        WebElement emailField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        emailField.sendKeys(UNREGISTERED_EMAIL);    // "vanita.patel@salesmate.io"
        System.out.println("Entered valid registered email: " + UNREGISTERED_EMAIL);

        // Click submit button
        WebElement submitButton = driver.findElement(By.id("btn_reset_password"));
        submitButton.click();
        System.out.println("Clicked submit with valid email format");

        // Wait for success message — Salesmate shows a confirmation after submission
        // The success message typically contains "sent" or "email" or "check"
        WebElement confirmationText = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                		By.xpath("//p[span[@data-translate-key='WE_HAVE_SENT'] and span[@id='email_placeholder'] and span[@data-translate-key='RESET_INSTRUCTION']]")));



        String actualText = confirmationText.getText().trim();
        System.out.println("Confirmation text: " + actualText);

        // Step 5: Build expected text using the same email that was entered
        String expectedText = "We have sent " + UNREGISTERED_EMAIL + " an email with reset instructions.";

        Assert.assertEquals(actualText, expectedText,
                "Confirmation message should contain the entered email: " + UNREGISTERED_EMAIL);
    }


    // ============================================================
    // TC8 — Verify "Back to Login" link navigates back to the login page
    //
    // Why this test?
    //   On the forgot password page, there should be a link to go back.
    //   Clicking it should return the user to the main login page.
    // ============================================================

    @Test(priority = 8)
    public void verifyBackToLoginNavigation() {
        System.out.println("\n--- TC8: Back to Login navigation ---");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Navigate to the forgot password page
        driver.get(FORGOT_PWD_URL);

        // Find the "Back to Login" link — it may say "Back", "Login", "Sign In" etc.
        WebElement backToLoginLink = driver.findElement(
        		By.xpath("//a[contains(text(),'Back') or contains(text(),'Login') " +
                        "or contains(text(),'Sign') or contains(text(),'back')]"));
        backToLoginLink.click();
        System.out.println("Clicked Back to Login link");

        // Wait for the URL to go back to the login page
        wait.until(ExpectedConditions.urlToBe(LOGIN_URL));

        String currentUrl = driver.getCurrentUrl();
        System.out.println("URL after clicking Back to Login: " + currentUrl);

        // Verify we are back on the login page
        Assert.assertTrue(currentUrl.equals(LOGIN_URL),
                "URL should contain 'login' after clicking Back to Login");

        // Also verify the login form is visible again (email field should be there)
        WebElement emailField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        Assert.assertTrue(emailField.isDisplayed(),
                "Email field on login page should be visible after navigating back");

        System.out.println("PASS: Back to Login navigation works correctly");
    }
   



    // ============================================================
    // TEARDOWN — runs ONCE after all tests in this class
    // ============================================================

    @AfterClass
    public void tearDown() {
        System.out.println("\n===== All Forgot Password tests completed. Closing browser. =====");
        driver.quit();    // close all windows and end the WebDriver session
    }
}
