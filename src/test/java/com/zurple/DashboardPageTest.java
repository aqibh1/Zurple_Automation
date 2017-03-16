package com.zurple;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.testng.annotations.Test;
import resources.classes.Alert;
import resources.orm.hibernate.models.Lead;
import resources.orm.hibernate.models.User;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertFalse;

public class DashboardPageTest
        extends PageTest
{

    private static DashboardPage page;

    public DashboardPage getPage(){
        if(page == null){
            page = new DashboardPage();
            page.setDriver(getDriver());
            getEnvironment().setAgentToCheck(4);
        }
        return page;
    }
    @Test
    public void testTitle() {
        assertEquals("Zurple Backoffice", getPage().getTitle());
    }

    @Test
    public void testNewLeadsBlock(){
        assertTrue(getPage().checkNewLeadsBlock());
        assertFalse(getPage().getNewLeadsBlock().getLeadIds().isEmpty());
        assertEquals(5,getPage().getNewLeadsBlock().getLeadIds().size());
    }

    @Test(groups = "asset")
    public void testNewLeadsLink(){
        assertTrue(getPage().checkNewLeadsBlock());
        Integer n = getPage().getNewLeadsBlock().getLeadIds().get(0);
        getEnvironment().setLeadToCheck(n);
        assertEquals("https://my.dev.zurple.com/lead/"+n+"?from=new", getPage().getNewLeadsBlock().getNewLeadLink(1).getAttribute("href"));
    }

    @Test
    public void testHotBehaviors(){
        assertTrue(getPage().checkHotBehaviorBlock());
        assertTrue(getPage().getHotBehaviorBlock().getHotBehaviorList().size()>0);
        for (Alert alert: getPage().getHotBehaviorBlock().getHotBehaviorList()) {
            Pattern pattern = Pattern.compile("https://my\\.dev\\.zurple\\.com/lead/(\\d+)\\?from=hotlead");
            Matcher matcher = pattern.matcher(alert.getLeadLink());
            assertTrue(matcher.find());
            Integer lead_id = Integer.parseInt(matcher.group(1));
            List<String> expectedFlags = getEnvironment().getLeadFlags(lead_id);
            List<String> parsedFlags = alert.getFlagsList().getFlagsList();
            assertTrue(expectedFlags.size()>0);
            assertFalse(expectedFlags.retainAll(parsedFlags));
        }

        // #1300 ticket - changing first lead's status to hidden - it should disappear from the list
        try
        {
            Integer leadId = getPage().getHotBehaviorBlock().getHotBehaviorList().get(0).getLeadId();
            User u = getEnvironment().getUserById(leadId);

            String initial_status = u.getUserStatus();
            u.setUserStatus("hidden");
            getEnvironment().updateUser(u);
            getDriver().navigate().refresh();
            assertFalse(leadId.equals(getPage().getHotBehaviorBlock().getHotBehaviorList().get(0).getLeadId()));

            u.setUserStatus(initial_status);
            getEnvironment().updateUser(u);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            assertTrue(false);
        }

    }

    public void clearPage(){
        page=null;
    };

}
