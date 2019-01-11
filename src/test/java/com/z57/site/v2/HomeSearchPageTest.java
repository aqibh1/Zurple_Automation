package com.z57.site.v2;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.z57.site.v2.pom.POMHomePage;
import com.z57.site.v2.pom.HomeSearchPage;

public class HomeSearchPageTest extends PageTest{

	private HomeSearchPage page;
	@Override
	public void testTitle() {
		assertEquals("Search Local Properties for Sale | zengtest1.us", getPage().getTitle());
		
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
			page = new HomeSearchPage(getDriver(),this.source_in_url);
			page.setDriver(getDriver());
		}
		return page;
	}

	@Override
	public void clearPage() {
		// TODO Auto-generated method stub
		
	}
	
//	@BeforeClass
//	  public void beforeClass(ITestContext context) {
//	    String value = context.getCurrentXmlTest().getParameter("1");
//	    System.err.println("webdriver.deviceName.iPhone = " + value);
//	 }
	//TODO
	//Will add the logic for multiple search fields
	@Test
	public void testSearchByDifferentDataSet() {
		
		POMHomePage homePageObj = new POMHomePage(getPage().getWebDriver());
		homePageObj.mouseoverHomeSearch();
		homePageObj.clickOnSearchHomes();
		
		assertTrue(page.clickOnSearchByOption("City"), "Could not click on Search By element as its not visible.");
//		assertTrue(page.clickOnInputField(), "Input field on Home Search is not visible");
		assertTrue(page.typeInInputField("San Diego"), "Input field on Home Search is not visible");
		assertTrue(page.clickOnSuggestedOptionsInInputField(),"Nothing was suggested in input field");
		assertTrue(page.clickOnSearchButton(),"Search Button on Home search screen is not visible");
		
		
		
//		return isSearchSuccessful;
		
	}
	

}
