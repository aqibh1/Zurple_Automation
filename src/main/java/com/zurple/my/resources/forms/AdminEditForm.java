package com.zurple.my.resources.forms;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;

import resources.elements.CheckBox;

public class AdminEditForm
        extends resources.forms.AbstractForm
{

    private CheckBox billingAccessCheckbox;

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

        billingAccessCheckbox.setLabel(form.findElement(By.xpath("./descendant::label[@for=\"billing_access_flag\"]")).getText());
        billingAccessCheckbox.setElement(form.findElement(By.xpath("./descendant::input[@id=\"billing_access_flag\"]")));

        return billingAccessCheckbox;
    }

    public String getErrorMessage(){
        try{
            return form.findElement(By.xpath("./descendant::p[@class=\"form-element-error\"]")).getText();
        }catch(StaleElementReferenceException e){
             return "";
        }
    }
}
