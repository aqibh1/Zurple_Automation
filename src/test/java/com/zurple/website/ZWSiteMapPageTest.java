package com.zurple.website;

import static org.testng.Assert.assertEquals;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import us.zengtest1.Page;
import us.zengtest1.PageTest;

public class ZWSiteMapPageTest extends PageTest{
	private WebDriver driver;
	private ZWSiteMapPage page;
	private JSONObject dataObject;

	private Page getPage(String pUrl) {
		page = null;
		if(page == null){
			driver = getDriver();
			page = new ZWSiteMapPage(driver);
			page.setUrl(pUrl);
			page.setDriver(driver);
		}
		return page;	
	}
	
	@Test
	@Parameters({"sitemapsDataFile"})
	public void testSiteMaps(String pDataFile) {
		page=null;
		getPage("/sitemap.xml");
		dataObject = getDataFile(pDataFile);
		assertEquals(page.getTagName("sitemap").trim(),dataObject.optString("tag_name"));
		assertEquals(page.getTagValue("sitemap").trim(),dataObject.optString("tag_value"));
		page=null;
		getPage("/sitemap2.xml");
		assertEquals(page.getTagName("sitemap2").trim(),dataObject.optString("tag_name"));
		assertEquals(page.getTagValue("sitemap").trim(),dataObject.optString("tag_value"));
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
	public Page getPage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clearPage() {
		// TODO Auto-generated method stub
		
	}

}
