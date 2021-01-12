/**
 * 
 */
package com.zurple.website;

import static org.testng.Assert.assertTrue;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import resources.AbstractPage;
import us.zengtest1.Page;
import us.zengtest1.PageTest;

/**
 * @author adar
 *
 */
public class ZWSoldHomesPageTest extends PageTest{
	
	private WebDriver driver;
	private ZWSoldHomesPage page;
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
		if(page==null) {
			driver = getDriver();
			page = new ZWSoldHomesPage(driver);
			page.setUrl("");
		}
		return page;
	}
	
	public AbstractPage getPage(String pUrl) {
		if(page==null) {
			driver = getDriver();
			page = new ZWSoldHomesPage(driver);
			page.setUrl(pUrl);
			page.setDriver(driver);
		}
		return page;
	}
	

	@Override
	public void clearPage() {
		// TODO Auto-generated method stub
		
	}
	@Test
	public void testSearchSoldHomes() {
		getPage("/sold-homes");
		assertTrue(page.isSoldHomesPageVisible(), "Sold Home page is not visible..");
		assertTrue(page.clickOnModifySearchButton(), "Unable to click on modify search button..");
		assertTrue(page.getModifySearchForm().isSearchFormVisible(), "Modify Search form is not visible..");
		assertTrue(page.getModifySearchForm().clickOnSearchButton(), "Unable to click on search button..");
	}

}
