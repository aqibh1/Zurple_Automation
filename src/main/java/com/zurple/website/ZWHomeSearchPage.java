/**
 * 
 */
package com.zurple.website;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.utility.ActionHelper;
import resources.utility.FrameworkConstants;
import us.zengtest1.Page;

/**
 * @author adar
 *
 */
public class ZWHomeSearchPage extends Page{
	
	@FindBy(id="city")
	WebElement search_input;
	
	@FindBy(xpath="//a[@href='/login']")
	WebElement login_link;

	@FindBy(xpath="//div[@class='page-header text-center']/h3[contains(text(),'Homes For Sale in')]")
	WebElement homesForSale_heading;
	
	@FindBy(xpath="//h3[text()='Featured Homes']")
	WebElement featuredHomes_heading;
	
	String logos_xpath = "//img[@src]";
	
	@FindBy(id="min_price")
	WebElement min_price;
	
	@FindBy(id="max_price")
	WebElement max_price;
	
	@FindBy(xpath="//input[@type='submit' and @value='SEARCH']")
	WebElement search_button;
	
	@FindBy(id="expand-search-fields")
	WebElement expandSearchFields;
	
	@FindBy(id="contract-search-fields")
	WebElement contractSearchFields;
	
	@FindBy(id="by_zip")
	WebElement zip_input;;
	
	@FindBy(id="by_address")
	WebElement address_input;
	
	@FindBy(id="by_mls")
	WebElement mls_input;
	
	@FindBy(id="by_neighborhood")
	WebElement neighbourhood_input;
	
	@FindBy(id="by_school")
	WebElement school_input;
	
	@FindBy(id="by_county")
	WebElement county_input;
	
	@FindBy(id="bedrooms")
	WebElement bedrooms_dropdown;
	
	@FindBy(id="bathrooms")
	WebElement bathrooms_dropdown;
	
	@FindBy(id="square_feet")
	WebElement square_feet_dropdown;
	
	@FindBy(id="year_built")
	WebElement year_built_dropdown;
	
	@FindBy(id="lot_sqft")
	WebElement lot_sqft_dropdown;
	
	@FindBy(id="type_home")
	WebElement homes_input;
	
	@FindBy(id="type_condo")
	WebElement condo_input;
	
	@FindBy(id="type_land")
	WebElement land_input;
	
	@FindBy(xpath="//select[@id='feature']/following-sibling::span/descendant::input[@type='search']")
	WebElement feature_input;
	
	String select_features = "//ul[@id='select2-feature-results']/li";
	
	@FindBy(xpath="//select[@id='style']/following-sibling::span/descendant::input[@type='search']")
	WebElement style_input;
	
	String select_style = "//ul[@id='select2-style-results']/li";
	
	@FindBy(xpath="//select[@id='view']/following-sibling::span/descendant::input[@type='search']")
	WebElement view_input;
	
	String select_view = "//ul[@id='select2-view-results']/li";
	
	String select_input = "//div[@id='jSuggestContainer']/descendant::li[contains(text(),'"+FrameworkConstants.DYNAMIC_VARIABLE+"')]";
	
	@FindBy(xpath="//div[@id='jSuggestContainer']")
	WebElement suggestion_container;
	
	@FindBy(xpath="//nav[@class='top-pagination-block']/descendant::li/a[@title='next page']")
	WebElement next_page;
	
	public ZWHomeSearchPage() {
		
	}
	public ZWHomeSearchPage(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean typeInputString(String pInputString) {
		boolean isSuccess = true;
		try {
			if(ActionHelper.ClearAndType(driver, search_input, pInputString)) {
				ActionHelper.staticWait(15);
				ActionHelper.getDynamicElement(driver,select_input, pInputString).click();;
//				isSuccess = ActionHelper.Click(driver, suggested_element);
			}
		}catch(Exception ex) {
			isSuccess = false;
		}
		return isSuccess;
	}

	public boolean verifyLogos() {
		boolean isFound = false;
		String [] logos_names = {"zurple_logo_64.png","79d4f98829243c758a1b869e5d0085d7.png"};
		List<WebElement> list_elements = driver.findElements(By.xpath(logos_xpath));
		for(String logo:logos_names) {
			isFound = false;
			for(WebElement element: list_elements) {
				if(element.getAttribute("src").contains(logo)) {
					isFound = true;
					break;
				}
			}
			if(!isFound) {
				break;
			}
		}
		return isFound;
	}
	
	public boolean isFeaturedhomeHeadingVisible() {
		return ActionHelper.waitForElementToBeVisible(driver, featuredHomes_heading, 30);
	}
	
	public boolean isHomesForSaleHeadingVisible() {
		return ActionHelper.waitForElementToBeVisible(driver, homesForSale_heading, 30);
	}
	public boolean selectMinPrice(String pMinPrice) {
		return ActionHelper.selectDropDownOption(driver, min_price, "", pMinPrice);
	}
	public boolean selectMaxPrice(String pMaxPrice) {
		return ActionHelper.selectDropDownOption(driver, max_price, "", pMaxPrice);
	}
	public boolean clickSearchButton() {
		return ActionHelper.Click(driver, search_button);
	}
	public boolean clickOnExpandSearchFields() {
		boolean isSuccess = false;
		if(ActionHelper.Click(driver, expandSearchFields)) {
			isSuccess = ActionHelper.waitForElementToBeVisible(driver, contractSearchFields, 10);
		}
		return isSuccess;
	}
	public boolean selectInputType(String pInputType) {
		boolean isSelectSuccess = false;
		switch(pInputType) {
		case "Zip":
			clickOnExpandSearchFields();
			ActionHelper.staticWait(5);
			isSelectSuccess = ActionHelper.Click(driver, zip_input);
			break;
		case "Address":
			clickOnExpandSearchFields();
			ActionHelper.staticWait(5);
			isSelectSuccess = ActionHelper.Click(driver, address_input);
			break;
		case "MLS":
			clickOnExpandSearchFields();
			ActionHelper.staticWait(5);
			isSelectSuccess = ActionHelper.Click(driver, mls_input);
			break;
		case "Neighborhood":
			clickOnExpandSearchFields();
			ActionHelper.staticWait(5);
			isSelectSuccess = ActionHelper.Click(driver, neighbourhood_input);
			break;
		case "School District":
			clickOnExpandSearchFields();
			ActionHelper.staticWait(5);
			isSelectSuccess = ActionHelper.Click(driver, school_input);
			break;
		case "County":
			clickOnExpandSearchFields();
			ActionHelper.staticWait(5);
			isSelectSuccess = ActionHelper.Click(driver, county_input);
			break;
		default:
			isSelectSuccess = true;
			break;
		}
		return isSelectSuccess;
	}
	
	public boolean selectBedrooms(String pBedrooms) {
		return ActionHelper.selectDropDownOption(driver, bedrooms_dropdown, "", pBedrooms);
	}
	
	public boolean selectBathrooms(String pBathrooms) {
		return ActionHelper.selectDropDownOption(driver, bathrooms_dropdown, "", pBathrooms);
	}
	
	public boolean selectSquareFeet(String pSquareFeet) {
		return ActionHelper.selectDropDownOption(driver, square_feet_dropdown, "", pSquareFeet);
	}
	public boolean selectYearBuilt(String pYearBuilt) {
		return ActionHelper.selectDropDownOption(driver, year_built_dropdown, "", pYearBuilt);
	}
	public boolean selectLotSize(String pLotSize) {
		return ActionHelper.selectDropDownOption(driver, lot_sqft_dropdown, "", pLotSize);
	}
	public boolean selectPropertyType(String[] pPropType) {
		boolean isChecked = false;
		boolean homeChecked = false;
		boolean condoChecked = false;
		boolean landChecked = false;
		for(String lPropType: pPropType) {
			isChecked = false;
			if(lPropType.equalsIgnoreCase("Homes")) {
				homeChecked = true;
				isChecked = ActionHelper.isElementSelected(driver, homes_input)?true:ActionHelper.Click(driver, homes_input);
			}else if(lPropType.equalsIgnoreCase("CONDO")) {
				condoChecked = true;
				isChecked = ActionHelper.isElementSelected(driver, condo_input)?true:ActionHelper.Click(driver, condo_input);
			}else if(lPropType.equalsIgnoreCase("LOTS/LAND")) {
				landChecked = true;
				isChecked = ActionHelper.isElementSelected(driver, land_input)?true:ActionHelper.Click(driver, land_input);

			}
			if(!isChecked) {
				break;
			}
		}
		//Unchecking the remaining options
		if(!homeChecked) {
			ActionHelper.Click(driver, homes_input);
		}
		if(!condoChecked) {
			ActionHelper.Click(driver, condo_input);
		}
		if(!landChecked) {
			ActionHelper.Click(driver, land_input);
		}
		
		return isChecked;
	}
	public boolean selectFeatures(String[] pFeatures) {
		return selectOptions(pFeatures, feature_input, select_features);
	}
	
	public boolean selectStyle(String[] pStyle) {
		return selectOptions(pStyle, style_input, select_style);
	}
	
	public boolean selectView(String[] pView) {
		return selectOptions(pView, view_input, select_view);
	}
	
	public boolean selectOptions(String[] pOptionsToSelect, WebElement pElementToClick, String pOptionsXpath) {
		boolean isSucceess = false;
		if(ActionHelper.Click(driver, pElementToClick)) {
			for(String lOption: pOptionsToSelect) {
				isSucceess = false;
				List<WebElement> list_of_element = ActionHelper.getListOfElementByXpath(driver, pOptionsXpath);
				for(WebElement element: list_of_element) {
					if(element.getText().trim().equalsIgnoreCase(lOption)) {
						isSucceess = ActionHelper.Click(driver, element);
						break;
					}
				}
				if(!isSucceess) {
					break;
				}
			}
		}
		return isSucceess;
	}
	
	public boolean clickOnLoginLink() {
		return ActionHelper.Click(driver, login_link);
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
	
	public boolean clickOnNextButton() {
		boolean isSuccessful = false;
		if(ActionHelper.waitForElementToBeVisible(driver, next_page, 30)) {
			WebElement webElement = driver.findElement(By.xpath("//nav[@class='top-pagination-block']/descendant::li/a[@title='next page']"));
			while(webElement!=null) {
				closeModal();
				ActionHelper.waitForElementToBeClickAble(driver, webElement);
				isSuccessful = ActionHelper.Click(driver, webElement);
				ActionHelper.staticWait(2);
				webElement = ActionHelper.getElementByXpath(driver,"//nav[@class='top-pagination-block']/descendant::li/a[@title='next page']");
			}
		}
		return isSuccessful;
	}
	public void closeModal() {
		List<WebElement> list = ActionHelper.getListOfElementByXpath(driver, "//button[@class='close']/span[@aria-hidden='true']");
		if(list!=null && list.size()>1) {
			if(ActionHelper.isElementVisible(driver, list.get(1))) {
				ActionHelper.Click(driver, list.get(1));
			}
		}
	}

}
