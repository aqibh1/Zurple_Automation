/**
 * 
 */
package com.zurple.website;

import static org.testng.Assert.assertTrue;

import java.util.Random;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import resources.ModuleCacheConstants;
import resources.ModuleCommonCache;
import resources.utility.ActionHelper;
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
		page = null;
		if(page == null){
			driver = getDriver();
			page = new ZWHomesForSalePage(driver);
			//This is comment out because this method is only called up after search button is clicked
			// to remain on the same page we are not setting driver.
//			+page.setUrl("");
//			page.setDriver(driver);
		}
		return page;
	}
	public Page getPage(String pUrl) {
		if(page == null){
			driver = getDriver();
			page = new ZWHomesForSalePage(driver);
			page.setUrl(pUrl);
			page.setDriver(driver);
		}
		return page;
	}
	@Override
	public void clearPage() {
		// TODO Auto-generated method stub
		
	}
	
	@Test(groups="testHomesForSale")
	@Parameters({"searchPropertyDataFile"})
	public void testHomesForSale(@Optional String pDataFile) {
		getPage();
		dataObject = getDataFile(pDataFile);
		assertTrue(page.isHomeForSalePage(),"Homes for Sale page is not found..");
		assertTrue(page.verifyNavigationTabs(),"Unable to verify navigation tabs on Homes for Sale page..");
		ActionHelper.staticWait(20);
		int totalListings = page.getTotalListings();
		boolean isClickSuccessful = false;
		int counter =0;
		AutomationLogger.info("Total listings are " + totalListings);
		do {
			counter++;
			if(totalListings>0) {
				int props = page.getPageNumOfProps();
				AutomationLogger.info("Page number of props are " + props);
				int rand = getRandomNumber(props);
				rand = rand>2?rand-1:rand;
				AutomationLogger.info("Random number is " + rand);
				isClickSuccessful = page.clickOnListing(rand);
				

			}else {
				AutomationLogger.error("No Listing found on search criteria..");
			}
		}while(!isClickSuccessful &&counter<5);
		assertTrue(isClickSuccessful,"Unable to click on the listing..");
		
		if(dataObject.optBoolean("view_address")) {
			ActionHelper.staticWait(30);
			assertTrue(new ZWPropertyDetailPage(driver).clickOnViewAddress(),"Unable to click on the listing..");

		}
		ActionHelper.staticWait(30);
	}
	
	@Test(groups="testHomesForSale")
	public void testHomesForSaleSkip() {
		AutomationLogger.info("Skipiing test homes for sale for Address and MLS realted search.");
		assertTrue(true,"Skipiing test homes for sale for Address and MLS realted search.");
		ActionHelper.staticWait(10);
	}
	@Test
	@Parameters({"captureLeadData"})
	public void testCaptureLeadFromSearchResults(String pDataFile) {
		AutomationLogger.startTestCase("Lead Capture from Search Results");
		getPage("/CA/Carlsbad");
		dataObject = getDataFile(pDataFile);
		
		String lName = updateName(dataObject.optString("name"));
		String lEmail = updateEmail(dataObject.optString("email"));
		String lThreadId = getThreadId().toString();
		
		assertTrue(page.isHomeForSalePage(),"Homes for Sale page is not found..");
		ActionHelper.RefreshPage(driver);
		ActionHelper.RefreshPage(driver);
		assertTrue(page.leadCaptureForm.isLeadCaptureFormIsVisible(),"Lead capture form is not visible..");
		assertTrue(page.leadCaptureForm.typeName(lName),"Lead capture form is not visible..");
		ActionHelper.staticWait(2);
		assertTrue(page.leadCaptureForm.typeEmail(lEmail),"Lead capture form is not visible..");
		ActionHelper.staticWait(2);
		assertTrue(page.leadCaptureForm.typePhone(dataObject.optString("phone")),"Lead capture form is not visible..");
		assertTrue(page.leadCaptureForm.isAlreadyRegisteredLinkVisible(),"Already register link not visible..");
		assertTrue(page.leadCaptureForm.isSubscribed(),"Subscribed checkbox is not checked..");
		assertTrue(page.leadCaptureForm.clickOnRegisterButton(),"Unable to click on register button..");
		
		assertTrue(new ZWRegisterUserPage(driver).isRegisterSuccessfully(),"Registration of user is unsuccessful..");
		
		String lLeadId = driver.getCurrentUrl().split("lead_id=")[1];
		
		ModuleCommonCache.updateCacheForModuleObject(lThreadId, ModuleCacheConstants.ZurpleLeadEmail, lEmail);
		ModuleCommonCache.updateCacheForModuleObject(lThreadId,lEmail,lLeadId);
		ModuleCommonCache.updateCacheForModuleObject(lThreadId, ModuleCacheConstants.ZurpleLeadName, lName);
		
		AutomationLogger.endTestCase();
		
		
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
