package com.zurple.backoffice;

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
	WebElement select_city_del;
	
	@FindBy(xpath="//select[@id='city_ids_from']//descendant::option[contains(text(),'Aguanga, CA')]")
	WebElement select_city_add;
	
	@FindBy(id="city_ids_del")
	WebElement city_del;
	
	@FindBy(id="city_ids_add")
	WebElement city_add;
	
	@FindBy(id="update")
	WebElement update_sitemgr;
	
	@FindBy(id="customize_v2_tx_url")
	WebElement customized_url;
	
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
		return ActionHelper.checkUncheckInputBox(driver,v2_checkbox,false);
	}
	
	public String getValidationMessage() {
		return ActionHelper.getText(driver, validation_message);
	}
	
	public boolean clickAdditionalCityForDel() {
		return ActionHelper.Click(driver, select_city_del);
	}
	
	public boolean clickAdditionalCityForAdd() {
		return ActionHelper.Click(driver, select_city_add);
	}
	
	public boolean clickDeleteCity() {
		return ActionHelper.Click(driver, city_del);
	}
	
	public boolean clickAddCity() {
		return ActionHelper.Click(driver, city_add);
	}
	
	public boolean clickUpdate() {
		return ActionHelper.Click(driver, update_sitemgr);
	}
	
	public boolean clickCustomizedURL() {
		return ActionHelper.Click(driver, customized_url);
	}
	
}
