package com.zurple;

import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

public class LoginPageTest
        extends PageTest
{

    private static com.zurple.LoginPage page;

    public com.zurple.LoginPage getPage(){
        if(page == null){
            page = new LoginPage();
            page.setDriver(getDriver());
        }
        return page;
    }

    public void clearPage(){
        page=null;
    };

    @Test
    public void testTitle() {
        assertEquals("Zurple Inc.", getPage().getTitle());
    }

    @Test
    public void testBrand() {
        assertFalse(getPage().getBrand() == null);
    }

    @Test
    public void testSubmittingEmptyLoginForm(){
        getPage().getLoginForm().clearFields();
        getPage().getLoginForm().submit();
        assertTrue(getPage().checkLoginFormExists());
    }

    @Test
    public void testSubmittingInvalidLoginForm(){
        getPage().getLoginForm().setInputValue("username","test@test.com");
        getPage().getLoginForm().setInputValue("passwd","123");
        getPage().getLoginForm().submit();
        assertTrue(getPage().checkLoginFormExists());
        assertEquals("This Admin account is not active. Please contact us at 800-520-2312.",getPage().getLoginForm().getErrorMessage());
    }

    @Parameters({"login","password"})
    @Test
    public void testSubmittingValidLoginForm(@Optional("testsiteowner@zurple.com") String login, @Optional("test") String password){
        getPage().getLoginForm().setInputValue("username",login);
        getPage().getLoginForm().setInputValue("passwd",password);
        getPage().getLoginForm().submit();
        assertEquals("https://my.dev.zurple.com/dashboard",getDriver().getCurrentUrl());
    }

}
