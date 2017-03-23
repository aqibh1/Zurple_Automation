package com.zurple.my;

import org.testng.annotations.Test;
import resources.AbstractPageTest;

import static org.testng.Assert.assertTrue;

public abstract class PageTest extends AbstractPageTest
{
    @Test(groups = { "asset" })
    public void testAssetsVersions() {
        assertTrue(checkAssetsVersion(getPage().getAssets()));
    }
}
