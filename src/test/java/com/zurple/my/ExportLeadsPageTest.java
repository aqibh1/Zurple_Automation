package com.zurple.my;

public class ExportLeadsPageTest
        extends PageTest
{

    private ExportLeadsPage page;

    public ExportLeadsPage getPage(){
        if(page == null){
            page = new ExportLeadsPage();
            page.setDriver(getDriver());
        }
        return page;
    }

    public void clearPage(){
        page=null;
    };

}
