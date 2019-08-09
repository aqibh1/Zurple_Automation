/**
 * 
 */
package com.zurple.website;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import resources.utility.ActionHelper;
import us.zengtest1.Page;
import us.zengtest1.PageTest;

/**
 * @author adar
 *
 */
public class ZWPropertyDetailPageTest extends PageTest{
	
	private WebDriver driver;
	private ZWPropertyDetailPage page;
	private JSONObject dataObject;
	
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
			driver = getDriver();
			page = new ZWPropertyDetailPage(driver);
		}
		return page;
	}

	@Override
	public void clearPage() {
		// TODO Auto-generated method stub
		
	}
	@Test
	@Parameters({"searchPropertyDataFile"})
	public void testVerifyPropertyDetails() {
		getPage();
		SoftAssert softAssert = new SoftAssert();
		String lInpuSearch = dataObject.optString("input_search");
		String lSearchBy = dataObject.optString("search_by");
		String lMinPrice = dataObject.optString("minimum_price");
		String lMaxPrice = dataObject.optString("maximum_price");
		String lNumOfBeds = dataObject.optString("number_of_beds");
		String lNumOfBaths = dataObject.optString("number_of_baths");
		String lPropType = dataObject.optString("property_type");
		String lFeatures = dataObject.optString("features");
		String lSquareFeet = dataObject.optString("square_feet");
		String lView = dataObject.optString("view");
		String lLotSize = dataObject.optString("lot_size");
		String lStyle = dataObject.optString("style");
		String lYearBuilt = dataObject.optString("year_built");
		
		assertTrue(page.verifyPropName(), "Property page is not visible..");
		assertTrue(!page.getPropPrice().isEmpty(),"Property Price is not visible in header..");
		
		switch(lSearchBy) {
		case "Zip":
			String lZip = page.getAddress().split("(")[1].replace(")", "");
			assertTrue(lInpuSearch.equalsIgnoreCase(lZip),"Zip is not according to criteria...");
			break;
		case "Address":
			assertTrue(page.getAddress().contains(lInpuSearch),"Address is not according to criteria...");
			break;
		case "MLS":
			assertTrue(page.getListingNumber().equalsIgnoreCase(lInpuSearch),"MLS is not according to criteria...");
			break;
		case "Neighborhood":
			assertTrue(page.getNeighborhood().contains(lInpuSearch),"Neighborhood is not according to criteria...");
			break;
//		case "School District":
//			isSelectSuccess = ActionHelper.Click(driver, school_input);
//			break;
		case "County":
			assertTrue(page.getCounty().equalsIgnoreCase(lInpuSearch),"County is not according to criteria..");
			break;
		default:
			assertTrue(page.getAddress().contains(lInpuSearch),"Property doesn' exist in the desired city..");
			break;
		}
		
		int lPropPriceOnPage = Integer.parseInt(page.getPropPrice().replaceAll(",", "").replace("$", ""));
		
		if(!lMinPrice.isEmpty()) {
			int lMinimumPrice = Integer.parseInt(dataObject.optString("minimum_price").replace(",", "").replace("$", ""));
			assertTrue(lPropPriceOnPage>=lMinimumPrice, "Property Price is less than minimum search price..");
		}
		if(!lMaxPrice.isEmpty()) {
			int lMaximumPrice = Integer.parseInt(dataObject.optString("maximum_price").replace(",", "").replace("$", ""));
			assertTrue(lMaximumPrice>=lPropPriceOnPage, "Property Price is greater than maximum search price..");
		}
		if(!lNumOfBeds.isEmpty()) {
			int lNumberOfBeds = Integer.parseInt(dataObject.optString("number_of_beds").replace("+", ""));
			assertTrue(Integer.parseInt(page.getBedrooms())>=lNumberOfBeds, "Bedrooms count is less than miniimum number of beds..");
		}
		if(!lNumOfBeds.isEmpty()) {
			int lNumberOfBeds = Integer.parseInt(dataObject.optString("number_of_beds").replace("+", ""));
			assertTrue(Integer.parseInt(page.getBedrooms())>=lNumberOfBeds, "Bedrooms count is less than miniimum number of beds..");
		}
		if(!lNumOfBaths.isEmpty()) {
			int lNumberOfBaths = Integer.parseInt(dataObject.optString("number_of_baths").replace("+", ""));
			assertTrue(Integer.parseInt(page.getBathrooms())>=lNumberOfBaths, "Bathrooms count is less than miniimum number of beds..");
		}
		if(!lFeatures.isEmpty()) {
			String [] lFeaturesOnPage = page.getFeatures().split(",");
			String [] lFeaturesToVerify = lFeatures.split(",");
			assertTrue(elementExists(lFeaturesOnPage, lFeaturesToVerify), "All the features doesn't exist on the page..");
		}
		if(!lSquareFeet.isEmpty()) {
			int lSqFeetOnPage = Integer.parseInt(page.getSqFeet().split("sq.ft")[0].trim());
			assertTrue(lSqFeetOnPage>=Integer.parseInt(lSquareFeet), "Square Feet is less than mentioned criteria..");
		}
		if(!lView.isEmpty()) {
			String [] lViewsOnPage = page.getViews().split(",");
			String [] lViewsToVerify = lView.split(",");
			assertTrue(elementExists(lViewsOnPage, lViewsToVerify), "All the views doesn't exist on the page..");
		}
//		if(!lLotSize.isEmpty()) {
//			if(lLotSize.contains("acres") && lLotSize.contains("sq ft")) {
//				lLotSize = lLotSize.split("/")[1].replace("+", "").trim();
//			}else if(lLotSize.contains("acres")) {
//				lLotSize = lLotSize.split("+")[0];
//			}else {
//				lLotSize = lLotSize.split("+")[0].replace(",", "");
//			}
//			int lLotSizeOnPage = Integer.parseInt(page.getLotSize().replace("acres", ""));
//			
//		}
		
		if(!lStyle.isEmpty()) {
			String lStyleOnPage[] = page.getStyle().split(",");
			String lStyleToVerify[] = lStyle.split(",");
			assertTrue(elementExists(lStyleOnPage, lStyleToVerify),"Unable to verify style..");
			
		}
		if(!lYearBuilt.isEmpty()) {
			int lYearBuiltOnPage = Integer.parseInt(page.getYearBuilt());
			int lYearBuiltToVerify = Integer.parseInt(lYearBuilt);
			assertTrue(lYearBuiltOnPage>=lYearBuiltToVerify,"Unable to verify year built..");
		}
		
		boolean isUserLoggedIn = new ZurpleWebsiteHeader(driver).isLeadLoggedIn();
		
		if(isUserLoggedIn) {
			softAssert.assertTrue(page.isFeaturesTableVisible(), "Features table is not visible..");
			assertTrue(page.isGoogleMapAndPinVisible(), "Google Map and pin is not visible..");
			assertTrue(page.verifyCommunityStatsVisible(), "Unable to verify community stats..");
			assertTrue(page.verifySchoolMap(), "Unable to verify school map..");
			assertTrue(page.verifyPOIMap(), "Unable to verify whats nearby map..");
		}else {
			softAssert.assertTrue(page.isFeaturesTableVisible(), "Features table is not visible..");
			assertTrue(page.isGoogleMapAndPinVisible(), "Google Map and pin is not visible..");
			assertFalse(page.verifyCommunityStatsVisible(), "Unable to verify community stats..");
			assertFalse(page.verifySchoolMap(), "Unable to verify school map..");
			assertFalse(page.verifyPOIMap(), "Unable to verify whats nearby map..");
		}
		
	}
	
	private boolean elementExists(String[] pElementsOnPage, String[] pElementsToVerify) {
		boolean isFound = false;
		for(String elementToVerify: pElementsToVerify) {
			for(String lElementsOnPage: pElementsOnPage) {
				if(elementToVerify.equalsIgnoreCase(lElementsOnPage)) {
					isFound = true;
					break;
				}
			}
		}
		return isFound;
		
	}

}
