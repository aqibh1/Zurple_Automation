/**
 * 
 */
package com.zurple.backoffice.admin;

import static org.testng.Assert.assertTrue;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.zurple.admin.ZAAdsManagerPage;
import com.zurple.my.PageTest;

import resources.AbstractPage;

/**
 * @author darrraqi
 *
 */
public class ZAAdsManagerPageTest extends PageTest{
	private WebDriver driver;
	private JSONObject dataObject;
	private ZAAdsManagerPage page;

	@Override
	public AbstractPage getPage() {
		if(page==null) {
			driver = getDriver();
			page = new ZAAdsManagerPage(driver);
			page.setDriver(driver);
		}
		return page;
	}
	public AbstractPage getPage(String pUrl) {
		if(page==null) {
			driver = getDriver();
			page = new ZAAdsManagerPage(driver);
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
	public void testVerifyStatusOfTheAd() {
		getPage("/admin/ads");
		searchAdPreCondition();
		assertTrue(page.getAdStatus().equalsIgnoreCase("PAUSED"), "AD Status is not PAUSED");
	}
	
	private void searchAdPreCondition() {
		String l_admin_id = getAdmin();
		String l_ad_id = getAdId();
		if(page.typeAdminId(l_admin_id) && page.typeAdId(l_ad_id) && page.clickOnSearchButton()) {
			
		}else {
			throw new SkipException("Skipping the test becasuse [Search Ad] pre-condition was failed.");
		}
	}
	private String getAdId() {
		return "";
	}
	private String getAdmin() {
		return "";
}
}
