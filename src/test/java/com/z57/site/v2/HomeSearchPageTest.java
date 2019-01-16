package com.z57.site.v2;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import java.io.IOException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import resources.data.z57.SearchFormData;

public class HomeSearchPageTest extends PageTest{

	private HomeSearchPage page;
	private SearchFormData searchFormData;
//	private static SearchFormData searchFormData;
	/*
	 * Adding this hardcoded data. Will modify this code to get this data from XML
	 * parameters. Will initialize these variables in constructor with data object
	 * from JSON.  
	 */
//	String lInputSearch="Chicago Heights, IL";
//	String lSearchByOption="City";
//	String lMinimumValue="25000";
//	String lMaximumValue="10000000";
//	String lNumberOfBeds="2";
//	String lNumberOfBaths="2";
//	String lPropertyType="";
//	String lFeaturesAnyOrAll="any";
//	String lFeatures="Air Conditioning";
//	String lSquareFootage="500+";
//	String lView="";
//	String lLotSize="2,000+ sq ft";
//	String lStyle="";
//	String lStatus="ACTIVE";
//	String lYearBuilt="1950+";
	
	String lInputSearch="";
	String lSearchByOption="";
	String lMinimumValue="";
	String lMaximumValue="";
	String lNumberOfBeds="";
	String lNumberOfBaths="";
	String lPropertyType="";
	String lFeaturesAnyOrAll="";
	String lFeatures="";
	String lSquareFootage="";
	String lView="";
	String lLotSize="";
	String lStyle="";
	String lStatus="";
	String lYearBuilt="";
	
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
		lInputSearch=searchFormData.getSearchFormDataObject().getInputSearch();
		lSearchByOption=searchFormData.getSearchFormDataObject().getSearchBy();
		lMinimumValue=searchFormData.getSearchFormDataObject().getMinimumValue();
		lMaximumValue=searchFormData.getSearchFormDataObject().getMaximumValue();
		lNumberOfBeds=searchFormData.getSearchFormDataObject().getNumberOfBeds();
		lNumberOfBaths=searchFormData.getSearchFormDataObject().getNumberOfBaths();
		lPropertyType=searchFormData.getSearchFormDataObject().getPropertyType();
		lFeaturesAnyOrAll=searchFormData.getSearchFormDataObject().getFeatureAnyAll();
		lFeatures=searchFormData.getSearchFormDataObject().getFeatures();
		lSquareFootage=searchFormData.getSearchFormDataObject().getSquareFotage();
		lView=searchFormData.getSearchFormDataObject().getView();
		lLotSize=searchFormData.getSearchFormDataObject().getLotSize();
		lStyle=searchFormData.getSearchFormDataObject().getStyle();
		lStatus=searchFormData.getSearchFormDataObject().getStatus();
		lYearBuilt=searchFormData.getSearchFormDataObject().getYearBuilt();

		return page;
	}
	
	@Override
	public void clearPage() {
		// TODO Auto-generated method stub
	}
	
	@BeforeClass
	@Parameters({"dataFile"})
	  public void beforeClass(String pFileLocation) throws JsonParseException, JsonMappingException, IOException {
		searchFormData= new SearchFormData();
		searchFormData.setSearchFormData(pFileLocation);
	 }
	
	@Test
	public void testSearchByDifferentDataSet() {
		
		System.out.println(lInputSearch);
		System.out.println(lSearchByOption);
		
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
			if(!lPropertyType.isEmpty()) {
				String[] lPropertyTypeArray = lPropertyType.split(",");
				for(String propertyType: lPropertyTypeArray) {
					assertTrue(page.getSearchForm().typeAndSelectPropertyType(propertyType), "Unable to select property type");
				}
			}
			
			//Selects any/all feature option from drop down
//			assertTrue(page.getSearchForm().selectFeature(lFeaturesAnyOrAll), "Unable to select any or all feature type");
			
			//Selects features
			assertTrue(page.getSearchForm().clickAndSelectFeature(lFeatures), "Unable to select any or all feature type");
			
			//Selects square footage from drop down
			assertTrue(page.getSearchForm().clickAndSelecctSquareFootage(lSquareFootage), "Unable to select square footage from drop down");
		
			//Selects the view
			if(!lView.isEmpty()) {
				String [] lViewsArray =lView.split(",");
				for (String view: lViewsArray) {
					assertTrue(page.getSearchForm().clickAndSelectView(view), "Unable to select view from drop down "+view);
				}
			}
			//Selects the Lot size
			assertTrue(page.getSearchForm().clickAndSelectLotSize(lLotSize), "Unable to select Lot size from drop down");
			
			//Selects the Style from dropdown
			if(!lStyle.isEmpty()) {
				String [] lStyleArray =lStyle.split(",");
				for (String style: lStyleArray) {
					assertTrue(page.getSearchForm().clickAndSelectStyle(style), "Unable to select style from drop down");
				}
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
		//page.getSearchForm().submit();
		//assertTrue(doDataValidation(),"Data Validation was not successfull");
//		return isSearchSuccessful;
		
	}
	
	/*
	 * private boolean doDataValidation() { boolean isValidationSuccessful=true;
	 * SearchResultsPage searchResultObj = new SearchResultsPage(); do {
	 * ArrayList<SearchResult> searchResultsList =
	 * searchResultObj.getSearchResultsBlock(page.getWebDriver()).
	 * getSearchResultsList(); assertFalse(searchResultsList.isEmpty());
	 * 
	 * List<String> typeList = null; if( !"".equals(lPropertyType) ) { typeList =
	 * Arrays.asList(lPropertyType.split(",")); }
	 * 
	 * List<String> featureList = null; if( !"".equals(lFeatures) ) { featureList =
	 * Arrays.asList(lFeatures.split(",")); }
	 * 
	 * List<String> viewList = null; if( !"".equals(lView) ) { viewList =
	 * Arrays.asList(lView.split(",")); }
	 * 
	 * for(SearchResult result:searchResultsList) { Property property =
	 * getEnvironment().getDetailedProperty(result.getId());
	 * assertEquals(property.getStatus(),lStatus);
	 * assertTrue(typeList.contains(property.getPropType()));
	 * 
	 * // if("city".equals(search_by)) // { //
	 * assertTrue(search_criteria.toLowerCase().contains(property.getCity().
	 * toLowerCase())); // }
	 * 
	 * assertTrue(lSearchByOption.toLowerCase().contains(property.getCity().
	 * toLowerCase()));
	 * 
	 * assertTrue(property.getBedrooms() >= Integer.parseInt(lNumberOfBeds));
	 * assertTrue(property.getBathrooms() >= Integer.parseInt(lNumberOfBaths));
	 * 
	 * if ( !"".equals(lMinimumValue) && !"".equals(lMaximumValue) ){
	 * assertTrue(property.getPrice() >= Integer.parseInt(lMinimumValue) &&
	 * property.getPrice() <= Integer.parseInt(lMaximumValue)); }
	 * 
	 * assertTrue(property.getYearBuilt() >= Integer.parseInt(lYearBuilt));
	 * assertTrue(property.getSquareFeet() >= Integer.parseInt(lSquareFootage));
	 * assertTrue(property.getLotSqft() >= Integer.parseInt(lLotSize));
	 * 
	 * List<String> propertiesFeatures = property.buildFeaturesList(); for(String
	 * feature:featureList){ assertTrue(propertiesFeatures.contains(feature)); }
	 * 
	 * }
	 * 
	 * try{
	 * 
	 * searchResultObj.goNextPage(); }catch(Exception e){
	 * isValidationSuccessful=false; break; }
	 * 
	 * } while (true); return isValidationSuccessful; }
	 */
}
