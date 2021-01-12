/**
 * 
 */
package resources.forms.z57;

import java.util.List;

import org.apache.commons.lang3.text.WordUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import resources.forms.AbstractForm;
import resources.utility.ActionHelper;

/**
 * @author adar
 * This class contains all the xpath related to search form
 *
 */
public class SearchForm extends AbstractForm{
	
	private static String DYNAMIC_VARIABLE="[--Dynamic Variable--]";
//	WebDriver localWebDriver;
	SearchForm searchForm;
	WebDriverWait wait;
	//Search Input Field
	@FindBy(xpath="//div[@class='input-group']/descendant::ul[@class='select2-choices']/descendant::input[@type='text']")
	WebElement searchField_input;

	String searchInputDropdown_xpath="//span[@role='status' and contains(text(),'"+DYNAMIC_VARIABLE+"')]";
	
	//Drop down list when anything is entered in input field.
//	@FindBy(xpath="//ul[@class='select2-choices']/descendant::input[@id='s2id_autogen1']")
	WebElement selectOption_dropDownList;

	//Search button
	@FindBy(xpath="//button[@id='main_search_submit']")
	WebElement search_button;

	//Search by dropdown
	@FindBy(xpath="//select[@id='search_location_type_main']")
	WebElement searchby_dropdown;

	//Select the search by option
	//@FindBy(xpath="//option[@id='[--Dynamic Variable--]']")
	WebElement searchby_option;
	String searchByOption_xpath="//option[@id='"+DYNAMIC_VARIABLE+"']";

	//Price Low drop down
	@FindBy(xpath="//div[@id='zfs_idx_search_options_container']/select[@id='price_low']")
	WebElement priceLow_dropDown;
	
	@FindBy(xpath="//select[@id='price_low']")
	WebElement priceLow_dropDown_idx;

	//Price Low option
	//@FindBy(xpath="//div[@id='zfs_idx_search_options_container']/select[@id='price_low']/option[@value='[--Dynamic Variable--]']")
	WebElement priceLow_options;
	String priceLowOptions_xpath="//div[@id='zfs_idx_search_options_container']/select[@id='price_low']/option[@value='[--Dynamic Variable--]']";
	String priceLowOptions_xpath_idx="//select[@id='price_low']/option[@value='[--Dynamic Variable--]']";

	//Price Max drop down
	@FindBy(xpath="//div[@id='zfs_idx_search_options_container']/select[@id='price_max']")
	WebElement priceMax_dropDown;
	
	@FindBy(xpath="//select[@id='price_max']")
	WebElement priceMax_dropDown_idx;
	
	//Price Max option
	//@FindBy(xpath="//div[@id='zfs_idx_search_options_container']/select[@id='price_max']/option[@value='[--Dynamic Variable--]']")
	WebElement priceMax_options;
	String priceMaxOptions_xpath="//div[@id='zfs_idx_search_options_container']/select[@id='price_max']/option[@value='[--Dynamic Variable--]']";
	String priceMaxOptions_xpath_idx="//select[@id='price_max']/option[@value='[--Dynamic Variable--]']";
	
	//Beds drop down
	@FindBy(xpath="//div[@id='zfs_idx_search_options_container']/select[@name='bedrooms']")
	WebElement beds_dropDown;
	
	@FindBy(xpath="//select[@name='bedrooms']")
	WebElement beds_dropDown_idx;
	//Beds option
	//		@FindBy(xpath="//div[@id='zfs_idx_search_options_container']/select[@name='bedrooms']/option[@value='[--Dynamic Variable--]']")
	WebElement beds_options;
	String bedsOptions_xpath="//div[@id='zfs_idx_search_options_container']/select[@name='bedrooms']/option[@value='[--Dynamic Variable--]']";
	String bedsOptions_xpath_idx="//select[@name='bedrooms']/option[@value='[--Dynamic Variable--]']";

	//Baths drop down
	@FindBy(xpath="//div[@id='zfs_idx_search_options_container']/select[@name='bathrooms']")
	WebElement bathrooms_dropDown;
	
	@FindBy(xpath="//select[@name='bathrooms']")
	WebElement bathrooms_dropDown_idx;
	
	//Baths option
	//		@FindBy(xpath="//div[@id='zfs_idx_search_options_container']/select[@name='bathrooms']/option[@value='[--Dynamic Variable--]']")
	WebElement bathrooms_options;
	String bathroomsOptions_xpath="//div[@id='zfs_idx_search_options_container']/select[@name='bathrooms']/option[@value='[--Dynamic Variable--]']";
	String bathroomsOptions_xpath_idx="//select[@name='bathrooms']/option[@value='[--Dynamic Variable--]']";

	@FindBy(xpath="//div[@id='zfs_idx_search_options_container']/descendant::button[@id='zfs_idx_form_more_options_button']")
	WebElement advanceSearchExpand_button;
	
	@FindBy(id="zfs_idx_form_more_options_button")
	WebElement moreOptions_button;

	@FindBy(xpath="//div[@id='s2id_property_type_select']/descendant::input[@id='s2id_autogen2']")
	WebElement propertyType_input;
	String propertyTypeInput_xpath="//div[@id='select2-drop']/descendant::div[contains(text(),'"+DYNAMIC_VARIABLE+"')]";

	@FindBy(xpath="//div[@class='col-md-6']/descendant::*[@id='zfs_features_range_select']")
	WebElement featuresAnyAll_dropdown;

	String selectAnyOrAllOptionFeature_xpath="//select[@id='zfs_features_range_select']/option[@value='"+DYNAMIC_VARIABLE+"']";
	WebElement anyOrAllOptionFeature_select;

	WebElement dropDownOptions;
	
	@FindBy(xpath="//div[@id='s2id_features_select']/descendant::input[@id='s2id_autogen5']")
	WebElement propertyFeatures_input;
	
	String selectFeaturesOptions_xpath="//div[@id='select2-drop']/descendant::span[text()='"+DYNAMIC_VARIABLE+"']";//"//div[@id='s2id_features_select']/following::select[@id='features_select']/option[text()='"+DYNAMIC_VARIABLE+"']";
	
	@FindBy(xpath="//div[@id='s2id_sq_footage_select']/descendant::span[@id='select2-chosen-9']")
	WebElement squareFootage_dropdown;
	
	@FindBy(xpath="//div[@class='select2-search']/descendant::input[@id='s2id_autogen9_search']")
	WebElement squareFootage_input;

	
	@FindBy(xpath="//div[@id='s2id_view_select']/descendant::input[@id='s2id_autogen4']")
	WebElement view_input;
	
	@FindBy(xpath="//div[@id='select2-drop']/descendant::div[@role='option']")
	WebElement select_View;
	String select_view_xpath = "//div[@id='select2-drop']/descendant::div[@role='option']";
	
//	String viewInput_xpath="//select[@id='view_select']/option[@value='"+DYNAMIC_VARIABLE+"']";
	String viewInput_xpath="//div[@id='select2-drop']/descendant::div[text()='"+DYNAMIC_VARIABLE+"']";
	
	String styleInput_xpath="//select[@id='style_select']/option[@value='"+DYNAMIC_VARIABLE+"']";
	
	@FindBy(xpath="//div[@id='s2id_style_select']/descendant::input[@id='s2id_autogen3']")
	WebElement styleInput_dropdown;
	
	@FindBy(xpath="//div[@id='s2id_lot_size_select']/descendant::span[@id='select2-chosen-8']")
	WebElement lotSize_dropdown;
	
	@FindBy(xpath="//div[@class='select2-search']/descendant::input[@id='s2id_autogen8_search']")
	WebElement lotSize_Input;
	
	@FindBy(xpath="//div[@id='s2id_status_select']/descendant::input[@id='s2id_autogen6']")
	WebElement status_dropdown;
	
	String status_xpath="//div[@id='select2-drop']/descendant::div[text()=' "+DYNAMIC_VARIABLE+"']";
	
	@FindBy(xpath="//div[@id='s2id_min_year_built_select']/descendant::span[@id='select2-chosen-7']")
	WebElement yearBuild_dropdown;
	
	@FindBy(xpath="//input[@id='address_input']")
	WebElement address_input;
	
	String addressMatch_xpath = "//div[@class='pac-container pac-logo hdpi']/descendant::span[@class]";
	
	@FindBy(xpath="//input[@id='mlsid_input']")
	WebElement mls_input;
	
	@FindBy(xpath="//div[@id='s2id_location_input_main']/descendant::ul[@class='select2-choices']/descendant::input")
	WebElement neighborhood_input;
	
	@FindBy(xpath="//ul[@class='select2-result-sub']/descendant::span")
	String neighborhood_dropdown="//ul[@class='select2-result-sub']/descendant::span";
	
	String yearBuildXpath="//div[@id='select2-drop']/descendant::div[text()='"+DYNAMIC_VARIABLE+"']";
	
	@FindBy(xpath="//div[@id='s2id_location_input_main']/descendant::input[@type='text']")
	WebElement city_input_idx;
	
	public SearchForm(WebDriver pWebDriver) {
		driver=pWebDriver;
		wait=new WebDriverWait(driver, 10);
		setSubmitButton(search_button);
		PageFactory.initElements(driver, this);
	}
	public boolean clickOnInputField() {
		if(searchField_input.isDisplayed()) {
			searchField_input.click();
			return true;
		}else {
			return false;
		}
	}
	public boolean clickOnSuggestedOptionsInInputField() {
		if(searchField_input.isDisplayed()) {
			searchField_input.sendKeys(Keys.ARROW_DOWN);
			searchField_input.sendKeys(Keys.ENTER);
			//				searchField_input.click();
			return true;
		}else {
			return false;
		}
	}

	public boolean typeInputAndSelect(String pStringToType) {

		boolean isTypeSuccessful=false;
		try {

			if(searchField_input.isDisplayed()) {
				searchField_input.sendKeys(Keys.BACK_SPACE);
				searchField_input.sendKeys(Keys.BACK_SPACE);
				searchField_input.sendKeys(pStringToType);

				List<WebElement> listOfWebElements = driver.findElements(By.xpath("//div[@id='select2-drop']/descendant::div"));
				for (WebElement singleElement: listOfWebElements){
					System.out.println(singleElement.getText());
					if(singleElement.getText().equalsIgnoreCase(pStringToType)) {
						singleElement.click();
						isTypeSuccessful= true;
						break;
					}
					
				}
			}
		}
		catch(Exception ex) {
			System.out.println(ex.toString());
		}
		return isTypeSuccessful;
	}
	public boolean typeAddress(String pText) {
		boolean isSuccess = false;
		if(ActionHelper.waitForElementToBeVisible(driver, address_input, 30) && ActionHelper.Type(driver, address_input, pText)) {
			ActionHelper.staticWait(2);
			List<WebElement> list_of_matches = ActionHelper.getListOfElementByXpath(driver, addressMatch_xpath);
			for(WebElement element: list_of_matches) {
				//if(element.getText().equalsIgnoreCase(pText)) {
					if(pText.contains(element.getText().trim())) {
					isSuccess = ActionHelper.Click(driver, element);
					ActionHelper.staticWait(3);
					break;
				}
			}
		}
		//		return typeAndDownArrowSelect(address_input, pText);
		return isSuccess;
	}
	public boolean typeMLS(String pText) {
		return typeAndDownArrowSelect(mls_input, pText);
	}
	public boolean typeAndSelectNeighborhood(String pNeighborhood) {
		return typeAndSelect(pNeighborhood, neighborhood_input, neighborhood_dropdown);
	}
	
	public boolean clickOnSearchButton() {
		boolean isClickSuccessful = false;
//		if(search_button.isDisplayed()) {
//			search_button.click();
//			return true;
//		}else {
//			return false;
//		}
		if(ActionHelper.waitForElementToBeVisible(driver, search_button, 30)) {
			isClickSuccessful = ActionHelper.Click(driver, search_button);
		}
		return isClickSuccessful;
	}

	public boolean clickOnSearchByOption(String pOption) {
		boolean isClickSuccessful=false;
		try {

			//Selects dynamically which option to select.
			switch(pOption) {

			case "Zip":
				searchby_option= getDynamicElement(searchByOption_xpath,"idx_location_button_ZI_main");
				break;

			case "City":
				searchby_option= getDynamicElement(searchByOption_xpath,"idx_location_button_LC_main");
				break;

			case "Address":
				searchby_option= getDynamicElement(searchByOption_xpath,"idx_location_button_ADDRESS_main");
				break;

			case "Neighborhood":
				searchby_option= getDynamicElement(searchByOption_xpath,"idx_location_button_NH_main");
				break;

			case "MLS":
				searchby_option= getDynamicElement(searchByOption_xpath,"idx_location_button_MLSID_main");
				break;

			default:
				searchby_option= getDynamicElement(searchByOption_xpath,"idx_location_button_ZI_main");
				break;
			}

			if(searchby_option!=null) {
				searchby_option.click();
				isClickSuccessful = true;
				ActionHelper.staticWait(3);
			}else {
				isClickSuccessful = false;
			}
		}catch(Exception ex) {
			System.out.println(ex.toString());
		}
		return isClickSuccessful;
	}

	public boolean clickOnPriceLowOption(String pPrice) {
		return clickAndSelect(priceLow_dropDown, pPrice, priceLowOptions_xpath);
	}

	public boolean clickOnPriceMaxOption(String pPrice) {
		return clickAndSelect(priceMax_dropDown, pPrice, priceMaxOptions_xpath);
	}

	public boolean clickOnBedsOption(String pBeds) {
		return clickAndSelect(beds_dropDown, pBeds, bedsOptions_xpath);
	}

	public boolean clickOnBathsOption(String pBathrooms) {
		return clickAndSelect(bathrooms_dropDown, pBathrooms, bathroomsOptions_xpath);
	}
	public boolean clickOnExpandSearchButton() {
		//Handled the condition if advance search button is already expanded
		boolean isClickSuccessful=false;
		if(!propertyType_input.isDisplayed()) {
			if(advanceSearchExpand_button.isDisplayed()) {
				advanceSearchExpand_button.click();
				isClickSuccessful=true;
			}
		}else {
			if(advanceSearchExpand_button.isDisplayed()) {
				isClickSuccessful=true;
			}
		}
		return isClickSuccessful;
	}
	public boolean typeAndSelectPropertyType(String pPropertyType) {
		boolean isSuccessful=false;
		if(clickAndSelectOneClick(propertyType_input, pPropertyType, propertyTypeInput_xpath)) {
			focusOut();
			isSuccessful=true;
		}
		return isSuccessful;

	}

	public boolean selectFeature(String pFeatureAnyAll) {
		driver.findElement(By.id("zfs_features_range_select")).click();
		dropDownOptions=getDynamicElement(selectAnyOrAllOptionFeature_xpath, pFeatureAnyAll);
		dropDownOptions.click();
		return true;
//		return clickAndSelect(featuresAnyAll_dropdown,pFeatureAnyAll,selectAnyOrAllOptionFeature_xpath);
	}
	
	public boolean clickAndSelectFeature(String pFeature) {
//		return typeAndSelect(propertyFeatures_input, pFeature, selectFeaturesOptions_xpath);
		boolean isSuccess = false;
		if(ActionHelper.Click(driver, propertyFeatures_input)) {
			ActionHelper.waitForElementToBeVisible(driver,select_View , 30);
			List<WebElement> list_of_elements = ActionHelper.getListOfElementByXpath(driver, select_view_xpath);
			for(WebElement element: list_of_elements) {
				if(element.getText().trim().equalsIgnoreCase(pFeature)) {
					isSuccess = ActionHelper.Click(driver, element);
					break;
				}
			}
		}
		ActionHelper.doubleClick(driver, moreOptions_button);
		return isSuccess;
	}
	
	public boolean clickAndSelecctSquareFootage(String pSquareFootValue) {
		boolean isSuccessfull=false;
		try {
			if(wait.until(ExpectedConditions.visibilityOf(squareFootage_dropdown))!=null) {
				squareFootage_dropdown.click();
				if(typeInputAndSelectTheOption(squareFootage_input,pSquareFootValue)) {
					isSuccessfull=true;
				}
			}
		}catch(Exception ex) {
			System.out.println("Unable to click on Square Footage drop down");
		}
		return isSuccessfull;

	}
	
	public boolean clickAndSelectView(String pView) {
		boolean isSuccessful=false;
//		if(clickAndSelectOneClick(view_input, pView, viewInput_xpath)) {
//			focusOut();
//			isSuccessful=true;
//		}
		if(ActionHelper.Click(driver, view_input)) {
			ActionHelper.waitForElementToBeVisible(driver,select_View , 30);
			List<WebElement> list_of_elements = ActionHelper.getListOfElementByXpath(driver, select_view_xpath);
			for(WebElement element: list_of_elements) {
				if(element.getText().trim().equalsIgnoreCase(pView)) {
					isSuccessful = ActionHelper.Click(driver, element);
					break;
				}
			}
		}
		ActionHelper.doubleClick(driver, moreOptions_button);
		return isSuccessful;
	}
	
	public boolean clickAndSelectStyle(String pStyle) {
		boolean isSuccessful=false;
		if(clickAndSelectOneClick(styleInput_dropdown, pStyle, viewInput_xpath)) {
			focusOut();
			isSuccessful=true;
		}
		return isSuccessful;
	}
	
	public boolean clickAndSelectLotSize(String pLotSize) {
		boolean isSuccessfull=false;
		ActionHelper.Click(driver, lotSize_dropdown);
		if(typeInputAndSelectTheOption(lotSize_Input,pLotSize)) {
			isSuccessfull=true;
		}
		return isSuccessfull;
//		return clickAndSelect(lotSize_dropdown, pLotSize, lotSizeOptions_xpath);
	}
	
	public boolean clickAndSelectStatus(String pStatus) {	
		boolean isClickSuccessful=false;
		try {
			ActionHelper.Click(driver, status_dropdown);
			ActionHelper.Type(driver, status_dropdown, Keys.BACK_SPACE);
			ActionHelper.Type(driver, status_dropdown, Keys.BACK_SPACE);
			dropDownOptions=getDynamicElement(status_xpath, WordUtils.capitalizeFully(pStatus));
			if(wait.until(ExpectedConditions.visibilityOf(dropDownOptions))!=null) {
				dropDownOptions.click();
				focusOut();
				isClickSuccessful=true;
			}
		}catch(Exception ex) {
			System.out.println(ex.toString());
		}
		return isClickSuccessful;
	}
	
	public boolean clickAndSelectYear(String pYear) {
		return clickAndSelectOneClick(yearBuild_dropdown, pYear, yearBuildXpath);
	}
	public void waitForLoadingOfPage() {
        ExpectedCondition<Boolean> pageLoadCondition = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
                    }
                };
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(pageLoadCondition);
    }
	private boolean typeAndDownArrowSelect(WebElement pInputField,String pStringToType) {
		boolean isTypeSuccessful=false;
		try {
			if(pInputField.isDisplayed()) {
				pInputField.sendKeys(pStringToType);
				 Thread.sleep(3000);
				//List<WebElement> dropDownElement = driver.findElements(By.xpath("//div[@class='pac-container pac-logo hdpi']/descendant::span[@class='pac-item-query']"));
//				if(!dropDownElement.get(0).getAttribute("style").contains("none")) {
					pInputField.sendKeys(Keys.ARROW_DOWN);
					pInputField.sendKeys(Keys.ENTER);
					isTypeSuccessful= true;
//				}
			}
		}
		catch(Exception ex) {
			System.out.println(ex.toString());
		}
		return isTypeSuccessful;
		
	}
	private boolean typeAndSelect(WebElement pDropDown, String pSelectValue, String pXpathOfElement) {
		boolean isClickSuccessful=false;
		try {
			pDropDown.sendKeys("");
			pDropDown.sendKeys(pSelectValue);
			dropDownOptions=getDynamicElement(pXpathOfElement, pSelectValue);
			Actions action = new Actions(driver);
			action.moveToElement(dropDownOptions).build().perform();
			if(dropDownOptions.isDisplayed()) {
				dropDownOptions.click();
				focusOut();
				isClickSuccessful=true;
			}
		}catch(Exception ex) {
			
		}
		return isClickSuccessful;
	}
	private boolean clickAndSelect(WebElement pDropDown, String pSelectValue, String pXpathOfElement) {
		boolean isClickSuccessful=false;
		try {
			new Actions(driver).moveToElement(pDropDown).perform();
			pDropDown.click();
			dropDownOptions=getDynamicElement(pXpathOfElement, pSelectValue);
			if(dropDownOptions.isDisplayed()) {
				dropDownOptions.click();
				pDropDown.click();
				isClickSuccessful=true;
			}
		}catch(Exception ex) {
			System.out.println(ex.toString());
		}
		return isClickSuccessful;
	}
	private boolean clickAndSelectOneClick(WebElement pDropDown, String pSelectValue, String pXpathOfElement) {
		boolean isClickSuccessful=false;
		try {
			if(wait.until(ExpectedConditions.visibilityOf(pDropDown))!=null) {
				pDropDown.click();
				dropDownOptions=getDynamicElement(pXpathOfElement, pSelectValue);
				if(wait.until(ExpectedConditions.visibilityOf(dropDownOptions))!=null) {
					dropDownOptions.click();
					isClickSuccessful=true;
				}
			}
		}catch(Exception ex) {
			System.out.println(ex.toString());
		}
		return isClickSuccessful;
	}
	
	private boolean typeInputAndSelectTheOption(WebElement pInputField,String pStringToType) {
		boolean isTypeSuccessful=false;
		try {
			if(wait.until(ExpectedConditions.visibilityOf(pInputField))!=null) {
				pInputField.sendKeys(pStringToType);
				pInputField.sendKeys(Keys.ENTER);
				isTypeSuccessful= true;
			}
		}
		catch(Exception ex) {
			System.out.println(ex.toString());
			return isTypeSuccessful;
		}
		return isTypeSuccessful;
	}
//	//Helper Methods
//	private WebElement getDynamicElement(String pXpath,String pDynamicVariable) {
//		try {
//		return driver.findElement(By.xpath(pXpath.replace(DYNAMIC_VARIABLE, pDynamicVariable)));
//		}catch(Exception ex) {
//			return null;
//		}
//	}
	private void focusOut() {
		Actions act = new Actions(driver);
		act.moveToElement(searchField_input).click().perform();;
	}
	private boolean typeAndSelect(String pStringToType, WebElement pInputField, String pDropDown) {

		boolean isTypeSuccessful=false;
		try {

			if(pInputField.isDisplayed()) {
				pInputField.sendKeys(pStringToType);
				Thread.sleep(4000);
				List<WebElement> listOfWebElements = driver.findElements(By.xpath(pDropDown));
				for (WebElement singleElement: listOfWebElements){
					System.out.println(singleElement.getText());
					if(singleElement.getText().equalsIgnoreCase(pStringToType)) {
						singleElement.click();
						isTypeSuccessful= true;
						break;
					}
					
				}
			}
		}
		catch(Exception ex) {
			System.out.println(ex.toString());
		}
		return isTypeSuccessful;
	}
	public boolean typeAndSelectZipCity(String pStringToType) {
		boolean isTypeSuccessful=false;
		if(ActionHelper.isElementVisible(driver, city_input_idx)) {
			ActionHelper.Type(driver, city_input_idx, Keys.BACK_SPACE);
			ActionHelper.Type(driver, city_input_idx, Keys.BACK_SPACE);
			ActionHelper.Type(driver, city_input_idx, pStringToType);
			List<WebElement> listOfWebElements = driver.findElements(By.xpath("//div[@id='select2-drop']/descendant::div"));
			for (WebElement singleElement: listOfWebElements){
				System.out.println(singleElement.getText());
				if(singleElement.getText().equalsIgnoreCase(pStringToType)) {
					singleElement.click();
					isTypeSuccessful= true;
					break;
				}				
			}
		}
		return isTypeSuccessful;
	}
	public boolean clickOnPriceLowOptionIdx(String pPrice) {
		return clickAndSelect(priceLow_dropDown_idx, pPrice, priceLowOptions_xpath_idx);
	}
	public boolean clickOnPriceMaxOptionIdx(String pPrice) {
		return clickAndSelect(priceMax_dropDown_idx, pPrice, priceMaxOptions_xpath_idx);
	}
	public boolean clickOnBedsOptionIdx(String pBeds) {
		return clickAndSelect(beds_dropDown_idx, pBeds, bedsOptions_xpath_idx);
	}
	public boolean clickOnBathsOptionIdx(String pBathrooms) {
		return clickAndSelect(bathrooms_dropDown_idx, pBathrooms, bathroomsOptions_xpath_idx);
	}

}
