package com.zurple.my.resources.blocks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.zurple.my.resources.forms.ReminderForm;
import com.zurple.my.resources.interfaces.HasHeader;

import resources.classes.Helper;
import resources.classes.Reminder;

public class RemindersBlock
        extends resources.blocks.AbstractBlock implements HasHeader
{

    protected ReminderForm reminderForm;

    public String getHeader(){
        return block.findElement(By.xpath("./div[1]/h2")).getText();
    }

    public ReminderForm getReminderForm()
    {
        reminderForm = new ReminderForm();
        reminderForm.setForm(block.findElement(By.xpath("./descendant::form")));
        reminderForm.setDriver(getDriver());
        return reminderForm;
    }

    public WebElement getRemindersList(){
        return block.findElement(By.xpath("./descendant::tbody[@class=\"yui-dt-data\"]"));
    }

    public List<Reminder> getReminders(){
        try{
            Wait<WebDriver> wait = new WebDriverWait(getDriver(), 10, 1000);
            wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath(Helper.generateXPATH(getRemindersList(), ""))));

            List<WebElement> allReminderRows = getRemindersList().findElements(By.xpath("./tr"));
            ArrayList<Reminder> list = new ArrayList<Reminder>();
            for (WebElement row: allReminderRows) {
                Reminder r = new Reminder();
                r.setDate(row.findElement(By.xpath("./td[1]")).getText());
                r.setText(row.findElement(By.xpath("./td[2]")).getText());
                r.setType(row.findElement(By.xpath("./td[3]")).getText());
                if(row.findElement(By.xpath("./td[4]")).getText()==""){
                    r.setEditable(false);
                }else{
                    r.setEditable(true);
                }

                list.add(r);
            }
            return list;
        }catch (StaleElementReferenceException e) {
            List<Reminder> emptyList = Collections.emptyList();
            return emptyList;
        } catch( TimeoutException e )
        {
            List<Reminder> emptyList = Collections.emptyList();
            return emptyList;
        }
    }
}
