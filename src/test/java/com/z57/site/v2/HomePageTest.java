package com.z57.site.v2;

import com.z57.site.v2.HomePage;
import com.z57.site.v2.Page;
import com.z57.site.v2.PageTest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class HomePageTest extends PageTest
{

    private HomePage page;

    public void clearPage(){
        page=null;
    };

    public Page getPage(){
        if(page == null){
            page = new HomePage(this.source_in_url);
            page.setDriver(getDriver());
        }
        return page;
    }

    public void testTitle() {
        assertEquals("San Diego Homes for Sale | zengtest1.us", getPage().getTitle());
    }

    public void testHeader() {
        Pattern pattern = Pattern.compile("\\d,\\d{3} HOMES FOR SALE IN SAN DIEGO, CA AND NEARBY");
        Matcher matcher = pattern.matcher(getPage().getHeader().getText());
        assertTrue(matcher.find());
    }


    public void testBrand() {
    	assertEquals("ZENG TEST PROPERTIES", getPage().getBrand().getText());    
    	}
   
    @Test
    public void signInWithValidEmail() {
    	boolean isLoginSuccessful = false;
    	if(getPage().isBootStrapModelxist()){
    		getPage().getBootstrapModal().close();
    		getPage().clearBootstrapModal();
    	}
    	
    	LoginForm loginFormObj = new LoginForm(getPage().getWebDriver());
    	clickOnSignInButton();
    	//Clicks on Already Registerd
    	clicksOnAlreadyRegistered();

    	loginFormObj.setEmail("z57testuser@gmail.com");
    	loginFormObj.clickLoginButton();
    	
    	isLoginSuccessful = loginFormObj.isLoginSuccessful();
//    	return isLoginSuccessful;

    }

	//Helper methods
    private void clickOnSignInButton() {
    	assertEquals("Sign In",getPage().getUserMenu().getText());
        getPage().getUserMenu().click();
    }
    
    private void clicksOnAlreadyRegistered() {
    	getPage().getRegisterForm().getElementById("widget_login_topbar").click();
    }
    


}
