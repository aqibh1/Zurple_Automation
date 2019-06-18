package com.z57.site.v2;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import resources.data.z57.OurCommunityData;
import resources.data.z57.RegisterUserData;
import resources.forms.z57.OurCommunitySearchForm;


public class CommunityReportsPageTest extends PageTest{
	
	CommunityReportsPage page;
	WebDriver driver;
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
		if(page == null){
			page = new CommunityReportsPage(getDriver());
			page.setUrl("");
			page.setDriver(getDriver());
			driver=getDriver();
		}
		return page;
	}

	@Override
	public void clearPage() {
		// TODO Auto-generated method stub
		
	}
	
	@Parameters({"dataFile"})
	@Test
	public void testCaptureLeadFromCommunityReportsPage(String pFolderLocation) {
		getPage();
		RegisterUserData registerUserData = new RegisterUserData();
    	registerUserData = registerUserData.setRegisterUserData(pFolderLocation);

    	String lName=registerUserData.getUserName();
    	String lEmail=registerUserData.getUserEmail();
    	String lPhone=registerUserData.getUserPhoneNumber();
    	String lComments=registerUserData.getComments();
    	
		closeBootStrapModal();
		PageHeader pageHeader = new PageHeader(driver);
		assertTrue(pageHeader.clickOnCommunityReports(), "Unable to click on Community Reports");
		captureLead(lName, lEmail, lPhone, lComments);
		
		assertTrue(page.isCommunityReportsPage(), "Page Title not found");
		
	}
	
	@Parameters({"dataFile"})
	@Test
	public void testSearchAndVerifyCommunityReportsPage(String pFolderLocation){
		getPage();
		OurCommunityData ourCommunityData = new OurCommunityData();
		ourCommunityData=ourCommunityData.setOurCommunityData(pFolderLocation);
		
		String lAddress=ourCommunityData.getAddress();
		String lCity = ourCommunityData.getCity();
		String lState = ourCommunityData.getState();
		String lZip = ourCommunityData.getZip();
    	
		closeBootStrapModal();
		PageHeader pageHeader = new PageHeader(driver);
		assertTrue(pageHeader.clickOnCommunityReports(), "Unable to click on Community Reports");
		
		closeBootStrapModal();
		
		assertTrue(page.isCommunityReportsPage(), "Page Title not found");
		
		OurCommunitySearchForm ourCommunitySearchForm = new OurCommunitySearchForm(driver);
		
		if(!lAddress.isEmpty()) {
			assertTrue(ourCommunitySearchForm.typeAddress(lAddress), "Unable to type address.");
		}
		if(!lCity.isEmpty()) {
			assertTrue(ourCommunitySearchForm.typeCity(lCity), "Unable to type City.");
		}
		if(!lZip.isEmpty()) {
			assertTrue(ourCommunitySearchForm.typeZip(lZip), "Unable to type Zip.");
		}
		if(!lState.isEmpty()) {
			assertTrue(ourCommunitySearchForm.selectState(lState), "Unable to select state.");
		}
		
		assertTrue(ourCommunitySearchForm.clickSubmitButton(),"Unable to Click on submit button");
		assertTrue(ourCommunitySearchForm.isSearchSuccessful(),"Search is not successful");
		
		PropertyListingPage propertyListingPage = new PropertyListingPage(driver);
		assertTrue(propertyListingPage.isCommunityStatsDisplayed(), "Community Stats are not displayed.");
		assertTrue(propertyListingPage.isPopulationDemographicChartVisible(), "Population Demographic chart is not displayed.");
		assertTrue(propertyListingPage.isPopulationRangeChartVisible(), "Population Range chart is not displayed.");
		assertTrue(propertyListingPage.isHouseholdsChartVisible(), "House holds chart is not displayed.");
		assertTrue(propertyListingPage.isEducationLevelChartVisible(), "Education Level chart is not displayed.");
		assertTrue(propertyListingPage.isEmploymentChartVisible(), "Employment chart is not displayed.");
		assertTrue(propertyListingPage.isCrimeChartVisible(), "Crime chart is not displayed.");
		assertTrue(propertyListingPage.isClimateChartVisible(), "Climate Demographic chart is not displayed.");
		assertTrue(propertyListingPage.isAreaRankingChartVisible(), "Area Ranking chart is not displayed.");
		
	}

}
