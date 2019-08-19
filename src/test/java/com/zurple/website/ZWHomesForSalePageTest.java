/**
 * 
 */
package com.zurple.website;

import static org.testng.Assert.assertTrue;

import java.util.Random;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import resources.utility.AutomationLogger;
import us.zengtest1.Page;
import us.zengtest1.PageTest;

/**
 * @author adar
 *
 */
public class ZWHomesForSalePageTest extends PageTest{
	
	private WebDriver driver;
	private ZWHomesForSalePage page;
	
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
			page = new ZWHomesForSalePage(driver);
//			page.setUrl("");
//			page.setDriver(driver);
		}
		return page;
	}

	@Override
	public void clearPage() {
		// TODO Auto-generated method stub
		
	}
	
	@Test
	public void testHomesForSale() {
		getPage();
		assertTrue(page.isHomeForSalePage(),"Homes for Sale page is not found..");
		assertTrue(page.verifyNavigationTabs(),"Unable to verify navigation tabs on Homes for Sale page..");
		if(page.getTotalListings()>0) {
			assertTrue(page.clickOnListing(getRandomNumber(page.getPageNumOfProps())),"Unable to click on the listing..");

		}else {
			AutomationLogger.error("No Listing found on search criteria..");
		}
	}
	
	private int getRandomNumber(int pRange) {
		// Obtain a number between [0 - 49].
		int lRandomNumber = new Random().nextInt(pRange);
		// Add 1 to the result to get a number from the required range
		// (i.e., [1 - 50]).
//		lRandomNumber += 1;
		return lRandomNumber;
	}

}
