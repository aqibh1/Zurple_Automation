package com.zurple.my.admin;

import com.zurple.my.Admin.SimplecontentmanagerPage;
import com.zurple.my.PageTest;

import static org.testng.Assert.assertTrue;

public class SimplecontentmanagerPageTest
        extends PageTest
{
    private static SimplecontentmanagerPage page;

    public SimplecontentmanagerPage getPage(){
        if(page == null){
            page = new SimplecontentmanagerPage();
            page.setUrl("https://my.dev.zurple.com/simplecontentmanager/edit/?key=zurple_updates_v2");
            page.setDriver(getDriver());
        }
        return page;
    }

    public void clearPage(){
        page=null;
    };

}
