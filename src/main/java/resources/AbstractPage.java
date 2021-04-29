package resources;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

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
    protected String url;

    public void setDriver(WebDriver driver){
        this.driver=driver;
        if(!this.driver.getCurrentUrl().equals(getFullUrl())){
            driver.get(getFullUrl());
        }
        focusOnPage();
    }
    public void setDriver(WebDriver driver, String pIdxUrl){
        this.driver=driver;
        driver.get(pIdxUrl);
        focusOnPage();
    }
    
    public void setDriver(WebDriver driver, String pIdxUrl,boolean variable){
        this.driver=driver;
        driver.get(getFullUrl());
        focusOnPage();
    }
    public WebDriver getWebDriver() {
    	return driver;
    }
    protected abstract String getBaseUrl();

    public String getCurrentUrl(){
        return this.driver.getCurrentUrl();
    }

    public List<Asset> getAssets(){

        ArrayList<Asset> list = new ArrayList<Asset>();

        for (WebElement style: driver.findElements(By.xpath("/html/descendant::link[@type='text/css']"))) {
            Asset asset = new Asset();
            asset.setType(style.getAttribute("type"));
            asset.setUrl(style.getAttribute("href"));
            list.add(asset);
        }

        for (WebElement js: driver.findElements(By.xpath("/html/descendant::script"))) {
            Asset asset = new Asset();
            asset.setType(js.getAttribute("type"));
            asset.setUrl(js.getAttribute("src"));
            list.add(asset);
        }

        return list;
    };

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String u){
        url = u;
    }

    public String getFullUrl()
    {
        return getBaseUrl() + url;
    }

    public String getTitle(){
        return driver.getTitle();
    }

    public void pressButton(Keys key){
        pressButton(key,0);
    }

    public void focusOnPage()
    {
        String currentWindow = driver.getWindowHandle();
        driver.switchTo().window(currentWindow);
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
    // Month/Day/Year
    public String getTodaysDate() {
		String lDate = "";
		LocalDate today = LocalDate.now();
		String tempDate[] = today.toString().split("-");
		lDate = tempDate[1]+"/"+tempDate[2]+"/"+tempDate[0];
		return lDate;
	}
    
    // Month/Day/Year(2 digits)
    public String getYesterdaysDate() {
    	 Calendar cal = Calendar.getInstance();
    	 DateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
    	 cal.add(Calendar.DATE, -1);
    	 return dateFormat.format(cal.getTime());
	}
    //"MM/dd/yy"
    public String getTodaysDate(String pFormat) {
   	 Calendar cal = Calendar.getInstance();
   	 DateFormat dateFormat = new SimpleDateFormat(pFormat);
   	 return dateFormat.format(cal.getTime());
	}
    protected static int generateRandomInt(int pUpperRange){
    	Random random = new Random();
    	int lNum = random.nextInt(pUpperRange);
    	if(lNum==pUpperRange) {
    		lNum = lNum - 1;
    	}
    	return lNum;
}
    protected String getCuurentTime() {
    	SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm");
    	String formattedDate = dateFormat.format(new Date(System.currentTimeMillis())).toString().toLowerCase();
    	return formattedDate;
    	
    }
}
