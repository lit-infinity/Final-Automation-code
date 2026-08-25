package contact;

import java.awt.RenderingHints.Key;
import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Login.Login;
import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;

public class contactlisting extends Login {
	
	private final By Contactmodule  	= By.xpath("//a[.//span[text()='Contacts']]");
    private final By Contactpencilicon  = By.xpath("(//span[@class='icon-pencil-wrapper'])[1]");
    private final By ContactName		= By.xpath("(//a[@title='Click to view details'])[1]");
    private final By Firstname      	= By.id("firstName");
    private final By Lastname			= By.id("lastName");
    private final By Ownerpencilicon	= By.xpath("//span[@title='Edit Contact Owner'][1]");
//    private final By Ownerdropdwon		= By.xpath("//span[contains(@id,'select2-owner_')]");
//    private final By Ownervalue			= By.xpath("//li[normalize-space(text())='Kinjal Mogha']");
    private final By OwnerCell       = By.xpath("//div[@col-id='owner']");
//    private final By Ownerpencilicon = By.xpath("//i[contains(@class,'icon-pencil')]");
    private final By OwnerDropdown   = By.xpath(
        "//span[contains(@id,'select2-owner_')]" +
        "/ancestor::span[@role='combobox']");
    private final By OwnerOption     = By.xpath(
        "//li[normalize-space(text())='Kinjal Mogha']");
    private final By Save				= By.id("saveBtn");
    private final By successmsg			= By.xpath("//span[@class='noty_text']");




	
//  for daynamic name
  Random rand = new Random();
	int num = rand.nextInt(1000);
	
  String updatedFirstName 	="vanita" + num;
  String updatedLastName	="patel";



  protected WebDriverWait wait;

  public void Contactlisting() throws InterruptedException {
      wait = new WebDriverWait(driver, Duration.ofSeconds(30));
      driver.findElement(Contactmodule).click();
		System.out.println("Clicked contact module");

		    // Wait until New button is visible and clickable
	  wait.until(ExpectedConditions.elementToBeClickable(ContactName));
	  System.out.println("Contact module loaded successfully - firstname is visible");

  	}
  	

  public void Updatename() throws InterruptedException {

      WebElement contactName = driver.findElement(ContactName);
      Thread.sleep(500);
      System.out.println("Contact found: " + contactName.getText());

      // Hover on contact name
      Actions actions = new Actions(driver);
      actions.moveToElement(contactName).perform();
      Thread.sleep(500);
      System.out.println("Hovered on contact name");

      // Wait for pencil icon
      wait.until(ExpectedConditions.visibilityOfElementLocated(Contactpencilicon));
      System.out.println("Pencil icon visible");

      // Click pencil icon
      driver.findElement(Contactpencilicon).click();;
      System.out.println("Clicked pencil icon");

      // Verify e view
      wait.until(ExpectedConditions.visibilityOfElementLocated(Firstname));
      Thread.sleep(2000);
      System.out.println(" Editname opened successfully");
      
      WebElement firstname =driver.findElement(Firstname);
      firstname.sendKeys(Keys.CONTROL + "a");
      firstname.sendKeys(Keys.DELETE);
      firstname.clear();
      firstname.sendKeys(updatedFirstName);
      System.out.println("firstname updated");
      
      WebElement lastname =driver.findElement(Lastname);
      lastname.sendKeys(Keys.CONTROL + "a");
      lastname.sendKeys(Keys.DELETE);
      lastname.clear();
      lastname.sendKeys(updatedLastName);
      System.out.println("lastname updated");
      
//      clicking the save button
      
      driver.findElement(Save).click();
      System.out.println("save button clicked");
      
      wait.until(ExpectedConditions.visibilityOfElementLocated(successmsg));
      String Actualmsg = driver.findElement(successmsg).getText().trim().toLowerCase();
      System.out.println("Actual Message "+ Actualmsg);
      Assert.assertTrue(Actualmsg.contains("updated"));
  }
  
  public void Updateowner() throws InterruptedException {

      WebElement contactOwner = driver.findElement(Ownerpencilicon);
      Thread.sleep(500);
      System.out.println("Contact owner found: " + contactOwner.getText());

      // Hover on contact name
      Actions actions = new Actions(driver);
      actions.moveToElement(contactOwner).perform();
      Thread.sleep(500);
      System.out.println("Hovered on contact owner");

      // Wait for pencil icon
      wait.until(ExpectedConditions.visibilityOfElementLocated(Ownerpencilicon));
      System.out.println("Pencil icon visible");

      // Click pencil icon
      driver.findElement(Ownerpencilicon).click();;
      System.out.println("Clicked pencil icon");

          // Step 1 - Scroll into view
      WebElement ownerDropdown = driver.findElement(OwnerDropdown);
      ((JavascriptExecutor) driver)
          .executeScript("arguments[0].scrollIntoView(true);", ownerDropdown);
      Thread.sleep(500);

      // Step 2 - Click to open dropdown
      ((JavascriptExecutor) driver)
          .executeScript("arguments[0].click();", ownerDropdown);
      System.out.println("Owner dropdown opened");
      Thread.sleep(500);

      // Step 3 - Wait for options to appear
      wait.until(ExpectedConditions.visibilityOfElementLocated(OwnerOption));

      // Step 4 - Click desired option
      driver.findElement(OwnerOption).click();
      System.out.println("Owner selected successfully");
      }

}

