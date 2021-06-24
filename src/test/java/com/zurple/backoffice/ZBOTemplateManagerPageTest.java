/**
 * 
 */
package com.zurple.backoffice;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.zurple.backoffice.marketing.ZBOTemplateManagerPage;
import com.zurple.my.PageTest;

import resources.AbstractPage;

/**
 * @author darrraqi
 *
 */
public class ZBOTemplateManagerPageTest extends PageTest{
	ZBOTemplateManagerPage page;
	WebDriver driver;
	
	@Override
	public AbstractPage getPage() {
		if(page==null) {
			driver = getDriver();
			page = new ZBOTemplateManagerPage(driver);
			setLoginPage(driver);
			page.setUrl("");
			page.setDriver(driver);
		}
		return page;
	}
	public AbstractPage getPage(String pUrl) {
		if(page==null) {
			driver = getDriver();
			page = new ZBOTemplateManagerPage(driver);
			setLoginPage(driver);
			page.setUrl(pUrl);
			page.setDriver(driver);
		}
		return page;
	}

	@Override
	public void clearPage() {
		// TODO Auto-generated method stub
		
	}

	@BeforeTest
	public void backOfficeLogin() {
		getPage();
		if(!getLoginPage().doLogin(getZurpeBOUsername(), getZurpeBOPassword())) {
			throw new SkipException("Skipping the test becasuse [Login] pre-condition was failed.");
		}
	}
	
	@Test
	public void testVerifyTemplateManagerHeadingIsDisplayed() {
		getPage("/marketing/templates");
		navigateToURL("/marketing/templates");
		assertTrue(page.isTemplateManagerPageVisible(), "Template manager heading is not visible");
	}
	
	@Test
	public void testVerifyTemplateNameLinkAreDisplayed() {
		assertTrue(page.isTemplateNameLinkVisible(), "Template name links are not visible");
	}
	
	@Test
	public void testVerifyPreviewIsDisplayedWhenTemplateNameIsClicked() {
		assertTrue(page.isPreviewDisplayed(), "Preview is not displayed when template name is clicked..");
	}
	
	@Test
	public void testVerifySortingIsWorkingForColumns() {
		assertTrue(page.isCreatedBySortingWorking(), "Created By sorting is not working..");
		assertTrue(page.isTemplateNameSortingWorking(), "Templates name sorting is not working..");
		assertTrue(page.isSubjectSortingWorking(), "Subject sorting is not working..");
		assertTrue(page.isTypeSortingWorking(), "Type name sorting is not working..");
	}
	
	private void navigateToURL(String pURL) {
		String l_url = driver.getCurrentUrl().replace("/dashboard", pURL);
		driver.navigate().to(l_url);	
	}
	@AfterTest
	public void closeBrowser() {
		closeCurrentBrowser();
	}
}
