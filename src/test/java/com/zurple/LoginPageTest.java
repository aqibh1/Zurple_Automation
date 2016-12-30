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
        assertFalse(getPage().getBrand()==null);
    }

    public void testSubmittingEmptyLoginForm(){
        getPage().getLoginForm().clearFields();
        getPage().getLoginForm().submit();
        getPage().getLoginForm().getRequiredInputs();
        //assertFalse(checkLoginFormErrorMessageIsShown());
    }

}
