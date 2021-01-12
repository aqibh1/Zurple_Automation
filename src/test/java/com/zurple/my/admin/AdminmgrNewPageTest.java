package com.zurple.my.admin;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.UUID;

import org.testng.annotations.Test;

import com.zurple.my.PageTest;
import com.zurple.my.Admin.AdminmgrNewPage;

import resources.ConfigReader;

public class AdminmgrNewPageTest
        extends PageTest
{
    private AdminmgrNewPage page;

    public AdminmgrNewPage getPage(){
        if(page == null){
            page = new AdminmgrNewPage();
            page.setDriver(getDriver());
        }
        return page;
    }

    public void clearPage(){
        page=null;
    };

    @Test
    public void testBillingAccessFlag(){
        assertTrue(getPage().getAdminCreateForm().checkBillingAccessCheckboxExists());

        //Checking default billing access flag state
        assertFalse(getPage().getAdminCreateForm().getBillingAccessCheckbox().getValue());
    }

    @Test
    public void testCreationNewAdmin(){

        ConfigReader configReader = ConfigReader.load();

        assertTrue(getPage().checkAdminCreateFormExists());
        String uuid = UUID.randomUUID().toString();
        String adminFirstName = uuid.substring(0,5)+"test";
        String adminLastName = "Admin";
        String adminEmail = adminFirstName+"_"+adminLastName+"@test.com";
        String adminAltEmail = adminFirstName+"_"+adminLastName+"_alt@test.com";
        String adminPassword = uuid.substring(0,5);
        getPage().getAdminCreateForm().setInputValue("first_name",adminFirstName);
        getPage().getAdminCreateForm().setInputValue("last_name",adminLastName);
        getPage().getAdminCreateForm().setInputValue("email",adminEmail);
        getPage().getAdminCreateForm().setInputValue("alt_email",adminAltEmail);
        getPage().getAdminCreateForm().setInputValue("password",adminPassword);
        getPage().getAdminCreateForm().setInputValue("password_confirm",adminPassword);
        getPage().getAdminCreateForm().setSelectValueByIndex("package_id",1);
        getPage().getAdminCreateForm().toggleCheckboxValue("owner_flag");

        getPage().getAdminCreateForm().submit();

        assertEquals(configReader.getPropertyByName("zurple_bo_base_url")+ "/adminmgr",getDriver().getCurrentUrl());

        assertTrue(getEnvironment().getAdminByEmail(adminEmail).getBillingAccessFlag());
    }

}
