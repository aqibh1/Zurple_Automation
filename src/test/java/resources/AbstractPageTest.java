package resources;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.LogManager;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import resources.classes.Asset;

import static org.testng.Assert.assertTrue;

public abstract class AbstractPageTest extends AbstractTest
{

    protected static WebDriver driver;
    protected AbstractPage page;
    protected static String source_in_url="";
    protected static Boolean incognito=false;

    public abstract AbstractPage getPage();

    public abstract void clearPage();

    public static WebDriver getDriver(){

        if(driver == null){

            driver = new ChromeDriver(new ChromeDriverService.Builder().withSilent(true).build());

        }
        return driver;
    }

    @Parameters({"source_in_url","incognito"})
    @BeforeTest
    public void globalSetUp(@Optional("") String source_in_url, @Optional("") String incognito){
        this.source_in_url = source_in_url;
        this.incognito = Boolean.parseBoolean(incognito);
    }

    @BeforeClass
    public void clearingPageObject(){
        clearPage();
    }

    @BeforeTest
    public void settingUpDriver(){
        getDriver();
    }

    protected boolean checkAssetsVersion(List<Asset> assets){
        for (Asset asset: getPage().getAssets()) {

            //We should check only self-hosted assets
            Boolean checkFlag = false;
            Pattern pattern_domain = Pattern.compile("^(?:https?:\\/\\/)?(?:[^@\\n]+@)?(?:www\\.)?([^:\\/\\n]+)");
            Matcher matcher_domain = pattern_domain.matcher(asset.getUrl());
            if (matcher_domain.find()) {
                String domain = matcher_domain.group();
                //We should check only self-hosted assets
                if(domain.contains("zurple.com") || domain.contains("zengtest")){
                    checkFlag = true;
                }

            }


            //We shouln't check ckeditor dynamically loaded assets
            Pattern pattern_ckeditor = Pattern.compile("\\/ckeditor\\/");
            Matcher matcher_ckeditor = pattern_ckeditor.matcher(asset.getUrl());


            Pattern pattern_version = Pattern.compile("\\?v=\\d+$");
            Matcher matcher_version = pattern_version.matcher(asset.getUrl());

            if(
                    checkFlag &&
                    !matcher_ckeditor.find() &&
                    !matcher_version.find()
            ){
                return false;
            }
        }
        return true;
    }

}
