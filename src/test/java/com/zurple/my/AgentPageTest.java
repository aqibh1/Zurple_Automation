package com.zurple.my;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * todo
 *
 * @author Vladimir
 */
public class AgentPageTest  extends PageTest
{
    private static AgentPage page;

    public AgentPage getPage(){
        if(page == null){
            page = new AgentPage();
            page.setAgentId(getEnvironment().getCurrentAgentId());
            page.setDriver(getDriver());
        }
        return page;
    }

    public void clearPage(){
        page=null;
    };

    @Test
    public void testSmsNotificationPreferences(){

        assertTrue(getPage().checkAgentEditFormExists());
        assertTrue(getPage().getAgentEditForm().checkElementExistsById("sms_notif_new_lead"));
        //assertEquals(getEnvironment().getAdmin().);
        assertTrue(getPage().getAgentEditForm().checkElementExistsById("sms_notif_property_inquiry"));
        assertTrue(getPage().getAgentEditForm().checkElementExistsById("sms_notif_seller_inquiry"));
        assertTrue(getPage().getAgentEditForm().checkElementExistsById("sms_notif_agent_Inquiry"));
        assertTrue(getPage().getAgentEditForm().checkElementExistsById("sms_notif_schedule_showing"));
        assertTrue(getPage().getAgentEditForm().checkElementExistsById("sms_notif_cma_inquiry"));
        assertTrue(getPage().getAgentEditForm().checkElementExistsById("sms_notif_lead_reply"));

    }

}
