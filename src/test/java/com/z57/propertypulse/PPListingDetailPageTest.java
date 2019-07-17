package com.z57.propertypulse;

import static org.testng.Assert.assertTrue;

import java.awt.AWTException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.http.HttpStatus;
import org.apache.http.entity.ContentType;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.z57.site.v2.HomePage;
import com.z57.site.v2.PageHeader;
import com.z57.site.v2.PropertyListingPage;
import com.zurple.my.PageTest;

import resources.AbstractPage;
import resources.EnvironmentFactory;
import resources.HttpRequestClient;
import resources.ModuleCommonCache;
import resources.data.z57.PPListingData;
import resources.orm.hibernate.HibernateUtil;
import resources.orm.hibernate.models.z57.Listings;
import resources.utility.ActionHelper;
import resources.utility.AutomationLogger;
import resources.utility.HTTPConstants;
import resources.utility.ValueMapper;

public class PPListingDetailPageTest extends PageTest{
	
	private WebDriver driver;
	private PPListingDetailPage page;
	private PageHeader header;
	private PPListingData listingData;
	
	@Override
	public AbstractPage getPage() {
		if(page == null){
			driver = getDriver();
			page = new PPListingDetailPage(driver);
			header = new PageHeader(driver);
			
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
			header = new PageHeader(driver);
			page.setUrl(pUrl);
			page.setDriver(driver);
		}
		return page;
	}
	
	@Test
	@Parameters({"dataFile"})
	public void testEditListingDetails(String pDataFile) throws AWTException {
		listingData = new PPListingData(pDataFile).getListingData();
		//If editing existing listing
		String lListing_ID = ModuleCommonCache.getModuleCommonCache("listing_id").toString();
		String lListing_Url = ModuleCommonCache.getModuleCommonCache("listing_url_pp").toString().split(EnvironmentFactory.configReader.getPropertyByName("z57_pp_base_url"))[1];
//		ModuleCommonCache.setModuleCommonCache("listing_url_pp", "/listings/details?lid=351386");
//		ModuleCommonCache.setModuleCommonCache("listing_url_wp", "http://aqibdar-1584.sites.z57.com/listings/351386/221-Baker-Street-San-Diego");
		String wp_url = ModuleCommonCache.getModuleCommonCache("listing_url_wp").toString().split(EnvironmentFactory.configReader.getPropertyByName("z57_site_v2_base_url"))[1];
		if(ModuleCommonCache.getModuleCommonCache("listing_url_pp")!=null) {
			getPage(lListing_Url);
		}else {
			getPage();
		}
		
		String lListingPrice = listingData.getPrice();
		String lListingStatus = listingData.getStatus();
		String lListingAddress = "";//updateName(listingData.getAddress());
		String lListingCounty = listingData.getCounty();
		String lListingCity= listingData.getCity();
		String lListingState = listingData.getState();
		String lListingZip = listingData.getZip();
		String lListingMLS = listingData.getMls();
		String lListingPropertyType = listingData.getPropertyType();
		String lListingSaleType = listingData.getSaleType();
		String lListingAgent = listingData.getAgent();
		String lListingBrokerage = listingData.getBrokerage();
		String lListingTitle = updateName(listingData.getTitle());
		String lListingDescription= listingData.getDescription();
		String lListingAccelatorCaption = listingData.getAcceleratorCaption();
		String lListingEmbeded = listingData.getEmbeded();
		String lListingVirtualTourLink = listingData.getVirtualTourLink();
		//Features
		String lListingFeatureBed = listingData.getBed();
		String lListingFeatureBath = listingData.getBath();
		String lListingFeatureInterior = listingData.getInterior();
		String lListingFeatureExterior = listingData.getExterior();
		String lListingFeatureSqFootage = listingData.getSqFootage();
		String lListingFeatureLotSize = listingData.getLotSize();
		String lListingFeatureLotDetails = listingData.getLotDetails();
		String lListingFeatureSaleInfo = listingData.getSaleInfo();
		String lListingFeatureYearBuilt = listingData.getYearBuilt();
		String lListingImagesPath=listingData.getImagePath();
		
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
			assertTrue(page.clickOnUploadIamgesButton(), "Unable to click on Upload button");
			assertTrue(page.getPpUploadImagesForm().isUploadImagesForm(), "Upload image form is not visible.");
			assertTrue(page.getPpUploadImagesForm().clickOnAddFilesButton(), "Unable to click on Add Files button");
			assertTrue(page.getPpUploadImagesForm().uploadImage(System.getProperty("user.dir")+image), "Unable to upload the image.");
			new ActionHelper(driver).Wait(10);
		}		
		//Clicking on Save button
		assertTrue(page.clickOnSaveButton(), "Unable to click on Save button");
		
		assertTrue(page.getMaxListingExposure.closeAlert(), "Unable to close listing ad Alert");
		assertTrue(page.isListingEditedSuccessfully(), "Listing was not updated successfully");
		
		//Verification from DB and on WP
		driver.navigate().to(ModuleCommonCache.getModuleCommonCache("listing_url_wp").toString());
		closeBootStrapModal();
		
		PropertyListingPage propertyListingPage = new PropertyListingPage(driver);
		assertTrue(propertyListingPage.isListingDetailPage(), "Listing Detail Page is not visible");
		HibernateUtil.setSessionFactoryEmpty();
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
		
		if(!lListingTitle.isEmpty()) {
			assertTrue(propertyListingPage.getPropertyTitleFromTheHeader().equalsIgnoreCase(lListingTitle), "Title of the Listing does not match.. Expected["+lListingTitle+"]");
			assertTrue(listingObject.getTitle().equalsIgnoreCase(lListingTitle), "Title of the Listing does not match.. Expected["+lListingTitle+"]");
		}
		
		if(!lListingDescription.isEmpty()) {
			assertTrue(propertyListingPage.getDescription().equalsIgnoreCase(lListingDescription), "Description of the Listing didn't match");
			assertTrue(listingObject.getDesciption().equalsIgnoreCase(lListingDescription), "DB Verification :: Description of the Listing didn't match");
		}
		
		if(!lListingPrice.isEmpty()) {
			assertTrue(propertyListingPage.getPropertValueFromHeader()==Integer.parseInt(lListingPrice), "Price of the Listing didn't match");
			assertTrue(listingObject.getPrice()==Integer.parseInt(lListingPrice), "DB Verification :: Price of the Listing didn't match");
		}
		if(!lListingPropertyType.isEmpty()) {
			assertTrue(propertyListingPage.getPropertyType("Type").equalsIgnoreCase(lListingPropertyType), "Property Type of the Listing didn't match");
			//TODO Property type mapping is unknown
//			assertTrue(listingObject.getPropertyType().equals(valueMapper.getPropertyTypeMapper(lListingPropertyType)), "DB Verification ::Property Type of the Listing didn't match");
		}
		if(!lListingFeatureBed.isEmpty()) {
			assertTrue(propertyListingPage.getPropertyType("Bedrooms").equalsIgnoreCase(lListingFeatureBed), "Bedrooms count of the Listing didn't match");
			assertTrue(listingObject.getBed().equals(Double.parseDouble(lListingFeatureBed)), "DB Verification :: Bedrooms count of the Listing didn't match");
		}
		if(!lListingFeatureBath.isEmpty()) {
			assertTrue(propertyListingPage.getPropertyType("Bathrooms").equalsIgnoreCase(lListingFeatureBath), "Bathrooms count of the Listing didn't match");
			assertTrue(listingObject.getBath().equals(Double.parseDouble(lListingFeatureBath)), "DB Verification :: Bathrooms count of the Listing didn't match");
		}

		if(!lListingFeatureInterior.isEmpty()) {
			assertTrue(propertyListingPage.verifyPropertyFeatures(lListingFeatureInterior.split(",")), "Unable to verify property interior features ..");
		}
		if(!lListingFeatureExterior.isEmpty()) {
			assertTrue(propertyListingPage.verifyPropertyFeatures(lListingFeatureExterior.split(",")), "Unable to verify property Exterior features ..");
		}
		if(!lListingFeatureSqFootage.isEmpty()) {
			assertTrue(propertyListingPage.getPropertyType("Sq. Ft.").equalsIgnoreCase(lListingFeatureSqFootage), "Square Footage of of the Listing didn't match");
		}
		if(!lListingFeatureLotSize.isEmpty()) {
			assertTrue(propertyListingPage.getPropertyType("Lot Size").equalsIgnoreCase(lListingFeatureLotSize), "Lot size of the Listing didn't match");
		}
		if(!lListingFeatureLotDetails.isEmpty()) {
			assertTrue(propertyListingPage.verifyPropertyFeatures(lListingFeatureLotDetails.split(",")), "Property and Lot details didn't match..");
		}
		if(!lListingFeatureSaleInfo.isEmpty()) {
			assertTrue(propertyListingPage.verifyPropertyFeatures(lListingFeatureSaleInfo.split(",")), "Sale Info details didn't match..");
		}
		if(!lListingFeatureYearBuilt.isEmpty()) {
			assertTrue(propertyListingPage.getPropertyType("Year Built").equalsIgnoreCase(lListingFeatureYearBuilt), "Year Built of the Listing didn't match");
		}
		
		
		//Verifying if listing is added to default slider widget or not
		assertTrue(header.clickOnHomeButton(), "Unable to click on Home Button");
		HomePage homePage = new HomePage(driver);
		assertTrue(homePage.isPropertyExistsInSliderWidget(wp_url), "Listing doesn't exists in Slider Widget");

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
//		while(it.hasNext()) {
//
//			cookieString+=it.next().toString();
//			System.out.println(cookieString);
//
//		}
		int index=0;
		for(Cookie c:cookieSet){
			index++;
			if(index==cookieSet.size()){
				cookieString+=c.getName()+"="+c.getValue();
			}else{
				cookieString+=c.getName()+"="+c.getValue()+"; ";
			}
		}
		return cookieString;
	}
}
