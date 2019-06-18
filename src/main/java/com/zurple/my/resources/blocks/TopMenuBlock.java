package com.zurple.my.resources.blocks;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

import resources.classes.MenuItem;

public class TopMenuBlock
        extends resources.blocks.AbstractBlock
{

    public WebElement getMenuItemWebElementByName(String name)
            throws Exception
    {
        try{

            List<WebElement> allItemsRows = block.findElements(By.xpath("./ul/li"));

            for (WebElement row: allItemsRows) {
                if(row.getText().trim().equals(name)){
                    return row;
                }
            }

        }catch (StaleElementReferenceException e) {}
        catch( TimeoutException e ) {}
        throw new Exception("Menu item was not found");
    }

    public List<MenuItem> getItemsList(){

        ArrayList<MenuItem> list = new ArrayList<MenuItem>();

        try{

            List<WebElement> allItemsRows = block.findElements(By.xpath("./ul/li"));

            for (WebElement row: allItemsRows) {

                try{
                    MenuItem menuItem = new MenuItem();
                    menuItem.setLink(row.findElement(By.xpath("./a")).getAttribute("href"));
                    menuItem.setTitle(row.findElement(By.xpath("./a")).getText());
                    list.add(menuItem);
                }catch(NoSuchElementException e){}
            }
            return list;
        }catch (StaleElementReferenceException e) {}
        catch( TimeoutException e ) {}
        return list;
    }

}
