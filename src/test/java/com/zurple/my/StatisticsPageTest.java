package com.zurple.my;

/**
 * todo
 *
 * @author Vladimir
 */
public class StatisticsPageTest  extends PageTest
{
    private static StatisticsPage page;

    public StatisticsPage getPage(){
        if(page == null){
            page = new StatisticsPage();
            page.setDriver(getDriver());
        }
        return page;
    }

    public void clearPage(){
        page=null;
    };

}
