package testcontact;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import contact.Contactquickview;
import contact.contactlisting;

public class testcontactquickview extends Contactquickview {

    @BeforeClass
    public void init() throws Exception {
        setup();       // Browser open and open the login page
        validLogin();  // Login only ONCE before all tests
    }

    @Test(priority = 1)
    public void contactlistingnavigation() throws InterruptedException {
        Contactlisting();
    }

    // ✅ Renamed to avoid calling itself
    @Test(priority = 2)
    public void contactQuickView() throws InterruptedException {
    	quickview(); 
    	
    }
    
    @Test(priority =3)
    public void Updatedetail() throws InterruptedException{
    	UpdateContact();
    }
    
    @Test(priority =4)
    public void timeline() throws InterruptedException{
    	verifyTimelineUpdates();
    }


    @AfterClass
    public void teardown() {
        tearDown();
    }
}