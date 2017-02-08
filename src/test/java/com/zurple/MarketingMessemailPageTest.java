package com.zurple;

/**
 * todo
 *
 * @author Vladimir
 */
public class MarketingMessemailPageTest  extends PageTest
{
    private static MarketingMessemailPage page;

    public MarketingMessemailPage getPage(){
        if(page == null){
            page = new MarketingMessemailPage();
            page.setDriver(getDriver());
        }
        return page;
    }
}
