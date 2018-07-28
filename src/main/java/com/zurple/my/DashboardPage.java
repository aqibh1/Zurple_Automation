package com.zurple.my;

import com.zurple.my.resources.blocks.AdminMenuBlock;
import com.zurple.my.resources.blocks.HotBehaviorBlock;
import com.zurple.my.resources.blocks.NewLeadsBlock;
import com.zurple.my.resources.blocks.TopMenuBlock;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;

public class DashboardPage
        extends Page
{

    NewLeadsBlock newLeadsBlock;
    AdminMenuBlock adminMenuBlock;
    TopMenuBlock topMenuBlock;
    HotBehaviorBlock hotBehaviorBlock;

    public DashboardPage(){
        url = "/dashboard";
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

    public boolean checkAdminMenuBlock(){
        try{
            getAdminMenuBlock();
            return true;
        }catch(StaleElementReferenceException e){
            return false;
        }
    }

    public AdminMenuBlock getAdminMenuBlock(){
        adminMenuBlock = new AdminMenuBlock();
        adminMenuBlock.setDriver(driver);
        adminMenuBlock.setBlock(driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div[2]/ul[2]")));
        return adminMenuBlock;
    }

    public boolean checkTopMenuBlock(){
        try{
            getTopMenuBlock();
            return true;
        }catch(StaleElementReferenceException e){
            return false;
        }
    }

    public TopMenuBlock getTopMenuBlock(){
        topMenuBlock = new TopMenuBlock();
        topMenuBlock.setDriver(driver);
        topMenuBlock.setBlock(driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div[2]")));
        return topMenuBlock;
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
