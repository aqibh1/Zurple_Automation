package com.zurple.my;

import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

public class LoginPageTest
        extends PageTest
{

    private static LoginPage page;

    public LoginPage getPage(){
        if(page == null){
            page = new LoginPage();
            page.setDriver(getDriver());
        }
        return page;
    }

    public void clearPage(){
        page=null;
    };

    @Test(priority=10)
    public void testTitle() {
        assertEquals(getPage().getTitle(),"Zurple Inc.");
    }

    @Test(priority=10)
    public void testBrand() {
        assertFalse(getPage().getBrand() == null);
    }

    @Test(priority=20)
    public void testSubmittingEmptyLoginForm(){
        getPage().getLoginForm().clearFields();
        getPage().getLoginForm().submit();
        assertTrue(getPage().checkLoginFormExists());
    }

    @Test(priority=30)
    public void testSubmittingInvalidLoginForm(){
        getPage().getLoginForm().setInputValue("username","test@test.com");
        getPage().getLoginForm().setInputValue("passwd","123");
        getPage().getLoginForm().submit();
        assertTrue(getPage().checkLoginFormExists());
        assertEquals("This Admin account is not active. Please contact us at 800-520-2312.",getPage().getLoginForm().getErrorMessage());
    }

    @Parameters({"login","password"})
    @Test(priority=40,groups = { "login" })
    public void testSubmittingValidLoginForm(@Optional("testsiteowner@zurple.com") String login, @Optional("test") String password){
        getPage().getLoginForm().setInputValue("username",login);
        getPage().getLoginForm().setInputValue("passwd",password);
        getPage().getLoginForm().submit();
        assertEquals("https://my.dev.zurple.com/dashboard",getDriver().getCurrentUrl());
    }

}
