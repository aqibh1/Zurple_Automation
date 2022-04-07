/**
 * 
 */
package com.zurple.website;

import static org.testng.Assert.assertTrue;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import resources.DBHelperMethods;
import resources.EnvironmentFactory;
import resources.utility.ActionHelper;
import us.zengtest1.Page;
import us.zengtest1.PageTest;

/**
 * @author adar
 *
 */
public class ZWHomeSearchPageTest extends PageTest{
	
	private WebDriver driver;
	private ZWHomeSearchPage page;
	private JSONObject dataObject;
	
	@Override
	public Page getPage() {
		if(page == null){
			driver = getDriver();
			page = new ZWHomeSearchPage(driver);
			page.setUrl("");
			page.setDriver(driver);
		}
		return page;
	}

	public Page getPage(String pUrl) {
		if(page == null){
			driver = getDriver();
			page = new ZWHomeSearchPage(driver);
			page.setUrl(pUrl);
			page.setDriver(driver);
		}
		return page;
	}
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
	public void clearPage() {
		// TODO Auto-generated method stub
		
	}
	
	@Test
	@Parameters({"searchPropertyDataFile"})
	public void testSearchPropoerty(String pDataFile) {
		getPage();		
		dataObject = getDataFile(pDataFile);
		searchProperty();
	}
	
	@Test
	@Parameters({"searchPropertyDataFile"})
	public void testSearchPropoertyAfterLoggedIn(String pDataFile) {
		getPage("");
		ZurpleWebsiteHeader webHeader = new ZurpleWebsiteHeader(driver);
		assertTrue(webHeader.clickOnCustomSearch(), "Unable to click on custom search button..");
		dataObject = getDataFile(pDataFile);
		ActionHelper.staticWait(5);
		searchProperty();
	}
	
	public void searchProperty() {
		assertTrue(page.isHomesForSaleHeadingVisible(), "Search page is not visible");
		assertTrue(page.selectInputType(dataObject.optString("search_by")), "Unable to select Input Type search criteria");
		ActionHelper.staticWait(7);
		String l_input_Search = dataObject.optString("input_search");
		if(dataObject.optString("search_by").equalsIgnoreCase("MLS")) {
			l_input_Search= EnvironmentFactory.configReader.getPropertyByName("zurple_mls_id");
		}
		assertTrue(page.typeInputString(l_input_Search), "Unable to type input string..");

		if(!dataObject.optString("minimum_price").isEmpty()) {
			assertTrue(page.selectMinPrice(dataObject.optString("minimum_price")), "Unable to select minimum price..");
		}
		if(!dataObject.optString("maximum_price").isEmpty()) {
			assertTrue(page.selectMaxPrice(dataObject.optString("maximum_price")), "Unable to select maximum price..");
		}
		
		if(!dataObject.optString("number_of_beds").isEmpty() || !dataObject.optString("number_of_baths").isEmpty() ||
				!dataObject.optString("square_feet").isEmpty() || !dataObject.optString("year_built").isEmpty() ||
				!dataObject.optString("lot_size").isEmpty() || !dataObject.optString("property_type").isEmpty()
				|| !dataObject.optString("features").isEmpty() || !dataObject.optString("style").isEmpty()
				|| !dataObject.optString("view").isEmpty()) {
			
			assertTrue(page.clickOnExpandSearchFields(), "Unable to click on expand search fields..");
			
			if(!dataObject.optString("number_of_beds").isEmpty()) {
				assertTrue(page.selectBedrooms(dataObject.optString("number_of_beds")), "Unable to select bedrooms..");
			}
			if(!dataObject.optString("number_of_baths").isEmpty()) {
				assertTrue(page.selectBathrooms(dataObject.optString("number_of_baths")), "Unable to select bathrooms..");
			}
			if(!dataObject.optString("square_feet").isEmpty()) {
				assertTrue(page.selectSquareFeet(dataObject.optString("square_feet")), "Unable to select square feet..");
			}
			if(!dataObject.optString("year_built").isEmpty()) {
				assertTrue(page.selectYearBuilt(dataObject.optString("year_built")), "Unable to select year built..");
			}
			if(!dataObject.optString("lot_size").isEmpty()) {
				assertTrue(page.selectLotSize(dataObject.optString("lot_size")), "Unable to select lot size..");
			}
			if(!dataObject.optString("property_type").isEmpty()) {
				assertTrue(page.selectPropertyType(dataObject.optString("property_type").split(",")), "Unable to select property type..");
			}
			if(!dataObject.optString("features").isEmpty()) {
				assertTrue(page.selectFeatures(dataObject.optString("features").split(",")), "Unable to select features..");
			}
			if(!dataObject.optString("style").isEmpty()) {
				assertTrue(page.selectStyle(dataObject.optString("style").split(",")), "Unable to select style.");
			}
			if(!dataObject.optString("view").isEmpty()) {
				assertTrue(page.selectView(dataObject.optString("view").split(",")), "Unable to select view..");
			}	
		}

		assertTrue(page.clickSearchButton(), "Unable to click on search button..");

	}

	@Test
	public void testConnectToZurpleDb() {
		assertTrue(!new DBHelperMethods(getEnvironment()).getMailgunNotifications().isEmpty(), "Didnt fetch any results for the email");
	}
	
}
