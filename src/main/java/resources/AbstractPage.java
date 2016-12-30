package resources;

import org.openqa.selenium.WebDriver;
import resources.interfaces.HavingHeader;

public abstract class AbstractPage implements HavingHeader
{
    protected WebDriver driver;
    protected String url;

    public void setDriver(WebDriver driver){
        this.driver=driver;
        driver.get(getUrl());
    }

    public String getUrl()
    {
        return url;
    }

    public String getTitle(){
        return driver.getTitle();
    }

}
