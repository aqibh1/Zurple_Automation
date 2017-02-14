package com.zurple;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.testng.annotations.Test;
import resources.orm.hibernate.dao.ManageEmailQueue;
import resources.orm.hibernate.models.EmailQueue;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertFalse;

/**
 * todo
 *
 * @author Vladimir
 */
public class MarketingMessemailPageTest  extends PageTest
{
    private static MarketingMessemailPage page;

    public MarketingMessemailPage getPage(){
        if(page == null){
            page = new MarketingMessemailPage();
            page.setUrl("https://my.dev.zurple.com/marketing/massemail/lead/"+getEnvironment().getLeadToCheck());
            page.setDriver(getDriver());
        }
        return page;
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
        getPage().getMassEmailForm().setInputValue("subject","  double  spaces  ");
        getPage().getMassEmailForm().setTextareaValue("body","  double  spaces  ");
        getPage().getMassEmailForm().submit();
        assertTrue(getPage().getMassEmailForm().waitWhileSubmitting());
        assertTrue(getPage().checkMassEmailSuccessfullySentAlertBlockExists());
        //Checking DB record body
        EmailQueue lastEmailQueueEntry = getEnvironment().getLastEmailQueueEntry();
        Pattern pattern = Pattern.compile("&nbsp;");
        Matcher matcher = pattern.matcher(lastEmailQueueEntry.getBodyHtml());
        assertFalse(matcher.find());
        System.out.println(lastEmailQueueEntry.getSubject());
        System.out.println(lastEmailQueueEntry.getBodyHtml());
    }
}
