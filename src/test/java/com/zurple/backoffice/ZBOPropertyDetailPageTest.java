/**
 * 
 */
package com.zurple.backoffice;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.zurple.backoffice.properties.ZBOPropertyDetailPage;
import com.zurple.my.PageTest;
import com.zurple.website.ZWPropertyDetailPage;

import resources.AbstractPage;
import resources.EnvironmentFactory;
import resources.utility.ZurpleListingConstants;

/**
 * @author adar
 *
 */
public class ZBOPropertyDetailPageTest extends PageTest{
	
	ZBOPropertyDetailPage page;
	private WebDriver driver;
	
	@Override
	public AbstractPage getPage() {
		// TODO Auto-generated method stub
		if(page==null) {
			driver = getDriver();
			page = new ZBOPropertyDetailPage(driver);
			page.setUrl("");
			page.setDriver(driver);
		}
		return page;
	}
	
	public AbstractPage getPage(String pUrl) {
		if(page==null) {
			driver = getDriver();
			page = new ZBOPropertyDetailPage(driver);
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
	public void testAddAndVerifyPropertyNote() {
		getPage();
		String lNote = updateName("Property note displayed on website");
		String lUrl = ZurpleListingConstants.zurple_bo_property_url_staging;
		String lWebsiteUrl = ZurpleListingConstants.zurple_website_property_url_staging;
		if(getIsProd()) {
			lUrl = ZurpleListingConstants.zurple_bo_property_url_prod;
			lWebsiteUrl = ZurpleListingConstants.zurple_website_property_url_prod;
		}
		page=null;
		getPage(lUrl);
		assertTrue(page.isPropertyDetailPageVisible(), "Back office property detail page is not visible...");
		assertTrue(page.typeNote(lNote), "Unable to type property note...");
		assertTrue(page.clickOnSaveButton(), "Unable to click on save button...");
		String lZurpleWebUrl = EnvironmentFactory.configReader.getPropertyByName("zurple_site_base_url")+lWebsiteUrl;
		driver.navigate().to(lZurpleWebUrl);
		ZWPropertyDetailPage propertyDetailPage = new ZWPropertyDetailPage(driver);
		assertTrue(propertyDetailPage.isScheduleShowingButtonVisible(), "Property detail page is not visible..");
		assertTrue(propertyDetailPage.verifyNotes(lNote), "Property Note not found on website...");
	}
}
