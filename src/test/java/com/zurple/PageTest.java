package com.zurple;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import resources.AbstractPageTest;

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
}
