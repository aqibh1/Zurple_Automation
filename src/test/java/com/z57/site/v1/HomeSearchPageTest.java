package com.z57.site.v1;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import resources.DBHelperMethods;
import resources.ModuleCacheConstants;
import resources.ModuleCommonCache;
import resources.data.z57.SaveSearchFormData;

public class HomeSearchPageTest extends PageTest{
	WebDriver driver;
	private HomeSearchPage page;
	
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
			driver =getDriver();
			page = new HomeSearchPage(driver);
		}
		return page;
	}

	public Page getPage(String pUrl) {
		if(page == null) {
			driver=getDriver();
			page = new HomeSearchPage(driver);
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
	 @Parameters({"saveSearchData"})
	 public void testSaveSearch(String pDataFile1) {
		 getPage("/idx/search?feed_ids=1098&zips=91910&sort=list_price+asc&search_distance=5&fp_anyall=any&search_location_type=ZI&location=91910&property_type%5B%5D=Apt%2FCondo%2FTownhome&min_price=0&max_price=10000000&bedrooms=0&bathrooms=0&status%5B%5D=ACTIVE&ts_zfs=pcfpdQ");

		 SaveSearchFormData saveListingFormData = new SaveSearchFormData(pDataFile1).getEmailListingData();

		 String lLeadName = updateName(saveListingFormData.getLeadName());
		 String lLeadEmail = updateEmail(saveListingFormData.getLeadEmail());
		 String lLeadPhone = saveListingFormData.getLeadPhoneNumber();
		 String lSearchTitle ="";
		 if(!saveListingFormData.getTitle().isEmpty()) {
			 lSearchTitle = updateName(saveListingFormData.getTitle());
		 }

		 assertTrue(page.isHomeSearchPage(), "Home Search page is not visible");

		 boolean isLeadLoggedIn=page.isLeadLoggedIn();

		 assertTrue(page.clickOnSaveSearch(), "Unable to click on save search button");

		 assertTrue(page.getSaveSearchForm().isSaveSearchForm(),"Save search Modal is not visible");

		 if(isLeadLoggedIn) {
			 //Verify the name of the lead is in respective fields
			 lLeadEmail = ModuleCommonCache.getElement(getThreadId().toString(), ModuleCacheConstants.RegisterFormLeadEmail);
			 assertFalse(page.getSaveSearchForm().isLeadEmailInputVisible(), "Lead is logged in but email field also visible.." );

		 }else {
			 assertTrue(page.getSaveSearchForm().typeLeadName(lLeadName),"Unable to type name in Save search field");
			 assertTrue(page.getSaveSearchForm().typeEmailAddress(lLeadEmail),"Unable to type email in Save search field");
			 if(!lLeadPhone.isEmpty()) {
				 assertTrue(page.getSaveSearchForm().typePhoneNumber(lLeadPhone),"Unable to type phone in Lead phone number field");
			 }
		 }
		 
		 if(lSearchTitle.isEmpty()) {
			 lSearchTitle = page.getSaveSearchForm().getTitle();
		 }else {
			 assertTrue(page.getSaveSearchForm().typeSearchTitle(lSearchTitle),"Unable to type Search Title..");
		 }
		 
		 assertTrue(page.getSaveSearchForm().clickOnSaveButton(),"Unable to click on Save button");
		 assertTrue(page.getSaveSearchForm().isSearchSaved(),"No expected message is shown");

		 DBHelperMethods dbHelper = new DBHelperMethods(getEnvironment());

		 assertTrue(dbHelper.verifyLeadByEmailInDB(lLeadEmail), "User is not added as Lead in PP ->" + lLeadEmail);

		 assertTrue(dbHelper.verifySearchIsSaved(lLeadEmail, lSearchTitle),"Unable to save search");

	 }


}
