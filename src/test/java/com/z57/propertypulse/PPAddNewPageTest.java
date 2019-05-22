/**
 * 
 */
package com.z57.propertypulse;

import static org.testng.Assert.assertTrue;

import javax.annotation.Priority;

import org.json.JSONArray;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.z57.site.v2.NewPageCreatedFromPP;
import com.zurple.my.PageTest;

import resources.AbstractPage;
import resources.EnvironmentFactory;
import resources.ModuleCacheConstants;
import resources.ModuleCommonCache;
import resources.TestEnvironment;
import resources.alerts.BootstrapModal;
import resources.data.z57.PPAddPageData;


/**
 * @author adar
 *
 */
public class PPAddNewPageTest extends PageTest{

	private WebDriver driver;
	private PPAddNewPage page;
	private PPWebsitePage ppWebsitePage;
	private PPHeader header;
	
	@Override
	public AbstractPage getPage() {
		if(page == null){
			driver = getDriver();
			page = new PPAddNewPage(driver);
			ppWebsitePage = new PPWebsitePage(driver);
			header = new PPHeader(driver);
		}
		return page;
	}
	public AbstractPage getPage(String pUrl) {
		if(page == null){
			driver = getDriver();
			page = new PPAddNewPage(driver);
			ppWebsitePage = new PPWebsitePage(driver);
			header = new PPHeader(driver);
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
	@Parameters({"addNewPageDataFile"})
	public void testAddNewPage(String pDataFile) {
		PPAddPageData ppAddPageData = new PPAddPageData(pDataFile).getAddPageData();
		
		String lPageTitle = updateName(ppAddPageData.getPageTitle());
		String lPageBody = ppAddPageData.getPageBody();
		boolean isLeadCapture = ppAddPageData.getLeadCaptureEnabled().equalsIgnoreCase("On")?true:false;
		String lLeadCaptureEnabled = ppAddPageData.getLeadCaptureEnabled();
		String lLeadCaptureStrength =ppAddPageData.getLeadStrength();
		String lLeadCaptureTrigger = ppAddPageData.getLeadCapturePrompt();
		
		getPage("");
		
		ModuleCommonCache.updateCacheForModuleObject(getThreadId().toString(), ModuleCacheConstants.PageTitle, lPageTitle);
		
		assertTrue(header.clickOnWebsite(), "Unable to click on Website tab..");
		
		assertTrue(ppWebsitePage.isWebSitePage(), "Unable to open website page..");
		assertTrue(ppWebsitePage.clickOnAddNewButton(), "Unable to click on Add new button..");
		
		assertTrue(page.isAddNewPage(), "Add new page is not open..");
		assertTrue(page.typePageTitle(lPageTitle), "Unable to type page title ..");
		assertTrue(page.typeInBody(lPageBody), "Unable to type in page body ..");
		
		//Lead Capture strength
		if(isLeadCapture) {
			configureLeadCaptureSettings(lLeadCaptureEnabled, lLeadCaptureStrength, lLeadCaptureTrigger);
		}
		
		assertTrue(page.clickOnPublishButton(), "Unable to click on Publish..");
		assertTrue(page.isPublishSuccessful(), "Unable to publish the page. Verification failed...");
		
		String pageUrl = page.getPageUrl();
		
		if(isLeadCapture) {
			verifyLeadCaptureSettingsOnWebsite(pageUrl, lLeadCaptureEnabled, lLeadCaptureStrength);
		}else {
			verifyPageOnWebsite(pageUrl, lPageTitle, lPageBody);
		}
		
	}
	
	@Test(priority=1)
	@Parameters({"addNewPageDataFile"})
	public void testEditPage(String pDataFile) {
		if(page!=null) {
			driver.navigate().to(EnvironmentFactory.configReader.getPropertyByName("z57_pp_base_url"));
		}else {
			getPage("");
		}
		
		assertTrue(header.clickOnWebsite(), "Unable to click on Website tab..");
		
		PPAddPageData ppAddPageData = new PPAddPageData(pDataFile).getAddPageData();
		
		String lPageTitle = ModuleCommonCache.getCachedModuleObject(getThreadId().toString(), ModuleCacheConstants.PageTitle).toString();
		String lPageBody = ppAddPageData.getPageBodyUpdate();
		boolean deletePage = ppAddPageData.isPageDelete();
		boolean isLeadCapture = ppAddPageData.getLeadCaptureEnabled().equalsIgnoreCase("On")?true:false;
		String lLeadCaptureEnabled = ppAddPageData.getLeadCaptureEnabled();
		String lLeadCaptureTrigger = ppAddPageData.getLeadCapturePrompt();
		String lLeadCaptureStrengthUpdated = ppAddPageData.getLeadStrengthUpdate();
		
		assertTrue(ppWebsitePage.isWebSitePage(), "Unable to open website page..");
		assertTrue(ppWebsitePage.typeInSearchField(lPageTitle), "Unable to type page title in search field..");
		assertTrue(ppWebsitePage.clickOnSearchButton(), "Unable to click on search button..");
		assertTrue(ppWebsitePage.clickOnRowPage(lPageTitle), "Unable to click on page row button..");
		
		assertTrue(page.isEditPage(), "Add new page is not open..");
		assertTrue(page.typeInBody(lPageBody), "Unable to type in page body ..");
		
		if(isLeadCapture) {
			configureLeadCaptureSettings(lLeadCaptureEnabled, lLeadCaptureStrengthUpdated, lLeadCaptureTrigger);
		}
		
		assertTrue(page.clickOnPublishButton(), "Unable to click on Publish..");
		assertTrue(page.isPublishSuccessful(), "Unable to publish the page. Verification failed...");
		
		String pageUrl = page.getPageUrl();
		String lEditPageUrl = driver.getCurrentUrl();
		
		if(isLeadCapture) {
			verifyLeadCaptureSettingsOnWebsite(pageUrl, lLeadCaptureEnabled, lLeadCaptureStrengthUpdated);
		}else {
			verifyPageOnWebsite(pageUrl, lPageTitle, lPageBody);
		}
		
		if(deletePage) {
			driver.navigate().to(lEditPageUrl);
			assertTrue(page.clickOnMoveToTrashButton(), "Unable to click on move to trash button...");
			assertTrue(ppWebsitePage.isPageDeletedSuccessfully(), "Page is not deleted, Success message is not displayed...");
			
		}
	}
	
	
	private void verifyPageOnWebsite(String pPageURL,String pPageTitle, String pPageBody) {
		
		//Switching to wordpress website
		driver.navigate().to(pPageURL);
		NewPageCreatedFromPP newPageCreated = new NewPageCreatedFromPP(driver);
		
		assertTrue(newPageCreated.isPageLoaded(), "Website page is not loaded successfully..");
		assertTrue(newPageCreated.verifyPageHeading(pPageTitle), "Unable to verify Page title..");
		assertTrue(newPageCreated.verifyPageBody(pPageBody), "Unable to verify Page body content..");
	}
	
	private void verifyLeadCaptureSettingsOnWebsite(String pPageURL,String pLeadCaptureEnabled, String pLeadCaptureStrength) {
		//Switching to wordpress website
		driver.navigate().to(pPageURL);
		NewPageCreatedFromPP newPageCreated = new NewPageCreatedFromPP(driver);
		assertTrue(newPageCreated.isPageLoaded(), "Website page is not loaded successfully..");
		assertTrue(verifyLeadCaptureSettingsOnWebsite(pLeadCaptureEnabled, pLeadCaptureStrength), "Unable to verify lead capture settings on website..");
	}
	
	private void configureLeadCaptureSettings(String pLeadCaptureEnabled, String pLeadCaptureStrength, String pLeadCaptureTrigger) {
		assertTrue(page.clickAndSelectLeadCaptureEnabled(pLeadCaptureEnabled),"Unable to select lead capture enable option.."+pLeadCaptureEnabled);
		assertTrue(page.clickAndSelectCaptureLeadStrength(pLeadCaptureStrength),"Unable to select lead capture strength option.."+pLeadCaptureStrength);
		assertTrue(page.clickAndSelectCaptureLeadTrigger(pLeadCaptureTrigger),"Unable to select lead capture trigger option.."+pLeadCaptureTrigger);
	}
	
	private boolean verifyLeadCaptureSettingsOnWebsite(String pLeadCaptureEnabled, String pLeadCaptureStrength) {
		
		boolean lLeadCaptureSettingVerified = false;
		boolean lLeadCaptureEnable = pLeadCaptureEnabled.equalsIgnoreCase("On")?true:false;
		BootstrapModal bootstrapModalObj = new BootstrapModal(driver);
		boolean bootStrapModalIsShown = bootstrapModalObj.checkBootsrapModalIsShown();
		
		if(bootStrapModalIsShown && lLeadCaptureEnable) {
			boolean isModalClosed = bootstrapModalObj.closeModal();
			if(!isModalClosed && pLeadCaptureStrength.equalsIgnoreCase("Strong")) {
				lLeadCaptureSettingVerified = true;
			}else if(isModalClosed && pLeadCaptureStrength.equalsIgnoreCase("Medium")) {
				lLeadCaptureSettingVerified = true;
			}else {
				lLeadCaptureSettingVerified = false;
			}
		}else if(!bootStrapModalIsShown && !lLeadCaptureEnable) {
			lLeadCaptureSettingVerified = true;
		}
		
		return lLeadCaptureSettingVerified;
	}
	

}
