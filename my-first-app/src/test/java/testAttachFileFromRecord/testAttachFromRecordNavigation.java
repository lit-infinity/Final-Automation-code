package testAttachFileFromRecord;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import attachFileFromRecord.AttachFromRecordNavigation;

public class testAttachFromRecordNavigation extends AttachFromRecordNavigation {
	
	
    @BeforeClass
    public void init() throws Exception {
        setup();       // Browser open and open the login page
//        synchronizedLogin(() -> {
//            try {
//                validLogin();
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//        });
        validLogin();  // Login only ONCE before all tests
        Contactlisting();
    }
    
    @Test(priority = 1)
    public void Emailclick() throws InterruptedException {
    	hoverAndClickEmail();
    }
    
    @Test(priority = 2)
    public void Attachfileclick() throws InterruptedException{
    	clickAttachIcon();
    }
    
    @Test(priority = 3)
    public void popuptabs() throws InterruptedException {
    	verifyPopupAndTabs();
    }
      
    
    @AfterClass
    public void teardown() {
    	tearDown();
    }

}
