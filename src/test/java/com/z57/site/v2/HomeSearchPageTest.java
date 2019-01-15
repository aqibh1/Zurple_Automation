package com.z57.site.v2;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class HomeSearchPageTest extends PageTest{

	private HomeSearchPage page;
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
			page = new HomeSearchPage(getDriver(),this.source_in_url);
			page.setDriver(getDriver());
		}
		return page;
	}

	@Override
	public void clearPage() {
		// TODO Auto-generated method stub
		
	}
	
	@BeforeClass
	  public void beforeClass(ITestContext context) {
	    String value = context.getCurrentXmlTest().getParameter("1");
	    System.err.println("webdriver.deviceName.iPhone = " + value);
	 }
	//TODO
	//Will add the logic for multiple search fields
	@Test
	public void testSearchByDifferentDataSet() {
		//Adding this hardcoded data. Wil modify this code to get this data from
		//XML parameters.
		String lInputSearch="Chicago Heights, IL";
		String lSearchByOption="City";
		String lMinimumValue="25000";
		String lMaximumValue="375000";
		String lNumberOfBeds="2";
		String lNumberOfBaths="2";
		String lPropertyType="Rental";
		String lFeaturesAnyOrAll="any";
		String lFeatures="Air Conditioning";
		String lSquareFootage="500+";
		String lView="Golf course view";
		String lLotSize="2,000+ sq ft";
		String lStyle="Cape Cod";
		String lStatus="ACTIVE";
		String lYearBuilt="1950+";
		
		HomePage homePageObj = new HomePage(getPage().getWebDriver());
		homePageObj.mouseoverHomeSearch();
		homePageObj.clickOnSearchHomes();
		
		//Select the search by option from dropdown
		assertTrue(page.getSearchForm().clickOnSearchByOption(lSearchByOption), "Could not click on Search By element as its not visible.");
		
		//Enters the data in input field and selects from drop down list
		assertTrue(page.getSearchForm().typeInputAndSelect(lInputSearch), "Input field on Home Search is not visible");
		
		//Clicks and select from Minimum price
		assertTrue(page.getSearchForm().clickOnPriceLowOption(lMinimumValue), "Unable to select minimum price from drop down");
		
		//Clicks and selects from Maximum price
		assertTrue(page.getSearchForm().clickOnPriceMaxOption(lMaximumValue), "Unable to select maximum price from drop down");
		
		//Clicks and selects number of beds option
		assertTrue(page.getSearchForm().clickOnBedsOption(lNumberOfBeds), "Unable to select number of beds from drop down");
		
		//Clicks and select number of baths option
		assertTrue(page.getSearchForm().clickOnBathsOption(lNumberOfBaths), "Unable to select number of baths from drop down");
		
		//If any of the advance filter is not empty than it will click expand button and clicks and selects other filters
		if(!lPropertyType.isEmpty() || !lFeaturesAnyOrAll.isEmpty() || !lFeatures.isEmpty() || !lSquareFootage.isEmpty() ||
				!lView.isEmpty() || !lLotSize.isEmpty() || !lStyle.isEmpty() || !lStatus.isEmpty() || !lYearBuilt.isEmpty()) {
			
			//Clicks on advance search filter button
			assertTrue(page.getSearchForm().clickOnExpandSearchButton(), "Unable to click on expand search button");
			
			//Clicks and Selects Property Types
			//Multiple Property Types will be comma separated
			String[] lPropertyTypeArray = lPropertyType.split(",");
			for(String propertyType: lPropertyTypeArray) {
				assertTrue(page.getSearchForm().typeAndSelectPropertyType(propertyType), "Unable to select property type");
			}
			
			//Selects any/all feature option from drop down
//			assertTrue(page.getSearchForm().selectFeature(lFeaturesAnyOrAll), "Unable to select any or all feature type");
			
			//Selects features
			assertTrue(page.getSearchForm().clickAndSelectFeature(lFeatures), "Unable to select any or all feature type");
			
			//Selects square footage from drop down
			assertTrue(page.getSearchForm().clickAndSelecctSquareFootage(lSquareFootage), "Unable to select square footage from drop down");
		
			//Selects the view
			String [] lViewsArray =lView.split(",");
			for (String view: lViewsArray) {
				assertTrue(page.getSearchForm().clickAndSelectView(view), "Unable to select view from drop down "+view);
			}
			
			//Selects the Lot size
			assertTrue(page.getSearchForm().clickAndSelectLotSize(lLotSize), "Unable to select Lot size from drop down");
			
			//Selects the Style from dropdown
			String [] lStyleArray =lStyle.split(",");
			for (String style: lStyleArray) {
				assertTrue(page.getSearchForm().clickAndSelectStyle(style), "Unable to select style from drop down");
			}
			
			//Selects the status from dropdown
			String [] lStatusArray =lStatus.split(",");
			for (String status: lStatusArray) {
				assertTrue(page.getSearchForm().clickAndSelectStatus(status), "Unable to select Status from drop down");
			}
			
			//Selects the year built
			assertTrue(page.getSearchForm().clickAndSelectYear(lYearBuilt), "Unable to select Year built from drop down");

		
		}
	
		
		
		assertTrue(page.getSearchForm().clickOnSearchButton(),"Search Button on Home search screen is not visible");
		
		
		
//		return isSearchSuccessful;
		
	}
	
}
