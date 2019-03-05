/**
 * 
 */
package com.z57.site.v2;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

/**
 * @author adar
 *
 */
public class UserCustomPageTest extends PageTest{
	
	WebDriver driver;
	UserCustomPage page;
	
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
			page = new UserCustomPage(getDriver());
			page.setUrl("");
			driver=getDriver();
			page.setDriver(driver);
		}
		return page;
	}
	public Page getPage(String pUrl) {
		if(page == null){
			page = new UserCustomPage(getDriver());
			page.setUrl(pUrl);
			driver=getDriver();
			page.setDriver(driver);
		}
		return page;
	}

	@Override
	public void clearPage() {
		// TODO Auto-generated method stub
		
	}

	@Test
	public void testUserCustomPageWorking() {
		getPage("/branding-options");
		closeBootStrapModal();
		assertTrue(page.isPageTitleDisplayed(), "Branding page is not displayed");
		assertTrue(page.isColorOptionsDisplayed(), "Color options button is not visible");
		assertTrue(page.isImageOptionsDisplayed(), "Image options button is not displayed");
		
	}
}
