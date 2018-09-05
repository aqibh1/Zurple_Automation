package com.zurple.my.admin;

import com.zurple.my.Admin.SimplecontentmanagerPage;
import com.zurple.my.PageTest;

import static org.testng.Assert.assertTrue;

public class SimplecontentmanagerPageTest
        extends PageTest
{
    private SimplecontentmanagerPage page;

    public SimplecontentmanagerPage getPage(){
        if(page == null){
            page = new SimplecontentmanagerPage();
            page.setDriver(getDriver());
        }
        return page;
    }

    public void clearPage(){
        page=null;
    };

}
