package com.zurple.my;

import java.util.ArrayList;
import org.testng.annotations.Test;
import resources.ConfigReader;
import resources.orm.hibernate.models.Site;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class DistributionRuleEditPageTest
        extends PageTest
{

    private static DistributionRuleEditPage page;

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
        assertEquals(getPage().getCurrentUrl(),configReader.getPropertyByName("bo_base_url")+ "/agents");
    }

}
