package com.zurple.my.admin;

import com.zurple.my.Admin.AdminmgrEditPage;
import com.zurple.my.PageTest;
import org.testng.annotations.Test;
import resources.ConfigReader;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class AdminmgrEditPageTest
        extends PageTest
{

    private AdminmgrEditPage page;

    public AdminmgrEditPage getPage()
    {
        if (page == null)
        {
            page = new AdminmgrEditPage();
            page.setAdminId(getEnvironment().getAgentToCheck());
            page.setDriver(getDriver());
        }
        return page;
    }

    public AdminmgrEditPage getPage(Integer admin_id)
    {

        ConfigReader configReader = ConfigReader.load();

        if (page == null)
        {
            page = new AdminmgrEditPage();
            page.setUrl(configReader.getPropertyByName("bo_base_url")+"/adminmgr/edit/admin_id/" + admin_id);
            page.setDriver(getDriver());
        }
        return page;
    }

    public void clearPage()
    {
        page = null;
    }

    @Test
    public void testBillingAccessFlag()
    {

        clearPage();

        assertTrue(getPage().getAdminEditForm().checkBillingAccessCheckboxExists());
        assertEquals(getPage().getAdminEditForm().getBillingAccessCheckbox().getValue(),
                getEnvironment().getAdmin(getEnvironment().getAgentToCheck()).getBillingAccessFlag());
    }

    @Test
    public void testToggleBillingAccessCheckbox()
    {

        clearPage();

        assertTrue(getPage().getAdminEditForm().checkBillingAccessCheckboxExists());
        assertEquals(getPage().getAdminEditForm().getBillingAccessCheckbox().getValue(),
                getEnvironment().getAdmin(getEnvironment().getAgentToCheck()).getBillingAccessFlag());
        Boolean initialBilligAccessCheckboxValue = getPage().getAdminEditForm().getBillingAccessCheckbox().getValue();
        getPage().getAdminEditForm().toggleCheckboxValue("billing_access_flag");
        assertTrue(
                initialBilligAccessCheckboxValue != getPage().getAdminEditForm().getBillingAccessCheckbox().getValue());

        getPage().getAdminEditForm().submit();
        clearPage();

        assertTrue(getPage().getAdminEditForm().checkBillingAccessCheckboxExists());
        assertTrue(
                initialBilligAccessCheckboxValue != getPage().getAdminEditForm().getBillingAccessCheckbox().getValue());
    }

}
