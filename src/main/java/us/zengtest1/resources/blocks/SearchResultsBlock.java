package us.zengtest1.resources.blocks;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import resources.classes.AdminProduct;
import resources.classes.Helper;
import resources.classes.SearchResult;

public class SearchResultsBlock
        extends resources.blocks.AbstractBlock
{
    public ArrayList<SearchResult> getSearchResultsList(){
    	final String regix="#############";
        ArrayList<SearchResult> list = new ArrayList<SearchResult>();
        Set<String> set_of_urls= new HashSet();
        try{

//            List<WebElement> allSearchResultsRows = driver.findElements(By.xpath("//div[@id='google_map_prop_list_sidebar']/descendant::div[@class='single-content listing-content zfs-sortable-listings-list']/descendant::div[@class='property_listing']"));
            List<WebElement> allSearchResultsRows= driver.findElements(By.xpath("//div[@class='single-content listing-content zfs-sortable-listings-list']/div"));
            for (WebElement row: allSearchResultsRows) {
                                List<WebElement> url = row.findElements(By.xpath("//div[@class='property_listing']/descendant::h4[@class='listing-title']/a"));
               for(WebElement element: url) {
                set_of_urls.add(element.getAttribute("href")+regix+element.getText());
               }
               break;
//                list.add(searchResult);
            }
            
            Iterator iterator = set_of_urls.iterator();
            while(iterator.hasNext()){
            SearchResult searchResult = new SearchResult();
              String element = (String) iterator.next();
              String urlAndTitle[] = element.split(regix);
              searchResult.setUrl(urlAndTitle[0]);
              searchResult.setTitle(urlAndTitle[1]);
              System.out.println(urlAndTitle[0]);
              System.out.println(urlAndTitle[1]);
              list.add(searchResult);
            }

        }catch (Exception ex) {
        	
        }
      
        return list;
    }

}
