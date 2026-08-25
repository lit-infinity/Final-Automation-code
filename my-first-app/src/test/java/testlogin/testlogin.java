package testlogin;

import Login.Login;
import utilitytest.DataProviders;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



public class testlogin extends Login {
	

	@BeforeClass
//	@BeforeMethod    
    public void init() throws Exception {
        setup();   
//        Thread.sleep(5000);
    }

	
	@Test(priority=1,enabled = false,dataProvider = "invalidLoginData", dataProviderClass = DataProviders.class)
	
	public void testInvalidlogin(String email, String password) {
		invalidLogin(email,password);
		
	}
	
	@Test(priority=2, enabled = true)
	
	public void testValidLogin() throws Exception {
		validLogin();
	}
	
	 @AfterClass
//	 @AfterMethod
	    public void teardown() {
	        driver.quit();  
//	        getDriver().quit(); // this is for dataprovide parellal =true
	    }


}
