package com.zurple.my.Admin;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.zurple.my.Page;

import resources.elements.Button;

public class ProcessEmailQueuePage
        extends Page
{

    protected Button leadsAlertsQueueProcessButton;
    protected Button leadsAlertsQueueFetchButton;

    public ProcessEmailQueuePage(){
        url = "/admin/processemailqueue";
    }

    public boolean checkLeadsAlertsQueueProcessingButtonExists(){
        try{
            getLeadsAlertsQueueProcessingButton();
            return true;
        }catch(NoSuchElementException e){
            return false;
        }
    }

    public Button getLeadsAlertsQueueFetchButton(){
        if (leadsAlertsQueueFetchButton == null)
        {
            leadsAlertsQueueFetchButton = new Button();
            leadsAlertsQueueFetchButton.setElement(
                    driver.findElement(By.xpath("//*[contains(concat(\" \",normalize-space(@class),\" \"),\" aq-fetch \")]")));
        }
        return leadsAlertsQueueFetchButton;
    }

    public Button getLeadsAlertsQueueProcessingButton(){
        if (leadsAlertsQueueProcessButton == null)
        {
            leadsAlertsQueueProcessButton = new Button();
            leadsAlertsQueueProcessButton.setElement(
                    driver.findElement(By.xpath("//*[contains(concat(\" \",normalize-space(@class),\" \"),\" aq-process \")]")));
        }
        return leadsAlertsQueueProcessButton;
    }

    public Button getIRQueueFetchButton(){
        if (leadsAlertsQueueFetchButton == null)
        {
            leadsAlertsQueueFetchButton = new Button();
            leadsAlertsQueueFetchButton.setElement(
                    driver.findElement(By.xpath("//*[contains(concat(\" \",normalize-space(@class),\" \"),\" irq-fetch \")]")));
        }
        return leadsAlertsQueueFetchButton;
    }

    public Button getIRQueueProcessingButton(){
        if (leadsAlertsQueueProcessButton == null)
        {
            leadsAlertsQueueProcessButton = new Button();
            leadsAlertsQueueProcessButton.setElement(
                    driver.findElement(By.xpath("//*[contains(concat(\" \",normalize-space(@class),\" \"),\" irq-process \")]")));
        }
        return leadsAlertsQueueProcessButton;
    }

    public Button getMassEmailQueueFetchButton(){
        if (leadsAlertsQueueFetchButton == null)
        {
            leadsAlertsQueueFetchButton = new Button();
            leadsAlertsQueueFetchButton.setElement(
                    driver.findElement(By.xpath("//*[contains(concat(\" \",normalize-space(@class),\" \"),\" maq-fetch \")]")));
        }
        return leadsAlertsQueueFetchButton;
    }

    public Button getMassEmailQueueProcessingButton(){
        if (leadsAlertsQueueProcessButton == null)
        {
            leadsAlertsQueueProcessButton = new Button();
            leadsAlertsQueueProcessButton.setElement(
                    driver.findElement(By.xpath("//*[contains(concat(\" \",normalize-space(@class),\" \"),\" maq-process \")]")));
        }
        return leadsAlertsQueueProcessButton;
    }

    public Boolean runLeadAlertsQueueProcessing(){

        Button fetchButton = getLeadsAlertsQueueFetchButton();
        fetchButton.click();
                
        try
        {
            Wait<WebDriver> wait = new WebDriverWait(driver, 180, 1000);
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[@class='blockUI blockMsg blockPage']")));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(
                    By.xpath("//div[@class='blockUI blockMsg blockPage']")));

            Button processButton = getLeadsAlertsQueueProcessingButton();
            processButton.click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[@class='blockUI blockMsg blockPage']")));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(
                    By.xpath("//div[@class='blockUI blockMsg blockPage']")));

        }catch(TimeoutException e ) {
            return false;
        }

        return true;
    }

    public Boolean runIRQueueProcessing(){

        Button fetchButton = getIRQueueFetchButton();
        fetchButton.click();

        try
        {
            Wait<WebDriver> wait = new WebDriverWait(driver, 180, 1000);
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[@class='blockUI blockMsg blockPage']")));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(
                    By.xpath("//div[@class='blockUI blockMsg blockPage']")));

            Button processButton = getIRQueueProcessingButton();
            processButton.click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[@class='blockUI blockMsg blockPage']")));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(
                    By.xpath("//div[@class='blockUI blockMsg blockPage']")));

        }catch(TimeoutException e ) {
            return false;
        }

        return true;
    }

    public Boolean runMassEmailQueueProcessing(){

        Button fetchButton = getMassEmailQueueFetchButton();
        fetchButton.click();

        try
        {
            Wait<WebDriver> wait = new WebDriverWait(driver, 180, 1000);
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[@class='blockUI blockMsg blockPage']")));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(
                    By.xpath("//div[@class='blockUI blockMsg blockPage']")));

            Button processButton = getMassEmailQueueProcessingButton();
            processButton.click();

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
