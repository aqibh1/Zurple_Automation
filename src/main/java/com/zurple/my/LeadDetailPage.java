package com.zurple.my;

import com.zurple.my.resources.blocks.BouncedEmailAlertBlock;
import com.zurple.my.resources.blocks.LeadsDetailsBlock;
import com.zurple.my.resources.blocks.RemindersBlock;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import resources.alerts.SweetAlertNotification;

public class LeadDetailPage
        extends Page
{

    BouncedEmailAlertBlock bouncedEmailAlertBlock;
    RemindersBlock remindersBlock;
    LeadsDetailsBlock leadsDetailsBlock;

    private SweetAlertNotification sweetAlertNotification;

    public SweetAlertNotification getSweetAlertNotification(){
        if(null == sweetAlertNotification){
            sweetAlertNotification = new SweetAlertNotification();
            sweetAlertNotification.setAlert(driver.findElement(
                    By.xpath(SweetAlertNotification.alertXpath)));
        }
        return sweetAlertNotification;
    }

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
            getRemindersBlock().getHeader();
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
        remindersBlock.setDriver(driver);
        return remindersBlock;
    }

    public boolean checkLeadDetailsBlock(){
        try{
            getLeadDetailsBlock();
            return true;
        }catch(StaleElementReferenceException e){
            return false;
        }catch(NoSuchElementException e){
            return false;
        }
    }

    public LeadsDetailsBlock getLeadDetailsBlock(){
        leadsDetailsBlock = new LeadsDetailsBlock();
        leadsDetailsBlock.setBlock(driver.findElement(By.xpath("//*[@id=\"lead-details-main\"]")));
        return leadsDetailsBlock;
    }

}
