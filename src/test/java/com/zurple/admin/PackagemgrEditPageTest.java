package com.zurple.admin;

import com.zurple.Admin.PackagemgrEditPage;
import com.zurple.Admin.PackagemgrPage;
import com.zurple.PageTest;

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
