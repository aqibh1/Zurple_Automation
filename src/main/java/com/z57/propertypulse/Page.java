/**
 * 
 */
package com.z57.propertypulse;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import resources.AbstractPage;
import resources.ConfigReader;
import resources.utility.ActionHelper;

/**
 * @author adar
 *
 */
public class Page extends AbstractPage{
	protected String baseUrl = null;
	protected ActionHelper actionHelper;
	
	protected String getBaseUrl(){
		if (baseUrl == null){
			ConfigReader configReader = ConfigReader.load();
			baseUrl = configReader.getPropertyByName("z57_pp_base_url");
		}
		return baseUrl;
	}
	 protected void setPageObject(WebDriver pWebDriver, Object pObject){
		 driver = pWebDriver;
		 actionHelper = new ActionHelper(driver);
		 PageFactory.initElements(driver, pObject); 
	 }
}
