package com.zurple.Admin;

import com.zurple.Page;
import com.zurple.resources.blocks.TemplatesListBlock;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;

public class SimpletmplmgrPage
        extends Page
{
    private TemplatesListBlock templatesListBlock;

    public boolean checkTemplatesListBlockExists(){
        try{
            getTemlatesListBlock();
            return true;
        }catch(StaleElementReferenceException e){
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
