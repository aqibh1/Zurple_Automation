package com.zurple;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import resources.classes.Alert;

import static java.util.regex.Pattern.compile;

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

    public void testTitle() {
        assertEquals("Zurple Backoffice", getPage().getTitle());
    }

    public void testNewLeadsBlock(){
        assertTrue(getPage().checkNewLeadsBlock());
        assertFalse(getPage().getNewLeadsBlock().getLeadIds().isEmpty());
        assertEquals(5,getPage().getNewLeadsBlock().getLeadIds().size());
    }

    public void testNewLeadsLink(){
        assertTrue(getPage().checkNewLeadsBlock());
        Integer n = getPage().getNewLeadsBlock().getLeadIds().get(0);
        getEnvironment().setLeadToCheck(n);
        assertEquals("https://my.dev.zurple.com/lead/"+n+"?from=new", getPage().getNewLeadsBlock().getNewLeadLink(1).getAttribute("href"));
    }

    public void testHotBehaviors(){
        assertTrue(getPage().checkHotBehaviorBlock());
        for (Alert alert: getPage().getHotBehaviorBlock().getHotBehaviorList()) {
            Pattern pattern = Pattern.compile("https://my\\.dev\\.zurple\\.com/lead/(\\d+)\\?from=hotlead");
            Matcher matcher = pattern.matcher(alert.getLeadLink());
            assertTrue(matcher.find());
            Integer lead_id = Integer.parseInt(matcher.group(1));
            System.out.println(lead_id);
            List<String> expectedFlags = getEnvironment().getLeadFlags(lead_id);
            List<String> parsedFlags = alert.getFlagsList();
            System.out.println(expectedFlags);
            System.out.println(parsedFlags);
            expectedFlags.retainAll(parsedFlags);

            assertEquals(0,expectedFlags.size());
        }
    }

}
