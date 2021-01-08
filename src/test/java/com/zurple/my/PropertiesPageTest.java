package com.zurple.my;

public class PropertiesPageTest
        extends PageTest
{

    private PropertiesPage page;

    public PropertiesPage getPage(){
        if(page == null){
            page = new PropertiesPage();
            page.setDriver(getDriver());
        }
        return page;
    }

    public void clearPage(){
        page=null;
    };

}
