package com.zurple.website;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.SkipException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zurple.admin.ZBOVariableLeadCapturePage;

import resources.AbstractPage;
import resources.ModuleCacheConstants;
import resources.ModuleCommonCache;
import resources.utility.ActionHelper;
import resources.utility.AutomationLogger;
import us.zengtest1.Page;
import us.zengtest1.PageTest;

public class ZWVariableLeadCapturePageTest extends PageTest{
	
	private WebDriver driver;
	private JSONObject dataObject;
	ZWVariableLeadCapturePage page;
	
	@Override
	public Page getPage() {
		if(page==null) {
			driver = getDriver();
			page = new ZWVariableLeadCapturePage(driver);
			page.setUrl("");
			page.setDriver(driver);
		}
		return page;
	}
	
	public Page getPage(String pUrl) {
		if(page==null) {
			driver = getDriver();
			page = new ZWVariableLeadCapturePage(driver);
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
	public void testVerifyVLCModalForSoftViews0() {
		getPage();
		setupBOLogin(true);
		page.VLCSetup("0", "soft");
		page=null;
		getPage();
		assertTrue(page.pageViews("0"),"Lead capture modal soft doesn't appear for views 0.."); 
		assertTrue(page.checkViewsAndMethod(1),"VLC method soft is not working for views 0..");
		driver.manage().deleteAllCookies();
	}
	
	@Test
	public void testVerifyVLCModalForSoftViews1() {
		getPage();
		setupBOLogin(false);
		page.VLCSetup("1", "soft");
		page=null;
		getPage();
		assertTrue(page.pageViews("1"),"Lead capture modal soft doesn't appear for views 1.."); 
		assertTrue(page.checkViewsAndMethod(0),"VLC method soft is not working for views 1..");
		driver.manage().deleteAllCookies();
	}
	
	@Test
	public void testVerifyVLCModalForSoftViews2() {
		getPage();
		setupBOLogin(false);
		page.VLCSetup("2", "soft");
		page=null;
		getPage();
		assertTrue(page.pageViews("2"),"Lead capture modal soft doesn't appear for views 2.."); 
		assertTrue(page.checkViewsAndMethod(0),"VLC method soft is not working for views 2..");
		driver.manage().deleteAllCookies();
	}
	
	@Test
	public void testVerifyVLCModalForSoftViews3() {
		getPage();
		setupBOLogin(false);
		page.VLCSetup("3", "soft");
		page=null;
		getPage();
		assertTrue(page.pageViews("3"),"Lead capture modal soft doesn't appear for views 3.."); 
		assertTrue(page.checkViewsAndMethod(0),"VLC method soft is not working for views 3..");
		driver.manage().deleteAllCookies();
	}
	
	@Test
	public void testVerifyVLCModalForSoftViews4() {
		getPage();
		setupBOLogin(false);
		page.VLCSetup("4", "soft");
		page=null;
		getPage();
		assertTrue(page.pageViews("4"),"Lead capture modal soft doesn't appear for views 4.."); 
		assertTrue(page.checkViewsAndMethod(0),"VLC method soft is not working for views 4..");
		driver.manage().deleteAllCookies();
	}
	
	@Test
	public void testVerifyVLCModalForSoftViews5() {
		getPage();
		setupBOLogin(false);
		page.VLCSetup("5", "soft");
		page=null;
		getPage();
		assertTrue(page.pageViews("5"),"Lead capture modal soft doesn't appear for views 5.."); 
		assertTrue(page.checkViewsAndMethod(0),"VLC method soft is not working for views 5..");
		driver.manage().deleteAllCookies();
	}
	
	@Test
	public void testVerifyVLCModalForStrongViews0() {
		getPage();
		setupBOLogin(false);
		page.VLCSetup("0", "strong");
		page=null;
		getPage();
		assertTrue(page.pageViews("0"),"Lead capture modal strong doesn't appear for views 0.."); 
		assertFalse(page.checkViewsAndMethod(1),"VLC method Strong is not working for views 0..");
		driver.manage().deleteAllCookies();
	}
	
	@Test
	public void testVerifyVLCModalForStrongViews1() {
		getPage();
		setupBOLogin(false);
		page.VLCSetup("1", "strong");
		page=null;
		getPage();
		assertTrue(page.pageViews("1"),"Lead capture modal strong doesn't appear for views 1.."); 
		assertFalse(page.checkViewsAndMethod(0),"VLC method Strong is not working for views 1..");
		driver.manage().deleteAllCookies();
	}
	
	@Test
	public void testVerifyVLCModalForStrongViews2() {
		getPage();
		setupBOLogin(false);
		page.VLCSetup("2", "strong");
		page=null;
		getPage();
		assertTrue(page.pageViews("2"),"Lead capture modal strong doesn't appear for views 2.."); 
		assertFalse(page.checkViewsAndMethod(0),"VLC method Strong is not working for views 2..");
		driver.manage().deleteAllCookies();
	}
	
	@Test
	public void testVerifyVLCModalForStrongViews3() {
		getPage();
		setupBOLogin(false);
		page.VLCSetup("3", "strong");
		page=null;
		getPage();
		assertTrue(page.pageViews("3"),"Lead capture modal strong doesn't appear for views 3.."); 
		assertFalse(page.checkViewsAndMethod(0),"VLC method Strong is not working for views 3..");
		driver.manage().deleteAllCookies();
	}
	
	@Test
	public void testVerifyVLCModalForStrongViews4() {
		getPage();
		setupBOLogin(false);
		page.VLCSetup("4", "strong");
		page=null;
		getPage();
		assertTrue(page.pageViews("4"),"Lead capture modal strong doesn't appear for views 4.."); 
		assertFalse(page.checkViewsAndMethod(0),"VLC method Strong is not working for views 4..");
		driver.manage().deleteAllCookies();
	}
	
	@Test
	public void testVerifyVLCModalForStrongViews5() {
		getPage();
		setupBOLogin(false);
		page.VLCSetup("5", "strong");
		page=null;
		getPage();
		assertTrue(page.pageViews("5"),"Lead capture modal doesn't appear for views 5.."); 
		assertFalse(page.checkViewsAndMethod(0),"VLC method Strong is not working for views 5..");
		driver.manage().deleteAllCookies();
	}
	
	@Test
	public void testVerifyVLCModalForRegisterViews0() {
		getPage();
		setupBOLogin(true);
		page.VLCSetup("0", "none");
		page=null;
		getPage();
		assertTrue(page.pageViewsForRegister("0"),"Lead capture page Register doesn't appear for views 0.."); 
		driver.manage().deleteAllCookies();
	}
	
	@Test
	public void testVerifyVLCModalForRegisterViews1() {
		getPage();
		setupBOLogin(false);
		page.VLCSetup("1", "none");
		page=null;
		getPage();
		assertTrue(page.pageViewsForRegister("1"),"Lead capture page Register doesn't appear for views 1.."); 
		driver.manage().deleteAllCookies();
	}
	
	@Test
	public void testVerifyVLCModalForRegisterViews2() {
		getPage();
		setupBOLogin(false);
		page.VLCSetup("2", "none");
		page=null;
		getPage();
		assertTrue(page.pageViewsForRegister("2"),"Lead capture page Register doesn't appear for views 2.."); 
		driver.manage().deleteAllCookies();
	}
	
	@Test
	public void testVerifyVLCModalForRegisterViews3() {
		getPage();
		setupBOLogin(false);
		page.VLCSetup("3", "none");
		page=null;
		getPage();
		assertTrue(page.pageViewsForRegister("3"),"Lead capture page Register doesn't appear for views 3.."); 
		driver.manage().deleteAllCookies();
	}
	
	@Test
	public void testVerifyVLCModalForRegisterViews4() {
		getPage();
		setupBOLogin(false);
		page.VLCSetup("4", "none");
		page=null;
		getPage();
		assertTrue(page.pageViewsForRegister("4"),"Lead capture page Register doesn't appear for views 4.."); 
		driver.manage().deleteAllCookies();
	}
	
	@Test
	public void testVerifyVLCModalForRegisterViews5() {
		getPage();
		setupBOLogin(false);
		page.VLCSetup("5", "none");
		page=null;
		getPage();
		assertTrue(page.pageViewsForRegister("5"),"Lead capture page Register doesn't appear for views 5.."); 
		driver.manage().deleteAllCookies();
	}
	
	@Test
	public void testVerifyRevertSettings() {
		getPage();
		setupBOLogin(false);
		page.VLCSetup("3", "soft");
		page=null;
		getPage();
		assertTrue(page.pageViews("3"),"Lead capture modal doesn't appear for views 3.."); 
		assertTrue(page.checkViewsAndMethod(0),"VLC method soft is not working for views 3..");
	}
	
	public void setupBOLogin(boolean doLogin) {
		String siteSettings = getIsProd()?"/pagemgr/leadcapture/site_id/5334":"/pagemgr/leadcapture/site_id/3215";
		ActionHelper.openUrlInCurrentTab(driver, getBOURL()+siteSettings);
		if(doLogin) {
			if(!page.doLogin(getBOUsername(), getBOPassword())) {
				throw new SkipException("Skipping the test becasuse [Login] pre-condition was failed.");
			}
		}
	}
	
	@Test
	public void closeBrowser() {
		closeCurrentBrowser();
	}

}
