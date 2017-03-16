package com.zurple;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.testng.annotations.Test;
import resources.AbstractPageTest;
import resources.classes.Asset;

import static org.testng.Assert.assertTrue;

public abstract class PageTest extends AbstractPageTest
{
    @Test(groups = { "asset" })
    public void testAssetsVersions() {
        assertTrue(checkAssetsVersion(getPage().getAssets()));
    }
}
