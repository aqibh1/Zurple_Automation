/**
 * 
 */
package com.z57.site.v2;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.testng.SkipException;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import resources.data.z57.SearchFormData;

/**
 * @author adar
 *
 */
public class IDXPropertyListingPageTest extends PageTest{

	IDXPropertyListingPage page;
	private SearchFormData searchFormData;	
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
		if (page == null) {
			page = new IDXPropertyListingPage(getDriver());
			driver = getDriver();
		}
		
		return page;
	}

	@Override
	public void clearPage() {
		// TODO Auto-generated method stub
		
	}
	
	@Parameters({"dataFile"})
	@Test
	public void testVerifyIdxPropertyListing(String pFolderLocation) {
		
		searchFormData = new SearchFormData(pFolderLocation).getSearchFormData();
		
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
		getPage();
		
		if (page.getCurrentUrl().contains("/listings/")) {
			// page.getWebDriver().navigate().to("http://robinsoldwisch-13878.sites.z57.com/idx/listings/cws/1098/170017412/5326-grand-del-mar-place-place-san-diego-san-diego-county-ca-92130");
			
			if(lSearchByOption.equalsIgnoreCase("zip") || lSearchByOption.equalsIgnoreCase("city")) {
				String optionToVerify = lInputSearch.split(",")[0];
				//Enters the data in input field and selects from drop down list
				assertTrue(page.getPropertyAddress().contains(optionToVerify), "Property Address doesn' contain "+optionToVerify);
			}else if(lSearchByOption.equalsIgnoreCase("address")) {
				String optionToVerify = lInputSearch.split("San Diego")[0];
				assertTrue(page.getPropertyAddress().replace(" ", "").contains(optionToVerify.replace(" ", "")), "Property Address doesn' contain "+lInputSearch);
			}else if(lSearchByOption.equalsIgnoreCase("neighborhood")) {
//				assertTrue(page.getSearchForm().typeAndSelectNeighborhood(lInputSearch), "Input field on Home Search is not visible");

			}
			else {
				assertTrue(page.getMLSNum().contains(lInputSearch), "Property Address doesn' contain "+lInputSearch);
			}
			
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
			SoftAssert softAssert = new SoftAssert();
			PropertyListingPage propertyListingPage = new PropertyListingPage(driver);
			// All the validations and actions on map tab.
			assertTrue(page.clickOnMapBar(), "Map button on Navigation bar is not visible");
			assertTrue(page.isGoogleMapDisplayed(), "Google map is not displayed");
			softAssert.assertTrue(page.isPinDsiplayedOnGoogleMaps(), "Property PIN is not visible on the Google Maps");

			// All the validations on Community stats
			assertTrue(page.clickOnCommunityStats(), "Community Stats on Navigation bar is not visible");
			assertTrue(propertyListingPage.isCommunityStatsDisplayed(), "Community Stats is not displayed");
			assertTrue(propertyListingPage.isPopulationDemographicChartVisible(),"Community Stats | Population Demographic chart is not displayed");
			assertTrue(propertyListingPage.isPopulationRangeChartVisible(),"Community Stats | Population Range chart is not displayed");
			assertTrue(propertyListingPage.isHouseholdsChartVisible(), "Community Stats | Households chart is not displayed");
			assertTrue(propertyListingPage.isEducationLevelChartVisible(), "Community Stats | Education Level chart is not displayed");
			assertTrue(propertyListingPage.isEmploymentChartVisible(), "Community Stats | Employment chart is not displayed");
			assertTrue(propertyListingPage.isCrimeChartVisible(), "Community Stats | Crime chart is not displayed");
			assertTrue(propertyListingPage.isClimateChartVisible(), "Community Stats | Climate chart is not displayed");
			assertTrue(propertyListingPage.isAreaRankingChartVisible(), "Community Stats | Area Ranking chart is not displayed");

			// All the validations on School Maps
			assertTrue(page.clickOnSchools(), "Schools on Navigation bar is not visible");
			assertTrue(propertyListingPage.isSchoolMapsDisplayed(), "Schools map and information is not displayed");
			softAssert.assertTrue(propertyListingPage.verifySchoolPins(), "Number of schools and pins on map count mismatched");

			// All the validations on POI
			assertTrue(page.clickOnWhatsNearBy(), "Whats near by on Navigation bar is not visible");
			assertTrue(propertyListingPage.isWhatsNearbyDisplayed(), "Whats near by is not displayed");
			softAssert.assertTrue(propertyListingPage.verifyPOIPins(), "Number of POI and pins on map count mismatched");
			
		}else {
			throw new SkipException("No property found on search criteria.");
		}
		
	}

}
