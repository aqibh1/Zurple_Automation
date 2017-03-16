package resources;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
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

public abstract class AbstractPageTest
{

    protected static WebDriver driver;
    protected static TestEnvironment environment;
    protected AbstractPage page;
    protected static String source_in_url="";

    public abstract AbstractPage getPage();

    public abstract void clearPage();

    public static WebDriver getDriver(){
        if(driver == null){
            driver = new ChromeDriver();
        }
        return driver;
    }

    @Parameters("source_in_url")
    @BeforeTest
    public void globalSetUp(@Optional("") String source_in_url){
        this.source_in_url = source_in_url;
    }

    @BeforeClass
    public void clearingPageObject(){
        clearPage();
    }

    @BeforeTest
    public void settingUpDriver(){
        getDriver();
    }

    public static TestEnvironment getEnvironment(){
        if(environment == null){
            environment = new TestEnvironment();
            environment.setAgentToCheck(4);
        }
        return environment;
    }

    public static void setEnvironment( TestEnvironment object){
        environment = object;
    }

    protected boolean checkAssetsVersion(List<Asset> assets){
        for (Asset asset: getPage().getAssets()) {

            //We should chekc only self-hosted assets
            Pattern pattern_host = Pattern.compile("zurple\\.com");
            Matcher matcher_host = pattern_host.matcher(asset.getUrl());

            //We shouln't check ckeditor dynamically loaded assets
            Pattern pattern_ckeditor = Pattern.compile("\\/ckeditor\\/");
            Matcher matcher_ckeditor = pattern_ckeditor.matcher(asset.getUrl());


            Pattern pattern_version = Pattern.compile("\\?v=\\d+$");
            Matcher matcher_version = pattern_version.matcher(asset.getUrl());

            if(
                    matcher_host.find() &&
                    !matcher_ckeditor.find() &&
                    !matcher_version.find()
            ){
                System.out.println(asset.getUrl());
                System.out.println(matcher_ckeditor.find());
                return false;
            }
        }
        return true;
    }

}
