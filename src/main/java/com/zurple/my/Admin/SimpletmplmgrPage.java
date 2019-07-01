package com.zurple.my.Admin;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import com.zurple.my.Page;
import com.zurple.my.resources.blocks.TemplatesListBlock;

public class SimpletmplmgrPage
        extends Page
{
    private TemplatesListBlock templatesListBlock;

    public SimpletmplmgrPage(){
        url = "/simpletemplmgr/";
    }

    public boolean checkTemplatesListBlockExists(){
        try{
            getTemlatesListBlock();
            return true;
        }catch(NoSuchElementException e){
            return false;
        }
    }

    public TemplatesListBlock getTemlatesListBlock(){
        templatesListBlock = new TemplatesListBlock();
        templatesListBlock.setBlock(driver.findElement(By.xpath("//*[@id=\"common_container\"]/div/div/div/div/form")));
        templatesListBlock.setDriver(driver);
        return templatesListBlock;
    }
}
