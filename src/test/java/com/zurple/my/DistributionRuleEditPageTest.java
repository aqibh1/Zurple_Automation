package com.zurple.my;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

import org.testng.annotations.Test;

import resources.ConfigReader;

public class DistributionRuleEditPageTest
        extends PageTest
{

    private DistributionRuleEditPage page;

    public DistributionRuleEditPage getPage(){
        if(page == null){
            page = new DistributionRuleEditPage();

            //TODO ArrayList<Site> sitesList = new ArrayList<Site>(getEnvironment().getAdminById(getEnvironment().getAgentToCheck()).getSites());

            page.setSiteId(1);
            page.setDriver(getDriver());
        }
        return page;
    }

    public void clearPage(){
        page=null;
    };

    @Test(priority=20)
    public void testSubmittingDistributionRulesForm(){

        ConfigReader configReader = ConfigReader.load();

        getPage().getDistributionRulesForm().submit();
        assertTrue(getPage().getDistributionRulesForm().checkDistributionRulesUpdateWarningExists());
        assertTrue(getPage().getDistributionRulesForm().checkDistributionRulesUpdateWarningVisible());
        getPage().getDistributionRulesForm().getDistributionRulesUpdateWarning().clickOkButton();
        assertEquals(getPage().getCurrentUrl(),configReader.getPropertyByName("zurple_bo_base_url")+ "/agents");
    }

}
