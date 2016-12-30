package com.zurple;

import resources.AbstractPageTest;
import com.zurple.LoginPage;

public class LoginPageTest
        extends AbstractPageTest
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
        assertEquals("Login | zengtest1.us", getPage().getTitle());
    }


    public void testBrand() {
        assertEquals("ZENG\n"
                + "TEST\n"
                + "PROPERTIES", getPage().getBrand().getText());
    }

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
