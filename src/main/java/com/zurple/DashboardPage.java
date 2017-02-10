package com.zurple;

import com.zurple.resources.blocks.HotBehaviorBlock;
import com.zurple.resources.blocks.NewLeadsBlock;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;

public class DashboardPage
        extends Page
{

    NewLeadsBlock newLeadsBlock;
    HotBehaviorBlock hotBehaviorBlock;

    public DashboardPage(){
        url = "https://my.dev.zurple.com/dashboard";
    }

    public boolean checkNewLeadsBlock(){
        try{
            getNewLeadsBlock();
            return true;
        }catch(StaleElementReferenceException e){
            return false;
        }
    }

    public boolean checkHotBehaviorBlock(){
        try{
            getHotBehaviorBlock();
            return true;
        }catch(StaleElementReferenceException e){
            return false;
        }
    }

    public NewLeadsBlock getNewLeadsBlock(){
        newLeadsBlock = new NewLeadsBlock();
        newLeadsBlock.setDriver(driver);
        newLeadsBlock.setBlock(driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/div[1]")));
        return newLeadsBlock;
    }

    public HotBehaviorBlock getHotBehaviorBlock(){
        hotBehaviorBlock = new HotBehaviorBlock();
        hotBehaviorBlock.setDriver(driver);
        hotBehaviorBlock.setBlock(driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/div[2]")));
        return hotBehaviorBlock;
    }

}
