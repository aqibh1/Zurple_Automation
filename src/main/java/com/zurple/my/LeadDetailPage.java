package com.zurple.my;

import com.zurple.my.resources.blocks.BouncedEmailAlertBlock;
import com.zurple.my.resources.blocks.LeadsDetailsBlock;
import com.zurple.my.resources.blocks.LeadsListBlock;
import com.zurple.my.resources.blocks.PropertiesViewedBlock;
import com.zurple.my.resources.blocks.RemindersBlock;
import com.zurple.my.resources.forms.LeadStatusForm;
import com.zurple.my.resources.forms.LeadsSearchForm;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import resources.alerts.SweetAlertNotification;

public class LeadDetailPage
        extends Page
{

    BouncedEmailAlertBlock bouncedEmailAlertBlock;
    PropertiesViewedBlock propertiesViewedBlock;
    RemindersBlock remindersBlock;
    LeadsDetailsBlock leadsDetailsBlock;
    private LeadStatusForm leadStatusForm;
    private Integer leadId;

    public LeadDetailPage(){
        url = "/lead/";
    }

    private SweetAlertNotification sweetAlertNotification;
    private SweetAlertNotification leadStatusUpdateNotification;
    private SweetAlertNotification leadStatusUpdatedNotification;

    public SweetAlertNotification getSweetAlertNotification(){
        if(null == sweetAlertNotification){
            sweetAlertNotification = new SweetAlertNotification();
            sweetAlertNotification.setAlert(driver.findElement(
                    By.xpath(SweetAlertNotification.alertXpath)));
        }
        return sweetAlertNotification;
    }

    public SweetAlertNotification getLeadStatusUpdateNotification(){
        if(null == leadStatusUpdateNotification){
            leadStatusUpdateNotification = new SweetAlertNotification();
            leadStatusUpdateNotification.setAlert(driver.findElement(
                    By.xpath("//div[contains(@class,\"swal2-modal\")]")));
        }                              
        return leadStatusUpdateNotification;
    }
    
    public SweetAlertNotification getLeadStatusUpdatedNotification(){
        if(null == leadStatusUpdatedNotification){
            leadStatusUpdatedNotification = new SweetAlertNotification();
            leadStatusUpdatedNotification.setAlert(driver.findElement(
                    By.xpath("//div[contains(@class,\"swal2-modal\")]")));
        }                              
        return leadStatusUpdatedNotification;
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

    public boolean checkPropertiesViewedBlock(){
        try{
            getPropertiesViewedBlock();
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

    public PropertiesViewedBlock getPropertiesViewedBlock(){
        propertiesViewedBlock = new PropertiesViewedBlock();
        propertiesViewedBlock.setBlock(driver.findElement(By.xpath("/html/body/div[2]/div[3]/div[2]/div[2]")));
        return propertiesViewedBlock;
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

    public boolean checkLeadStatusFormExists(){
        try{
            getLeadStatusForm();
            return true;
        }catch(StaleElementReferenceException e){
            return false;
        }
    }

    public LeadStatusForm getLeadStatusForm(){
        leadStatusForm = new LeadStatusForm();
        leadStatusForm.setForm(driver.findElement(By.xpath("//*[@id=\"form_change_status\"]")));
        return leadStatusForm;
    }

    public Integer getLeadId() {
        return leadId;
    }

    public void setLeadId(Integer leadId) {
        this.leadId = leadId;
    }

    public String getFullUrl()
    {
        return getBaseUrl() + url + getLeadId();
    }

}
