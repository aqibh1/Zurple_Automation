package com.zurple;

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
            System.out.print(alert.getLeadLink());
            assertTrue(Pattern.matches("https://ya\\.ru/param","https://ya.ru/param"));
            assertTrue(Pattern.matches("https://my\\.dev\\.zurple\\.com/lead/(\\d+)\\?from=hotlead",alert.getLeadLink()));
        }

    }

}
