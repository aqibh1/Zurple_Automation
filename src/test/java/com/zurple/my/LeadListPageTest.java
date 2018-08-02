package com.zurple.my;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import resources.classes.LeadSearchCriteria;
import resources.orm.hibernate.models.User;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

/**
 * todo
 *
 * @author Vladimir
 */
public class LeadListPageTest  extends PageTest
{
    private static LeadListPage page;

    public LeadListPage getPage(String url){
        page = new LeadListPage();
        page.setUrl(url);
        page.setDriver(getDriver());
        return page;
    }

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
    @Parameters({"url"})
    public void testLeadList(@Optional("") String url){
        getPage(url);
        assertTrue(getPage().checkLeadsListBlockExists());

        assertEquals(getPage().getLeadsListBlock().getLeadList().size(),15);
        assertEquals((int)Math.ceil(getPage().getLeadsListBlock().getTotalLeadsNumber()/15.0),(int)getPage().getLeadsListBlock().getNumberOfPages());
    }

    @Test
    @Parameters({"status","ignore_automation"})
    public void testLeadStatusAutomationIcon(@Optional("") String status, @Optional("0") Integer ignore_automation){

        User user = getEnvironment().getUserToCheck();
        if (ignore_automation == 0)
        {
            assertEquals(getPage().getLeadsListBlock().getUserStatusAutomationIcon(user.getId()),null);
        }else if(ignore_automation == 1)
        {
            assertEquals(getPage().getLeadsListBlock().getUserStatusAutomationIcon(user.getId()).getAttribute("title"),"30 days until lead automation is re-enabled");
            assertEquals(getPage().getLeadsListBlock().getUserStatusAutomationIcon(user.getId()),"z-lead-automation-marker z-lead-automation-paused");
        }else
        {
            assertEquals(getPage().getLeadsListBlock().getUserStatusAutomationIcon(user.getId()),"Lead status is paused from automation. To allow for automation to occur again, please update the status and select Temporary change.");
            assertEquals(getPage().getLeadsListBlock().getUserStatusAutomationIcon(user.getId()),"z-lead-automation-marker z-lead-automation-disabled");
        }
    }

}
