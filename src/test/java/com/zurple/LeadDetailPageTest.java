package com.zurple;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import resources.alerts.SweetAlertNotification;

public class LeadDetailPageTest
        extends PageTest
{

    private static LeadDetailPage page;

    public LeadDetailPage getPage(){
        if(page == null){
            page = new LeadDetailPage();
            page.setUrl("https://my.dev.zurple.com/lead/"+getEnvironment().getLeadToCheck()+"?from=new");
            page.setDriver(getDriver());
        }
        return page;
    }

    public void testTitle() {
        assertEquals("Zurple Backoffice", getPage().getTitle());
    }

    public void testRemindersBlock(){
        assertTrue(getPage().checkRemindersBlock());
    }

    public void testAddNewEmailReminder(){
        getPage().getRemindersBlock().getReminderForm().setTextareaValue("text_area_reminder", "Test reminder");
        //Just touching reminder input
        getPage().getRemindersBlock().getReminderForm().setInputValue("task_reminder_date_1", "");
        assertTrue(getPage().getRemindersBlock().getReminderForm().emailCheckboxEnabled());
        getPage().getRemindersBlock().getReminderForm().toggleCheckboxValue("task_reminder_type_sms");
        assertFalse(getPage().getRemindersBlock().getReminderForm().smsCheckboxEnabled());
        getPage().getRemindersBlock().getReminderForm().submit();
        assertTrue(getPage().getRemindersBlock().getReminderForm().waitWhileSubmitting());
    }

}
