package com.zurple;

import com.zurple.resources.forms.LeadsSearchForm;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

public class LeadListPage
        extends Page
{
    public LeadListPage(){
        url = "https://my.dev.zurple.com/leads/";
    }

    public WebElement getBrand(){
        return driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div[1]/a/img"));
    }

    private LeadsSearchForm leadsSearchForm;

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
}
