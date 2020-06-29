package com.zurple.website;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.zurple.admin.ZAProcessEmailQueuesPage;

import resources.ModuleCacheConstants;
import resources.ModuleCommonCache;
import resources.utility.ActionHelper;
import us.zengtest1.Page;
import us.zengtest1.PageTest;

/**
 * @author aaqib
 *
 */

public class ZWZillowRatingsAgentsPageTest extends PageTest {
	private WebDriver driver;
	private ZWZillowRatingsAgentsPage page;
	
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
	
	private Page getPage(String pUrl) {
		page = null;
		if(page == null){
			driver = getDriver();
			page = new ZWZillowRatingsAgentsPage(driver);
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
	public void testZillowRatings() {
		getPage("");
			if(!getIsProd()) {
				//page=null;
				driver.get("https://www.stage01.zengtest4.us/agents");
			//	driver.navigate().to("https://www.stage01.zengtest4.us/agents");
			} else {
				//page=null;
				driver.navigate().to("https://zengtest4.us/agents");
			}
		
		assertEquals(page.headerText(),"Agents");
		assertTrue(page.clickAgentName(),"Unable to click on agents name");
	}
}
