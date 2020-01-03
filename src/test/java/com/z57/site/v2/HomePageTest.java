package com.z57.site.v2;

import com.z57.site.v2.Page;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.z57.site.v2.PageTest;
import com.z57.site.v2.HomePage;
import resources.DBHelperMethods;
import resources.EnvironmentFactory;
import resources.ModuleCacheConstants;
import resources.ModuleCommonCache;

import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import resources.forms.z57.LoginForm;
import resources.alerts.BootstrapModal;
import resources.forms.z57.RegisterForm;
import resources.utility.ActionHelper;
import resources.utility.AutomationLogger;
import org.testng.annotations.Parameters;
import static org.testng.Assert.assertTrue;
import resources.data.z57.RegisterUserData;
import static org.testng.Assert.assertEquals;

public class HomePageTest extends PageTest
{
	private RegisterUserData registerUserData;
    private HomePage page;
    private WebDriver driver;

    public void clearPage(){
        page=null;
    };

    public Page getPage(){
    	
        if(page == null){
            page = new HomePage(getDriver());
            page.setUrl("");
            page.setDriver(getDriver());
            driver = getDriver();
            AutomationLogger.info("Title : "+driver.getTitle());
            AutomationLogger.info("Title : "+driver.getCurrentUrl());
            AutomationLogger.info("Window Size: "+driver.manage().window().getSize());
            System.out.println(driver.getTitle());
            System.out.println(driver.getCurrentUrl());
            System.out.println(driver.manage().window().getSize());
        }
        return page;
    }
    public Page getPage(String pUrl){
        if(page == null){
        	System.out.println("Expected URL: "+pUrl);
        	driver=getDriver();
            page = new HomePage(driver);
			page.setDriver(driver,pUrl);
			AutomationLogger.info("Title : "+driver.getTitle());
            AutomationLogger.info("Title : "+driver.getCurrentUrl());
            AutomationLogger.info("Window Size: "+driver.manage().window().getSize());
            System.out.println("Browser Windows Title: "+driver.getTitle());
            System.out.println("Current URL: "+driver.getCurrentUrl());
            System.out.println("Browser Dimesnions: "+driver.manage().window().getSize());
        }
        return page;
    }
    public void setDriver(WebDriver pWebDriver) {
    	driver = pWebDriver;
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
    	getPage();
    	
    	AutomationLogger.info("Closing Bottstrap modal");
    	closeBootStrapModal();
    	
    	LoginForm loginFormObj = new LoginForm(page.getWebDriver());
//    	assertEquals("Sign In",getPage().getUserMenu().getText());
    	
    	AutomationLogger.info("Clicking On SignIn button");
    	assertTrue(loginFormObj.clickOnSignInButton(),"Sign In button not visible on Home Page");
    	
    	RegisterForm registerFormObj = new RegisterForm(page.getWebDriver());
    	//Clicks on Already Registered
    	assertTrue(registerFormObj.clickOnAlreadyRegistered(),"Already registered link is not visible");

    	assertTrue(loginFormObj.setEmail(EnvironmentFactory.configReader.getPropertyByName("z57_user_v2")),"Unable to type email address");
    	assertTrue(loginFormObj.clickLoginButton(),"Unable to click on Login button");
    
    }
    @Parameters({"dataFile"})
    @Test
    public void testRegisterNewUser(String pFolderLocation) {
    	registerUserData = new RegisterUserData();
    	registerUserData = registerUserData.setRegisterUserData(pFolderLocation);
    	
    	String lUserName = updateName(registerUserData.getUserName());
    	String lEmail = updateEmail(registerUserData.getUserEmail());
    	
    	BootstrapModal bootstrapModalObj = new BootstrapModal(getPage().getWebDriver());

    	if(bootstrapModalObj.checkBootsrapModalIsShown()){
        	bootstrapModalObj.getBootstrapModal().close();
        	bootstrapModalObj.clearBootstrapModal();
        }
    	
    	LoginForm loginFormObj = new LoginForm(page.getWebDriver());
    	assertEquals("Sign In",getPage().getUserMenu().getText());
    	assertTrue(loginFormObj.clickOnSignInButton(),"Sign In button not visible on Home Page");
    	
    	assertTrue(registerLead(lUserName,lEmail,registerUserData.getUserPhoneNumber()), "The dialog didn't disappear after clicking Register button");
    	DBHelperMethods dbHelperMethods = new DBHelperMethods(getEnvironment());
    	assertTrue(dbHelperMethods.verifyLeadInDB(registerUserData.getUserEmail(),getLeadId()),"Lead not verified in DB");
      
    }
   
    @Test
    public void testVerifyHomePage() {
    	getPage();
    	assertTrue(page.isFindMyLocationButtonWorking(), "Find My Location button not working on Home Page"); 
  	
        assertTrue(page.isSellBuyContactImagesAreDisplayed(), "Correct Images are not displayed for Contact/Buy/Sell on home page");
    	
    	assertTrue(page.isBackgroundImageSlidersAreDisplayed(), "Correct Images are not displayed for Background Slider");
    	
    	List<String> list_of_listing_ids_with_no_img = new ArrayList<String>();
    	list_of_listing_ids_with_no_img=page.isFeaturePropertyImagesAreDisplayed();
    	assertTrue(verifyImagesFromDB(list_of_listing_ids_with_no_img), "Correct Images are not displayed for Featured Properties");
    	
    	assertTrue(page.clickOnSliderArrows(),"Slider arrows button not working");

    	List<String> list_of_agent_with_no_img = new ArrayList<String>();
    	list_of_agent_with_no_img=page.isAgentProfilePicDisplayed();
    	assertTrue(verifyImagesFromDB(list_of_agent_with_no_img), "Agent profile picture is not displayed correctly on Home Page");
    	
    }
    
    @Parameters({"page"})
    @Test
    public void testSignUpWithFacebook(String pSignUpPage) {
//    	String lFacebookEmail="propertypulsetest7@gmail.com";
//    	String lFacebookPassword="Mesarim10045";
    	String lFacebookEmail="z57testuser@gmail.com";
    	String lFacebookPassword="Bcsf08m020@";
    	ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.FacebookLeadEmail, lFacebookEmail);
    	getPage();
    	closeBootStrapModal();
    	
    	switch(pSignUpPage) {
    	case "Home":
    		assertEquals("Sign In",getPage().getUserMenu().getText());
    		assertTrue(page.getLoginForm().clickOnSignInButton(),"Sign In button not visible on Home Page");
    		break;
    	case "Home Search":
    		assertTrue(page.getPageHeader().clickOnSearchHomes(),"Unable to click on Home Search menu");
    		break;
    	case "Listing":
    		assertTrue(page.getPageHeader().clickOnFeaturedProperties(),"Unable to click on Featured Properties page.");
    	}
    	
    	String parentWindowHandle = driver.getWindowHandle();
    	assertTrue(page.getRegisterFormNew().isRegisterFormDisplayed(), "Register form is not displayed");
    	assertTrue(page.getLoginForm().clickOnSignUpWithFacebookButton(), "Unable to click on Sign Up with facebook button");
    	
    	for(String windowHandle: driver.getWindowHandles()) {
    		if(!windowHandle.equalsIgnoreCase(parentWindowHandle)) {
    			driver.switchTo().window(windowHandle);
    			assertTrue(page.getLoginForm().typeFacebookEmail(lFacebookEmail), "Unable to type Email on facebook login page.");
    			assertTrue(page.getLoginForm().typeFacebookPassword(lFacebookPassword), "Unable to type Password on facebook login page.");
    			assertTrue(page.getLoginForm().clickOnFacebookLoginButton(), "Unable to click on facebook login button.");
    			driver.switchTo().window(parentWindowHandle);
    			break;
    		}
    	}
    	assertTrue(page.getLoginForm().waitForLoginFormToDisappear(),"Login form didn't disappear");
    	ActionHelper.staticWait(5);
    	ActionHelper.RefreshPage(driver);
    	assertTrue(page.getPageHeader().isLeadLoggedIn(), "Lead is not logged in trough Facebook");
    	

		DBHelperMethods dbHelper = new DBHelperMethods(getEnvironment());
		
		//Verifies if Lead is added in the DB
		assertTrue(dbHelper.verifyLeadByEmailInDB(lFacebookEmail), "User is not added as Lead in PP ->" + lFacebookEmail);
		
		//Verifies Agent has received a email with subject 'You have a new lead'
		//Lead is sent welcome only 1st time. We are using same fb account multiple
		//times so won't be able to verify welcome email
		//assertTrue(dbHelper.verifyEmailIsSentToAgent(lAgent_email, lFacebookEmail),"Unable to sent email 'You have a new lead' to Agent for ->" + lAgent_email);
    }
    
    @Test
    public void testSignInWithValidEmailOnIdx() {
    	//Get the IDX url from 
    	String idxUrl = EnvironmentFactory.configReader.getPropertyByName("z57_standalone_idx");
    	String lZ57_user_v2 = EnvironmentFactory.configReader.getPropertyByName("z57_user_v2");
    	getPage(idxUrl);

    	assertTrue(page.getLoginForm().clickOnIdxSigninButton(),"Sign In button not visible on IDX Page");
    	
    	//Clicks on Already Registered
    	assertTrue(page.getRegisterFormNew().clickOnAlreadyRegisterIdx(),"Already registered link is not visible on IDX Register form");

    	assertTrue(page.getLoginForm().typeEmailIdx(lZ57_user_v2),"Unable to type email address");
    	assertTrue(page.getLoginForm().clickOnIdxLoginButton(),"Unable to click on Login button");
    	assertTrue(page.getLoginForm().isIdxLoginSuccessful(), "Unable to login with credentials: "+lZ57_user_v2);
    }
    
    //All the leads register related test cases will use this method.
    public boolean registerLead(String pName, String pUserEmail, String pPUserPhoneNumber) {
    	RegisterForm registerFormObj = new RegisterForm(driver);
    	assertTrue(registerFormObj.isRegisterFormDisplayed(), "Register form is not displayed..");
    	if(!pName.isEmpty()) {
    		assertTrue(registerFormObj.setName(pName),"Unable to type Name in input field");
    	}
    	if(!pUserEmail.isEmpty()) {
    		assertTrue(registerFormObj.setEmail(pUserEmail),"Unable to type Email in input field");
    	}
    	if(!pPUserPhoneNumber.isEmpty()) {
    		assertTrue(registerFormObj.setPhoneNumber(pPUserPhoneNumber),"Unable to type Phone Number in input field");
    	}
    	assertTrue(registerFormObj.clickOnRegisterButton(),"Unable to click on Register button");
    	return registerFormObj.isUserSuccessfullyRegistered();
    }
    
    
    
 
    
}
