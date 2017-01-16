package resources;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import resources.classes.Asset;

public abstract class AbstractPage
{
    protected WebDriver driver;
    protected static String url;

    public void setDriver(WebDriver driver){
        this.driver=driver;
        if(this.driver.getCurrentUrl()!=getUrl()){
            driver.get(getUrl());
        }
        focusOnPage();
    }

    public List<Asset> getAssets(){

        ArrayList<Asset> list = new ArrayList<Asset>();

        for (WebElement style: driver.findElements(By.xpath("/html/head/link[@type='text/css']"))) {
            Asset asset = new Asset();
            asset.setType(style.getAttribute("type"));
            asset.setUrl(style.getAttribute("href"));
            list.add(asset);
        }

        for (WebElement js: driver.findElements(By.xpath("/html/head/script"))) {
            Asset asset = new Asset();
            asset.setType(js.getAttribute("type"));
            asset.setUrl(js.getAttribute("src"));
            list.add(asset);
        }

        return list;
    };

    public static String getUrl()
    {
        return url;
    }

    public void setUrl(String u){
        url = u;
    }

    public String getTitle(){
        return driver.getTitle();
    }

    public void pressButton(Keys key){
        pressButton(key,0);
    }

    public void focusOnPage()
    {
        driver.findElement(By.xpath("/html/body")).click();
    }

    public void pressButton(Keys key, int timeout)
    {
        Actions builder = new Actions(driver);
        Action button= builder
                .sendKeys(key)
                .build();
        button.perform();

        try {
            Thread.sleep(timeout);
        } catch (InterruptedException qq) {
            qq.printStackTrace();
        }
    }
}
