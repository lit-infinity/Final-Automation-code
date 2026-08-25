package learingPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class SignUpCountryDropdownTest {

    WebDriver driver;

    @BeforeClass
    public void setup() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://accounts-dev.salesmate.io/registration/#/signup/1cfb6451-a9e3-457c-8f13-a9680f760ce5");
        System.out.println("Browser opened");

        // Wait 3 seconds so you can SEE the page before test starts
        Thread.sleep(3000);
    }

    @Test
    public void selectCountryFromDropdown() throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Step 1: Click the flag/country dropdown to OPEN it
        driver.findElement(
                By.xpath("//*[@id=\"flagWrap_phone\"]/span[1]")).click();
        System.out.println("Clicked country dropdown - it is now open");
        Thread.sleep(2000); // wait 2 seconds so you can SEE the dropdown opened

        // Step 2: Type country name in the SEARCH box
        WebElement searchBox = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//input[@role='textbox']")));
        searchBox.sendKeys("India");
        System.out.println("Typed India in search box");
        Thread.sleep(2000); // wait 2 seconds so you can SEE the filtered results

        // Step 3: Click on India from the filtered results
        WebElement indiaOption = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//li[contains(@class,'select2-results__option')]//span[text()='India']")));
        indiaOption.click();
        System.out.println("Clicked on India from dropdown list");
        Thread.sleep(2000); // wait 2 seconds so you can SEE India is selected

        // Step 4: Verify India is selected
        WebElement selectedCountry = driver.findElement(
                By.xpath("//span[@class='select2-selection__rendered']"));
        String selectedText = selectedCountry.getAttribute("title");
        System.out.println("Selected country: " + selectedText);
        Thread.sleep(2000); // wait 2 seconds before assertion

        Assert.assertEquals(selectedText, "India",
                "India should be selected in the country dropdown");

        System.out.println("PASS: Country dropdown working correctly");
        Thread.sleep(3000); // wait 3 seconds at the end so you can SEE the final result
    }

    @AfterClass
    public void tearDown() throws InterruptedException {
        Thread.sleep(2000); // wait 2 seconds before closing browser
        driver.quit();
        System.out.println("Browser closed");
    }
}