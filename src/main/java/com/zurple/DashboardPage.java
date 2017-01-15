package com.zurple;

import com.zurple.resources.blocks.HotBevaiorBlock;
import com.zurple.resources.blocks.NewLeadsBlock;
import com.zurple.resources.forms.LoginForm;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardPage
        extends Page
{

    NewLeadsBlock newLeadsBlock;
    HotBevaiorBlock hotBevaiorBlock;

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

    public HotBevaiorBlock getHotBehaviorBlock(){
        hotBevaiorBlock = new HotBevaiorBlock();
        hotBevaiorBlock.setDriver(driver);
        hotBevaiorBlock.setBlock(driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/div[2]")));
        return hotBevaiorBlock;
    }

}
