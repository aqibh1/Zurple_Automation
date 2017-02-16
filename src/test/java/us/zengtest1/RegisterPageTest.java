package us.zengtest1;

import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertFalse;
import resources.AbstractPageTest;

import static org.testng.Assert.assertEquals;

public class RegisterPageTest
        extends AbstractPageTest
{

    private static RegisterPage page;

    public RegisterPage getPage(){
        if(page == null){
            page = new RegisterPage();
            page.setDriver(getDriver());
        }
        return page;
    }

    @Test
    public void testTitle() {
        assertEquals("Search for Homes in San Diego, CA", getPage().getTitle());
    }

    @Test
    public void testBrand() {
        assertEquals(getPage().getBrand().getText(), "ZENG TEST PROPERTIES");
    }

    @Test
    public void testSubmittingEmptyRegisterForm(){
        getPage().getRegisterForm().clearFields();
        getPage().getRegisterForm().submit();
        getPage().getRegisterForm().getRequiredInputs();
        assertTrue(getPage().getRegisterForm().getFormErrorMessagesList().isEmpty());
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
