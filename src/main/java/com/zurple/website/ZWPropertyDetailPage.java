/**
 * 
 */
package com.zurple.website;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import resources.alerts.zurple.website.ZWScheduleShowingAlert;
import resources.forms.zurple.website.ZWContactAgentForm;
import resources.forms.zurple.website.ZWLeadCaptureForm;
import resources.utility.ActionHelper;
import resources.utility.AutomationLogger;
import resources.utility.FrameworkConstants;
import us.zengtest1.Page;

/**
 * @author adar
 *
 */
public class ZWPropertyDetailPage extends Page{

	@FindBy(xpath="//div[@id='propdetail']/descendant::h2[1]")
	WebElement propName_heading;
	
	@FindBy(xpath="//div[@id='propdetail']/descendant::h2[2]")
	WebElement propPrice_heading;
	
	@FindBy(xpath="//div[@class='prop_icons']/descendant::img[@src='img/icons/icon_bed1.png']")
	WebElement bedIcon;
	
	@FindBy(xpath="//div[@class='prop_icons']/descendant::img[@src='img/icons/icon_bath1.png']")
	WebElement bathIcon;
	
	@FindBy(xpath="//div[@class='prop_icons']/descendant::img[@src='img/icons/icon_size1.png']")
	WebElement sizeIcon;
	
	@FindBy(xpath="//div[@class='prop_icons']")
	WebElement propValues;
	
	String propDetails_xpath = "//table[@class='table table-striped']/descendant::th[text()='"+FrameworkConstants.DYNAMIC_VARIABLE+"']/following-sibling::td";
	
	@FindBy(xpath="//div[@class='col-md-12 mls-description']/p")
	WebElement mlsDescription;
	
	@FindBy(id="listing_map_canvas")
	WebElement listing_map;
	
	@FindBy(xpath="//div[@id='listing_map_canvas']/descendant::img[@src='https://maps.gstatic.com/mapfiles/api-3/images/spotlight-poi2_hdpi.png'][1]")
	WebElement googleMapPinIcon;
	
	String navigationTabs_xpath = "//ul[@class='nav nav-tabs nav-justified']/descendant::a[text()='"+FrameworkConstants.DYNAMIC_VARIABLE+"']";
	
	String lPropAndLotDetails = "//table[@class='table table-condensed table-striped']/descendant::strong[text()='"+FrameworkConstants.DYNAMIC_VARIABLE+"']/ancestor::td/following-sibling::td";
	
	@FindBy(xpath="//div[@class='row address-row']/descendant::h2[1]")
	WebElement addressInHeading;
	//////////////////////////////////////////// Community Stats elements //////////////////////////////////////////////////////////////////////
	
	@FindBy(id="overview-table")
	WebElement overiviewTable;
	
	@FindBy(id="salestax-container")
	WebElement expenseContainer;
	
	@FindBy(id="crimerate-container")
	WebElement generalContainer;
	
	@FindBy(id="population-container")
	WebElement populationContainer;
	
	@FindBy(id="edu-container")
	WebElement educationContainer;
	
	@FindBy(id="household-container")
	WebElement householdContainer;
	
	@FindBy(id="crime-index-container")
	WebElement crimeIndexContainer;
	
	@FindBy(id="climate-container")
	WebElement climateContainer;
	
	@FindBy(id="quality-container")
	WebElement qualityContainer;
	
	//////////////////////////////////////////// School Map Elements ////////////////////////////////////////////////////////////////////////
	
	@FindBy(id="schoolsMap")
	WebElement schoolMap;
	
	@FindBy(xpath="//div[@id='schools-table']/descendant::ul[@class='pagination']/li[@data-page='last']/a")
	WebElement lastPagePagination_button_school;
	
	@FindBy(xpath="//table[@id='schools']/descendant::div[@class='footable-pagination-wrapper']/descendant::span")
	WebElement totalNumOfPages_schools;
	
	String lastPageSchools = "//table[@id='schools']/descendant::tbody/tr";
	
	String schoolPins = "//div[@id='schoolsMap']/descendant::img[@src='https://maps.gstatic.com/mapfiles/api-3/images/spotlight-poi2_hdpi.png']";
	
	//////////////////////////////////////// POI MAP Elements /////////////////////////////////////////////////////////////////////////////
	
	@FindBy(id="poiMap")
	WebElement poiMap;
	
	String poi_pins_xpath = "//div[@id='poiMap']/descendant::div[@title]/img";
	
	String lastPagePOI = "//table[@id='poi']/descendant::tbody/tr";
	
	@FindBy(xpath="//ul[@class='nav nav-tabs nav-justified']/descendant::a[contains(text(),'NEARBY')]")
	WebElement nearybyTabElement;
	
	@FindBy(xpath="//div[@id='poi-table']/descendant::ul[@class='pagination']/li[@data-page='last']/a")
	WebElement lastPagePagination_button_poi;
	
	@FindBy(xpath="//table[@id='poi']/descendant::div[@class='footable-pagination-wrapper']/descendant::span")
	WebElement totalNumOfPages_poi;
	///////////////////////////////////// FEATURES /////////////////////////////////////////////////////
	
	String lFeaturesHeading_xpath = "//div[@id='listing-features']/descendant::h4";
	
	@FindBy(xpath="//a[@id='pick_schedule']/button")
	WebElement scheduleShowing_button;
	
//	@FindBy(xpath="//div[@id='ui-datepicker-div']/descendant::a[@class='ui-state-default ui-state-highlight']")
	@FindBy(xpath="//div[@id='ui-datepicker-div']/descendant::a[@href='#']")

	WebElement datePicker;
	
	@FindBy(xpath="//a[@title='view address']")
	WebElement viewAddress;
	
	String propertyNotes_xpath = "//div[@class='row top-buffer']/descendant::p";

	private ZWContactAgentForm contactAgentForm;
	private ZWScheduleShowingAlert scheduleShowingAlert;
	private ZWLeadCaptureForm leadCaptureForm;
	
	@FindBy(xpath="//div[@id='propdetail']/descendant::h2[1]")
	WebElement property_heading;
	
	public ZWPropertyDetailPage(WebDriver pWebDriver) {
		driver = pWebDriver;
		setContactAgentForm();
		setScheduleShowingAlert();
		setLeadCaptureForm();
		PageFactory.initElements(driver, this);
	}
	
	public ZWContactAgentForm getContactAgentForm() {
		return contactAgentForm;
	}

	public void setContactAgentForm() {
		contactAgentForm = new ZWContactAgentForm(driver);
	}
	
	public ZWScheduleShowingAlert getScheduleShowingAlert() {
		return scheduleShowingAlert;
	}

	public void setScheduleShowingAlert() {
		this.scheduleShowingAlert = new ZWScheduleShowingAlert(driver);
	}
	public void setLeadCaptureForm() {
		this.leadCaptureForm = new ZWLeadCaptureForm(driver);
	}
	public ZWLeadCaptureForm getLeadCaptureForm() {
		return leadCaptureForm;
	}
	
	public boolean verifyPropName() {
		return !ActionHelper.getText(driver, propName_heading).isEmpty();
	}
	public String getPropPrice() {
		return ActionHelper.getText(driver, propPrice_heading).trim();
	}
	public boolean verifyBedBathSizeIcons() {
		boolean lBedIcon = false;
		boolean lBathIcon = false;
		boolean lSizeIcon = false;
		lBedIcon = ActionHelper.isElementVisible(driver, bedIcon);
		lBathIcon = ActionHelper.isElementVisible(driver, bathIcon);
		lSizeIcon = ActionHelper.isElementVisible(driver, sizeIcon);
		return (lBedIcon && lBathIcon && lSizeIcon);
	}
	
	//TODO
	//Need to verify other items base on in which format text is retrive 
	public boolean verifyBedBathSizeCountFromHeader(int pBed, int pBath, int pSize) {
		int lBedCount =Integer.parseInt(ActionHelper.getText(driver, propValues).trim().split(" ")[0]);
		return lBedCount>=pBed;
	}
	
	public String getAddress() {
		return ActionHelper.getText(driver, addressInHeading).trim();
	}
	public String getAddressFromPropDetails() {
		return ActionHelper.getText(driver, ActionHelper.getDynamicElement(driver, propDetails_xpath, "Address:")).trim();
	}
	public String getFeatures() {
		return ActionHelper.getText(driver, ActionHelper.getDynamicElement(driver, propDetails_xpath, "Features:")).trim();
	}
	public String getBedrooms() {
		return ActionHelper.getText(driver, ActionHelper.getDynamicElement(driver, propDetails_xpath, "Bedrooms:")).trim();
	}
	public String getBathrooms() {
		String lBathroomCount = ActionHelper.getText(driver, ActionHelper.getDynamicElement(driver, propDetails_xpath, "Bathrooms:")).trim();
		if (lBathroomCount.contains("full") || lBathroomCount.contains("half")) {
			lBathroomCount = lBathroomCount.split("\\(")[0].trim();
		}
		return lBathroomCount;
	}
	public String getSqFeet() {
		return ActionHelper.getText(driver, ActionHelper.getDynamicElement(driver, propDetails_xpath, "Square Feet:")).trim();
	}
	public String getLotSize() {
		return ActionHelper.getText(driver, ActionHelper.getDynamicElement(driver, propDetails_xpath, "Lot Size:")).trim();
	}
	public String getViews() {
		return ActionHelper.getText(driver, ActionHelper.getDynamicElement(driver, propDetails_xpath, "Views:")).trim();
	}
	public String getPropertyType() {
		return ActionHelper.getText(driver, ActionHelper.getDynamicElement(driver, propDetails_xpath, "Property Type:")).trim();
	}
	public String getCommunity() {
		return ActionHelper.getText(driver, ActionHelper.getDynamicElement(driver, propDetails_xpath, "Community:")).trim();
	}
	public String getNeighborhood() {
		return ActionHelper.getText(driver, ActionHelper.getDynamicElement(driver, propDetails_xpath, "Neighborhood:")).trim();
	}
	public String getCounty() {
		return ActionHelper.getText(driver, ActionHelper.getDynamicElement(driver, propDetails_xpath, "County:")).trim();
	}
	public String getListDate() {
		return ActionHelper.getText(driver, ActionHelper.getDynamicElement(driver, propDetails_xpath, "List Date:")).trim();
	}
	public String getListingNumber() {
		return ActionHelper.getText(driver, ActionHelper.getDynamicElement(driver, propDetails_xpath, "Listing Number:")).trim();
	}
	public String getVirtualTour() {
		return ActionHelper.getText(driver, ActionHelper.getDynamicElement(driver, propDetails_xpath, "Virtual Tour:")).trim();
	}
	public String getStyle() {
		return ActionHelper.getText(driver, ActionHelper.getDynamicElement(driver, propDetails_xpath, "Styles:")).trim();
	}
	public String getYearBuilt() {
		return ActionHelper.getText(driver, ActionHelper.getDynamicElement(driver, lPropAndLotDetails, "Year Built:")).trim();
	}
	public String getMLSDescription() {
		return ActionHelper.getText(driver, mlsDescription).trim();
	}
	public boolean isFeaturesTableVisible() {
		boolean isSuccess = false;
		String lFeatureHeadingArray[] = {"Interior","Exterior","Property and Lot Details"};
		if(ActionHelper.Click(driver, ActionHelper.getDynamicElement(driver, navigationTabs_xpath, "FEATURES"))) {
			List<WebElement> list_of_features_heading = ActionHelper.getListOfElementByXpath(driver, lFeaturesHeading_xpath);
			for(String lFeature: lFeatureHeadingArray) {
				isSuccess = false;
				for(WebElement element: list_of_features_heading) {
					if(element.getText().trim().equalsIgnoreCase(lFeature)) {
						isSuccess = true;
						break;
					}
				}
				if(!isSuccess) {
					break;
				}
			}
		}
		return isSuccess;
	}
	public boolean isGoogleMapAndPinVisible() {
		ActionHelper.staticWait(10);
		int counter = 0;
		WebDriverWait wait=new WebDriverWait(driver, 20);
		ActionHelper.ScrollToElement(driver, ActionHelper.getDynamicElement(driver, navigationTabs_xpath, "MAP"));
		boolean isSuccess = false;
		if(ActionHelper.Click(driver, ActionHelper.getDynamicElement(driver, navigationTabs_xpath, "MAP"))) {
			ActionHelper.staticWait(10);
			if(ActionHelper.waitForElementToBeVisible(driver, listing_map,30)) {
////				isSuccess = ActionHelper.isElementVisible(driver, googleMapPinIcon);
//				try {
//					isSuccess = wait.until(ExpectedConditions.attributeContains(By.xpath("//map[@id='gmimap0']/parent::div/img"), "src", ".png"));
//				}catch(Exception ex) {
//					System.out.println("No Pin is displayed on Google MAPS");
//					return isSuccess;
//				}
				isSuccess = true;
			}
		}
		return isSuccess;
	}
	
	public boolean verifyCommunityStatsVisible() {
		ActionHelper.staticWait(10);
		if(ActionHelper.Click(driver, ActionHelper.getDynamicElement(driver, navigationTabs_xpath, "COMMUNITY STATS"))) {
			if(!ActionHelper.waitForElementToBeVisible(driver, overiviewTable, 10)) {
				AutomationLogger.error("Overview table under Community Stats is not visible..");
				return false;
			}
			if(!ActionHelper.waitForElementToBeVisible(driver, expenseContainer, 10)) {
				AutomationLogger.error("Expense table under Community Stats is not visible..");
				return false;
			}
			if(!ActionHelper.waitForElementToBeVisible(driver, generalContainer, 10)) {
				AutomationLogger.error("General table under Community Stats is not visible..");
				return false;
			}
			if(!ActionHelper.waitForElementToBeVisible(driver, populationContainer, 10)) {
				AutomationLogger.error("Population table under Community Stats is not visible..");
				return false;
			}
			if(!ActionHelper.waitForElementToBeVisible(driver, educationContainer, 10)) {
				AutomationLogger.error("Education table under Community Stats is not visible..");
				return false;
			}
			if(!ActionHelper.waitForElementToBeVisible(driver, householdContainer, 10)) {
				AutomationLogger.error("House Hold table under Community Stats is not visible..");
				return false;
			}
			if(!ActionHelper.waitForElementToBeVisible(driver, crimeIndexContainer, 10)) {
				AutomationLogger.error("Crime Index table under Community Stats is not visible..");
				return false;
			}
			if(!ActionHelper.waitForElementToBeVisible(driver, climateContainer, 10)) {
				AutomationLogger.error("Climate table under Community Stats is not visible..");
				return false;
			}
			if(!ActionHelper.waitForElementToBeVisible(driver, qualityContainer, 10)) {
				AutomationLogger.error("Quality table under Community Stats is not visible..");
				return false;
			}
		}else {
			return false;
		}
		return true;
	}
	public boolean verifySchoolMap() {
		if(ActionHelper.Click(driver, ActionHelper.getDynamicElement(driver, navigationTabs_xpath, "SCHOOLS"))) {
			if(!ActionHelper.waitForElementToBeVisible(driver, schoolMap, 10)) {
				AutomationLogger.error("School Map is not visible....");
				return false;
			}
			if(!verifySchoolPins()) {
				AutomationLogger.error("Pins of school map count mismatched....");
				return false;
			}
			
		}else {
			return false;
		}
		return true;
	}
	public boolean verifyPOIMap() {
		if(ActionHelper.Click(driver, nearybyTabElement)) {
			if(!ActionHelper.waitForElementToBeVisible(driver, poiMap, 10)) {
				AutomationLogger.error("POI Map is not visible....");
				return false;
			}
			if(!verifyPOIPins()) {
				AutomationLogger.error("Pins of POI map count mismatched....");
				return false;
			}
		}else {
			return false;
		}
		return true;
	}
	private boolean verifySchoolPins() {
		boolean isSuccess = false;
		if(ActionHelper.Click(driver, lastPagePagination_button_school)) {
			List<WebElement> list_of_schools = ActionHelper.getListOfElementByXpath(driver, lastPageSchools);
			int lSchoolPins = ActionHelper.getListOfElementByXpath(driver, schoolPins).size()/2;
			int lTotalPages = Integer.parseInt(ActionHelper.getText(driver, totalNumOfPages_schools).split("of")[1].trim())-1;
			int lTotalSchools = (lTotalPages*15)+list_of_schools.size();
			isSuccess = lSchoolPins==lTotalSchools;
		}
		return isSuccess;
	}
	
	private boolean verifyPOIPins() {
		boolean isSuccess = false;
		if(ActionHelper.Click(driver, lastPagePagination_button_poi)) {
			List<WebElement> list_of_POI = ActionHelper.getListOfElementByXpath(driver, lastPagePOI);
			int lPins = ActionHelper.getListOfElementByXpath(driver, poi_pins_xpath).size();
			int lTotalPages = Integer.parseInt(ActionHelper.getText(driver, totalNumOfPages_poi).split("of")[1].trim())-1;
			int lTotalPins = (lTotalPages*15)+list_of_POI.size();
			isSuccess = lPins==lTotalPins;
		}
		return isSuccess;
	}
	
	public boolean clickOnScheduleShowingButton() {
		return ActionHelper.Click(driver, scheduleShowing_button);
	}
	public boolean isScheduleShowingButtonVisible() {
		return ActionHelper.waitForElementToBeVisible(driver, scheduleShowing_button, 30);
	}
	
	public boolean selectCurrentDate() {
//		boolean isSuccess = true;
//		try {
//			datePicker.isDisplayed();
//		datePicker.click();//ActionHelper.Click(driver, datePicker);
//		}catch(Exception ex) {
//			isSuccess = false;
//		}
		return ActionHelper.Click(driver, datePicker);
	}
	public boolean clickOnViewAddress() {
		return ActionHelper.Click(driver, viewAddress);
	}
	public String getPropHeading() {
		return ActionHelper.getText(driver, property_heading);
	}
	@Override
	public WebElement getHeader() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WebElement getBrand() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WebElement getTopMenu() {
		// TODO Auto-generated method stub
		return null;
	}
	public boolean verifyNotes(String pNote) { 
		ActionHelper.waitForStringXpathToBeVisible(driver, propertyNotes_xpath, 30);
		boolean isVerified = false;
		String actualText = "";
		String expectedtext = "";
		List<WebElement> list_of_elements = ActionHelper.getListOfElementByXpath(driver, propertyNotes_xpath);
		for (WebElement element: list_of_elements) {
			if(ActionHelper.getText(driver, element).contains(pNote)) {
				actualText = ActionHelper.getText(driver, element).trim().split("\n")[0];
				expectedtext = pNote.trim();
				if(actualText.equals(expectedtext)) {
					isVerified = true;
					break;
				}
			}
		}
		return isVerified;
	}
}
