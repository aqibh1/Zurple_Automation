package us.zengtest1;

import org.testng.annotations.Test;
import resources.AbstractPageTest;
import static org.testng.Assert.assertEquals;

public class LoginPageTest
        extends PageTest
{

    private static LoginPage page;

    public void clearPage(){
        page=null;
    };

    public LoginPage getPage(){
        if(page == null){
            page = new LoginPage();
            page.setDriver(getDriver());
        }
        return page;
    }

    @Test
    public void testHeader() {
        assertEquals("MEMBER LOGIN", getPage().getHeader().getText());
    }

    @Test
    public void testTitle() {
        assertEquals("Login | zengtest1.us", getPage().getTitle());
    }

    @Test
    public void testBrand() {
        assertEquals("ZENG\n"
                + "TEST\n"
                + "PROPERTIES", getPage().getBrand().getText());
    }

    @Test
    public void testSubmittingEmptyLoginForm(){
        getPage().getLoginForm().clearFields();
        getPage().getLoginForm().submit();
        getPage().getLoginForm().getRequiredInputs();
        //assertFalse(checkLoginFormErrorMessageIsShown());
    }

    /*public void testLoginFormSubmitting(){
        getPage().getLoginForm().submit();
        //assertEquals("http://dev.zengtest1.us/login", getDriver().getCurrentUrl());
        //assertTrue(checkLoginFormErrorMessageIsShown());
    } */

    /*public boolean checkLoginFormErrorMessageIsShown(){
        //return getPage().getLoginForm().findElement(By.xpath("//div[1]/div[4]/div/p")).isDisplayed();
    } */

}
