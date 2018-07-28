package com.zurple.my;

import com.zurple.my.resources.blocks.CraigsListBlock;
import com.zurple.my.resources.blocks.LeadsListBlock;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;

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
