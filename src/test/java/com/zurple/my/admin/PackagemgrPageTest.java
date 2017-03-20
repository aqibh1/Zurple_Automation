package com.zurple.my.admin;

import com.zurple.my.Admin.PackagemgrPage;
import com.zurple.my.PageTest;

public class PackagemgrPageTest
        extends PageTest
{
    private static PackagemgrPage page;

    public PackagemgrPage getPage(){
        if(page == null){
            page = new PackagemgrPage();
            page.setUrl("https://my.dev.zurple.com/packagemgr/");
            page.setDriver(getDriver());
        }
        return page;
    }

    public void clearPage(){
        page=null;
    };
}
