/**
 * 
 */
package com.zurple.website;

import static org.testng.Assert.assertTrue;

import org.json.JSONObject;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import com.zurple.my.PageTest;

import resources.AbstractPage;

/**
 * @author adar
 *
 */
public class ZWHomeSearchPageTest extends PageTest{
	private WebDriver driver;
	private ZWHomeSearchPage page;
	private JSONObject dataObject;
	
	@Override
	public AbstractPage getPage() {
		if(page == null){
			driver = getDriver();
			page = new ZWHomeSearchPage(driver);
			page.setUrl("");
			page.setDriver(driver);
		}
		return page;
	}

	@Override
	public void clearPage() {
		// TODO Auto-generated method stub
		
	}
	
	@Test
	public void testSearchPropoerty() {
		dataObject = getDataFile("");
	}
	
	private void searchProperty() {
		assertTrue(page.selectInputType(dataObject.optString("input_type")), "Unable to select Input Type search criteria");
		assertTrue(page.typeInputString(dataObject.optString("input_string")), "Unable to type input string..");
	
		if(!dataObject.optString("min_price").isEmpty()) {
			assertTrue(page.selectMinPrice(dataObject.optString("min_price")), "Unable to select minimum price..");
		}
		if(!dataObject.optString("max_price").isEmpty()) {
			assertTrue(page.selectMaxPrice(dataObject.optString("max_price")), "Unable to select maximum price..");
		}
		
		if(!dataObject.optString("bedrooms").isEmpty() || !dataObject.optString("bathrooms").isEmpty() ||
				!dataObject.optString("square_feet").isEmpty() || !dataObject.optString("year_built").isEmpty() ||
				!dataObject.optString("lot_size").isEmpty() || !dataObject.optString("property_type").isEmpty()
				|| !dataObject.optString("features").isEmpty() || !dataObject.optString("style").isEmpty()
				|| !dataObject.optString("view").isEmpty()) {
			
			assertTrue(page.clickOnExpandSearchFields(), "Unable to click on expand search fields..");
			
			if(!dataObject.optString("bedrooms").isEmpty()) {
				assertTrue(page.selectBedrooms(dataObject.optString("bedrooms")), "Unable to select bedrooms..");
			}
			if(!dataObject.optString("bathrooms").isEmpty()) {
				assertTrue(page.selectBathrooms(dataObject.optString("bathrooms")), "Unable to select bathrooms..");
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

}
