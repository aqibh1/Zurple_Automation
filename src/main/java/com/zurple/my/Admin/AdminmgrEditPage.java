package com.zurple.my.Admin;

import com.zurple.my.Page;
import com.zurple.my.resources.blocks.TemplatesListBlock;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import resources.elements.Button;
import resources.elements.CheckBox;

public class AdminmgrEditPage
        extends Page
{

    private CheckBox billingAccessCheckbox;
    private Button updateButton;

    public boolean checkBillingAccessCheckboxExists(){
        try{
            getBillingAccessCheckbox();
            return true;
        }catch(NoSuchElementException e){
            return false;
        }
    }

    public CheckBox getBillingAccessCheckbox(){
        billingAccessCheckbox = new CheckBox();

        WebElement we_label = driver.findElement(By.xpath("//*[@id=\"billing_access_flag\"]"));
        billingAccessCheckbox.setLabel(driver.findElement(By.xpath("//*[@for=\"billing_access_flag\"]")).getText());
        billingAccessCheckbox.setElement(driver.findElement(By.xpath("//*[@id=\"billing_access_flag\"]")));

        return billingAccessCheckbox;
    }

    public boolean checkUpdateButtonExists(){
        try{
            getUpdateButton();
            return true;
        }catch(NoSuchElementException e){
            return false;
        }
    }

    public Button getUpdateButton(){
        updateButton = new Button();
        updateButton.setElement(driver.findElement(By.xpath("//*[@id=\"update\"]")));

        return updateButton;
    }

    public void toggleBillingAccessCheckbox(){
        billingAccessCheckbox.getElement().click();
    }

    public void submitPage()
    {
        if(updateButton==null){
            getUpdateButton();
        }
        updateButton.getElement().click();
    }
}
