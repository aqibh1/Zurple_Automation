package com.zurple.my;

import static org.testng.Assert.assertTrue;

import java.text.ParseException;

import org.testng.annotations.Test;

import com.zurple.backoffice.ZBOLeadCRMPage;
import com.zurple.backoffice.ZBOLeadPage;

import resources.AbstractPageTest;
import resources.utility.ActionHelper;

public abstract class PageTest extends AbstractPageTest
{
    @Test(groups = { "asset" })
    public void testAssetsVersions() {
        assertTrue(checkAssetsVersion(getPage().getAssets()));
    }

    protected static void waitLoad(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException qq) {
            qq.printStackTrace();
        }
    }
    protected void applyMultipleFilter(String pFilterName, String pFilterValue) throws ParseException {
		ZBOLeadPage leadPage = new ZBOLeadPage(getDriver());
		ZBOLeadCRMPage leadCRMPage = new ZBOLeadCRMPage(getDriver());
		String[] lFilterNameList = pFilterName.split(",");
		String[] lFilterValueList = pFilterValue.split(",");
		for(int i=0;i<lFilterNameList.length;i++) {
			assertTrue(leadPage.clickAndSelectFilterNameMultiple(lFilterNameList[i],Integer.toString(i+1)),"Unable to select the filter type "+lFilterNameList[i]);
			ActionHelper.staticWait(10);
			assertTrue(leadPage.clickAndSelectFilterValueMultiple(lFilterValueList[i],Integer.toString(i+1)),"Unable to select the filter value "+lFilterValueList[i]);
			assertTrue(leadCRMPage.clickOnAddFilterButton());
		}
		assertTrue(leadPage.clickOnSearchButton(),"Unable to click on search button..");
	}
}
