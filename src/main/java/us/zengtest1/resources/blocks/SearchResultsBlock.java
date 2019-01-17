package us.zengtest1.resources.blocks;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
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

        ArrayList<SearchResult> list = new ArrayList<SearchResult>();

        try{


            Wait<WebDriver> wait = new WebDriverWait(getDriver(), 10, 1000);
//            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Helper.generateXPATH(block,"") + "/div[contains(concat(\" \",normalize-space(@class),\" \"),\" property-photo-grid \")]/div")));
//            List<WebElement> allSearchResultsRows = block.findElements(By.xpath("./div[contains(concat(\" \",normalize-space(@class),\" \"),\" property-photo-grid \")]/div"));
            List<WebElement> allSearchResultsRows = driver.findElements(By.xpath("//div[@id='google_map_prop_list_sidebar']/descendant::div[@class='single-content listing-content zfs-sortable-listings-list']/descendant::div[@class='property_listing']"));

            for (WebElement row: allSearchResultsRows) {
                SearchResult searchResult = new SearchResult();
                searchResult.setImgLink(row.findElement(By.xpath("//div[@class='listing-unit-img-wrapper']/descendant::div[@class='carousel-inner']/div/a")).getAttribute("href"));
                searchResult.setTitle(row.findElement(By.xpath("//h4[@class='listing-title']/a")).getText());
                searchResult.setUrl(row.findElement(By.xpath("//div[@class='listing-unit-img-wrapper']/descendant::div[@class='carousel-inner']/div/a")).getAttribute("href"));
                searchResult.setElement(row);

                list.add(searchResult);
            }

        }catch (StaleElementReferenceException e) {
        	
        }
        catch( TimeoutException e ) {
        	
        }

        return list;
    }

}
