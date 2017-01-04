package com.zurple;

public class DashboardPageTest
        extends PageTest
{

    private static DashboardPage page;

    public DashboardPage getPage(){
        if(page == null){
            page = new DashboardPage();
            page.setDriver(getDriver());
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

    public void testNewLeadLink(){
        assertTrue(getPage().checkNewLeadsBlock());
        Integer n = getPage().getNewLeadsBlock().getLeadIds().get(0);
        getPage().getNewLeadsBlock().getNewLeadLink(1).click();
        assertEquals("https://my.dev.zurple.com/lead/"+n+"?from=new", getDriver().getCurrentUrl());
    }

}
