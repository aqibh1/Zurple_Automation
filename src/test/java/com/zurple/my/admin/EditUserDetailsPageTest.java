package com.zurple.my.admin;

import com.zurple.my.PageTest;
import com.zurple.my.Admin.EditUserDetailsPage;

public class EditUserDetailsPageTest
        extends PageTest
{
    private EditUserDetailsPage page;

    public EditUserDetailsPage getPage(){
        if(page == null){
            page = new EditUserDetailsPage();
            page.setDriver(getDriver());
        }
        return page;
    }

    public void clearPage(){
        page=null;
    };
}
