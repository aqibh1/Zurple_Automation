package com.zurple.my;

import java.util.Date;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import resources.orm.hibernate.dao.ManageUser;
import resources.orm.hibernate.dao.ManageUserActivity;
import resources.orm.hibernate.models.SessionUser;
import resources.orm.hibernate.models.User;

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

    public void clearPage(){
        page=null;
    };

    @Test
    public void testTitle() {
        assertEquals("Zurple Backoffice", getPage().getTitle());
    }

    @Test
    public void testLead() {
        //getEnvironment().getLeadObject();
    }

    @Parameters({"lead_source", "transaction_goals"})
    @Test
    public void testLeadDetailsBlock(@Optional("") String lead_source,@Optional("") String transaction_goals) {
        assertEquals(getPage().getLeadDetailsBlock().getLeadSource(),lead_source);
        assertEquals(getPage().getLeadDetailsBlock().getLeadTransactionGoals().trim(),transaction_goals.trim());
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
    
    @Test
    public void testPropertiesViewedListOrder(){
        
        //Prepare data - create lead, user, views history records
        User user = getEnvironment().getUserObject();
        SessionUser session_user = new SessionUser();
        ManageUser mu = new ManageUser(getEnvironment().getSession());
        Date today = new Date (System.currentTimeMillis());
        mu.createActivity(
                user,
                session_user,
                today,
                "property",
                1
        );
        
        Date yesterday = new Date (System.currentTimeMillis() -  24 * 60 * 60 * 1000);
        mu.createActivity(
                user,
                session_user,
                yesterday,
                "property",
                1
        );
        
        Date beforeyesterday = new Date (System.currentTimeMillis() -  2 * (24 * 60 * 60 * 1000));
        mu.createActivity(
                user,
                session_user,
                beforeyesterday,
                "property",
                1
        );
        
        assertTrue(getPage().checkPropertiesViewedBlock());
        
    }

}
