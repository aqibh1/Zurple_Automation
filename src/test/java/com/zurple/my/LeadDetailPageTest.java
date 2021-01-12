package com.zurple.my;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.Date;

import org.openqa.selenium.By;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zurple.my.resources.forms.LeadStatusForm;

import resources.orm.hibernate.dao.zurple.ManageUser;
import resources.orm.hibernate.models.zurple.SessionUser;
import resources.orm.hibernate.models.zurple.User;

public class LeadDetailPageTest
        extends PageTest
{

    private LeadDetailPage page;

    public LeadDetailPage getPage(){
        if(page == null){
            page = new LeadDetailPage();
            
            Integer leadToCheck = getEnvironment().getLeadToCheck();
            if (leadToCheck == null){
                User lastRegisteredUser = getEnvironment().getUserToCheck();
                leadToCheck = lastRegisteredUser.getId();
            }
            
            page.setLeadId(leadToCheck);
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
        
    @Test
    @Parameters({"status", "temporary_status_change"})
    public void testLeadStatusUpdate(@Optional("") String status,@Optional("false") Boolean temporary_status_change){
        assertTrue(getPage().checkLeadStatusFormExists());
        LeadStatusForm form = getPage().getLeadStatusForm();

        form.setSelectValueByValue("lead_status",status);

        assertTrue(getPage().getLeadStatusUpdateNotification().getAlert().isDisplayed());
        assertEquals(getPage().getLeadStatusUpdateNotification().getMessage(),"Is this a temporary update?");
        if (temporary_status_change == true){
            getPage().getLeadStatusUpdateNotification().clickOkButton();
        }else{
            getPage().getLeadStatusUpdateNotification().clickCancelButton();
        }

        waitLoad();

    }

    @Parameters({"status", "ignore_automation"})
    @Test
    public void testLeadStatusBlock(@Optional("") String status,@Optional("0") Integer ignore_automation) {
        if ( !"".equals(status) )
        {
            assertEquals(getPage().getLeadStatusForm().getStatus().getValue(),status);
        }

        if (ignore_automation == 0)
        {
            assertEquals(getPage().getLeadStatusForm().getStatusAutomationIcon(),null);
        }else if(ignore_automation == 1)
        {
            assertEquals(getPage().getLeadStatusForm().getStatusAutomationIcon().getAttribute("title"),"30 days until lead automation is re-enabled");
            assertEquals(getPage().getLeadStatusForm().getStatusAutomationIcon().getAttribute("class"),"z-lead-automation-marker z-lead-automation-clock");
        }else
        {
            assertEquals(getPage().getLeadStatusForm().getStatusAutomationIcon().getAttribute("title"),"Lead status is paused from automation. To allow for automation to occur again, please update the status and select Temporary change.");
            assertEquals(getPage().getLeadStatusForm().getStatusAutomationIcon().findElement(By.tagName("img")).getAttribute("class"),"z-lead-automation-pause");
        }
    }
}
