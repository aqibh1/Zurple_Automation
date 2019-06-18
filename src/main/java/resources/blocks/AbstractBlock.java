package resources.blocks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import resources.classes.HotAlertsFlags;
import resources.interfaces.UsesDriver;

public abstract class AbstractBlock implements UsesDriver
{
    protected WebElement block;
    protected WebDriver driver;

    public void setBlock(WebElement object){
        block = object;
    }

    public void setDriver(WebDriver object){
        driver = object;
    }

    public WebDriver getDriver(){
        return driver;
    }

    public HotAlertsFlags parseHotAlertsFlags(WebElement flagsBlock){
        HotAlertsFlags flags = new HotAlertsFlags();
        //Adding flags
        for(WebElement flag: flagsBlock.findElements(By.xpath("./descendant::li[contains(concat(\" \",normalize-space(@class),\" \"),\" flag-selected \")]"))){
            String flagTitle=flag.getAttribute("class")
                    .replaceAll("flag ", "")
                    .replaceAll("flag-selected", "")
                    .replaceAll("flag-", "")
                    .replaceAll(" ", "");
            flags.addFlag(flagTitle);
        }

        return flags;
    }

}
