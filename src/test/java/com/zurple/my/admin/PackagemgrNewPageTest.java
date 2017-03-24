package com.zurple.my.admin;

import com.zurple.my.Admin.PackagemgrEditPage;
import com.zurple.my.Admin.PackagemgrNewPage;
import com.zurple.my.PageTest;
import java.util.UUID;
import org.testng.annotations.Test;
import resources.classes.FormErrorMessage;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class PackagemgrNewPageTest
        extends PageTest
{
    private static PackagemgrNewPage page;

    public PackagemgrNewPage getPage(){
        if(page == null){
            page = new PackagemgrNewPage();
            page.setUrl("https://my.dev.zurple.com/packagemgr/edit/");
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
    public void testNetsuiteIdValidator()
    {

        assertTrue(getPage().checkPackageEditFormExists());

        getPage().getPackageEditForm().submit();

        Boolean valid_flag=true;
        for(FormErrorMessage errorMessage : getPage().getPackageEditForm().getFormErrorMessagesList()){
            if(errorMessage.getMessage().equals("You must enter NetSuite ID")){
                valid_flag=false;
            }
        }
        assertFalse(valid_flag);


        getPage().getPackageEditForm().setInputValue("netsuite_id","some_string");
        getPage().getPackageEditForm().submit();

        valid_flag=true;
        for(FormErrorMessage errorMessage : getPage().getPackageEditForm().getFormErrorMessagesList()){
            if(errorMessage.getMessage().equals("You must enter correct NetSuite ID")){
                valid_flag=false;
            }
        }
        assertFalse(valid_flag);

        Integer randomNum = 1000 + (int)(Math.random() * 9999);
        getPage().getPackageEditForm().clearFields();
        getPage().getPackageEditForm().setInputValue("netsuite_id",randomNum.toString());
        getPage().getPackageEditForm().submit();

        valid_flag=true;
        for(FormErrorMessage errorMessage : getPage().getPackageEditForm().getFormErrorMessagesList()){
            if(
                errorMessage.getMessage().equals("You must enter NetSuite ID") ||
                errorMessage.getMessage().equals("You must enter correct NetSuite ID")
            ){
                valid_flag=false;
            }
        }
        assertTrue(valid_flag);
    }

    @Test
    public void testNewPackageCreate()
    {

        assertTrue(getPage().checkPackageEditFormExists());

        Integer randomNum = 1000000 + (int)(Math.random() * 9999999);

        String uuid = UUID.randomUUID().toString();
        String packageFullName = uuid.substring(0,10)+"_test_package";
        String packagePhone = randomNum.toString();
        String packageNetsuiteId = randomNum.toString();
        String packageZurpleId = randomNum.toString();
        String packageSetupFee = randomNum.toString().substring(0,3);
        String packageEmail = packageFullName+"@test.com";
        String packagePath = "https://dev.site1.zurple.com";
        getPage().getPackageEditForm().setInputValue("full_name",packageFullName);
        getPage().getPackageEditForm().setInputValue("phone",packagePhone);
        getPage().getPackageEditForm().setInputValue("email",packageEmail);
        getPage().getPackageEditForm().setInputValue("netsuite_id",packageNetsuiteId);
        getPage().getPackageEditForm().setInputValue("setup_fee",packageSetupFee);
        getPage().getPackageEditForm().setInputValue("path",packagePath);
        getPage().getPackageEditForm().setInputValue("zrm_client_id",packageZurpleId);
        getPage().getPackageEditForm().toggleRadioButtonValue("admin_limit-1");

        getPage().getPackageEditForm().submit();

        assertEquals(getPage().getPackageEditForm().getFormErrorMessagesList().size(),0);

    }

}
