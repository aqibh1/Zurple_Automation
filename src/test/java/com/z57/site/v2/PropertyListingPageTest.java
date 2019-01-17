package com.z57.site.v2;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import resources.data.z57.SearchFormData;

public class PropertyListingPageTest extends PageTest{

	private PropertyListingPage page;
	private SearchFormData searchFormData;
	
	
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
			page = new PropertyListingPage(getDriver());
//			page.setDriver(getDriver());
		}
		setSearchParams();
		return page;
	}

	@Override
	public void clearPage() {
		// TODO Auto-generated method stub
		
	}
	private void setSearchParams() {
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
	}
	
	@BeforeClass
	@Parameters({"dataFile"})
	  public void beforeClass(String pFileLocation) throws JsonParseException, JsonMappingException, IOException {
		searchFormData= new SearchFormData();
		searchFormData.setSearchFormData(pFileLocation);
	 }
	
	@Test
	public void testVerificationOfPropertyListing() {
		PropertyListingPage propertyListingObj = new PropertyListingPage(getPage().getWebDriver());
		
//		page.getWebDriver().navigate().to("http://robinsoldwisch-13878.sites.z57.com/idx/listings/cws/1098/170017412/5326-grand-del-mar-place-place-san-diego-san-diego-county-ca-92130");
		
		assertTrue(lInputSearch.contains(page.getAddress(lSearchByOption)), "Input Search criteria does not meets the address results");
		
		if(!lMinimumValue.isEmpty()) {
			int propertyPriceFromPage=page.getPropertValueFromHeader();
			int minimum_value=Integer.parseInt(lMinimumValue);
			assertTrue(propertyPriceFromPage>=minimum_value,"Property Value doesn't matches the minimum value set");
		}
		
		if(!lMaximumValue.isEmpty()) {
			assertTrue(page.getPropertValueFromHeader()<=Integer.parseInt(lMaximumValue),"Property Value doesn't matches the minimum value set");
		}
		
		if(!lNumberOfBeds.isEmpty()) {
			assertTrue(page.getNumberOfBeds()>=Integer.parseInt(lNumberOfBeds),"Number of BEDS doesn't matches the value set");
		}
		
		if(!lNumberOfBaths.isEmpty()) {
			assertTrue(page.getNumberOfBaths()>=Integer.parseInt(lNumberOfBeds),"Number of BATHS doesn't matches the value set");

		}
		
		if(!lLotSize.isEmpty()) {
			assertTrue(page.getLotSize()>=Integer.parseInt(lLotSize),"Lot size doesn't matches the value set");

		}
		
		if(!lFeatures.isEmpty()) {
			String[] featuresArray = lFeatures.split(",");
			for(String lFeat:featuresArray) {
				boolean isFeaturePresent = page.propertyInteriorVerification(lFeat);
				assertTrue(isFeaturePresent,"Feature is not present in the listing");
			}
			
		}
		if(!lStatus.isEmpty()) {
			assertTrue(page.getPropertyStatusFromHeader().equalsIgnoreCase(lStatus),"Status is mismatched");
		}
	}

}
