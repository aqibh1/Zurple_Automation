package us.zengtest1.resources.blocks;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

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
              searchResult.setOnlyUrl(urlAndTitle[0]);
              searchResult.setTitle(urlAndTitle[1]);
              System.out.println(urlAndTitle[0]);
              System.out.println(urlAndTitle[1]);
              list.add(searchResult);
            }

        }catch (Exception ex) {
        	
        }
      
        return list;
    }
    
   public ArrayList<SearchResult> getIdxSearchResults(){
	   ArrayList<SearchResult> list_of_search_results = new ArrayList<SearchResult>();
	   List<WebElement> list_of_Search_properties = driver.findElements(By.xpath("//div[@class='resultbox listing-properties']/descendant::a[text()='View']"));
	   for(WebElement single_property: list_of_Search_properties) {
		   SearchResult searchResult = new SearchResult();
		   searchResult.setUrl(single_property.getAttribute("href"));
		   list_of_search_results.add(searchResult);
	   }
	   return list_of_search_results;
	   
   }

}
