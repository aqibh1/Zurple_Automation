package com.zurple.my;

import java.util.UUID;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * todo
 *
 * @author Vladimir
 */
public class AgentCreatePageTest
        extends PageTest
{
    private AgentCreatePage page;

    public AgentCreatePage getPage(){
        if(page == null){
            page = new AgentCreatePage();
            page.setDriver(getDriver());
        }
        return page;
    }

    public void clearPage(){
        page=null;
    };


    @Test
    public void testCreationNewAdmin(){
        assertTrue(getPage().checkAgentEditForm());
        String uuid = UUID.randomUUID().toString();
        String adminFirstName = uuid.substring(0,5)+"test";
        String adminLastName = "Admin";
        String adminEmail = adminFirstName+"_"+adminLastName+"@test.com";
        String adminAltEmail = adminFirstName+"_"+adminLastName+"_alt@test.com";
        String adminPassword = uuid.substring(0,5);
        getPage().getAgentCreateForm().setInputValue("first_name",adminFirstName);
        getPage().getAgentCreateForm().setInputValue("last_name",adminLastName);
        getPage().getAgentCreateForm().setInputValue("email",adminEmail);
        getPage().getAgentCreateForm().setInputValue("password",adminPassword);
        getPage().getAgentCreateForm().setInputValue("password_confirm",adminPassword);

        getPage().getAgentCreateForm().submit();

        AssertJUnit.assertTrue(getPage().getAgentCreateForm().checkAgentCreateWarningExists());
        AssertJUnit.assertTrue(getPage().getAgentCreateForm().checkAgentCreateWarningVisible());
        getPage().getAgentCreateForm().getAgentCreateWarning().clickOkButton();

        assertFalse(getEnvironment().getAdminByEmail(adminEmail).getBillingAccessFlag());
    }
}
