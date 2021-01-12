package com.zurple.my.Admin;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;

import com.zurple.my.Page;
import com.zurple.my.resources.blocks.AdminsListBlock;

public class AdminmgrPage
        extends Page
{

    private AdminsListBlock adminsListBlock;

    public AdminmgrPage(){
        url = "/adminmgr/";
    }

    public boolean checkAdminListBlockExists(){
        try{
            getAdminsListBlock();
            return true;
        }catch(StaleElementReferenceException e){
            return false;
        }
    }

    public AdminsListBlock getAdminsListBlock(){
        adminsListBlock = new AdminsListBlock();
        adminsListBlock.setBlock(driver.findElement(By.xpath("//*[@id=\"center_pane\"]")));
        adminsListBlock.setDriver(driver);
        return adminsListBlock;
    }
}
