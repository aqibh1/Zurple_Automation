package com.zurple.backoffice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.zurple.my.Page;

import resources.utility.ActionHelper;

public class ZBOV2TemplatePage extends Page {

	@FindBy(xpath="//div[@class='form-element-input']//descendant::input[@id='domain_name']")
	WebElement domain_name;
		
	@FindBy(id="site_options-v2txtemplate")
	WebElement v2_checkbox;
	
	@FindBy(id="warning_tx_temp")
	WebElement validation_message;

	@FindBy(xpath="//select[@id='city_ids_to']//descendant::option[contains(text(),'Aguanga, CA')]")
	WebElement select_city_del1;
	
	@FindBy(xpath="//select[@id='city_ids_to']//descendant::option[contains(text(),'Agua Dulce, CA')]")
	WebElement select_city_del2;
	
	@FindBy(xpath="//select[@id='city_ids_to']//descendant::option[contains(text(),'Agoura Hills, CA')]")
	WebElement select_city_del3;
	
	@FindBy(xpath="//select[@id='city_ids_to']//descendant::option[contains(text(),'29 Palms, CA')]")
	WebElement select_city_del4;
	
	@FindBy(xpath="//select[@id='city_ids_from']//descendant::option[contains(text(),'Aguanga, CA')]")
	WebElement select_city_add1;
	
	@FindBy(xpath="//select[@id='city_ids_from']//descendant::option[contains(text(),'Agua Dulce, CA')]")
	WebElement select_city_add2;
	
	@FindBy(xpath="//select[@id='city_ids_from']//descendant::option[contains(text(),'Agoura Hills, CA')]")
	WebElement select_city_add3;
	
	@FindBy(xpath="//select[@id='city_ids_from']//descendant::option[contains(text(),'29 Palms, CA')]")
	WebElement select_city_add4;
	
	@FindBy(id="city_ids_del")
	WebElement city_del;
	
	@FindBy(id="city_ids_add")
	WebElement city_add;
	
	@FindBy(id="update")
	WebElement update_sitemgr;
	
	@FindBy(id="customize_v2_tx_url")
	WebElement customized_url;
	
	@FindBy(xpath="//div[@id='agent-website-preferences-panel']/descendant::h3[contains(text(),'Homepage Settings')]")
	WebElement homepage_settings;
	
	@FindBy(id="blurb_title")
	WebElement blurb_title;
	
	@FindBy(xpath="//div[@class='row']/descendant::textarea[@id='blurb_text']")
	WebElement blurb_text;
	     
	@FindBy(id="save")
	WebElement submit_page;
	
	@FindBy(xpath="//div[@id='accordion']/descendant::a[@site_id='3215']")
	WebElement manage_sites;
	
	@FindBy(className="cke_wysiwyg_frame")
	WebElement iframe;
	
	@FindBy(id="form-element-site_theme")
	WebElement site_theme;
	
	@FindBy(id="blurb_title")
	WebElement blurb_settings;
	
	@FindBy(id="city4_image")
	WebElement upload_city4;
	
	@FindBy(id="update")
	WebElement update_settings;
	
	@FindBy(id="customize_v2_tx_url")
	WebElement customize_link;
	
	@FindBy(xpath="//img[contains(@alt,'city4_image')]")
	WebElement image_src;
	
	ZBOV2TemplatePage(){
	}
	
	ZBOV2TemplatePage(WebDriver pWebDriver){
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}
	
	public String getDomainName() {
		return ActionHelper.getValue(driver, domain_name);
	}
	
	public boolean clickV2Checkbox() {
		return ActionHelper.checkUncheckInputBox(driver,v2_checkbox,true);
	}
	
	public boolean clickV2UnCheck() {
		return ActionHelper.Click(driver,v2_checkbox);
	}
	
	public String getValidationMessage() {
		return ActionHelper.getText(driver, validation_message);
	}
	
	public boolean clickDeleteCity() {
		return ActionHelper.Click(driver, city_del);
	}
	
	public boolean clickAddCity() {
		return ActionHelper.Click(driver, city_add);
	}
	
	public boolean addSingleAdditionalCity() {
		return ActionHelper.Click(driver, select_city_add1);
	}
	
	public boolean clickAdditionalCityForAdd() {
		try {
			ActionHelper.Click(driver, select_city_add2);
			clickAddCity();
			ActionHelper.Click(driver, select_city_add3);
			clickAddCity();
			ActionHelper.Click(driver, select_city_add4);
			clickAddCity();
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean clickAdditionalCityForDel() {
		try {
			ActionHelper.Click(driver, select_city_del1);
			clickDeleteCity();
			ActionHelper.Click(driver, select_city_del2);
			clickDeleteCity();
			ActionHelper.Click(driver, select_city_del3);
			clickDeleteCity();
			ActionHelper.Click(driver, select_city_del4);
			clickDeleteCity();
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean clickUpdate() {
		return ActionHelper.Click(driver, update_sitemgr);
	}
	
	public boolean clickCustomizedURL() {
		return ActionHelper.Click(driver, customized_url);
	}
	
	public boolean homeSettingsHeader() {
		return ActionHelper.waitForElementToBeVisible(driver, homepage_settings, 30);
	}
	
	public String getBlurbHeader() {
		return ActionHelper.getAttribute(blurb_title, "value");
	}
	
	public String getBlurbText() {
		return ActionHelper.getText(driver, blurb_text);
	}
	
	public boolean clickSiteSettings() {
		return ActionHelper.Click(driver, manage_sites);
	}
	
	public boolean saveManageSitesPage() {
		return ActionHelper.Click(driver, submit_page);
	}
	
	public boolean uploadFile(String pDataFile) {
		try {
			ActionHelper.TypeForUploadImage(driver, upload_city4,System.getProperty("user.dir")+pDataFile);
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean customizeSiteLink() {
		return ActionHelper.Click(driver, customize_link);
	}
	
	public boolean siteTheme(String pExpected) {
		if(ActionHelper.getText(driver, site_theme).contains(pExpected)) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean blurbTitleInSettings() {
		if(!ActionHelper.getAttribute(blurb_settings,"value").isEmpty()){
			return true;
		} else {
			return false;
		}
	}
	
	public String checkImageBeforeUpdate() {
		return ActionHelper.getAttribute(image_src, "src");
	}
	
	public boolean checkImageAfterUpdate(String pSource) {
		if(!ActionHelper.getAttribute(image_src, "src").contains(pSource)) {
			return true;
		} else {
			return false;
		}
	}
	
	
}
