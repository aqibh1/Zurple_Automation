package com.zurple.my.Admin;

import com.zurple.my.Page;
import com.zurple.my.resources.alerts.LeadStatusAutomationProcessingWarning;
import com.zurple.my.resources.blocks.ImportsListBlock;
import com.zurple.my.resources.blocks.LeadImportFeedbackBlock;
import com.zurple.my.resources.forms.LeadsImportForm;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import resources.classes.Helper;
import resources.elements.Button;

public class LeadStatusAutomationPage
        extends Page
{

    protected Button leadsStatusAutomationProcessButton;
    protected LeadStatusAutomationProcessingWarning processingWarning;

    public boolean checkLeadsStatusAutomationProcessingButtonExists(){
        try{
            getLeadsStatusAutomationProcessingButton();
            return true;
        }catch(NoSuchElementException e){
            return false;
        }
    }

    public Button getLeadsStatusAutomationProcessingButton(){
        if (leadsStatusAutomationProcessButton == null)
        {
            leadsStatusAutomationProcessButton = new Button();
            leadsStatusAutomationProcessButton.setElement(
                    driver.findElement(By.xpath("//*[@id=\"common_container\"]/div[1]/table/tbody/tr/td/button")));
        }
        return leadsStatusAutomationProcessButton;
    }
    
    public Boolean runLeadStatusAutomationProcessing(){
        
        Button button = getLeadsStatusAutomationProcessingButton();
        button.click();
        
        try
        {
            Wait<WebDriver> wait = new WebDriverWait(driver, 5, 1000);
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[@class='blockUI blockMsg blockPage']")));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(
                    By.xpath("//div[@class='blockUI blockMsg blockPage']")));
        }catch(TimeoutException e ) {
            return false;
        }
        
        return true;
    }
}
