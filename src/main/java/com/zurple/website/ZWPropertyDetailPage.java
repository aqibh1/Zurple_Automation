/**
 * 
 */
package com.zurple.website;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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
	
	@FindBy(xpath="//ul[@class='pagination']/li[@data-page='last']/a")
	WebElement lastPagePagination_button;
	
	@FindBy(xpath="//div[@class='footable-pagination-wrapper']/descendant::span")
	WebElement totalNumOfPages;
	
	String lastPageSchools = "//table[@id='schools']/descendant::tbody/tr";
	
	String schoolPins = "//div[@id='schoolsMap']/descendant::img[@src='https://maps.gstatic.com/mapfiles/api-3/images/spotlight-poi2_hdpi.png']";
	
	//////////////////////////////////////// POI MAP Elements /////////////////////////////////////////////////////////////////////////////
	
	@FindBy(id="poiMap")
	WebElement poiMap;
	
	String poi_pins_xpath = "//div[@id='poiMap']/descendant::div[@title]/img";
	
	String lastPagePOI = "//table[@id='poi']/descendant::tbody/tr";
	
	///////////////////////////////////// FEATURES /////////////////////////////////////////////////////
	
	String lFeaturesHeading_xpath = "//div[@id='listing-features']/descendant::h4";
	
	public ZWPropertyDetailPage(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
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
		boolean isSuccess = false;
		if(ActionHelper.Click(driver, ActionHelper.getDynamicElement(driver, navigationTabs_xpath, "MAP"))) {
			if(ActionHelper.isElementVisible(driver, listing_map)) {
				isSuccess = ActionHelper.isElementVisible(driver, googleMapPinIcon);
			}
		}
		return isSuccess;
	}
	
	public boolean verifyCommunityStatsVisible() {
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
		if(ActionHelper.Click(driver, ActionHelper.getDynamicElement(driver, navigationTabs_xpath, "WHAT'S NEARBY"))) {
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
		if(ActionHelper.Click(driver, lastPagePagination_button)) {
			List<WebElement> list_of_schools = ActionHelper.getListOfElementByXpath(driver, lastPageSchools);
			int lSchoolPins = ActionHelper.getListOfElementByXpath(driver, schoolPins).size()/2;
			int lTotalPages = Integer.parseInt(ActionHelper.getText(driver, totalNumOfPages).split("of")[1].trim());
			int lTotalSchools = (lTotalPages*15)+list_of_schools.size();
			isSuccess = lSchoolPins==lTotalSchools;
		}
		return isSuccess;
	}
	
	private boolean verifyPOIPins() {
		boolean isSuccess = false;
		if(ActionHelper.Click(driver, lastPagePagination_button)) {
			List<WebElement> list_of_POI = ActionHelper.getListOfElementByXpath(driver, lastPagePOI);
			int lPins = ActionHelper.getListOfElementByXpath(driver, poi_pins_xpath).size();
			int lTotalPages = Integer.parseInt(ActionHelper.getText(driver, totalNumOfPages).split("of")[1].trim());
			int lTotalPins = (lTotalPages*15)+list_of_POI.size();
			isSuccess = lPins==lTotalPins;
		}
		return isSuccess;
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

}
