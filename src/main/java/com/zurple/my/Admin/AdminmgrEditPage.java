package com.zurple.my.Admin;

import com.zurple.my.Page;
import com.zurple.my.resources.blocks.TemplatesListBlock;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import resources.elements.CheckBox;

public class AdminmgrEditPage
        extends Page
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

        WebElement we_label = driver.findElement(By.xpath("//*[@id=\"billing_access_flag\"]"));
        billingAccessCheckbox.setValue(Boolean.parseBoolean(driver.findElement(By.xpath("//*[@id=\"billing_access_flag\"]")).getAttribute("value")));
        billingAccessCheckbox.setLabel(driver.findElement(By.xpath("//*[@for=\"billing_access_flag\"]")).getText());

        return billingAccessCheckbox;
    }
}
