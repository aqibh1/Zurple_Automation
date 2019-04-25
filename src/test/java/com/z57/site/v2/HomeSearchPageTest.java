package com.z57.site.v2;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import resources.DBHelperMethods;
import resources.EnvironmentFactory;
import resources.blocks.z57.Pagination;
import resources.classes.SearchResult;
import resources.data.z57.EmailListingFormData;
import resources.data.z57.SaveSearchFormData;
import resources.data.z57.SearchFormData;
import resources.utility.FrameworkConstants;

public class HomeSearchPageTest extends PageTest{

	private HomeSearchPage page;
	private SearchFormData searchFormData;	
	WebDriver driver;


	@Override
	public void testTitle() {
		assertEquals("Search Local Properties for Sale | zengtest1.us", getPage().getTitle());

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
		if(page == null){
			driver =getDriver();
			page = new HomeSearchPage(driver,this.source_in_url);
		}
		return page;
	}
	public Page getPage(String pUrl) {
		if(page == null) {	
			page = new HomeSearchPage(getDriver());
			page.setUrl(pUrl);
			page.setDriver(getDriver());
			driver=getDriver();
		}
		return page;
	}

	@Override
	public void clearPage() {
		// TODO Auto-generated method stub
	}



	@Test
	@Parameters({"dataFile"})
	public void testSearchByDifferentDataSet(String pFolderLocation) {
		searchFormData = new SearchFormData(pFolderLocation).getSearchFormData();
		
		HomePage homePageObj = new HomePage(getPage().getWebDriver());
		homePageObj.mouseoverHomeSearch();
		homePageObj.clickOnSearchHomes();
		
		searchHomes();
		
		SearchResultsPage searchResultObj = new SearchResultsPage();
		ArrayList<SearchResult> searchResultsList =searchResultObj.getSearchResultsBlock(page.getWebDriver()).getSearchResultsList();
		if(searchResultsList.size()>0) {
			int random = (int)(Math.random() * (searchResultsList.size()-1) + 0);
			String goToListing = searchResultsList.get(random).getUrl();
			System.out.println(goToListing);
			page.getWebDriver().navigate().to(goToListing);

			PropertyListingPage propListingObj = new PropertyListingPage(page.getWebDriver());

			assertEquals(searchResultsList.get(random).getTitle(), propListingObj.getPropertyTitleFromTheHeader(),"Could not click on requested property");
		}else {
			assertEquals(searchResultObj.getPropertiesCount().getText(),"Nothing Found","Expecting 'Nothing found'");
		}

		
	}
	
	@Test
	@Parameters({"dataFile"})
	public void testSearchIdxHomes(String pFolderLocation) {
		//Get the IDX url from 
		String idxUrl = EnvironmentFactory.configReader.getPropertyByName("z57_standalone_idx");
		getPage();
		
		searchFormData= new SearchFormData(pFolderLocation).getSearchFormData();
		
		String lInputSearch=searchFormData.getInputSearch();
		String lSearchByOption=searchFormData.getSearchBy();
		String lMinimumValue=searchFormData.getMaximumValue();
		String lMaximumValue=searchFormData.getMaximumValue();
		String lNumberOfBeds=searchFormData.getNumberOfBeds();
		String lNumberOfBaths=searchFormData.getNumberOfBaths();
		
		//Select the search by option from dropdown
		assertTrue(page.getSearchForm().clickOnSearchByOption(lSearchByOption), "Could not click on Search By element as its not visible.");

		if(lSearchByOption.equalsIgnoreCase("zip") || lSearchByOption.equalsIgnoreCase("city")) {
			//Enters the data in input field and selects from drop down list
			assertTrue(page.getSearchForm().typeAndSelectZipCity(lInputSearch), "Input field on Home Search is not visible");
		}else if(lSearchByOption.equalsIgnoreCase("address")) {
			assertTrue(page.getSearchForm().typeAddress(lInputSearch), "Input field on Home Search is not visible");
		}else if(lSearchByOption.equalsIgnoreCase("neighborhood")) {
			assertTrue(page.getSearchForm().typeAndSelectNeighborhood(lInputSearch), "Input field on Home Search is not visible");

		}
		else {
			assertTrue(page.getSearchForm().typeMLS(lInputSearch), "Input field on Home Search is not visible");
		}
		//Clicks and select from Minimum price
		if(!lMinimumValue.isEmpty()) {
			assertTrue(page.getSearchForm().clickOnPriceLowOptionIdx(lMinimumValue), "Unable to select minimum price from drop down");
		}

		//Clicks and selects from Maximum price
		if(!lMaximumValue.isEmpty()) {
			assertTrue(page.getSearchForm().clickOnPriceMaxOptionIdx(lMaximumValue), "Unable to select maximum price from drop down");
		}

		//Clicks and selects number of beds option
		if(!lNumberOfBeds.isEmpty()) {
			assertTrue(page.getSearchForm().clickOnBedsOptionIdx(lNumberOfBeds), "Unable to select number of beds from drop down");
		}
		//Clicks and select number of baths option
		if(!lNumberOfBaths.isEmpty()) {
			assertTrue(page.getSearchForm().clickOnBathsOptionIdx(lNumberOfBaths), "Unable to select number of baths from drop down");
		}
		assertTrue(page.getSearchForm().clickOnSearchButton(),"Search Button on Home search screen is not visible");
		
		SearchResultsPage searchResultObj = new SearchResultsPage();
		ArrayList<SearchResult> searchResultsList =searchResultObj.getIDXSearchResultsBlock(driver).getIdxSearchResults();
		if(searchResultsList.size()>0) {
			int random = (int)(Math.random() * (searchResultsList.size()-1) + 0);
			String goToListing = searchResultsList.get(random).getUrl();
			System.out.println(goToListing);
			page.getWebDriver().navigate().to(goToListing);

		}else {
			assertEquals(searchResultObj.getPropertiesCount().getText(),"Nothing Found","Expecting 'Nothing found'");
		}

		
	}
	
	private void searchHomes() {
		String lInputSearch=searchFormData.getInputSearch();
		String lSearchByOption=searchFormData.getSearchBy();
		String lMinimumValue=searchFormData.getMaximumValue();
		String lMaximumValue=searchFormData.getMaximumValue();
		String lNumberOfBeds=searchFormData.getNumberOfBeds();
		String lNumberOfBaths=searchFormData.getNumberOfBaths();
		String lPropertyType=searchFormData.getPropertyType();
		String lFeaturesAnyOrAll=searchFormData.getFeatureAnyAll();
		String lFeatures=searchFormData.getFeatures();
		String lSquareFootage=searchFormData.getSquareFotage();
		String lView=searchFormData.getView();
		String lLotSize=searchFormData.getLotSize();
		String lStyle=searchFormData.getStyle();
		String lStatus=searchFormData.getStatus();
		String lYearBuilt=searchFormData.getYearBuilt();
		
		//Select the search by option from dropdown
		assertTrue(page.getSearchForm().clickOnSearchByOption(lSearchByOption), "Could not click on Search By element as its not visible.");

		if(lSearchByOption.equalsIgnoreCase("zip") || lSearchByOption.equalsIgnoreCase("city")) {
			//Enters the data in input field and selects from drop down list
			assertTrue(page.getSearchForm().typeInputAndSelect(lInputSearch), "Input field on Home Search is not visible");
		}else if(lSearchByOption.equalsIgnoreCase("address")) {
			assertTrue(page.getSearchForm().typeAddress(lInputSearch), "Input field on Home Search is not visible");
		}else if(lSearchByOption.equalsIgnoreCase("neighborhood")) {
			assertTrue(page.getSearchForm().typeAndSelectNeighborhood(lInputSearch), "Input field on Home Search is not visible");

		}
		else {
			assertTrue(page.getSearchForm().typeMLS(lInputSearch), "Input field on Home Search is not visible");
		}
		//Clicks and select from Minimum price
		if(!lMinimumValue.isEmpty()) {
			assertTrue(page.getSearchForm().clickOnPriceLowOption(lMinimumValue), "Unable to select minimum price from drop down");
		}

		//Clicks and selects from Maximum price
		if(!lMaximumValue.isEmpty()) {
			assertTrue(page.getSearchForm().clickOnPriceMaxOption(lMaximumValue), "Unable to select maximum price from drop down");
		}

		//Clicks and selects number of beds option
		if(!lNumberOfBeds.isEmpty()) {
			assertTrue(page.getSearchForm().clickOnBedsOption(lNumberOfBeds), "Unable to select number of beds from drop down");
		}
		//Clicks and select number of baths option
		if(!lNumberOfBaths.isEmpty()) {
			assertTrue(page.getSearchForm().clickOnBathsOption(lNumberOfBaths), "Unable to select number of baths from drop down");
		}
		//If any of the advance filter is not empty than it will click expand button and clicks and selects other filters
		if(!lPropertyType.isEmpty() || !lFeaturesAnyOrAll.isEmpty() || !lFeatures.isEmpty() || !lSquareFootage.isEmpty() ||
				!lView.isEmpty() || !lLotSize.isEmpty() || !lStyle.isEmpty() || !lStatus.isEmpty() || !lYearBuilt.isEmpty()) {

			//Clicks on advance search filter button
			assertTrue(page.getSearchForm().clickOnExpandSearchButton(), "Unable to click on expand search button");

			//Clicks and Selects Property Types
			//Multiple Property Types will be comma separated
			if(!lPropertyType.isEmpty()) {
				String[] lPropertyTypeArray = lPropertyType.split(",");
				for(String propertyType: lPropertyTypeArray) {
					assertTrue(page.getSearchForm().typeAndSelectPropertyType(propertyType), "Unable to select property type");
				}
			}

			//Selects any/all feature option from drop down
			//			assertTrue(page.getSearchForm().selectFeature(lFeaturesAnyOrAll), "Unable to select any or all feature type");

			//Selects features
			if(!lFeatures.isEmpty()) {
				assertTrue(page.getSearchForm().clickAndSelectFeature(lFeatures), "Unable to select any or all feature type");
			}

			//Selects square footage from drop down
			if(!lSquareFootage.isEmpty()) {
				assertTrue(page.getSearchForm().clickAndSelecctSquareFootage(lSquareFootage), "Unable to select square footage from drop down");
			}
			//Selects the view
			if(!lView.isEmpty()) {
				String [] lViewsArray =lView.split(",");
				for (String view: lViewsArray) {
					assertTrue(page.getSearchForm().clickAndSelectView(view), "Unable to select view from drop down "+view);
				}
			}
			//Selects the Lot size
			if(!lLotSize.isEmpty()) {
				assertTrue(page.getSearchForm().clickAndSelectLotSize(lLotSize), "Unable to select Lot size from drop down");
			}
			//Selects the Style from dropdown
			if(!lStyle.isEmpty()) {
				String [] lStyleArray =lStyle.split(",");
				for (String style: lStyleArray) {
					assertTrue(page.getSearchForm().clickAndSelectStyle(style), "Unable to select style from drop down");
				}
			}
			//Selects the status from dropdown
			if(!lStatus.isEmpty()) {
				String [] lStatusArray =lStatus.split(",");
				for (String status: lStatusArray) {
					assertTrue(page.getSearchForm().clickAndSelectStatus(status), "Unable to select Status from drop down");
				}
			}
			//Selects the year built
			if(!lYearBuilt.isEmpty()) {
				assertTrue(page.getSearchForm().clickAndSelectYear(lYearBuilt), "Unable to select Year built from drop down");
			}

		}	

		assertTrue(page.getSearchForm().clickOnSearchButton(),"Search Button on Home search screen is not visible");

		
	}
	
	@Test
	@Parameters({"dataFile","dataFile2","changeEmail"})
	public void testEmailSearch(String pDataFile1, String pDataFile2, Boolean changeEmail) {
		getPage("/idx");
		
		searchFormData = new SearchFormData(pDataFile1).getSearchFormData();
		EmailListingFormData emailListingFormData = new EmailListingFormData(pDataFile2).getEmailListingData();
		
		String lLeadName = updateName(emailListingFormData.getLeadName());
		String lLeadEmail = updateEmail(emailListingFormData.getLeadEmail());
		String lLeadPhone = emailListingFormData.getLeadPhoneNumber();
		String lR1Name = updateName(emailListingFormData.getRecipientOneName());
		String lR1Email = updateEmail(emailListingFormData.getRecipientOneEmail());
		String lR2Name = updateName(emailListingFormData.getRecipientTwoName());
		String lR2Email = updateEmail(emailListingFormData.getRecipientTwoEmail());
		
		assertTrue(page.isHomeSearchPage(), "Home Search page is not visible");
		
		PageHeader pageHeader = new PageHeader(driver);
		
		boolean isLeadLoggedIn=pageHeader.isLeadLoggedIn();
		
		searchHomes();
		
		assertTrue(page.clickOnEmailSearchButton(), "Unable to click on email search button");
		
		assertTrue(page.getEmailSearchForm().isEmailSearchModalVisible(),"Email Listing Modal is not visible");		

		String lSenderEmail = lLeadEmail;

		if(isLeadLoggedIn) { 
			//Verify the name of the lead is in respective fields 
			lSenderEmail = lLeadEmail = EnvironmentFactory.configReader.getPropertyByName("z57_user_v2");
			assertTrue(page.getEmailSearchForm().getLeadName().isEmpty(), "Lead is logged in but No Name in Email Listing form" );
			assertTrue(page.getEmailSearchForm().getLeadEmail().isEmpty(), "Lead is logged in but No Name in Email Listing form" );

		}else {

			assertTrue(page.getEmailSearchForm().typeLeadName(lLeadName),"Unable to type name in Lead Name field");

			//#5895
            if ( changeEmail )
            {
                lSenderEmail = "changed_"+lLeadEmail;
            }

            assertTrue(page.getEmailSearchForm().typeEmailAddress(lSenderEmail),"Unable to type email in Lead email field");
			if(!lLeadPhone.isEmpty()) {
				assertTrue(page.getEmailSearchForm().typePhoneNumber(lLeadPhone),"Unable to type phone in Lead phone number field"); 
			} 
		}

		assertTrue(page.getEmailSearchForm().typeR1Name(lR1Name),"Unable to write the name oof Recipient 1");
		assertTrue(page.getEmailSearchForm().typeR1Email(lR1Email),"Unable to write the email of Recepient 1");

		assertTrue(page.getEmailSearchForm().typeR2Name(lR2Name),"Unable to write the name oof Recipient 2");
		assertTrue(page.getEmailSearchForm().typeR2Email(lR2Email),"Unable to write the email of Recepient 2");

		assertTrue(page.getEmailSearchForm().clickOnSendButton(),"Unable to click on Send button");
		assertTrue(page.getEmailSearchForm().isEmailSent(),"After clicking Send button 'Email this Listing' modal is still visible");

		DBHelperMethods dbHelper = new DBHelperMethods(getEnvironment());
		
		assertTrue(dbHelper.verifyLeadByEmailInDB(lLeadEmail), "User is not added as Lead in PP ->" + lLeadEmail);
		assertTrue(dbHelper.verifyLeadByEmailInDB(lR1Email), "Recipient1 is not added as Lead in PP ->" + lR1Email);
		assertTrue(dbHelper.verifyLeadByEmailInDB(lR2Email), "Recipient2 is not added as Lead in PP ->" + lR2Email);

		// Verifies the email has been sent on respective email addresses.
		if(!isLeadLoggedIn) {
			//TODO
			//Add check for welcome email
            assertTrue(dbHelper.verifyEmailIsSentToLead(lLeadEmail, FrameworkConstants.ThanksForConnecting),"Unable to sent 'Thanks for connecting' email to Sender");
        }

		assertTrue(dbHelper.verifyEmailIsSent(lR1Email, FrameworkConstants.CheckOutThisPropertySearch),"Unable to sent 'Checkout Property' email to Recipient1");
		assertTrue(dbHelper.verifyEmailIsSent(lR2Email, FrameworkConstants.CheckOutThisPropertySearch),"Unable to sent 'Checkout Property' email to Recipient2");

        //#5895
        assertTrue(dbHelper.verifyEmailIsSentToLead(lR1Email, FrameworkConstants.ThanksForConnecting),"Unable to sent 'Thanks for connecting' email to Recipient1");
        assertTrue(dbHelper.verifyEmailIsSentToLead(lR2Email, FrameworkConstants.ThanksForConnecting),"Unable to sent 'Thanks for connecting' email to Recipient2");

        if ( !lSenderEmail.equals(lLeadEmail) )
        {
            assertTrue(dbHelper.verifyEmailIsSentToLead(lSenderEmail, FrameworkConstants.ThanksForConnecting),"Unable to sent 'Thanks for connecting' email to Changed Sender");
        }

		String lAgent_email = EnvironmentFactory.configReader.getPropertyByName("z57_propertypulse_user_email");

		// Verifies the agent have received the email for all the leads
		if(!isLeadLoggedIn) {
		assertTrue(dbHelper.verifyEmailIsSentToAgent(lAgent_email, lLeadEmail),"Unable to sent email to Agent for ->" + lLeadEmail);
		}
		assertTrue(dbHelper.verifyEmailIsSentToAgent(lAgent_email, lR1Email),"Unable to sent email to Agent for ->" + lR1Email);
		assertTrue(dbHelper.verifyEmailIsSentToAgent(lAgent_email, lR2Email),"Unable to sent email to Agent for ->" + lR2Email);

		
	}


    @Test
    @Parameters({"dataFile","dataFile2"})
    public void testSaveSearch(String pDataFile1, String pDataFile2) {
        getPage("/idx");

        searchFormData = new SearchFormData(pDataFile1).getSearchFormData();
        SaveSearchFormData saveListingFormData = new SaveSearchFormData(pDataFile2).getEmailListingData();

        String lLeadName = updateName(saveListingFormData.getLeadName());
        String lLeadEmail = updateEmail(saveListingFormData.getLeadEmail());
        String lLeadPhone = saveListingFormData.getLeadPhoneNumber();

        assertTrue(page.isHomeSearchPage(), "Home Search page is not visible");

        PageHeader pageHeader = new PageHeader(driver);

        boolean isLeadLoggedIn=pageHeader.isLeadLoggedIn();

        searchHomes();

        assertTrue(page.clickOnSaveSearchButton(), "Unable to click on save search button");

        assertTrue(page.getSaveSearchForm().isSaveSearchModalVisible(),"Save search Modal is not visible");

        if(isLeadLoggedIn) {
            //Verify the name of the lead is in respective fields
            lLeadEmail = EnvironmentFactory.configReader.getPropertyByName("z57_user_v2");
            assertTrue(page.getSaveSearchForm().getLeadName().isEmpty(), "Lead is logged in but No Name in Save search form" );
            assertTrue(page.getSaveSearchForm().getLeadEmail().isEmpty(), "Lead is logged in but No Name in Save search form" );

        }else {
            assertTrue(page.getSaveSearchForm().typeLeadName(lLeadName),"Unable to type name in Save search field");
            assertTrue(page.getSaveSearchForm().typeEmailAddress(lLeadEmail),"Unable to type email in Save search field");
            if(!lLeadPhone.isEmpty()) {
                assertTrue(page.getSaveSearchForm().typePhoneNumber(lLeadPhone),"Unable to type phone in Lead phone number field");
            }
        }

		String lSearchTitle = page.getSaveSearchForm().getTitle();

		assertTrue(page.getSaveSearchForm().clickOnSaveButton(),"Unable to click on Save button");
		assertTrue(page.getSaveSearchForm().isSearchSaved(),"No expected message is shown");

		DBHelperMethods dbHelper = new DBHelperMethods(getEnvironment());

        assertTrue(dbHelper.verifyLeadByEmailInDB(lLeadEmail), "User is not added as Lead in PP ->" + lLeadEmail);

		assertTrue(dbHelper.verifySearchIsSaved(lLeadEmail, lSearchTitle),"Unable to save search");

    }

	@Test
	@Parameters({"dataFile"})
	public void testVerifyPaginationOnHomeSearch(String pDataFile) {
		searchFormData = new SearchFormData(pDataFile).getSearchFormData();
		getPage("/idx");
		searchHomes();
		Pagination pagination = new Pagination(driver);
		assertTrue(pagination.verifyAllPaginationButtonsWorking(),"Pagination buttons not working on Listing Page");
		
	}
}
