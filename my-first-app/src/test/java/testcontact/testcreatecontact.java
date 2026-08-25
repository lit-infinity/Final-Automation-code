package testcontact;

import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import contact.createcontact;



public class testcreatecontact extends createcontact{
	
	@BeforeClass
    public void init() throws Exception {
		setup();  //Brwoser open and open the login page
        validLogin();  // login only ONCE before all tests
    }
	
	@Test(priority=1, enabled = true)
	public void contactnavigation(){
		contactNavigation();
	}
	
	@Test(priority=2, enabled = false)
	public void checkvalidation() throws InterruptedException {
		validationcheck();
	}
	
	@Test(priority=3, enabled = false)
	public void emailvalidation() {
		Invalidemail();
	}
	
	 
	@Test(priority=4, enabled = true)
	public void createcontact() throws Exception {
		ContactCreation();
	}
	
	@Test(priority=5,enabled =false)
	public void dropdwoncheck() {
		verifyAndSelectDropdown();
	}
	
	@Test(priority=6,enabled =false)
	public void closesidebar() throws InterruptedException {
		Closesidebar();
	}
	
	@Test(priority=7,enabled =false)
	public void ownerdropdown1() throws InterruptedException{
		ownerdropdown();
	}
	
	@AfterClass
	public void teardown() {
		tearDown();
	}
}
