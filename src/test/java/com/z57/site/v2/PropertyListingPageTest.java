package com.z57.site.v2;

import static org.testng.Assert.assertTrue;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.SkipException;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import resources.DBHelperMethods;
import resources.EnvironmentFactory;
import resources.data.z57.EmailListingFormData;
import resources.data.z57.RequestInfoFormData;
import resources.data.z57.ScheduleListingFormData;
import resources.data.z57.SearchFormData;
import resources.utility.ActionHelper;
import resources.utility.AutomationLogger;
import resources.utility.FrameworkConstants;
import resources.utility.Z57ListingConstants;

public class PropertyListingPageTest extends PageTest {

	private PropertyListingPage page;
	private SearchFormData searchFormData;
	private WebDriver driver;

	String lInputSearch = "";
	String lSearchByOption = "";
	String lMinimumValue = "";
	String lMaximumValue = "";
	String lNumberOfBeds = "";
	String lNumberOfBaths = "";
	String lPropertyType = "";
	String lFeaturesAnyOrAll = "";
	String lFeatures = "";
	String lSquareFootage = "";
	String lView = "";
	String lLotSize = "";
	String lStyle = "";
	String lStatus = "";
	String lYearBuilt = "";

	@Override
	public void testTitle() {
		// TODO Auto-generated method stub

	}

	@Override
	public void testHeader() {
		// TODO Auto-generated method stub

	}

	@Override
	public void testBrand() {
		// TODO Auto-generated method stub

	}

	@Override
	public Page getPage() {
		if (page == null) {
			page = new PropertyListingPage(getDriver());
//			page.setDriver(getDriver());
		}
		
		return page;
	}

	public Page getPage(String pUrl) {
		if (page == null) {
			page = new PropertyListingPage(getDriver());
			page.setUrl(pUrl);
			page.setDriver(getDriver());
			driver = getDriver();
		}
		return page;
	}

	@Override
	public void clearPage() {
		// TODO Auto-generated method stub

	}

	private void setSearchParams() {
		lInputSearch = searchFormData.getInputSearch();
		lSearchByOption = searchFormData.getSearchBy();
		lMinimumValue = searchFormData.getMinimumValue();
		lMaximumValue = searchFormData.getMaximumValue();
		lNumberOfBeds = searchFormData.getNumberOfBeds();
		lNumberOfBaths = searchFormData.getNumberOfBaths();
		lPropertyType = searchFormData.getPropertyType();
		lFeaturesAnyOrAll = searchFormData.getFeatureAnyAll();
		lFeatures = searchFormData.getFeatures();
		lSquareFootage = searchFormData.getSquareFotage();
		lView = searchFormData.getView();
		lLotSize = searchFormData.getLotSize();
		lStyle = searchFormData.getStyle();
		lStatus = searchFormData.getStatus();
		lYearBuilt = searchFormData.getYearBuilt();
	}

	@Parameters({ "dataFile" })
	@Test
	public void testVerificationOfPropertyListing(String pFileLocation) {
		
		searchFormData = new SearchFormData(pFileLocation).getSearchFormData();
//		searchFormData.setSearchFormData(pFileLocation);
		setSearchParams();
		PropertyListingPage propertyListingObj = new PropertyListingPage(getPage().getWebDriver());
		SoftAssert softAssert = new SoftAssert();
		if (page.getCurrentUrl().contains("/listings/")) {
			// page.getWebDriver().navigate().to("http://robinsoldwisch-13878.sites.z57.com/idx/listings/cws/1098/170017412/5326-grand-del-mar-place-place-san-diego-san-diego-county-ca-92130");

			softAssert.assertTrue(page.getPropertyTitleFromTheHeader().contains(lInputSearch),"Input Search criteria does not meets the address results");

			if (!lMinimumValue.isEmpty()) {
				int propertyPriceFromPage = page.getPropertValueFromHeader();
				int minimum_value = Integer.parseInt(lMinimumValue);
				assertTrue(propertyPriceFromPage >= minimum_value,"Property Value doesn't matches the minimum value set");
			}

			if (!lMaximumValue.isEmpty()) {
				assertTrue(page.getPropertValueFromHeader() <= Integer.parseInt(lMaximumValue),"Property Value doesn't matches the minimum value set");
			}

			if (!lNumberOfBeds.isEmpty()) {
				assertTrue(page.getNumberOfBeds() >= Integer.parseInt(lNumberOfBeds),"Number of BEDS doesn't matches the value set");
			}

			if (!lNumberOfBaths.isEmpty()) {
				assertTrue(page.getNumberOfBaths() >= Integer.parseInt(lNumberOfBaths),"Number of BATHS doesn't matches the value set");

			}

			if (!lLotSize.isEmpty()) {
				lLotSize = lLotSize.replace(",", "");
				lLotSize = lLotSize.split("\\+")[0];
				assertTrue(page.getLotSize() >= Integer.parseInt(lLotSize), "Lot size doesn't matches the value set");

			}

			if (!lFeatures.isEmpty()) {
				String[] featuresArray = lFeatures.split(",");
				for (String lFeat : featuresArray) {
					boolean isFeaturePresent = page.propertyInteriorVerification(lFeat);
					assertTrue(isFeaturePresent, "Feature is not present in the listing");
				}

			}
			if (!lStatus.isEmpty()) {
				assertTrue(page.getPropertyStatusFromHeader().equalsIgnoreCase(lStatus), "Status is mismatched");
			}

			assertTrue(page.isFeatureTabExpanded(), "Features tab is not expanded");

			if (!lStyle.isEmpty()) {
				assertTrue(page.verifyPropertyStyle(lStyle), "Style is not presented in the listing");

			}
			if (!lPropertyType.isEmpty()) {
				assertTrue(page.verifyPropertyType(lPropertyType), "Building is not presented in the listing");

			}
			// All the validations and actions on map tab.
			assertTrue(page.clickOnMapBar(), "Map button on Navigation bar is not visible");
			assertTrue(page.isGoogleMapDisplayed(), "Google map is not displayed");
			softAssert.assertTrue(page.isPinDsiplayedOnGoogleMaps(), "Property PIN is not visible on the Google Maps");

			// All the validations on Community stats
			assertTrue(page.clickOnCommunityStats(), "Community Stats on Navigation bar is not visible");
			assertTrue(page.isCommunityStatsDisplayed(), "Community Stats is not displayed");
			assertTrue(page.isPopulationDemographicChartVisible(),"Community Stats | Population Demographic chart is not displayed");
			assertTrue(page.isPopulationRangeChartVisible(),"Community Stats | Population Range chart is not displayed");
			assertTrue(page.isHouseholdsChartVisible(), "Community Stats | Households chart is not displayed");
			assertTrue(page.isEducationLevelChartVisible(), "Community Stats | Education Level chart is not displayed");
			assertTrue(page.isEmploymentChartVisible(), "Community Stats | Employment chart is not displayed");
			assertTrue(page.isCrimeChartVisible(), "Community Stats | Crime chart is not displayed");
			assertTrue(page.isClimateChartVisible(), "Community Stats | Climate chart is not displayed");
			assertTrue(page.isAreaRankingChartVisible(), "Community Stats | Area Ranking chart is not displayed");

			// All the validations on School Maps
			assertTrue(page.clickOnSchools(), "Schools on Navigation bar is not visible");
			assertTrue(page.isSchoolMapsDisplayed(), "Schools map and information is not displayed");
			softAssert.assertTrue(page.verifySchoolPins(), "Number of schools and pins on map count mismatched");

			// All the validations on POI
			assertTrue(page.clickOnWhatsNearBy(), "Whats near by on Navigation bar is not visible");
			assertTrue(page.isWhatsNearbyDisplayed(), "Whats near by is not displayed");
			softAssert.assertTrue(page.verifyPOIPins(), "Number of POI and pins on map count mismatched");
		} else {
			throw new SkipException("No property found on search criteria.");
		}
	}
	@Parameters({ "dataFile","changeEmail" })
	@Test
	public void testEmailListing(String pFileName, @Optional Boolean changeEmail) {
		//Initiliazing data 
		EmailListingFormData emailListingFormData = new EmailListingFormData(pFileName).getEmailListingData();
		
		String lLeadName = updateName(emailListingFormData.getLeadName());
		String lLeadEmail = updateEmail(emailListingFormData.getLeadEmail());
		String lLeadPhone = emailListingFormData.getLeadPhoneNumber();
		String lR1Name = updateName(emailListingFormData.getRecipientOneName());
		String lR1Email = updateEmail(emailListingFormData.getRecipientOneEmail());
		
		
		getPage(Z57ListingConstants.z57_listing);
		
		String lAgent_email = EnvironmentFactory.configReader.getPropertyByName("z57_propertypulse_user_email");
		
		closeBootStrapModal();
		
		assertTrue(page.isPropertyTitleVisible(), "Property Title is not visible");
		
		PageHeader pageHeader = new PageHeader(driver);
		
		boolean isLeadLoggedIn=pageHeader.isLeadLoggedIn();
		
		assertTrue(page.clickOnEmailListing(),"Unable to click on Email Listing button");
		assertTrue(page.getEmailListingForm().isListingEmailModalVisible(),"Email Listing Modal is not visible");

		String lSenderEmail = lLeadEmail;
		
		if(isLeadLoggedIn) { 
			//Verify the name of the lead is in respective fields 
			lSenderEmail = lLeadEmail = EnvironmentFactory.configReader.getPropertyByName("z57_user_v2");
			assertTrue(page.getEmailListingForm().getLeadName().isEmpty(), "Lead is logged in but No Name in Email Listing form" );
			assertTrue(page.getEmailListingForm().getLeadEmail().isEmpty(), "Lead is logged in but No Name in Email Listing form" );

		}else {
			assertTrue(page.getEmailListingForm().typeLeadName(lLeadName),"Unable to type name in Lead Name field");

			//#5895
			if ( changeEmail!=null && changeEmail )
			{
				lSenderEmail = "changed_"+lLeadEmail;
			}

			assertTrue(page.getEmailListingForm().typeEmailAddress(lSenderEmail),"Unable to type email in Lead email field");
			if(!lLeadPhone.isEmpty()) {
				assertTrue(page.getEmailListingForm().typePhoneNumber(lLeadPhone),"Unable to type phone in Lead phone number field"); 
			} 
		}

		assertTrue(page.getEmailListingForm().typeR1Name(lR1Name),"Unable to write the name oof Recipient 1");
		assertTrue(page.getEmailListingForm().typeR1Email(lR1Email),"Unable to write the email of Recepient 1");
		
		String lR2Name = updateName(emailListingFormData.getRecipientTwoName());
		String lR2Email = updateEmail(emailListingFormData.getRecipientTwoEmail());
		
		assertTrue(page.getEmailListingForm().typeR2Name(lR2Name),"Unable to write the name oof Recipient 2");
		assertTrue(page.getEmailListingForm().typeR2Email(lR2Email),"Unable to write the email of Recepient 2");
		
		ActionHelper.staticWait(5);
		
		assertTrue(page.getEmailListingForm().clickOnSendButton(),"Unable to click on Send button");
		assertTrue(page.getEmailListingForm().isEmailSent(),"After clicking Send button 'Email this Listing' modal is still visible");

		DBHelperMethods dbHelper = new DBHelperMethods(getEnvironment());
		
		assertTrue(dbHelper.verifyLeadByEmailInDB(lLeadEmail), "User is not added as Lead in PP ->" + lLeadEmail);
		assertTrue(dbHelper.verifyLeadByEmailInDB(lR1Email), "Recipient1 is not added as Lead in PP ->" + lR1Email);
		assertTrue(dbHelper.verifyLeadByEmailInDB(lR2Email), "Recipient2 is not added as Lead in PP ->" + lR2Email);

		// Verifies the email has been sent on respective email addresses.
		if(!isLeadLoggedIn) {
			assertTrue(dbHelper.verifyEmailIsSentToLead(lLeadEmail, FrameworkConstants.ThanksForConnecting),"Unable to sent 'Thanks for connecting' email to Sender");
			assertTrue(dbHelper.verifyEmailIsSentToAgent(lAgent_email, lLeadEmail),"Unable to sent email 'You have a New lead' to Agent for ->" + lLeadEmail);

		}
		assertTrue(dbHelper.verifyEmailIsSent(lR1Email, FrameworkConstants.CheckoutThisListing),"Unable to sent email to Recipient1");
		assertTrue(dbHelper.verifyEmailIsSent(lR2Email, FrameworkConstants.CheckoutThisListing),"Unable to sent email to Recipient2");

		//#5895
		//According to Logan #5671 email should not be sent to R1 and R2
//		assertTrue(dbHelper.verifyEmailIsSentToLead(lR1Email, FrameworkConstants.ThanksForConnecting),"Unable to sent 'Thanks for connecting' email to Recipient1");
//		assertTrue(dbHelper.verifyEmailIsSentToLead(lR2Email, FrameworkConstants.ThanksForConnecting),"Unable to sent 'Thanks for connecting' email to Recipient2");

		if ( !lSenderEmail.equals(lLeadEmail) )
		{
			assertTrue(dbHelper.verifyEmailIsSentToLead(lSenderEmail, FrameworkConstants.ThanksForConnecting),"Unable to sent 'Thanks for connecting' email to Changed Sender");
		}

		assertTrue(dbHelper.verifyEmailIsSentToAgent(lAgent_email, lR1Email),"Unable to sent email 'You have a New lead' to Agent for ->" + lR1Email);
		assertTrue(dbHelper.verifyEmailIsSentToAgent(lAgent_email, lR2Email),"Unable to sent email 'You have a New lead' to Agent for ->" + lR2Email);

	}
	
	@Parameters({ "dataFile" })
	@Test
	public void testRequestInfo(String pFileName) {
		//Initiliazing data 
		RequestInfoFormData requestInfoFormData = new RequestInfoFormData(pFileName).getRequestInfoFormData();
				
		String lLeadName = updateName(requestInfoFormData.getLeadName());
		String lLeadEmail =updateEmail(requestInfoFormData.getLeadEmail());
		String lLeadPhoneNumber =requestInfoFormData.getLeadPhoneNumber();
		String lComments = requestInfoFormData.getComments();
		String lListingUrl = Z57ListingConstants.z57_listing;
		
		if(!lListingUrl.isEmpty()) {
			getPage(lListingUrl);
		}else {
			getPage();
		}
		
		closeBootStrapModal();
		
		assertTrue(page.isPropertyTitleVisible(), "Property Title is not visible");
		
		PageHeader pageHeader = new PageHeader(driver);
		
		boolean isLeadLoggedIn=pageHeader.isLeadLoggedIn();
		
		assertTrue(page.clickOnRequestInfo(), "Unable to click on Request Info button.");
		
		if(isLeadLoggedIn) { 
			//Verify the name of the lead is in respective fields 
			lLeadEmail = EnvironmentFactory.configReader.getPropertyByName("z57_user_v2");
			lLeadName = EnvironmentFactory.configReader.getPropertyByName("z57_user_name_v2");
			assertTrue(page.getRequestInfoForm().getLeadName().isEmpty(), "Lead is logged in but No Name in Request Info form" );
			assertTrue(page.getRequestInfoForm().getLeadEmail().isEmpty(), "Lead is logged in but No Name in Request Info form" );

		}else {
			assertTrue(page.getRequestInfoForm().typeLeadName(lLeadName),"Unable to type name in Lead Name field");
			assertTrue(page.getRequestInfoForm().typeLeadEmail(lLeadEmail),"Unable to type email in Lead email field"); 
			if(!lLeadPhoneNumber.isEmpty()) {
				assertTrue(page.getRequestInfoForm().typeLeadPhoneNumber(lLeadPhoneNumber),"Unable to type phone in Lead phone number field"); 
			} 
			if(!lComments.isEmpty()) {
				assertTrue(page.getRequestInfoForm().typeComments(lComments),"Unable to type comments in the field"); 
			} 
		}
		
		assertTrue(page.getRequestInfoForm().clickOnSaveButton(),"Unable to click on Save button");
		assertTrue(page.getRequestInfoForm().isRequestInfoSent(),"After clicking Save button 'Request Info' modal is still visible");
		
		String lAgent_email = EnvironmentFactory.configReader.getPropertyByName("z57_propertypulse_user_email");

		DBHelperMethods dbHelper = new DBHelperMethods(getEnvironment());
		//Verifies if Lead is added in the DB
		assertTrue(dbHelper.verifyLeadByEmailInDB(lLeadEmail), "User is not added as Lead in PP ->" + lLeadEmail);
		
		//Verifies Agent has received a email with subject 'You have a new lead'
		if(!isLeadLoggedIn) {
			assertTrue(dbHelper.verifyEmailIsSentToAgent(lAgent_email, lLeadEmail),"Unable to sent email 'You have a new lead' to Agent for ->" + lLeadEmail);
			//Verify welcome email is sent to Lead
            assertTrue(dbHelper.verifyEmailIsSentToLead(lLeadEmail, FrameworkConstants.ThanksForConnecting),"Unable to sent 'Thanks for connecting' email to Sender");
		}	
		//Verifies Agent has received the email with subject 'Inquired about Listing'
		assertTrue(dbHelper.verifyEmailIsSentToAgent(lAgent_email,lLeadEmail,lLeadName+" "+FrameworkConstants.InquiredAboutListing),"Unable to sent email [Information is on the Way] to Agent");

		// Verifies the email has been sent to lead with subject 'Information is on the way!'.
		assertTrue(dbHelper.verifyEmailIsSentToLead(lLeadEmail,FrameworkConstants.InformationIsOnTheWay),"Unable to sent email to Lead");
		
	}
	
	@Parameters({ "dataFile" })
	@Test
	public void testScheduleShowing(String pFileName) {
		//Initiliazing data 
		ScheduleListingFormData scheduleListingFormData = new ScheduleListingFormData(pFileName).getScheduleListingFormData();
				
		String lLeadName = updateName(scheduleListingFormData.getLeadName());
		String lLeadEmail =updateEmail(scheduleListingFormData.getLeadEmail());
		String lLeadPhoneNumber =scheduleListingFormData.getLeadPhoneNumber();
		String lComments = scheduleListingFormData.getComments();
		String lListingUrl = Z57ListingConstants.z57_listing;
		
		if(!lListingUrl.isEmpty()) {
			getPage(lListingUrl);
		}else {
			getPage();
		}
		
		closeBootStrapModal();
		
		assertTrue(page.isPropertyTitleVisible(), "Property Title is not visible");
		
		PageHeader pageHeader = new PageHeader(driver);
		
		boolean isLeadLoggedIn=pageHeader.isLeadLoggedIn();
		
		assertTrue(page.clickOnSheduleShowing(), "Unable to click on Schedule a showing button.");
		
		if(isLeadLoggedIn) { 
			//Verify the name of the lead is in respective fields 
			lLeadEmail = EnvironmentFactory.configReader.getPropertyByName("z57_user_v2");
			lLeadName = EnvironmentFactory.configReader.getPropertyByName("z57_user_name_v2");
			assertTrue(page.getRequestInfoForm().getLeadName().isEmpty(), "Lead is logged in but No Name in Request Info form" );
			assertTrue(page.getRequestInfoForm().getLeadEmail().isEmpty(), "Lead is logged in but No Name in Request Info form" );

		}else {
			assertTrue(page.getScheduleListingForm().typeLeadName(lLeadName),"Unable to type name in Lead Name field");
			assertTrue(page.getScheduleListingForm().typeLeadEmail(lLeadEmail),"Unable to type email in Lead email field"); 
			if(!lLeadPhoneNumber.isEmpty()) {
				assertTrue(page.getScheduleListingForm().typeLeadPhoneNumber(lLeadPhoneNumber),"Unable to type phone in Lead phone number field"); 
			} 
			if(!lComments.isEmpty()) {
				assertTrue(page.getScheduleListingForm().typeComments(lComments),"Unable to type comments in the field"); 
			} 
		}
		
		assertTrue(page.getScheduleListingForm().clickOnSaveButton(),"Unable to click on Save button");
		assertTrue(page.getScheduleListingForm().isScheduleShowingRequestSent(),"After clicking Save button 'Schedule Showing' modal is still visible");
		
		String lAgent_email = EnvironmentFactory.configReader.getPropertyByName("z57_propertypulse_user_email");

		DBHelperMethods dbHelper = new DBHelperMethods(getEnvironment());
		//Verifies if Lead is added in the DB
		assertTrue(dbHelper.verifyLeadByEmailInDB(lLeadEmail), "User is not added as Lead in PP ->" + lLeadEmail);
		
		//Verifies Agent has received a email with subject 'You have a new lead'
		if(!isLeadLoggedIn) {
			assertTrue(dbHelper.verifyEmailIsSentToAgent(lAgent_email, lLeadEmail),"Unable to sent email 'You have a new lead' to Agent for ->" + lLeadEmail);
			//Verify welcome email is sent to Lead
            assertTrue(dbHelper.verifyEmailIsSentToLead(lLeadEmail, FrameworkConstants.ThanksForConnecting),"Unable to sent 'Thanks for connecting' email to Sender");
		}	
		//Verifies Agent has received the email with subject 'Inquired about A Showing'
		assertTrue(dbHelper.verifyEmailIsSentToAgent(lAgent_email,lLeadEmail,lLeadName+" "+FrameworkConstants.InquiredAboutShowing),"Unable to sent email to Agent. Subject [Inquired About A Showing]");

		// Verifies the email has been sent to lead with subject 'Information is on the way!'.
		assertTrue(dbHelper.verifyEmailIsSentToLead(lLeadEmail,FrameworkConstants.InformationIsOnTheWay),"Unable to sent email to Lead");
		
	}
	
	@Parameters({"dataFile"})
	@Test
	public void testListingDetailsFavoriteListing(String pDataFile) {
		AutomationLogger.startTestCase("Home search Favorite Listing");
		JSONObject lJsonDataObj = getDataFile(pDataFile);
		getPage(Z57ListingConstants.z57_listing);
		
		PageHeader pageHeader = new PageHeader(driver);
		boolean isLeadLoggedIn=pageHeader.isLeadLoggedIn();
		
		assertTrue(page.clickOnFavoriteButton(), "Unable to click on Favorite button...");
		
		if(!isLeadLoggedIn) {
			registrationFormFill(lJsonDataObj);
		}else {
			ActionHelper.RefreshPage(driver);
		}
		assertTrue(page.isListingMarkedFavorite(), "Listing is not marked as Favorite...");
		if(isLeadLoggedIn) {
			assertTrue(page.clickOnRemoveFavoriteButton(), "Unable to unfavorite the listing...");
			ActionHelper.staticWait(5);
		}
		AutomationLogger.endTestCase();
	}
	
	@Parameters({"dataFile"})
	@Test
	public void testCaptureLeadFromListingDetailPage(String pDataFile) {
		JSONObject lJsonDataObj = getDataFile(pDataFile);
		getPage(Z57ListingConstants.z57_listing);
		ActionHelper.RefreshPage(driver);
//		captureLead(lJsonDataObj.optString("user_name"), lJsonDataObj.optString("user_email"), lJsonDataObj.optString("user_phone_number"), lJsonDataObj.optString("comments"));
		registrationFormFill(lJsonDataObj);
	}
	@Parameters
	public void registrationFormFill(JSONObject lJsonDataObj) {
		// TODO Auto-generated method stub
		HomePageTest homePageTest = new HomePageTest();
		homePageTest.setDriver(driver);
		String lName = updateName(lJsonDataObj.optString("user_name"));
		String lEmail = updateEmail(lJsonDataObj.optString("user_email"));
		homePageTest.registerLead(lName,lEmail, lJsonDataObj.optString("user_phone_number"));
		DBHelperMethods dbHelperMethods = new DBHelperMethods(getEnvironment());
    	assertTrue(dbHelperMethods.verifyLeadInDB(lEmail,getLeadId()),"Lead not verified in DB");
       	assertTrue(dbHelperMethods.verifyEmailIsSentToLead(lEmail, FrameworkConstants.ThanksForRegistering),"Unable to sent email to Lead with subject "+FrameworkConstants.ThanksForRegistering+"\n Lead Email: "+lEmail);
    	assertTrue(dbHelperMethods.verifyEmailIsSentToAgent(EnvironmentFactory.configReader.getPropertyByName("z57_propertypulse_user_email"), lEmail, FrameworkConstants.YouHaveANewLead), "Unable to sent email to Agent with subject "+FrameworkConstants.YouHaveANewLead);

	}
}
