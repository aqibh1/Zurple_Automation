package com.zurple.my.Admin;

import com.zurple.my.Page;
import com.zurple.my.resources.blocks.ImportsListBlock;
import com.zurple.my.resources.blocks.LeadImportFeedbackBlock;
import com.zurple.my.resources.forms.AdminEditForm;
import com.zurple.my.resources.forms.LeadsImportForm;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

public class LeadImportPage
        extends Page
{

    protected LeadsImportForm leadsImportForm;
    protected ImportsListBlock importsListBlock;
    protected LeadImportFeedbackBlock leadImportFeedbackBlock;

    public LeadImportPage(){
        url = "/leadmgr/import";
    }

    public boolean checkLeadsImportFormExists(){
        try{
            getLeadsImportForm();
            return true;
        }catch(NoSuchElementException e){
            return false;
        }
    }

    public LeadsImportForm getLeadsImportForm(){
        leadsImportForm = new LeadsImportForm();
        leadsImportForm.setForm(driver.findElement(By.xpath("//*[@id=\"import_form\"]")));
        leadsImportForm.setDriver(driver);
        return leadsImportForm;
    }

    public boolean checkLeadsImportFeedbackBlockExists(){
        try{
            getLeadsImportFeedbackBlock();
            return true;
        }catch(NoSuchElementException e){
            return false;
        }
    }

    public LeadImportFeedbackBlock getLeadsImportFeedbackBlock(){
        leadImportFeedbackBlock = new LeadImportFeedbackBlock();
        leadImportFeedbackBlock.setBlock(driver.findElement(By.xpath("//*[@id=\"feedback\"]")));
        return leadImportFeedbackBlock;
    }

    public boolean checkImportsListBlockExists(){
        try{
            getImportsListBlock();
            return true;
        }catch(NoSuchElementException e){
            return false;
        }
    }

    public ImportsListBlock getImportsListBlock(){
        importsListBlock = new ImportsListBlock();
        importsListBlock.setBlock(driver.findElement(By.xpath("//table[@id=\"imports\"]")));
        importsListBlock.setDriver(driver);
        return importsListBlock;
    }

}
