package learingPackage;

//A class in Selenium used to locate elements on a webpage.
import org.openqa.selenium.By;
//An interface in Selenium that controls the browser.
import org.openqa.selenium.WebDriver;
//A class that implements WebDriver specifically for Google Chrome.
import org.openqa.selenium.chrome.ChromeDriver;

public class LinkTextExample {
//	public void ClickLinkBYText(String url, String LinkText) {
//		
////		intialize the chrome browser
//		
//		WebDriver driver =new ChromeDriver();
//		
////		open the website
//		driver.get(url);
//		System.out.println("first par executed");
//		
////		locate the link by it text and click it
//		driver.findElement(By.linkText(LinkText)).click();
//		
////		Print the confirmation
//		System.out.println(LinkText + " Link clicked");
//		
////		Close the browser
//		
//		driver.quit();
//	}
	
	WebDriver driver;
//	open the browser
	public void openBrowser(String URL) {
		driver =new ChromeDriver();
		driver.get(URL);
		System.out.println(URL +"browser opened successfully");
	}
	
//	fill email and password by id
	
	public void fillLoginForm(String email, String password) {
		driver.findElement(By.id("email")).sendKeys(email);;
		driver.findElement(By.id("password")).sendKeys(password);
		System.out.println("email and password are filled");
	}
	
//	click login button using class
	
	public void clickLoginButton() {
		driver.findElement(By.className("btn-primary")).click();
		System.out.println("login button is clicked successfully");
	}
	
//	click link by the excect text
	
	public void clickLinkByText(String linkText) {
		driver.findElement(By.linkText(linkText)).click();
		System.out.println("Link clicked using linkText: " + linkText);
		
	}
	
    // Method to click link by partial link text

	public void clickLinkByPartialText(String PartialText) {
		driver.findElement(By.partialLinkText(PartialText)).click();
		System.out.println("link clicked using partial link: "+PartialText);	
	}
	
//	locate the element by the css selector(ID)
	
	public void fillPasswordByCssId(String password) {
		driver.findElement(By.cssSelector("#password")).sendKeys(password);
		System.out.println("password filled is filled using css selector");
	}
	
//	clickSubmitByCssClass (class)
	
	public void clickSubmitByCssClass() {
		driver.findElement(By.cssSelector(".btn-primary")).click();
		System.out.println("loging button is clicked");
	}
	
//	 Locate element by tag + class
	
	public void fillInputByTagClass(String value) {
		driver.findElement(By.cssSelector("input")).sendKeys(value);
        System.out.println("Filled input using tag + class");

	}
	
    // 7. Locate element by attribute
	
	public void fillUsernameByAttribute(String username) {
		driver.findElement(By.cssSelector("input[type=text]")).sendKeys(username);
		System.out.println("Filled username using attribute selector");
		
		
	}
	
	  // 8. Locate element by multiple attributes
    public void fillPasswordByMultipleAttributes(String password) {
    	driver.findElement(By.cssSelector("input[type='password'][id='password']")).clear();
    	driver.findElement(By.cssSelector("input[type='password'][id='password']")).sendKeys(password);
        System.out.println("Filled password using multiple attributes");
    }

    // 9. Locate element by child selector
    public void fillInputByChildSelector(String value) {
        driver.findElement(By.cssSelector("div.w-full > input")).sendKeys(value);
        System.out.println("Filled input using child selector");
    }
    
//    fill email using relative xpath
    public void fillemail(String email) {
    	driver.findElement(By.xpath("//input[@id='email']")).sendKeys(email);
    	System.out.println("email is filled with xpath");
    }
    
//  fill password with xpath
    
    public void fillpassword(String password) {
    	driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);
    	System.out.println("Password is entered successfully");
    }
    
//    click on the login button
    
    public void click() {
    	driver.findElement(By.xpath("//span[normalize-space()='Login']")).click();
    	System.out.println("login button is clicked successfully");
    }


	
//	 to close the browser
	 public void closeBrowser() {
	        driver.quit();
	        System.out.println("Browser closed");
	    }
	
	public static void main(String[]args) {
		LinkTextExample example = new LinkTextExample();
//		example.ClickLinkBYText("https://dev33.salesmate.io/login/#/", "Sign up");
		example.openBrowser("https://dev33.salesmate.io/login/#/");
//		example.fillLoginForm("vanita.patel@salesmate.io", "Samehere@123");
//		example.clickLoginButton();
//		example.clickLinkByText("Sign up");
//		example.clickLinkByPartialText("Sign");

//	    example.fillPasswordByCssId("Password123");
//	    example.clickSubmitByCssClass();
//	    example.fillInputByTagClass("InputValue");
//	    example.fillUsernameByAttribute("admin");
//	    example.fillPasswordByMultipleAttributes("123456");
//	    example.fillInputByChildSelector("ChildInput");
	    example.fillemail("vanita.patel@salesmate.io");
	    example.fillpassword("Samehere@123");
	    example.click();
		example.closeBrowser();

}
}
