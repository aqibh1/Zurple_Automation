package com.zurple.my;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import com.zurple.my.resources.blocks.LeadsListBlock;
import com.zurple.my.resources.forms.LeadsSearchForm;

public class LeadListPage
        extends Page
{
    public LeadListPage(){
        url = "/leads/";
    }

    public WebElement getBrand(){
        return driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div[1]/a/img"));
    }

    private LeadsSearchForm leadsSearchForm;
    private LeadsListBlock leadsListBlock;

    public boolean checkLeadsSearchFormExists(){
        try{
            getLeadsSearchForm();
            return true;
        }catch(StaleElementReferenceException e){
            return false;
        }
    }

    public LeadsSearchForm getLeadsSearchForm(){
        leadsSearchForm = new LeadsSearchForm();
        leadsSearchForm.setForm(driver.findElement(By.xpath("//*[@id=\"leads-grid-filter-form\"]")));
        return leadsSearchForm;
    }

    public boolean checkLeadsListBlockExists(){
        try{
            getLeadsListBlock();
            return true;
        }catch(StaleElementReferenceException e){
            return false;
        }
    }

    public LeadsListBlock getLeadsListBlock(){
        leadsListBlock = new LeadsListBlock();
        leadsListBlock.setBlock(driver.findElement(By.xpath("//*[@id=\"DataTables_Table_0_wrapper\"]")));
        leadsListBlock.setDriver(driver);
        return leadsListBlock;
    }
}
