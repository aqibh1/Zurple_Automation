package com.z57.site.v2;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.z57.site.v2.Page;

public class HomePage extends Page
{
	WebDriver localWebDriver;
	
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

	
	
	public HomePage(WebDriver pWebDriver){
		localWebDriver=pWebDriver;
		PageFactory.initElements(localWebDriver, this);
	}
	public HomePage(WebDriver pWebDriver,String pSourceUrl){
		url=pSourceUrl;
		localWebDriver=pWebDriver;
		PageFactory.initElements(localWebDriver, this);
	}

	public void mouseoverHomeSearch() {
		Actions action = new Actions(localWebDriver);
		action.moveToElement(homeSearch_dropdown).build().perform();
	}
	
	public boolean clickOnSearchHomes() {
		WebDriverWait wait = new WebDriverWait(localWebDriver, 10);
		boolean isElementPresent = wait.until(ExpectedConditions.visibilityOf(searchHomes_submenu))!=null?true:false;
		if(isElementPresent) {
			searchHomes_submenu.click();
		}
		wait.until(ExpectedConditions.visibilityOf(homeSearch_title));
		
		return homeSearch_title.getText().startsWith("Home Search");
	}

	private void waitForLoadingOfPage() {
        ExpectedCondition<Boolean> pageLoadCondition = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
                    }
                };
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(pageLoadCondition);
    }
}
