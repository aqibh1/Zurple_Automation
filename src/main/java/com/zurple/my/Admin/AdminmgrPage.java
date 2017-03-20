package com.zurple.my.Admin;

import com.zurple.my.Page;
import com.zurple.my.resources.blocks.AdminsListBlock;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;

public class AdminmgrPage
        extends Page
{

    private AdminsListBlock adminsListBlock;

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
