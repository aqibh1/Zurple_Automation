package com.z57.site.v2;
import com.z57.site.v2.Page;
import resources.ConfigReader;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.z57.site.v2.PageTest;
import com.z57.site.v2.HomePage;
import resources.DBHelperMethods;
import resources.EnvironmentFactory;

import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import resources.forms.z57.LoginForm;
import resources.alerts.BootstrapModal;
import resources.forms.z57.RegisterForm;
import resources.orm.hibernate.models.z57.ListingImages;
import resources.utility.AutomationLogger;
import resources.utility.FrameworkConstants;

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
            System.out.println(driver.getTitle());
            System.out.println(driver.getCurrentUrl());
            
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
    	
    	assertTrue(registerLead(registerUserData.getUserName(),registerUserData.getUserEmail(),registerUserData.getUserPhoneNumber()), "The dialog didn't disappear after clicking Register button");
    	DBHelperMethods dbHelperMethods = new DBHelperMethods(getEnvironment());
    	assertTrue(dbHelperMethods.verifyLeadInDB(registerUserData.getUserEmail(),getLeadId()),"Lead not verified in DB");
      
    }
   
    @Test
    public void testVerifyHomePage() {
    	getPage();
    	assertTrue(page.isFindMyLocationButtonWorking(), "Find My Location button not working on Home Page"); 
//    	assertTrue(page.typeInputAndSelect("SAN DIEGO","SAN DIEGO, CA"), "Unable to type in input field");
//    	assertTrue(page.clickSearchButton(), "Unable to click search button");
//    	
//    	HomeSearchPage homeSearchPage = new HomeSearchPage(driver);
//    	assertTrue(homeSearchPage.isHomeSearchPage(), "Page is not redirected to Home Search Page");
//    	homeSearchPage.goBack();
//    	
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
    	String lFacebookEmail="propertypulsetest7@gmail.com";
    	String lFacebookPassword="Mesarim10045";
    	String lAgent_email = EnvironmentFactory.configReader.getPropertyByName("z57_propertypulse_user_email");
    	
    	closeBootStrapModal();
    	getPage();
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
    			assertTrue(page.getLoginForm().clickOnFacbookLoginButton(), "Unable to click on facebook login button.");
    			driver.switchTo().window(parentWindowHandle);
    		}
    	}
    	assertTrue(page.getLoginForm().waitForLoginFormToDisappear(),"Login form didn't disappear");
    	assertTrue(page.getPageHeader().isLeadLoggedIn(), "Lead is not logged in trough Facebook");
    	

		DBHelperMethods dbHelper = new DBHelperMethods(getEnvironment());
		
		//Verifies if Lead is added in the DB
		assertTrue(dbHelper.verifyLeadByEmailInDB(lFacebookEmail), "User is not added as Lead in PP ->" + lFacebookEmail);
		
		//Verifies Agent has received a email with subject 'You have a new lead'
		assertTrue(dbHelper.verifyEmailIsSentToAgent(lAgent_email, lFacebookEmail),"Unable to sent email 'You have a new lead' to Agent for ->" + lAgent_email);
    }
    
    //All the leads register related test cases will use this method.
    private boolean registerLead(String pName, String pUserEmail, String pPUserPhoneNumber) {
    	RegisterForm registerFormObj = new RegisterForm(page.getWebDriver());
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
