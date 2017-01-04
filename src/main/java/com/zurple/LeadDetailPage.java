package com.zurple;

import com.zurple.resources.blocks.BouncedEmailAlertBlock;
import com.zurple.resources.blocks.RemindersBlock;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;

public class LeadDetailPage
        extends Page
{

    BouncedEmailAlertBlock bouncedEmailAlertBlock;
    RemindersBlock remindersBlock;

    public boolean checkBouncedEmailAlertBlock(){
        try{
            getBouncedEmailAlertBlock();
            return true;
        }catch(StaleElementReferenceException e){
            return false;
        }catch(NoSuchElementException e){
            return false;
        }
    }

    public boolean checkRemindersBlock(){
        try{
            System.out.println(getRemindersBlock().getHeader());
            return true;
        }catch(StaleElementReferenceException e){
            return false;
        }catch(NoSuchElementException e){
            return false;
        }
    }

    public BouncedEmailAlertBlock getBouncedEmailAlertBlock(){
        bouncedEmailAlertBlock = new BouncedEmailAlertBlock();
        bouncedEmailAlertBlock.setBlock(driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[@class=\"alert alert-warning\"]")));
        return bouncedEmailAlertBlock;
    }

    public RemindersBlock getRemindersBlock(){
        remindersBlock = new RemindersBlock();
        remindersBlock.setBlock(driver.findElement(By.xpath("/html/body/div[2]/div[3]/div[1]/div[5]")));
        return remindersBlock;
    }

}
