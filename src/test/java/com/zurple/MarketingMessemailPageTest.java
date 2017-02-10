package com.zurple;

import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;

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
    }
}
