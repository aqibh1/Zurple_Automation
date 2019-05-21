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
		
		getPage("");
		
		ModuleCommonCache.updateCacheForModuleObject(getThreadId().toString(), ModuleCacheConstants.PageTitle, lPageTitle);
		
		assertTrue(header.clickOnWebsite(), "Unable to click on Website tab..");
		
		assertTrue(ppWebsitePage.isWebSitePage(), "Unable to open website page..");
		assertTrue(ppWebsitePage.clickOnAddNewButton(), "Unable to click on Add new button..");
		
		assertTrue(page.isAddNewPage(), "Add new page is not open..");
		assertTrue(page.typePageTitle(lPageTitle), "Unable to type page title ..");
		assertTrue(page.typeInBody(lPageBody), "Unable to type in page body ..");
		
		assertTrue(page.clickOnPublishButton(), "Unable to click on Publish..");
		assertTrue(page.isPublishSuccessful(), "Unable to publish the page. Verification failed...");
		
		String pageUrl = page.getPageUrl();
		verifyPageOnWebsite(pageUrl, lPageTitle, lPageBody);
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
		
		assertTrue(ppWebsitePage.isWebSitePage(), "Unable to open website page..");
		assertTrue(ppWebsitePage.typeInSearchField(lPageTitle), "Unable to type page title in search field..");
		assertTrue(ppWebsitePage.clickOnSearchButton(), "Unable to click on search button..");
		assertTrue(ppWebsitePage.clickOnRowPage(lPageTitle), "Unable to click on page row button..");
		
		assertTrue(page.isEditPage(), "Add new page is not open..");
		assertTrue(page.typeInBody(lPageBody), "Unable to type in page body ..");
		assertTrue(page.clickOnPublishButton(), "Unable to click on Publish..");
		assertTrue(page.isPublishSuccessful(), "Unable to publish the page. Verification failed...");
		
		String pageUrl = page.getPageUrl();
		String lEditPageUrl = driver.getCurrentUrl();
		verifyPageOnWebsite(pageUrl, lPageTitle, lPageBody);
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
	

}
