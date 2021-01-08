/**
 * 
 */
package com.zurple.website;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import us.zengtest1.Page;
import us.zengtest1.PageTest;

/**
 * @author adar
 *
 */
public class ZWLoginPageTest extends PageTest{
	private WebDriver driver;
	private ZWLoginPage page;
	ZWHomeSearchPage object;
	
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
			object = new ZWHomeSearchPage(driver);
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
//		testBrand();
//		testHeader();
		ZWHomeSearchPage homeSearchPage = new ZWHomeSearchPage(driver);
		assertTrue(homeSearchPage.clickOnLoginLink());
		page.doLogin("autoadmin_zurpleqa@mailinator.com");
	}
	
	@Test
	public void testSignIn1() {
		String typeCity = "San Francisco, CA";//
		String lUrl = "https://www.stage01.zengtest3.us/";
		getPage();
		int counter = 0;
		driver.navigate().to(lUrl);
		object.typeInputString(typeCity);
		while(counter<5) {
		assertTrue(object.clickSearchButton(), "Unable to click on search button..");
		assertTrue(object.clickOnNextButton(), "Unable to click on  next button");
		driver.navigate().to(lUrl);
		}
	}
	@Test
	public void testSignIn2() {
		String typeCity = "San Francisco, CA";//
		String lUrl = "https://www.stage01.zengtest3.us/";
		getPage();
		int counter = 0;
		driver.navigate().to(lUrl);
		object.typeInputString(typeCity);
		while(counter<5) {
		assertTrue(object.clickSearchButton(), "Unable to click on search button..");
		assertTrue(object.clickOnNextButton(), "Unable to click on  next button");
		counter++;
		driver.navigate().to(lUrl);
		}
	}
	@Test
	public void testSignIn3() {
		String typeCity = "San Diego, CA";
		String lUrl = "https://www.stage01.zengtest4.us/";
		getPage();
		int counter = 0;
		driver.navigate().to(lUrl);
		object.typeInputString(typeCity);
		while(counter<5) {
		
		
		assertTrue(object.clickSearchButton(), "Unable to click on search button..");
		assertTrue(object.clickOnNextButton(), "Unable to click on  next button");
		counter++;
		driver.navigate().to(lUrl);
		}
	}
	@Test
	public void testSignIn4() {
		
		String lUrl = "https://www.stage01.zengtest6.us/";
		getPage();
		int counter = 0;
		driver.navigate().to(lUrl);
		while(counter<5) {
		
		assertTrue(object.clickSearchButton(), "Unable to click on search button..");
		assertTrue(object.clickOnNextButton(), "Unable to click on  next button");
		counter++;
		driver.navigate().to(lUrl);
		}
	}
	@Test
	public void testSignIn5() {
		String typeCity ="Bridge City, TX";
		String lUrl = "https://www.stage01.zengtest7.us/";
		getPage();
		int counter = 0;
		driver.navigate().to(lUrl);
		object.typeInputString(typeCity);
		while(counter<5) {
		
		
		assertTrue(object.clickSearchButton(), "Unable to click on search button..");
		assertTrue(object.clickOnNextButton(), "Unable to click on  next button");
		counter++;
		driver.navigate().to(lUrl);
		}
	}
	@Test
	public void testSignIn6() {
		
		String typeCity = "San Francisco, CA";//
		String lUrl = "https://www.stage01.zengtest3.us/";
		getPage();
		int counter = 0;
		driver.navigate().to(lUrl);
		object.typeInputString(typeCity);
		while(counter<5) {
		
		
		assertTrue(object.clickSearchButton(), "Unable to click on search button..");
		assertTrue(object.clickOnNextButton(), "Unable to click on  next button");
		driver.navigate().to(lUrl);
		counter++;
		}
	}
	@Test
	public void testSignIn7() {
		String typeCity = "San Francisco, CA";//
		String lUrl = "https://www.stage01.zengtest3.us/";
		getPage();
		int counter = 0;
		driver.navigate().to(lUrl);
		object.typeInputString(typeCity);
		while(counter<5) {
		
		
		assertTrue(object.clickSearchButton(), "Unable to click on search button..");
		assertTrue(object.clickOnNextButton(), "Unable to click on  next button");
		driver.navigate().to(lUrl);
		counter++;
		}
	}
	@Test
	public void testSignIn8() {
		
		String typeCity = "San Diego, CA";
		String lUrl = "https://www.stage01.zengtest4.us/";
		getPage();
		int counter = 0;
		driver.navigate().to(lUrl);
		object.typeInputString(typeCity);
		while(counter<5) {
		
		
		assertTrue(object.clickSearchButton(), "Unable to click on search button..");
		assertTrue(object.clickOnNextButton(), "Unable to click on  next button");
		counter++;
		driver.navigate().to(lUrl);
		}
	}
	@Test
	public void testSignIn9() {
		
		String lUrl = "https://www.stage01.zengtest6.us/";
		getPage();
		int counter = 0;
		driver.navigate().to(lUrl);
		while(counter<5) {
		
		assertTrue(object.clickSearchButton(), "Unable to click on search button..");
		assertTrue(object.clickOnNextButton(), "Unable to click on  next button");
		counter++;
		driver.navigate().to(lUrl);
		}
	}
	@Test
	public void testSignIn10() {
		
		String typeCity ="Bridge City, TX";
		String lUrl = "https://www.stage01.zengtest7.us/";
		getPage();
		int counter = 0;
		driver.navigate().to(lUrl);
		object.typeInputString(typeCity);
		while(counter<5) {
		
		
		assertTrue(object.clickSearchButton(), "Unable to click on search button..");
		assertTrue(object.clickOnNextButton(), "Unable to click on  next button");
		counter++;
		driver.navigate().to(lUrl);
		}
	}
	@Test
	public void testSignIn11() {
		
		String typeCity = "San Francisco, CA";//
		String lUrl = "https://www.stage01.zengtest3.us/";
		getPage();
		int counter = 0;
		driver.navigate().to(lUrl);
		object.typeInputString(typeCity);
		while(counter<5) {
		
		
		assertTrue(object.clickSearchButton(), "Unable to click on search button..");
		assertTrue(object.clickOnNextButton(), "Unable to click on  next button");
		driver.navigate().to(lUrl);
		counter++;
		}
	}
	@Test
	public void testSignIn12() {
		
		String typeCity = "San Francisco, CA";//
		String lUrl = "https://www.stage01.zengtest3.us/";
		getPage();
		int counter = 0;
		driver.navigate().to(lUrl);
		object.typeInputString(typeCity);
		while(counter<5) {
		
		
		assertTrue(object.clickSearchButton(), "Unable to click on search button..");
		assertTrue(object.clickOnNextButton(), "Unable to click on  next button");
		driver.navigate().to(lUrl);
		counter++;
		}
	}
	@Test
	public void testSignIn13() {
		
		String typeCity = "San Diego, CA";
		String lUrl = "https://www.stage01.zengtest4.us/";
		getPage();
		int counter = 0;
		driver.navigate().to(lUrl);
		object.typeInputString(typeCity);
		while(counter<5) {
	
		assertTrue(object.clickSearchButton(), "Unable to click on search button..");
		assertTrue(object.clickOnNextButton(), "Unable to click on  next button");
		counter++;
		driver.navigate().to(lUrl);
		}
	}
	

	

}
