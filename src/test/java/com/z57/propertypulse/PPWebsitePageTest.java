package com.z57.propertypulse;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.z57.site.v2.NewPageCreatedFromPP;
import com.zurple.my.PageTest;

import resources.AbstractPage;
import resources.EnvironmentFactory;

public class PPWebsitePageTest extends PageTest{


	private WebDriver driver;
	private PPWebsitePage page;
	private PPHeader header;
	
	@Override
	public AbstractPage getPage() {
		if(page == null){
			driver = getDriver();
			page = new PPWebsitePage(driver);
			header = new PPHeader(driver);
		}
		return page;
	}
	public AbstractPage getPage(String pUrl) {
		if(page == null){
			driver = getDriver();
			page = new PPWebsitePage(driver);
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
	public void testAddEditPageWidgets(){
		
		getPage("");
		String lWidgetType = "Calendar";
		
		assertTrue(header.clickOnWebsite(), "Unable to click on Website tab..");
		String lWidgetPageUrl = driver.getCurrentUrl().split(".com")[0]+".com";
		lWidgetPageUrl =lWidgetPageUrl+"/wp-admin/widgets.php";
		navigateToUrl(lWidgetPageUrl);
		
		//Drag the available Widget to Default Sidebar
		setWidgetToDefaultSidebar(false,lWidgetType);
		
		String lWPSite_Url = driver.getCurrentUrl().split(".com")[0]+".com";
		navigateToUrl(lWPSite_Url+"/category/real-estate-news");

		assertTrue(verifyWidgetsOnWebsite(lWidgetType),"Unable to verify widget on website "+lWidgetType);		

		driver.navigate().to(EnvironmentFactory.configReader.getPropertyByName("z57_pp_base_url"));
		assertTrue(header.clickOnWebsite(), "Unable to click on Website tab..");
		navigateToUrl(lWidgetPageUrl);
		
		//Drag the widget from Default Side bar to available Widget 
		setWidgetToDefaultSidebar(true,lWidgetType);
		navigateToUrl(lWPSite_Url+"/category/real-estate-news");
		
		assertFalse(verifyWidgetsOnWebsite(lWidgetType),"Unable to verify widget on website "+lWidgetType);	
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////
	//////////////HELPER METHODS FOR WEBSITE VERIFICATIONS AND//////////////////////////////
	/////////////////////////////COMMON TEST STEPS///////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////
	
	private void setWidgetToDefaultSidebar(boolean pSourceDefaultSidebar,String pWidget) {
//		assertTrue(page.clickOnWidget(), "Unable to click on Appearance->Widget button...");
		
		PPWidgetsPage widgetsPage = new PPWidgetsPage(driver);
		assertTrue(widgetsPage.isWidgetsPage(), "Widgets Page is not visbile...");
		if(!pSourceDefaultSidebar) {
			assertTrue(widgetsPage.dragTheWidgetToDeafultSidebar(pWidget),"Unable to drag the widget from available to default sidebar..");
		}else {
			assertTrue(widgetsPage.dragTheWidgetFromDefaultSidebar(pWidget),"Unable to drag the widget from default sidebar to available ..");

		}
	}
	//Verification of other widgets can be added accordingly
	private boolean verifyWidgetsOnWebsite(String pWidget) {
		boolean isVerificationSuccessful = false;
		NewPageCreatedFromPP pageCreated = new NewPageCreatedFromPP(driver);
		switch(pWidget) {
		case "Calendar":
			isVerificationSuccessful = pageCreated.isCalendarWidgetVisible();
			break;
		case "Facebook Like Box":
			break;
		default:
			break;
		}
		return isVerificationSuccessful;

	}
	
	private void navigateToUrl(String pUrl) {
		driver.navigate().to(pUrl);
	}
}
