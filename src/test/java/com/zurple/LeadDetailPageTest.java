package com.zurple;

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

    public void testTitle() {
        assertEquals("Zurple Backoffice", getPage().getTitle());
    }

    public void testRemindersBlock(){
        assertTrue(getPage().checkRemindersBlock());
    }

}
