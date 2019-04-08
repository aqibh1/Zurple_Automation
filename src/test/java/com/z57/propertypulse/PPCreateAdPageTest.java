/**
 * 
 */
package com.z57.propertypulse;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;

import com.zurple.my.PageTest;

import resources.AbstractPage;
import resources.EnvironmentFactory;

/**
 * @author adar
 *
 */
public class PPCreateAdPageTest extends PageTest{

	private WebDriver driver;
	private PPCreateAdPage page;

	@Override
	public AbstractPage getPage() {
		if(page == null){
			driver = getDriver();
			page = new PPCreateAdPage(driver);
		}
		return page;
	}

	@Override
	public void clearPage() {
		// TODO Auto-generated method stub
		
	}
	
	public void testCreateAnAd() {
		String lAdTitle="";
		String lAdDesc="";
		String lAdTargetedZip="";
		
		getPage();
		
		assertTrue(page.isCreateAdPage(),"Create Ad Page is not displayed");
		assertTrue(page.isValidPreviewLink(EnvironmentFactory.configReader.getPropertyByName("z57_site_v2_base_url")), "The preview link is not valid");
		
		if(lAdTitle.isEmpty()) {
			assertTrue(page.isValidTitle(),"Ad Title is Empty");
		}else {
			assertTrue(page.typeAdTitle(lAdTitle),"Unable to type Ad Title in the field");
		}
		
		if(lAdDesc.isEmpty()) {
			assertTrue(page.isValidDescription(),"Ad Desc is Empty");
		}else {
			assertTrue(page.typeAdDescription(lAdDesc),"Unable to type Ad Description in the field");
		}
		
		assertTrue(page.clickOnNextButton(), "Unable to click on Next step button");
		assertTrue(page.isSelectAdVisibilityOptionsPage(),"Step 2 page is not visible");
		
		if(lAdTargetedZip.isEmpty()) {
			assertTrue(page.isValidZip(),"Ad targeted Zip is Empty");
		}else {
			assertTrue(page.typeAdZipCode(lAdTargetedZip),"Unable to type Zip in the field");
		}
		assertTrue(page.clickOnNextButton(), "Unable to click on Next step button");
		
		assertTrue(page.clickOnPaymentCheckBox(), "Unable to click on Payment checkbox");
		assertTrue(page.clickOnFBTestAdCheckBox(), "Unable to click on FB test ad checkkbox");
	}

}
