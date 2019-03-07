package com.z57.site.v2;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import resources.alerts.BootstrapModal;
import us.zengtest1.resources.blocks.SearchResultsBlock;

public class SearchResultsPage extends Page{
	
    private SearchResultsBlock searchResultsBlock;

    public SearchResultsBlock getSearchResultsBlock(WebDriver pWebDriver){
    	driver=pWebDriver;
 //   	searchResultsBlock=null;
        if(null == searchResultsBlock){
            searchResultsBlock = new SearchResultsBlock();
            searchResultsBlock.setDriver(driver);
            searchResultsBlock.setBlock(driver.findElement(By.xpath("//*[@class=\"single-content listing-content zfs-sortable-listings-list\"]")));
        }
        return searchResultsBlock;
    }
    public SearchResultsBlock getIDXSearchResultsBlock(WebDriver pWebDriver){
    	driver=pWebDriver;
        if(null == searchResultsBlock){
            searchResultsBlock = new SearchResultsBlock();
            searchResultsBlock.setDriver(driver);
        }
        return searchResultsBlock;
    }
    
    public SearchResultsPage(){
        url = "/search/page/1";
    }

    public SearchResultsPage(String url){
        this.url = url;
    }

    //TODO not standart header location
    public WebElement getHeader(){
        return driver.findElement(By.xpath("//*[@id=\"search-result\"]/div[2]/div/h2"));
    }

    public WebElement getBrand(){
        return driver.findElement(By.xpath("//*[@id=\"wrap\"]/nav/div/div[1]/a"));
    }

    public WebElement getTopMenu(){
        return driver.findElement(By.xpath("//*[@id=\"wrap\"]/nav/div/div[2]/ul"));
    }
    public WebElement getPropertiesCount() {
    	return driver.findElement(By.xpath("//h1[@class='entry-title title_prop']/span"));
    }
    public Integer getNumberOfResults(){
        String numberString = driver.findElement(By.xpath("//*[@id=\"search-result\"]/div[3]/div")).getText();
        Pattern p = Pattern.compile("(\\d+)");
        Matcher m = p.matcher(numberString);

        if(m.find())
        {
            return Integer.parseInt(m.group(1));
        }else
        {
            return 0;
        }
    }

    public void goNextPage(){
    	BootstrapModal bootstrapModalObj = new BootstrapModal(getWebDriver());
        if(bootstrapModalObj.checkBootsrapModalIsShown()){
        	bootstrapModalObj.getBootstrapModal().close();
        }
        //TODO
        //No need to set the object to null as we are already instantiation the new object
        bootstrapModalObj = null;
        searchResultsBlock = null;

        driver.findElement(By.xpath("//a[@title=\"next page\"]")).click();
    }


}
