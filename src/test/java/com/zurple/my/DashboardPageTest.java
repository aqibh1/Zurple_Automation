package com.zurple.my;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.ConfigReader;
import resources.classes.MenuItem;
import resources.classes.Alert;
import resources.orm.hibernate.models.User;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertFalse;

public class DashboardPageTest
        extends PageTest
{

    private DashboardPage page;

    public DashboardPage getPage(){
        if(page == null){
            ConfigReader configReader = ConfigReader.load();
            page = new DashboardPage();
            page.setDriver(getDriver());
            getEnvironment().setAgentToCheck(Integer.parseInt(configReader.getPropertyByName("zurple_bo_default_agent_id")));
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
        Assert.assertEquals(5,getPage().getNewLeadsBlock().getLeadIds().size());
    }

    @Test
    public void testAdminMenuBlock(){
        assertTrue(getPage().checkAdminMenuBlock());
        assertFalse(getPage().getAdminMenuBlock().getItemsList().isEmpty());
        //Hover
        Actions builder = new Actions(getDriver());
        String adminName = getEnvironment().getAdmin().getFirstName()+" "+getEnvironment().getAdmin().getLastName();
        try
        {
            WebElement we = getPage().getTopMenuBlock().getMenuItemWebElementByName(adminName);
            builder.moveToElement(we).perform();
        }
        catch (Exception e)
        {
            assertTrue(false);
        }

        //Checking if billing item is shown
        Boolean billing_access_flag = false;
        for(MenuItem item:getPage().getAdminMenuBlock().getItemsList()){
            if(item.getTitle().equals("Billing")){
                billing_access_flag=true;
            }
        }

        assertEquals(billing_access_flag,getEnvironment().getAdmin().getBillingAccessFlag());

    }

    @Test(groups = "asset")
    public void testNewLeadsLink(){

        ConfigReader configReader = ConfigReader.load();

        assertTrue(getPage().checkNewLeadsBlock());
        Integer n = getPage().getNewLeadsBlock().getLeadIds().get(0);
        getEnvironment().setLeadToCheck(n);
        Assert.assertEquals(configReader.getPropertyByName("zurple_bo_base_url")+"/lead/"+n+"?from=new", getPage().getNewLeadsBlock().getNewLeadLink(1).getAttribute("href"));
    }

    @Test
    public void testHotBehaviors(){

        ConfigReader configReader = ConfigReader.load();

        assertTrue(getPage().checkHotBehaviorBlock());
        assertTrue(getPage().getHotBehaviorBlock().getHotBehaviorList().size()>0);
        for (Alert alert: getPage().getHotBehaviorBlock().getHotBehaviorList()) {
            Pattern pattern = Pattern.compile(configReader.getPropertyByName("zurple_bo_base_url")+ "/lead/(\\d+)\\?from=hotlead");
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
            u.save();
            getDriver().navigate().refresh();
            assertFalse(leadId.equals(getPage().getHotBehaviorBlock().getHotBehaviorList().get(0).getLeadId()));

            u.setUserStatus(initial_status);
            u.save();
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
