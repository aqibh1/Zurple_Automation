package com.zurple.my.admin;

import com.zurple.my.Admin.PackagemgrEditPage;
import com.zurple.my.PageTest;

public class PackagemgrEditPageTest
        extends PageTest
{
    private static PackagemgrEditPage page;

    public PackagemgrEditPage getPage(){
        if(page == null){
            page = new PackagemgrEditPage();
            page.setUrl("https://my.dev.zurple.com/packagemgr/edit");
            page.setDriver(getDriver());
        }
        return page;
    }

    public void clearPage(){
        page=null;
    };
}
