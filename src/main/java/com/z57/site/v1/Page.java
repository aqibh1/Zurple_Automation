package com.z57.site.v1;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import resources.AbstractPage;
import resources.ConfigReader;
import resources.interfaces.HasHeader;

public class Page extends AbstractPage implements HasHeader{

    private String baseUrl = null;
    protected static String DYNAMIC_VARIABLE="[--Dynamic Variable--]";
    protected WebDriverWait wait;
    
    protected String getBaseUrl(){
        if (baseUrl == null){
            ConfigReader configReader = ConfigReader.load();
            baseUrl = configReader.getPropertyByName("z57_site_v1_base_url");
        }
        return baseUrl;
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
