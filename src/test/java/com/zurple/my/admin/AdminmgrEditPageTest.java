package com.zurple.my.admin;

import com.zurple.my.Admin.AdminmgrEditPage;
import com.zurple.my.PageTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class AdminmgrEditPageTest
        extends PageTest
{
    private static AdminmgrEditPage page;

    public AdminmgrEditPage getPage(){
        if(page == null){
            page = new AdminmgrEditPage();
            page.setUrl("https://my.dev.zurple.com/adminmgr/edit/admin_id/"+getEnvironment().getAgentToCheck());
            page.setDriver(getDriver());
        }
        return page;
    }

    public void clearPage(){
        page=null;
    };

    @Test
    public void testBillingAccessFlag(){
        assertTrue(getPage().checkBillingAccessCheckboxExists());
        assertEquals(getPage().getBillingAccessCheckbox().getValue(),getEnvironment().getAdmin(getEnvironment().getAgentToCheck()).getBillingAccessFlag());
    }
}
