package Selenium;

import org.openqa.selenium.By;

public class locatorsemail {
    private final By Emailcolumn      = By.xpath("//span[text()=' Email']");
    private final By Emailaddress =By.xpath("(//a[@title='Click to send email'])[5]");
    private final By Attachfiles = By.xpath("//span[normalize-space()='Attach Files']");
    private final By Attachfilesfromrecord =By.xpath("//a[normalize-space()='Attach files from record']");
    private final By attachpopup =By.xpath("//file-attachment-dialog");
    private final By attachfilesheader = By.xpath("//h4[normalize-space()='Attach files']");
    private final By ThisRecordtab =By.xpath("//a[normalize-space()='This Record']");
    private final By AssociatedDealstab = By.xpath("//a[normalize-space()='Associated Deals']");
    private final By AssociatedTicketstab =By.xpath("//a[normalize-space()='Associated Tickets']");
    private final By SearchBox =By.xpath("(//input[normalize-space(@placeholder)='Search'])[2]");
    private final By ListView = By.xpath("//button[.//svg//path[contains(@d,'M1 1.5h10')]]");
    private final By GridView = By.xpath("//button[.//svg//path[contains(@d,'M13.375 5.3')]]");
    private final By UploadFromDevice = By.xpath("//button[normalize-space()='Upload from device']");
    private final By crossicon = By.xpath("//a[@class='close s-m-l-md']");
    private final By AttachSelected =By.xpath("//button[normalize-space()='Attach selected' and @disabled]");
    private final By CancelButton = By.xpath("(//button[normalize-space()='Cancel'])[4]");

    



}
