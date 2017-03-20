package com.zurple.my;

/**
 * todo
 *
 * @author Vladimir
 */
public class CraiglistPropertyPageTest
        extends PageTest
{
    private static CraiglistPropertyPage page;

    public CraiglistPropertyPage getPage(){
        if(page == null){
            page = new CraiglistPropertyPage();
            page.setUrl("https://my.dev.zurple.com/property/craigslist/prop_id/"+getEnvironment().getPropertyToCheck());
            page.setDriver(getDriver());
        }
        return page;
    }

    public void clearPage(){
        page=null;
    };
}
