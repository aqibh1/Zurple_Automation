package com.zurple.my;

/**
 * todo
 *
 * @author Vladimir
 */
public class CraiglistPropertyPageTest
        extends PageTest
{
    private CraiglistPropertyPage page;

    public CraiglistPropertyPage getPage(){
        if(page == null){
            page = new CraiglistPropertyPage();
            page.setPropertyId(getEnvironment().getPropertyToCheck());
            page.setDriver(getDriver());
        }
        return page;
    }

    public void clearPage(){
        page=null;
    };
}
