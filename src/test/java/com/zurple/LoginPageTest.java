package com.zurple;

import resources.AbstractPageTest;
import com.zurple.LoginPage;

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

    public void testTitle() {
        assertEquals("Zurple Inc.", getPage().getTitle());
    }


    public void testBrand() {
        assertFalse(getPage().getBrand() == null);
    }

    public void testSubmittingEmptyLoginForm(){
        getPage().getLoginForm().clearFields();
        getPage().getLoginForm().submit();
        assertTrue(getPage().checkLoginFormExists());
    }

    public void testSubmittingInvalidLoginForm(){
        getPage().getLoginForm().setInputValue("username","test@test.com");
        getPage().getLoginForm().setInputValue("passwd","123");
        getPage().getLoginForm().submit();
        assertTrue(getPage().checkLoginFormExists());
        assertEquals("This Admin account is not active. Please contact us at 800-520-2312.",getPage().getLoginForm().getErrorMessage());
    }

    public void testSubmittingValidLoginForm(){
        getPage().getLoginForm().setInputValue("username","testsiteowner@zurple.com");
        getPage().getLoginForm().setInputValue("passwd","test");
        getPage().getLoginForm().submit();
        assertEquals("https://my.dev.zurple.com/dashboard",getDriver().getCurrentUrl());
    }

}
