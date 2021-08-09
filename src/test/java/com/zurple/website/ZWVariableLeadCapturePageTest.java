package com.zurple.website;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.SkipException;
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
		setupBOLogin();
		page.VLCSetup("0", "soft");
		assertTrue(page.pageViews("0"),"Lead capture modal doesn't appear for views 0.."); 
		assertTrue(page.checkViewsAndMethod(),"VLC method soft is not working for views 0..");
	}
	
	@Test
	public void testVerifyVLCModalForSoftViews1() {
		getPage();
		assertTrue(page.pageViews("1"),"Lead capture modal doesn't appear for views 1.."); 
		assertTrue(page.checkViewsAndMethod(),"VLC method soft is not working for views 1..");
	}
	
	@Test
	public void testVerifyVLCModalForSoftViews2() {
		getPage();
		assertTrue(page.pageViews("2"),"Lead capture modal doesn't appear for views 2.."); 
		assertTrue(page.checkViewsAndMethod(),"VLC method soft is not working for views 2..");
	}
	
	@Test
	public void testVerifyVLCModalForSoftViews3() {
		getPage();
		assertTrue(page.pageViews("3"),"Lead capture modal doesn't appear for views 3.."); 
		assertTrue(page.checkViewsAndMethod(),"VLC method soft is not working for views 3..");
	}
	
	@Test
	public void testVerifyVLCModalForSoftViews4() {
		getPage();
		assertTrue(page.pageViews("4"),"Lead capture modal doesn't appear for views 4.."); 
		assertTrue(page.checkViewsAndMethod(),"VLC method soft is not working for views 4..");
	}
	
	@Test
	public void testVerifyVLCModalForSoftViews5() {
		getPage();
		assertTrue(page.pageViews("5"),"Lead capture modal doesn't appear for views 5.."); 
		assertTrue(page.checkViewsAndMethod(),"VLC method soft is not working for views 5..");
	}
	
	@Test
	public void testVerifyVLCModalForStrongViews0() {
		getPage();
		assertTrue(page.pageViews("0"),"Lead capture modal doesn't appear for views 0.."); 
		assertTrue(page.checkViewsAndMethod(),"VLC method Strong is not working for views 0..");
	}
	
	@Test
	public void testVerifyVLCModalForStrongViews1() {
		getPage();
		assertTrue(page.pageViews("1"),"Lead capture modal doesn't appear for views 1.."); 
		assertTrue(page.checkViewsAndMethod(),"VLC method Strong is not working for views 1..");
	}
	
	@Test
	public void testVerifyVLCModalForStrongViews2() {
		getPage();
		assertTrue(page.pageViews("2"),"Lead capture modal doesn't appear for views 2.."); 
		assertTrue(page.checkViewsAndMethod(),"VLC method Strong is not working for views 2..");
	}
	
	@Test
	public void testVerifyVLCModalForStrongViews3() {
		getPage();
		assertTrue(page.pageViews("3"),"Lead capture modal doesn't appear for views 3.."); 
		assertTrue(page.checkViewsAndMethod(),"VLC method Strong is not working for views 3..");
	}
	
	@Test
	public void testVerifyVLCModalForStrongViews4() {
		getPage();
		assertTrue(page.pageViews("4"),"Lead capture modal doesn't appear for views 4.."); 
		assertTrue(page.checkViewsAndMethod(),"VLC method Strong is not working for views 4..");
	}
	
	@Test
	public void testVerifyVLCModalForStrongViews5() {
		getPage();
		assertTrue(page.pageViews("5"),"Lead capture modal doesn't appear for views 5.."); 
		assertTrue(page.checkViewsAndMethod(),"VLC method Strong is not working for views 5..");
	}
	
	public void setupBOLogin() {
		String siteSettings = getIsProd()?"/pagemgr/leadcapture/site_id/5334":"/pagemgr/leadcapture/site_id/3215";
		ActionHelper.openUrlInCurrentTab(driver, getBOURL()+siteSettings);
		if(!getLoginPage().doLogin(getBOUsername(), getBOPassword())) {
			throw new SkipException("Skipping the test becasuse [Login] pre-condition was failed.");
		}
	}

}
