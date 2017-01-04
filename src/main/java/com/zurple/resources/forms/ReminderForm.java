package com.zurple.resources.forms;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import resources.alerts.SweetAlertNotification;

public class ReminderForm
        extends resources.forms.LoginForm
{

    public Boolean emailCheckboxEnabled(){
        try{
            return form.findElement(By.xpath("./descendant::input[@id=\"task_reminder_type_email\"]")).isSelected();
        }catch(StaleElementReferenceException e){
             return false;
        }
    }

    public Boolean smsCheckboxEnabled(){
        try{
            return form.findElement(By.xpath("./descendant::input[@id=\"task_reminder_type_sms\"]")).isSelected();
        }catch(StaleElementReferenceException e){
             return false;
        }
    }

    private WebElement getLoader(){
        return form.findElement(By.xpath("./descendant::div[@id=\"submit_reminder_posting\"]"));
    }

    public Boolean waitWhileSubmitting(){
        try{
            Wait<WebDriver> wait = new WebDriverWait(getDriver(), 10, 1000);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(generateXPATH(getLoader(), ""))));
            return true;
        }catch(StaleElementReferenceException e){
             return false;
        }catch(TimeoutException e){
             return false;
        }
    }

    private String generateXPATH(WebElement childElement, String current) {
        String childTag = childElement.getTagName();
        if(childTag.equals("html")) {
            return "/html[1]"+current;
        }
        WebElement parentElement = childElement.findElement(By.xpath(".."));
        List<WebElement> childrenElements = parentElement.findElements(By.xpath("*"));
        int count = 0;
        for(int i=0;i<childrenElements.size(); i++) {
            WebElement childrenElement = childrenElements.get(i);
            String childrenElementTag = childrenElement.getTagName();
            if(childTag.equals(childrenElementTag)) {
                count++;
            }
            if(childElement.equals(childrenElement)) {
                return generateXPATH(parentElement, "/" + childTag + "[" + count + "]"+current);
            }
        }
        return null;
    }

}
