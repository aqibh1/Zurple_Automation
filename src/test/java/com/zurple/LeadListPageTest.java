package com.zurple;

import org.testng.Assert;
import org.testng.annotations.Test;
import resources.classes.LeadSearchCriteria;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * todo
 *
 * @author Vladimir
 */
public class LeadListPageTest  extends PageTest
{
    private static LeadListPage page;

    public LeadListPage getPage(){
        if(page == null){
            page = new LeadListPage();
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
    public void testByAgentSearchFilterCriteriaExists(){
        assertTrue(getPage().checkLeadsSearchFormExists());
        Boolean flag = false;
        for(LeadSearchCriteria lsc: getPage().getLeadsSearchForm().getSearchCriteriaVariants(1)){
            if(lsc.getDescription().equals("By Agent")){
                flag=true;
            }
        }
        assertTrue(flag);

        //Adding second filter and checking there
        flag=false;
        getPage().getLeadsSearchForm().toggleSecondFilter();
        for(LeadSearchCriteria lsc: getPage().getLeadsSearchForm().getSearchCriteriaVariants(2)){
            if(lsc.getDescription().equals("By Agent")){
                flag=true;
            }
        }
        assertTrue(flag);
    }

    @Test
    public void testLeadList(){
        assertTrue(getPage().checkLeadsListBlockExists());

        assertEquals(getPage().getLeadsListBlock().getLeadList().size(),15);
        assertEquals(Math.ceil(getPage().getLeadsListBlock().getTotalLeadsNumber()/15),(double)getPage().getLeadsListBlock().getNumberOfPages());
    }

}
