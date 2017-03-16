package com.zurple.admin;

import com.zurple.Admin.AdmgrPage;
import com.zurple.Admin.EditUserDetailsPage;
import com.zurple.PageTest;

public class EditUserDetailsPageTest
        extends PageTest
{
    private static EditUserDetailsPage page;

    public EditUserDetailsPage getPage(){
        if(page == null){
            page = new EditUserDetailsPage();
            page.setUrl("https://my.dev.zurple.com/admin/edituserdetails");
            page.setDriver(getDriver());
        }
        return page;
    }

    public void clearPage(){
        page=null;
    };
}
