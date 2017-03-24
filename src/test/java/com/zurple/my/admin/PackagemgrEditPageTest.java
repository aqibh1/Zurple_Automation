package com.zurple.my.admin;

import com.zurple.my.Admin.AdminmgrEditPage;
import com.zurple.my.Admin.PackagemgrEditPage;
import com.zurple.my.PageTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class PackagemgrEditPageTest
        extends PageTest
{
    private static PackagemgrEditPage page;

    public PackagemgrEditPage getPage(){
        if(page == null){
            page = new PackagemgrEditPage();
            page.setUrl("https://my.dev.zurple.com/packagemgr/edit/package_id/"+getEnvironment().getAdmin().getPackage().getId());
            page.setDriver(getDriver());
        }
        return page;
    }

    public void clearPage(){
        page=null;
    };

    @Test
    public void testNetsuiteIdInputExists()
    {
        assertTrue(getPage().getPackageEditForm().checkElementExistsById("netsuite_id"));
    }

    @Test
    public void testNetsuiteIdShownCorrect()
    {
        assertTrue(getPage().checkPackageEditFormExists());
        assertEquals(getPage().getPackageEditForm().getInputValue("netsuite_id"),getEnvironment().getAdmin().getPackage().getNetsuiteId().toString());
    }

    @Test
    public void testEditNetsuiteId()
    {

        assertTrue(getPage().checkPackageEditFormExists());
        Integer randomNum = 1000 + (int)(Math.random() * 9999);
        getPage().getPackageEditForm().clearFieldById("netsuite_id");
        getPage().getPackageEditForm().setInputValue("netsuite_id",randomNum.toString());

        getPage().getPackageEditForm().submit();

        clearPage();

        assertEquals(getPage().getPackageEditForm().getInputValue("netsuite_id"),randomNum.toString());

        //Returning back to normal ns id
        getPage().getPackageEditForm().clearFieldById("netsuite_id");
        getPage().getPackageEditForm().setInputValue("netsuite_id","4990247");
        getPage().getPackageEditForm().submit();
        clearPage();
        assertEquals(getPage().getPackageEditForm().getInputValue("netsuite_id"),"4990247");

    }

}
