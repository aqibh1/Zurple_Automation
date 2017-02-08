package com.zurple;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertFalse;

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
    @Test
    public void testTitle() {
        assertEquals("Zurple Backoffice", getPage().getTitle());
    }

    @Test
    public void testLead() {
        //getEnvironment().getLeadObject();
    }

    @Test
    public void testRemindersBlock(){
        assertTrue(getPage().checkRemindersBlock());
    }

    @Test
    public void testAddNewEmailReminder(){
        getPage().getRemindersBlock().getReminderForm().setTextareaValue("text_area_reminder", "Test reminder");
        //Just touching reminder input
        getPage().getRemindersBlock().getReminderForm().setInputValue("task_reminder_date_1", "");
        assertTrue(getPage().getRemindersBlock().getReminderForm().emailCheckboxEnabled());
        getPage().getRemindersBlock().getReminderForm().toggleCheckboxValue("task_reminder_type_sms");
        assertFalse(getPage().getRemindersBlock().getReminderForm().smsCheckboxEnabled());

        getPage().getRemindersBlock().getReminderForm().submit();
        assertTrue(getPage().getRemindersBlock().getReminderForm().waitWhileSubmitting());

        assertTrue(getPage().getSweetAlertNotification().getAlert().isDisplayed());
        assertEquals("New reminder has been added.", getPage().getSweetAlertNotification().getMessage());
        getPage().getSweetAlertNotification().close();

        assertEquals("Test reminder",getPage().getRemindersBlock().getReminders().get(1).getText());
    }

}
