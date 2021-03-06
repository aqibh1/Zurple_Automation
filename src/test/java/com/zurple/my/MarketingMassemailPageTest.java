package com.zurple.my;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import resources.orm.hibernate.models.zurple.EmailQueue;

/**
 * todo
 *
 * @author Vladimir
 */
public class MarketingMassemailPageTest extends PageTest
{
    private MarketingMessemailPage page;

    public MarketingMessemailPage getPage(){
        if(page == null){
            page = new MarketingMessemailPage();
            page.setDriver(getDriver());
        }
        return page;
    }

    public void clearPage(){
        page=null;
    };

    @Test
    public void testCKEditorStatusBarIsHidden(){
        assertFalse(getPage().getMassEmailForm().statusBarIsEmpty());
    }

    @Test
    public void testSendingIndividualEmail(){
        assertTrue(getPage().checkMassEmailFormExists());
        try
        {
            getPage().getMassEmailForm().getRecipientByLabel("Individual Lead with any Status other than Inactive").getElement().click();
        }
        catch (Exception e)
        {
            assertTrue(false);
        }
        String uuid = UUID.randomUUID().toString();
        String subject = "  double    spaces  "+uuid;
        String body = "  double    spaces  "+uuid;
        getPage().getMassEmailForm().setInputValue("subject",subject);
        getPage().getMassEmailForm().setTextareaValue("body",body);
        getPage().getMassEmailForm().submit();
        assertTrue(getPage().getMassEmailForm().waitWhileSubmitting());
        assertTrue(getPage().checkMassEmailSuccessfullySentAlertBlockExists());
        //Checking DB record body
        EmailQueue lastEmailQueueEntry = getEnvironment().getLastEmailQueueEntry();

        assertEquals(lastEmailQueueEntry.getSubject(),subject.replaceAll("\\p{Blank}+", " "));
        assertEquals(lastEmailQueueEntry.getBodyHtml(),body.replaceAll("\\p{Blank}+", " "));

        Pattern pattern = Pattern.compile("&nbsp;");
        Matcher matcher = pattern.matcher(lastEmailQueueEntry.getBodyHtml());
        assertFalse(matcher.find());
    }

    @Test
    @Parameters({"status"})
    public void testSendingMassEmailToLeadsWithStatus(@Optional("") String status){
        assertTrue(getPage().checkMassEmailFormExists());
        try
        {
            getPage().getMassEmailForm().getRecipientByLabel("All Leads with Status of Prospect - " + status).getElement().click();
        }
        catch (Exception e)
        {
            assertTrue(false);
        }
        String uuid = UUID.randomUUID().toString();
        String subject = "  double    spaces  "+uuid;
        String body = "  double    spaces  "+uuid;
        getPage().getMassEmailForm().setInputValue("subject",subject);
        getPage().getMassEmailForm().setTextareaValue("body",body);
        getPage().getMassEmailForm().submit();
        assertTrue(getPage().getMassEmailForm().waitWhileSubmitting());
        assertTrue(getPage().checkMassEmailSuccessfullySentAlertBlockExists());
    }
}
