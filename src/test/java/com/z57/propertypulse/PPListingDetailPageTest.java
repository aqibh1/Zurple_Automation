package com.z57.propertypulse;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.net.ProtocolException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.entity.ContentType;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.z57.site.v2.PropertyListingPage;
import com.zurple.my.PageTest;

import resources.AbstractPage;
import resources.HttpRequestClient;
import resources.ModuleCommonCache;
import resources.orm.hibernate.models.z57.Listings;
import resources.utility.ActionHelper;
import resources.utility.AutomationLogger;
import resources.utility.HTTPConstants;
import resources.utility.ValueMapper;

public class PPListingDetailPageTest extends PageTest{
	
	private WebDriver driver;
	private PPListingDetailPage page;
	
	@Override
	public AbstractPage getPage() {
		if(page == null){
			driver = getDriver();
			page = new PPListingDetailPage(driver);
			
		}
		return page;
	}

	@Override
	public void clearPage() {
		// TODO Auto-generated method stub
		
	}
	
	private PPListingDetailPage getPage(String pUrl) {
		if(page == null){
			driver = getDriver();
			page = new PPListingDetailPage(driver);
			page.setUrl(pUrl);
			page.setDriver(driver);
		}
		return page;
	}
	
	@Test
	public void testEditListingDetails() {
		//If editing existing listing
		String lListing_ID = "351386";
		ModuleCommonCache.setModuleCommonCache("listing_url_pp", "/listings/details?lid=351386");
		ModuleCommonCache.setModuleCommonCache("listing_url_wp", "http://aqibdar-1584.sites.z57.com/listings/351386/221-Baker-Street-San-Diego");
		
		if(ModuleCommonCache.getModuleCommonCache("listing_url_pp")!=null) {
			getPage(ModuleCommonCache.getModuleCommonCache("listing_url_pp").toString());
		}else {
			getPage();
		}
		String lListingPrice = "750000";
		String lListingStatus = "Just Reduced";
		String lListingAddress = "221 Baker Street";
		String lListingCounty = "San Diego";
		String lListingCity= "San Diego";
		String lListingState = "California";
		String lListingZip = "91910";
		String lListingMLS = "";
		String lListingPropertyType = "Rental";
		String lListingSaleType = "New Home";
		String lListingAgent = "";
		String lListingBrokerage = "";
		String lListingTitle = "For Rent 221 Baker Street";
		String lListingDescription= "Best place in town";
		String lListingAccelatorCaption = "Sherlock homes, home is available for rent";
		String lListingEmbeded = "";
		String lListingVirtualTourLink = "https://www.youtube.com/watch?v=-e_A2xtaH0s";
		//Features
		String lListingFeatureBed = "4";
		String lListingFeatureBath = "5";
		String lListingFeatureInterior = "Air Conditioning,Basement,Dining Room,Foyer,Fireplace";
		String lListingFeatureExterior = "Balcony,BBQ,Barn,Deck,Driveway";
		String lListingFeatureSqFootage = "650";
		String lListingFeatureLotSize = "650";
		String lListingFeatureLotDetails = "Beach Front, City View, Close to Nightlife";
		String lListingFeatureSaleInfo = "Rental,Reverse Mortgage";
		String lListingFeatureYearBuilt = "2010";
		String lListingImagesPath="C:\\Users\\adar\\Downloads\\Homes\\images.jpg";
		
		assertTrue(page.isListingDetailPage(), "Listing Detail Page is not visible..");
		if(!lListingTitle.isEmpty()) {
			assertTrue(page.typeTitle(lListingTitle), "Unable to type Listing Title..");
		}
		if(!lListingDescription.isEmpty()) {
			assertTrue(page.typeListingDescription(lListingDescription), "Unable to type Listing Description..");
		}
		if(!lListingAccelatorCaption.isEmpty()) {
			assertTrue(page.typeAccelatorCaption(lListingAccelatorCaption), "Unable to type Accelator Caption..");
		}
		if(!lListingEmbeded.isEmpty()) {
			assertTrue(page.typeTitle(lListingTitle), "Unable to type Embeded Code..");
		}
		if(!lListingVirtualTourLink.isEmpty()) {
			assertTrue(page.typeTitle(lListingTitle), "Unable to type Virtual Tour Link..");
		}
		if(!lListingPrice.isEmpty()) {
			assertTrue(page.typePrice(lListingPrice), "Unable to type Listing Price..");
		}
		if(!lListingStatus.isEmpty()) {
			assertTrue(page.selectStatus(lListingStatus) ,"Unable to select Listing status..");
		}
		if(!lListingAddress.isEmpty()) {
			assertTrue(page.typeAddress(lListingAddress), "Unable to type Listing Address..");
		}
		if(!lListingCounty.isEmpty()) {
			assertTrue(page.typeCounty(lListingCounty), "Unable to type Listing County..");
		}
		if(!lListingCity.isEmpty()) {
			assertTrue(page.typeCity(lListingCity), "Unable to type Listing City..");
		}
		if(!lListingState.isEmpty()) {
			assertTrue(page.selectState(lListingState), "Unable to type Listing state .."+lListingState);
		}
		if(!lListingZip.isEmpty()) {
			assertTrue(page.typeZip(lListingZip), "Unable to type Listing zip .."+lListingZip);
		}
		if(!lListingMLS.isEmpty()) {
			assertTrue(page.typeMLS(lListingMLS), "Unable to type Listing MLS .."+lListingMLS);
		}
		if(!lListingPropertyType.isEmpty()) {
			assertTrue(page.selectPropertytype(lListingPropertyType), "Unable to select Listing property type .."+lListingPropertyType);
		}
		if(!lListingSaleType.isEmpty()) {
			assertTrue(page.selectSaleType(lListingSaleType), "Unable to select Listing sale type .."+lListingSaleType);
		}
		if(!lListingAgent.isEmpty()) {
			assertTrue(page.typeAgentName(lListingAgent), "Unable to type Listing agent .."+lListingAgent);
		}
		if(!lListingBrokerage.isEmpty()) {
			assertTrue(page.typeBrokarage(lListingBrokerage), "Unable to type Listing brokerage .."+lListingBrokerage);
		}
		
		//Features
		
		if(!lListingFeatureBed.isEmpty()) {
			assertTrue(page.typeBed(lListingFeatureBed), "Unable to type Listing bed .."+lListingFeatureBed);
		}
		if(!lListingFeatureBath.isEmpty()) {
			assertTrue(page.typeBath(lListingFeatureBath), "Unable to type Listing MLS .."+lListingFeatureBath);
		}
		if(!lListingFeatureInterior.isEmpty()) {
			assertTrue(page.selectInterior(lListingFeatureInterior.split(",")), "Unable to select Listing Interior type .."+lListingFeatureInterior);
		}
		if(!lListingFeatureExterior.isEmpty()) {
			assertTrue(page.selectExterior(lListingFeatureExterior.split(",")), "Unable to select Listing exterior type .."+lListingFeatureExterior);
		}
		if(!lListingFeatureSqFootage.isEmpty()) {
			assertTrue(page.typeSquareFootage(lListingFeatureSqFootage), "Unable to type Listing Square Footage .."+lListingFeatureSqFootage);
		}
		if(!lListingFeatureLotSize.isEmpty()) {
			assertTrue(page.typeLotSize(lListingFeatureLotSize), "Unable to type Listing Lot Size .."+lListingFeatureLotSize);
		}
		if(!lListingFeatureLotDetails.isEmpty()) {
			assertTrue(page.selectLotDetails(lListingFeatureLotDetails.split(",")), "Unable to select Listing Lot Details type .."+lListingFeatureLotDetails);
		}
		if(!lListingFeatureSaleInfo.isEmpty()) {
			assertTrue(page.selectSaleInfo(lListingFeatureSaleInfo.split(",")), "Unable to select Listing sale type .."+lListingFeatureSaleInfo);
		}
		if(!lListingFeatureYearBuilt.isEmpty()) {
			assertTrue(page.typeYearBuilt(lListingFeatureYearBuilt), "Unable to type Listing Year Built .."+lListingFeatureYearBuilt);
		}
		//Uploading listing images.
		String[] images = lListingImagesPath.split(",");
		for(String image: images) {
			assertTrue(uploadListingImages(lListing_ID, image), "Unable to upload image.."+image);
		}
		ActionHelper.RefreshPage(driver);
		assertTrue(page.clickOnSaveButton(), "Unable to type Listing Year Built .."+lListingFeatureYearBuilt);
		
		driver.navigate().to(ModuleCommonCache.getModuleCommonCache("listing_url_wp").toString());
		PropertyListingPage propertyListingPage = new PropertyListingPage(driver);
		assertTrue(propertyListingPage.isListingDetailPage(), "Listing Detail Page is not visible");
		
		Listings listingObject = getEnvironment().getListingById(Integer.parseInt(lListing_ID));
		ValueMapper valueMapper = new ValueMapper();
		
		//Listing Verification from UI and DB
		if(!lListingStatus.isEmpty()) {
			assertTrue(propertyListingPage.getPropertyStatusFromHeader().equalsIgnoreCase(lListingStatus), "Status of Listing does not match.. Expected ["+lListingStatus+"]");
			assertTrue(listingObject.getStatusId().equals(valueMapper.getListingStatus(lListingStatus)), "DB Verification :: Status of Listing does not match.. Expected ["+lListingStatus+"]");
		}
		
		if(!lListingAddress.isEmpty()) {
			assertTrue(propertyListingPage.getAddress("Address").equalsIgnoreCase(lListingAddress), "ADDRESS of Listing does not match.. Expected ["+lListingAddress+"]");
			assertTrue(listingObject.getAddress().equalsIgnoreCase(lListingAddress), "DB Verification :: ADDRESS of Listing does not match.. Expected ["+lListingAddress+"]");
		}
		
		if(!lListingCity.isEmpty()) {
			assertTrue(propertyListingPage.getAddress("City").equalsIgnoreCase(lListingCity), "CITY of Listing does not match.. Expected ["+lListingCity+"]");
			assertTrue(listingObject.getCity().equalsIgnoreCase(lListingCity), "DB Verification :: CITY of Listing does not match.. Expected ["+lListingCity+"]");
		}
		
		if(!lListingState.isEmpty()) {
			assertTrue(propertyListingPage.getAddress("State").equalsIgnoreCase(valueMapper.getState(lListingState)), "STATE of Listing does not match.. Expected ["+lListingState+"]");
			assertTrue(listingObject.getState().equalsIgnoreCase(valueMapper.getState(lListingState)), "DB Verification :: STATE of Listing does not match.. Expected ["+lListingState+"]");

		}
		
		if(!lListingZip.isEmpty()) {
			assertTrue(propertyListingPage.getAddress("Zip").equalsIgnoreCase(lListingZip), "ZIP of Listing does not match.. Expected ["+lListingZip+"]");
			assertTrue(listingObject.getZip().equalsIgnoreCase(lListingZip), "DB Verification :: ZIP of Listing does not match.. Expected ["+lListingZip+"]");

		}
		
		if(!lListingCounty.isEmpty()) {
			assertTrue(propertyListingPage.getAddress("County").equalsIgnoreCase(lListingCounty), "COUNTY of Listing does not match.. Expected ["+lListingCounty+"]");
			assertTrue(propertyListingPage.getAddress("County").equalsIgnoreCase(lListingCounty), "COUNTY of Listing does not match.. Expected ["+lListingCounty+"]");

		}
		
		

	}
	

	
	

	private boolean uploadListingImages(String pListingId, String pBody){
		boolean imagesUploadedSuccessfully= true;
		try {
			HttpRequestClient request = new HttpRequestClient();
			request.setUrl("https://propertypulse.z57.com/media/listing_image_upload?lid="+pListingId+"");
			request.setHeader(HTTPConstants.ContentType, ContentType.MULTIPART_FORM_DATA.toString());
			request.setHeader(HTTPConstants.Cookie, getCookies());
			request.setRequestType(HTTPConstants.POST);
			request.setBody("file", pBody);
			request.execute();
			if(request.getStatus()==HttpStatus.SC_OK) {
				AutomationLogger.info("Image uploaded successfully");
			}
		}catch(Exception ex) {
			imagesUploadedSuccessfully = false;
		}
		return imagesUploadedSuccessfully;
	}

	private String getCookies() {
		String cookieString ="";
		Set<Cookie> cookieSet = new HashSet<Cookie>();
		cookieSet = getDriver().manage().getCookies();
		Iterator<Cookie> it = cookieSet.iterator();
		while(it.hasNext()) {
			if(cookieSet.isEmpty()) {
				cookieString=it.next().toString();
			}else {
				cookieString=cookieString+it.next();
			}
		}
		return cookieString;
	}
}
