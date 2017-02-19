package us.zengtest1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import us.zengtest1.resources.blocks.SearchResultsBlock;
import us.zengtest1.resources.forms.SearchForm;

public class SearchResultsPage
        extends Page
{

    private SearchResultsBlock searchResultsBlock;

    public SearchResultsBlock getSearchResultsBlock(){
        if(null == searchResultsBlock){
            searchResultsBlock = new SearchResultsBlock();
            searchResultsBlock.setBlock(driver.findElement(By.xpath("//*[@id=\"search-result\"]")));
        }
        return searchResultsBlock;
    }

    public SearchResultsPage(){
        url = "http://dev.zengtest1.us/search/page";
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

}
