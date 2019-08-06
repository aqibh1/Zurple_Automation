/**
 * 
 */
package com.zurple.website;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import resources.AbstractPage;
import us.zengtest1.Page;
import us.zengtest1.PageTest;

/**
 * @author adar
 *
 */
public class ZWLoginPageTest extends PageTest{
	private WebDriver driver;
	private ZWLoginPage page;
	
	@Override
	public void testTitle() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void testHeader() {
		assertTrue(new ZurpleWebsiteHeader(driver).isTopNavigationBarValid(), "Page header is not valid..");
		
	}

	@Override
	public void testBrand() {
		assertTrue(new ZWHomeSearchPage(driver).verifyLogos());	
	}

	@Override
	public Page getPage() {
		if(page == null){
			driver = getDriver();
			page = new ZWLoginPage(driver);
			page.setUrl("");
			page.setDriver(driver);
		}
		return page;
	}

	@Override
	public void clearPage() {
		// TODO Auto-generated method stub
		
	}
	
	@Test
	public void testSignIn() {
		getPage();
		testBrand();
		testHeader();
		ZWHomeSearchPage homeSearchPage = new ZWHomeSearchPage(driver);
		assertTrue(homeSearchPage.clickOnLoginLink());
		page.doLogin("autoadmin_zurpleqa@mailinator.com");
	}

	

}
