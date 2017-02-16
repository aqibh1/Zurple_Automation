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
import resources.classes.SearchResult;

public class SearchResultsBlock
        extends resources.blocks.AbstractBlock
{
    public List<SearchResult> getSearchResultsList(){

        List<SearchResult> list = new ArrayList<SearchResult>();

        try{

            List<WebElement> allSearchResultsRows = block.findElements(By.xpath("./div[contains(concat(\" \",normalize-space(@class),\" \"),\" property-photo-grid \")]/div"));

            for (WebElement row: allSearchResultsRows) {
                SearchResult searchResult = new SearchResult();
                searchResult.setImgLink(row.findElement(By.xpath("./a/img")).getAttribute("src"));
                searchResult.setTitle(row.findElement(By.xpath("./a/text()[1]")).getText());
                searchResult.setUrl(row.findElement(By.xpath("./a")).getAttribute("href"));

                list.add(searchResult);
            }

        }catch (StaleElementReferenceException e) {}
        catch( TimeoutException e ) {}

        return list;
    }

}