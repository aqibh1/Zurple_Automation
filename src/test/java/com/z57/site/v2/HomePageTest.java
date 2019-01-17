package com.z57.site.v2;

import com.z57.site.v2.HomePage;
import com.z57.site.v2.Page;
import com.z57.site.v2.PageTest;

import resources.alerts.BootstrapModal;
import resources.forms.z57.LoginForm;
import resources.forms.z57.RegisterForm;

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
    public void testSignInWithValidEmail() {
   
//    	if(getPage().checkBootsrapModalIsShown()){
//    		getPage().getBootstrapModal().close();
//    		getPage().clearBootstrapModal();
//    	}
    	
    	BootstrapModal bootstrapModalObj = new BootstrapModal(getPage().getWebDriver());
        
    	if(bootstrapModalObj.checkBootsrapModalIsShown()){
        	bootstrapModalObj.getBootstrapModal().close();
        	bootstrapModalObj.clearBootstrapModal();
        }

    	
    	LoginForm loginFormObj = new LoginForm(page.getWebDriver());
    	assertEquals("Sign In",getPage().getUserMenu().getText());
    	assertTrue(loginFormObj.clickOnSignInButton(),"Sign In button not visible on Home Page");
    	
    	RegisterForm registerFormObj = new RegisterForm(page.getWebDriver());
    	//Clicks on Already Registered
    	assertTrue(registerFormObj.clickOnAlreadyRegistered(),"Already registered link is not visible");

    	assertTrue(loginFormObj.setEmail("adar@gmail.com"),"Unable to type email address");
    	assertTrue(loginFormObj.clickLoginButton(),"Unable to click on Login button");
    	
    	assertTrue(loginFormObj.isLoginSuccessful(),"Unable to logon with givern credentials");
    
    }
}
