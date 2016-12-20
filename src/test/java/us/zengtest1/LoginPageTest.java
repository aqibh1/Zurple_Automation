package us.zengtest1;

import junit.framework.TestCase;
import org.junit.AfterClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class LoginPageTest
        extends HomePageTest
{

    private static LoginPage page;

    protected static LoginPage getPage(){
        if(page == null){
            page = new LoginPage();
            page.setDriver(getDriver());
        }
        return page;
    }

    public void testHeader() {
        assertEquals("MEMBER LOGIN", getPage().getHeader().getText());
    }

    public void testTitle() {
        assertEquals("Login | zengtest1.us", getPage().getTitle());
    }

}
