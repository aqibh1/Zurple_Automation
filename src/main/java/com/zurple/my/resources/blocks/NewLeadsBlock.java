package com.zurple.my.resources.blocks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NewLeadsBlock
        extends resources.blocks.AbstractBlock
{
    public List<Integer> getLeadIds(){
        try{
            Wait<WebDriver> wait = new WebDriverWait(getDriver(), 10, 1000);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"z-new-leads-grid\"]/table/tbody[2]/tr/td/div/span[1]/span[1]/a")));

            List<WebElement> allLeadRows = block.findElements(By.xpath("//*[@id=\"z-new-leads-grid\"]/table/tbody[2]/tr"));
            ArrayList<Integer> list = new ArrayList<Integer>();
            for (WebElement row: allLeadRows) {
                WebElement lead = row.findElement(By.xpath("./td/div/span[1]/span[1]/a"));
                Pattern p = Pattern.compile("[0-9]+");
                Matcher m = p.matcher(lead.getAttribute("href"));
                while (m.find()) {
                    Integer n = Integer.parseInt(m.group());
                    list.add(n);
                }
            }
            return list;
        }catch (StaleElementReferenceException e) {
            List<Integer> emptyList = Collections.emptyList();
            return emptyList;
        } catch( TimeoutException e )
        {
            List<Integer> emptyList = Collections.emptyList();
            return emptyList;
        }
    }

    public WebElement getNewLeadLink(Integer n){
        try{
            return block.findElement(By.xpath("./descendant::table/tbody[2]/tr["+n+"]//td/div/span[1]/span[1]/a"));
        }catch(StaleElementReferenceException e){
            return null;
        }
    }
}
