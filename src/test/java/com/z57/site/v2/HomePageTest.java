package com.z57.site.v2;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.z57.site.v2.HomePage;
import com.z57.site.v2.Page;
import com.z57.site.v2.PageTest;

import resources.ConfigReader;
import resources.alerts.BootstrapModal;
import resources.data.z57.RegisterUserData;
import resources.data.z57.SearchFormData;
import resources.forms.z57.LoginForm;
import resources.forms.z57.RegisterForm;
import resources.orm.hibernate.models.AbstractLead;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.constraints.AssertTrue;

import org.openqa.selenium.Cookie;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class HomePageTest extends PageTest
{
	private RegisterUserData registerUserData;
    private HomePage page;

    public void clearPage(){
        page=null;
    };

    public Page getPage(){
    	ConfigReader configReader = ConfigReader.load();
        if(page == null){
            page = new HomePage();
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
    
//    @BeforeClass(groups="RegisterUser")
//	@Parameters({"dataFile"})
//	public void beforeClass(String pFolderLocation) throws JsonParseException, JsonMappingException, IOException {
//    	registerUserData = new RegisterUserData();
//    	registerUserData = registerUserData.setRegisterUserData(pFolderLocation);
//	}
   
    @Test
    public void testSignInWithValidEmail() {
    	ConfigReader configReader = ConfigReader.load();
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

    	assertTrue(loginFormObj.setEmail(configReader.getPropertyByName("z57_user_v2")),"Unable to type email address");
    	assertTrue(loginFormObj.clickLoginButton(),"Unable to click on Login button");
    
    }
    @Parameters({"dataFile"})
    @Test
    public void testRegisterNewUser(String pFolderLocation) {
    	registerUserData = new RegisterUserData();
    	registerUserData = registerUserData.setRegisterUserData(pFolderLocation);
    	
    	BootstrapModal bootstrapModalObj = new BootstrapModal(getPage().getWebDriver());

    	if(bootstrapModalObj.checkBootsrapModalIsShown()){
        	bootstrapModalObj.getBootstrapModal().close();
        	bootstrapModalObj.clearBootstrapModal();
        }
    	
    	LoginForm loginFormObj = new LoginForm(page.getWebDriver());
    	assertEquals("Sign In",getPage().getUserMenu().getText());
    	assertTrue(loginFormObj.clickOnSignInButton(),"Sign In button not visible on Home Page");
    	
    	RegisterForm registerFormObj = new RegisterForm(page.getWebDriver());
    	if(!registerUserData.getUserName().isEmpty()) {
    		assertTrue(registerFormObj.setName(registerUserData.getUserName()),"Unable to type Name in input field");
    	}
    	if(!registerUserData.getUserEmail().isEmpty()) {
    		assertTrue(registerFormObj.setEmail(registerUserData.getUserEmail()),"Unable to type Email in input field");
    	}
    	if(!registerUserData.getUserPhoneNumber().isEmpty()) {
    		assertTrue(registerFormObj.setPhoneNumber(registerUserData.getUserPhoneNumber()),"Unable to type Phone Number in input field");
    	}
    	assertTrue(registerFormObj.clickOnRegisterButton(),"Unable to click on Register button");
    	assertTrue(registerFormObj.isUserSuccessfullyRegistered(),"Register dialog is not closed after Register button is clicked");
    	assertTrue(verifyLeadInDB(),"Lead not verified in DB");
      
    }
    
    private boolean verifyLeadInDB() {
    	  Cookie cks = getDriver().manage().getCookieNamed("zfs_lead_id");
          Integer lead_id = Integer.parseInt(cks.getValue());

          //Checking created lead source
          //Checking DB record body
          AbstractLead newLead = getEnvironment().getLeadObject(lead_id);
          return registerUserData.getUserEmail().equalsIgnoreCase(newLead.getEmail());
    }
}
