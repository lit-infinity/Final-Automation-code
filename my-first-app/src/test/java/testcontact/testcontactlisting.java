package testcontact;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import contact.contactlisting;

public class testcontactlisting extends contactlisting {

    @BeforeClass
    public void init() throws Exception {
        setup();       // Browser open and open the login page
        validLogin();  // Login only ONCE before all tests
    }
    
    @Test(priority = 1)
    public void contactlistingnavigation() throws InterruptedException {
        Contactlisting();
        Assert.fail("Testing screenshot capture"); // ← add here to test
    }
    
    @Test(priority = 2, enabled = false)
    public void Editname() throws InterruptedException {
    	Updatename();
    }
    
    @Test(priority = 2)
    public void Editowner() throws InterruptedException {
    	Updateowner();
    }
       
    @AfterClass
    public void teardown() {
        tearDown();
    }
}