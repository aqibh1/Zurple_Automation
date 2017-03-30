package com.zurple.my.admin;

import com.zurple.my.Admin.AdminmgrEditPage;
import com.zurple.my.BillingPage;
import com.zurple.my.BillingPageTest;
import com.zurple.my.PageTest;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import org.apache.commons.io.IOUtils;
import org.testng.Assert;
import org.testng.TestNG;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class AdminmgrEditPageTest
        extends PageTest
{

    private static AdminmgrEditPage page;

    public AdminmgrEditPage getPage()
    {
        if (page == null)
        {
            page = new AdminmgrEditPage();
            page.setUrl("https://my.dev.zurple.com/adminmgr/edit/admin_id/" + getEnvironment().getAgentToCheck());
            page.setDriver(getDriver());
        }
        return page;
    }

    public AdminmgrEditPage getPage(Integer admin_id)
    {
        if (page == null)
        {
            page = new AdminmgrEditPage();
            page.setUrl("https://my.dev.zurple.com/adminmgr/edit/admin_id/" + admin_id);
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