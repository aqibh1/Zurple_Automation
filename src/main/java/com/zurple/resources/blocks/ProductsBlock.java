package com.zurple.resources.blocks;

import java.util.ArrayList;
import java.util.Collections;
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
import resources.classes.Alert;

public class ProductsBlock
        extends resources.blocks.AbstractBlock
{
    public List<Alert> getProductsList(){
        try{
            Wait<WebDriver> wait = new WebDriverWait(getDriver(), 10, 1000);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"products-grid\"]/table/tbody/tr[1]")));

            List<WebElement> allProductsRows = block.findElements(By.xpath("//*[@id=\"products-grid\"]/table/tbody/tr"));
            ArrayList<Alert> list = new ArrayList<Alert>();
            for (WebElement row: allProductsRows) {
                Alert alert = new Alert();
                alert.setLeadLink(row.findElement(By.xpath("./td/div/span[1]/span[1]/a")).getAttribute("href"));
                try{
                    alert.setPropertyLink(row.findElement(By.xpath("./td/div/span[2]/span[1]/a")).getAttribute("href"));
                }catch(NoSuchElementException e){
                    alert.setPropertyLink("");
                }
                list.add(alert);
            }
            return list;
        }catch (StaleElementReferenceException e) {
            List<Alert> emptyList = Collections.emptyList();
            return emptyList;
        } catch( TimeoutException e )
        {
            List<Alert> emptyList = Collections.emptyList();
            return emptyList;
        }
    }

}
