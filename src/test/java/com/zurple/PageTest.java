package com.zurple;

import java.util.regex.Pattern;
import org.testng.annotations.Test;
import resources.AbstractPageTest;
import resources.classes.Asset;

import static org.testng.Assert.assertTrue;

public abstract class PageTest extends AbstractPageTest
{
    @Test
    public void testAssetsVersions() {
        for (Asset asset: getPage().getAssets()) {
            assertTrue(Pattern.matches("\\?v=\\d{4}\\.\\d{2}\\.\\d$",asset.getUrl()));
        }
    }
}
