package com.zurple.my;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;

import com.zurple.my.resources.blocks.CraigsListBlock;

public class CraigslistPage
        extends Page
{

    public CraigslistPage(){
        url = "/properties/craigslist";
    }

    private CraigsListBlock craigsListBlock;

    public boolean checkLeadsListBlockExists(){
        try{
            getLeadsListBlock();
            return true;
        }catch(StaleElementReferenceException e){
            return false;
        }
    }

    public CraigsListBlock getLeadsListBlock(){
        craigsListBlock = new CraigsListBlock();
        craigsListBlock.setBlock(driver.findElement(By.xpath("//*[@id=\"propertiesGrid_wrapper\"]")));
        craigsListBlock.setDriver(driver);
        return craigsListBlock;
    }

}
