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

    public SearchResultsPage(Integer page_number){
        url = "http://dev.zengtest1.us/search/page/"+page_number;
    }

    public WebElement getHeader(){
        return driver.findElement(By.xpath("//*[@id=\"wrap\"]/div[1]/div/div/div[1]/h3"));
    }

    public WebElement getBrand(){
        return driver.findElement(By.xpath("//*[@id=\"wrap\"]/nav/div/div[1]/a"));
    }

    public WebElement getTopMenu(){
        return driver.findElement(By.xpath("//*[@id=\"wrap\"]/nav/div/div[2]/ul"));
    }

}
