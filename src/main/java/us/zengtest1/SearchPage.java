package us.zengtest1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import us.zengtest1.resources.forms.SearchForm;

public class SearchPage
        extends Page
{

    private SearchForm searchForm;

    public SearchForm getSearchForm(){
        if(null == searchForm){
            searchForm = new SearchForm();
            searchForm.setForm(driver.findElement(By.xpath("//*[@id=\"property_search_form\"]")));
        }
        return searchForm;
    }

    public SearchPage(){
        url = "http://dev.zengtest1.us/search";
    }

    public SearchPage(String source_in_url){
        url = "http://dev.zengtest1.us/search/"+source_in_url;
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
