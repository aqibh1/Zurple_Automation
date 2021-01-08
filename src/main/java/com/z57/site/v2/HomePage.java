package com.z57.site.v2;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import resources.forms.z57.LoginForm;
import resources.forms.z57.RegisterForm;
import resources.utility.ActionHelper;
import resources.utility.AutomationLogger;
import resources.utility.FrameworkConstants;

public class HomePage extends Page
{
	//WebDriver localWebDriver;
	private LoginForm loginForm;
	private PageHeader pageHeader;
	private RegisterForm registerForm;
	
	//Header Menu Home Search
	@FindBy(xpath="//div[@class=\"menu-top-navigation-container\"]/descendant::*[text()=\"Home Search\"]")
	WebElement homeSearch_dropdown;
	
	//Sub Menu under Home Search, Search Homes
	@FindBy(xpath="//div[@class=\"menu-top-navigation-container\"]/descendant::*[text()=\"Search Homes\"]")
	WebElement searchHomes_submenu;
	
	//Sub Menu under Home Search, Local Home Values
	@FindBy(xpath="//div[@class=\"menu-top-navigation-container\"]/descendant::*[text()=\"Local Home Values\"]")
	WebElement localHomeValues_submenu;
	
	@FindBy(xpath="//h1[@class='entry-title title_prop']")
	WebElement homeSearch_title;
	
	@FindBy(xpath="//div[@id='adv-search-2']/descendant::button[contains(@id,'find_my_location_widget') and text()='Find My Location']")
	WebElement findMyLocation_button;
	
	@FindBy(xpath="//span[@id='select2-chosen-1']")
	WebElement findMyLocation_result;
	
	@FindBy(xpath="//div[@id='adv-search-2']/descendant::div[contains(@id,'s2id_location_input')]/descendant::span[text()='Type to search by city']")
	WebElement search_input_span;
	
	@FindBy(xpath="//div[@id='select2-drop']/descendant::input[@type='text']")
	WebElement search_input;
	
	@FindBy(xpath="//div[@id='adv-search-2']/descendant::button[@type='submit' and text()='Search']")
	WebElement search_button;
	
	@FindBy(xpath="//div[@class='sp-slides-container']/descendant::div[@class='sp-image-container']/img")
	WebElement background_slider_image;
	String background_slider_image_xpath="//div[@class='sp-slides-container']/descendant::div[@class='sp-image-container']/img";
	
	@FindBy(xpath="//div[@class='col-md-4']/descendant::img[@data-pretty]")
	WebElement buySellContact_image;
	String buySellContactImage_xpath="//div[@class='col-md-4']/descendant::img[@data-pretty]";
	
	@FindBy(xpath="//button[@class='bx-prev wpb_button wpb_btn-info wpb_btn-large']/i[@class='fa fa-angle-left']")
	WebElement propertySliderLeft_button;
	
	@FindBy(xpath="//button[@class='bx-next wpb_button wpb_btn-info wpb_btn-large']/i[@class='fa fa-angle-right']")
	WebElement propertySliderRight_button;
	
	@FindBy(xpath="//div[@class='col-md-12']/descendant::div[@class='slide']/descendant::div[@class='item active']/a/img")
	WebElement feature_listings_slider_image;
	String feature_listings_slider_image_xpath="//div[@class='col-md-12']/descendant::div[@class='slide']/descendant::div[@class='item active']/a/img";
	
	@FindBy(xpath="//div[@class='col-md-12']/descendant::div[@class='slide']/descendant::div[@class='item active']/a")
	WebElement listing_id_href;
	
	@FindBy(xpath="//div[@class='agent-unit-img-wrapper']/img")
	WebElement agent_image;
	String agent_image_xpath="//div[@class='agent-unit-img-wrapper']/img";
	
	@FindBy(xpath="//div[@id='select2-drop']/descendant::div[text()='Cities']")
	WebElement search_dropdown_div;
	
	String propertyWidgetSlider_xpath="//div[@class='col-md-12']/descendant::h4[@class='listing-title']/a[@href='"+FrameworkConstants.DYNAMIC_VARIABLE+"']";
	
    public HomePage(){
        url = "";
    }

    public HomePage(String source_in_url){
        url = source_in_url;
    }

    public WebElement getHeader(){
        return driver.findElement(By.xpath("//*[@id=\"wrap\"]/div[1]/div/div/div[1]/h3"));
    }

    public WebElement getBrand(){
        return driver.findElement(By.xpath("//*[@id=\"wrap\"]/nav/div/div[1]/a"));
    }

	
	
	public LoginForm getLoginForm() {
		return loginForm;
	}

	public void setLoginForm(WebDriver pWebDriver) {
		loginForm = new LoginForm(pWebDriver);
	}
	
	public RegisterForm getRegisterFormNew() {
		return registerForm;
	}

	public void setRegisterForm(WebDriver pWebDriver) {
		registerForm = new RegisterForm(pWebDriver);
	}

	public HomePage(WebDriver pWebDriver){
		driver = pWebDriver;
		setLoginForm(driver);
		setPageHeader(driver);
		setRegisterForm(driver);
		PageFactory.initElements(driver, this);
	}
	public HomePage(WebDriver pWebDriver,String pSourceUrl){
		url=pSourceUrl;
		driver=pWebDriver;
		setLoginForm(driver);
		setPageHeader(driver);
		setRegisterForm(driver);
		PageFactory.initElements(driver, this);
	}

	public PageHeader getPageHeader() {
		return pageHeader;
	}

	public void setPageHeader(WebDriver pWebDriver) {
		pageHeader = new PageHeader(pWebDriver);
	}

	public void mouseoverHomeSearch() {
		Actions action = new Actions(driver);
		action.moveToElement(homeSearch_dropdown).build().perform();
	}
	
	public boolean clickOnSearchHomes() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		boolean isElementPresent = wait.until(ExpectedConditions.visibilityOf(searchHomes_submenu))!=null?true:false;
		if(isElementPresent) {
			searchHomes_submenu.click();
		}
		wait.until(ExpectedConditions.visibilityOf(homeSearch_title));
		
		return homeSearch_title.getText().startsWith("Home Search");
	}
	
	public boolean isFindMyLocationButtonWorking() {
		boolean isSuccessful =false;
		if(ActionHelper.Click(driver, findMyLocation_button)) {
			isSuccessful=ActionHelper.getText(driver, findMyLocation_result).isEmpty()?false:true;
		}
		return isSuccessful;
	}
	
	public boolean clickSearchButton() {
		return ActionHelper.Click(driver, search_button);
	}
	
	public boolean isSellBuyContactImagesAreDisplayed() {
		ActionHelper.ScrollToElement(driver, buySellContact_image);
		List<WebElement> list_of_elemenets = ActionHelper.getListOfElementByXpath(driver, buySellContactImage_xpath);
		return isImageDisplayedCorrectly(list_of_elemenets);
		
	}
	public boolean isBackgroundImageSlidersAreDisplayed() {
		List<WebElement> list_of_elemenets = ActionHelper.getListOfElementByXpath(driver, background_slider_image_xpath);
		return isImageDisplayedCorrectly(list_of_elemenets);
	}
	public List<String> isFeaturePropertyImagesAreDisplayed() {
		List<WebElement> list_of_elemenets = ActionHelper.getListOfElementByXpath(driver, feature_listings_slider_image_xpath);
		return isListingImageDisplayedCorrectly(list_of_elemenets);
	}
	public List<String> isAgentProfilePicDisplayed() {
		List<WebElement> list_of_elemenets = ActionHelper.getListOfElementByXpath(driver, agent_image_xpath);
		return isAgentImageDisplayedCorrectly(list_of_elemenets);
	}
	public boolean clickOnSliderArrows() {
		boolean lRight_button = false, lLeft_button=false;
		while(ActionHelper.isElementVisible(driver, propertySliderRight_button)) {
			lRight_button = ActionHelper.Click(driver, propertySliderRight_button);
		}
		while(ActionHelper.isElementVisible(driver, propertySliderLeft_button)) {
			lLeft_button = ActionHelper.Click(driver, propertySliderLeft_button);
		}
		return (lRight_button && lLeft_button);
	}
	
	private List<String> isListingImageDisplayedCorrectly(List<WebElement> pListOfElements) {
		List<String> listing_ids_list = new ArrayList<String>();
		
		for(WebElement element: pListOfElements) {
			String lSourceImg_url = element.getAttribute("src");
			String onErrorImg_url = element.getAttribute("onerror");
			String lSourceImg = lSourceImg_url.split("/")[lSourceImg_url.split("/").length-1];
			String lOnErrorImg = onErrorImg_url.split("/")[onErrorImg_url.split("/").length-1].replace("'", "");
			if(lSourceImg.equalsIgnoreCase(lOnErrorImg)) {
				WebElement listing_url=element.findElement(By.xpath(".."));
				String listing_url_str=listing_url.getAttribute("href");
				String[] listing_id_array = listing_url_str.split("listings/");
				String listing_id = listing_id_array[1].split("/")[0];
				listing_ids_list.add(listing_id);
				AutomationLogger.info("SOURCE IMAGE & ON ERROR IMAGE ARE SAME");
				AutomationLogger.info("SOURCE IMAGE URL :: "+lSourceImg_url);
				AutomationLogger.info("ON ERROR IMAGE URL :: "+onErrorImg_url);
				AutomationLogger.info("VERIFYING THE IMAGES IN DB");
			}	
		}
		return listing_ids_list;
	}
	
	private List<String> isAgentImageDisplayedCorrectly(List<WebElement> pListOfElements) {
		List<String> listing_ids_list = new ArrayList<String>();
		
		for(WebElement element: pListOfElements) {
			String lSourceImg_url = element.getAttribute("src");
			String onErrorImg_url = element.getAttribute("onerror");
			String lSourceImg = lSourceImg_url.split("/")[lSourceImg_url.split("/").length-1];
			String lOnErrorImg = onErrorImg_url.split("/")[onErrorImg_url.split("/").length-1].replace("'", "");
			if(lSourceImg.equalsIgnoreCase(lOnErrorImg)) {
//				WebElement listing_url=element.findElement(By.xpath(".."));
//				String listing_url_str=listing_url.getAttribute("href");
				String[] listing_id_array = lSourceImg.split("user/1/11");
				String listing_id = listing_id_array[1].split("/")[0];
				listing_ids_list.add(listing_id);

				AutomationLogger.info("SOURCE IMAGE & ON ERROR IMAGE ARE SAME");
				AutomationLogger.info("SOURCE IMAGE URL :: "+lSourceImg_url);
				AutomationLogger.info("ON ERROR IMAGE URL :: "+onErrorImg_url);
				AutomationLogger.info("VERIFYING THE IMAGES IN DB");
			}	
		}
		return listing_ids_list;
	}
	
	//Verifies that image is not equals to image provided in onerror method
	private boolean isImageDisplayedCorrectly(List<WebElement> pListOfElements) {
		boolean isSuccessful = true;
		String lOnErrorImg="";
		String onErrorImg_url="";
		String lSourceImg_url ="";
		try {
			for(WebElement element: pListOfElements) {
				lSourceImg_url = element.getAttribute("src");
				onErrorImg_url = element.getAttribute("onerror");
				if(lSourceImg_url!=null && onErrorImg_url!=null) {
					String lSourceImg = lSourceImg_url.split("/")[lSourceImg_url.split("/").length-1];
					lOnErrorImg = onErrorImg_url.split("/")[onErrorImg_url.split("/").length-1].replace("'", "");
					if(lSourceImg.equalsIgnoreCase(lOnErrorImg)) {
						AutomationLogger.error("SOURCE IMAGE & ON ERROR IMAGE ARE SAME");
						AutomationLogger.error("SOURCE IMAGE URL :: "+lSourceImg_url);
						AutomationLogger.error("ON ERROR IMAGE URL :: "+onErrorImg_url);
						isSuccessful = false;
						break;
					}
				}
			}
		}catch(Exception ex) {
			AutomationLogger.info("On Error Image URL: "+onErrorImg_url);
			AutomationLogger.info("Source Image URL: "+lSourceImg_url);
			return false;
		}
		return isSuccessful;
	}
	
	public boolean typeInputAndSelect(String pStringToType,String pStringToFind) {
		boolean isTypeSuccessful=false;
		try {
			ActionHelper.Click(driver, findMyLocation_result);
			if(ActionHelper.isElementVisible(driver, search_input)) {
				ActionHelper.Type(driver, search_input, Keys.BACK_SPACE);
				ActionHelper.Type(driver, search_input, pStringToType);
				ActionHelper.waitForElementToBeVisible(driver, search_dropdown_div, 5);
				
				List<WebElement> listOfWebElements = driver.findElements(By.xpath("//div[@id='select2-drop']/descendant::div"));
				for (WebElement singleElement: listOfWebElements){
					System.out.println(singleElement.getText());
					if(singleElement.getText().equalsIgnoreCase(pStringToFind)) {
						singleElement.click();
						isTypeSuccessful= true;
						break;
					}
					
				}
			}
		}
		catch(Exception ex) {
			System.out.println(ex.toString());
			AutomationLogger.error("Cannot type string in input field. String to type ->"+pStringToType);
			AutomationLogger.error("Exception ->"+ex.toString());
		}
		return isTypeSuccessful;
	}
	public boolean isPropertyExistsInSliderWidget(String pListingUrl) {
		List<WebElement> list_of_props = driver.findElements(By.xpath("//div[@class='col-md-12']/descendant::h4[@class='listing-title']/a"));
		for(WebElement element: list_of_props) {
			String href = element.getAttribute("href");
			System.out.println("HREF:: "+href);
			System.out.println("FREE:: "+pListingUrl);
			if(href.contains(pListingUrl.toLowerCase())) {
				return true;
			}
		}
		return false;
		//return ActionHelper.i(driver, ActionHelper.getDynamicElement(driver, propertyWidgetSlider_xpath, pListingUrl.toLowerCase()), 15);
	}

	
}
