package learingPackage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.Cookie;
import java.util.Set;

public class TestBrowser {

    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();
//        driver.manage().window().maximize();
//    	WebDriver driver =new FirefoxDriver();
//    	WebDriver driver = new EdgeDriver();
//        driver.get("https://google.com");
//        Thread.sleep(3000);
        
        driver.navigate().to("https://www.amazon.in/");
        Thread.sleep(4000);

//        driver.navigate().back();
//        Thread.sleep(3000);

//        driver.navigate().forward();
//        Thread.sleep(3000);
//
//        driver.navigate().refresh();
//        Thread.sleep(3000);
        
        driver.manage().window().setSize(new Dimension(1024,768));

        driver.manage().window().setPosition(new Point(0,0));

        System.out.println("Window Size: " + driver.manage().window().getSize());
        System.out.println("Window Position: " + driver.manage().window().getPosition());

        
        String title = driver.getTitle();
        System.out.println("URL:- "+ title);
        
        String currenturl =driver.getCurrentUrl();
        System.out.println("current URL:- "+ currenturl);
        
        int sourcecode =driver.getPageSource().length();
        System.out.println("length of pagesource:- "+ sourcecode);   
        
       
        
        Cookie name = driver.manage().getCookieNamed("session-id");
        System.out.println("cookie name :"+ name.getName());
        
//        add new cookie
        
        Cookie newone =new Cookie("testcookie", "12345");
        driver.manage().addCookie(newone);
        
        Cookie justdelete = driver.manage().getCookieNamed("testcookie");
        driver.manage().deleteCookie(justdelete);
        
        driver.manage().deleteAllCookies();
        
        
        Set<Cookie> cookies =driver.manage().getCookies();
        for(Cookie ck :cookies) {
        	System.out.println(ck.getName() + " : " +ck.getValue());
        }
        
        driver.quit();


    }
}
