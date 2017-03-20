package com.zurple.my.admin;

import com.zurple.my.Admin.EditUserDetailsPage;
import com.zurple.my.PageTest;

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
