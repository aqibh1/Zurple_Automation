package us.zengtest1;

import org.openqa.selenium.By;
import resources.AbstractPage;
import resources.ConfigReader;
import resources.alerts.SweetAlertNotification;
import resources.interfaces.HasHeader;

/**
 * todo
 *
 * @author Vladimir
 */
public abstract class Page extends AbstractPage implements HasHeader
{

    private String baseUrl = null;

    protected String getBaseUrl(){
        if (baseUrl == null){
            ConfigReader configReader = ConfigReader.load();
            baseUrl = configReader.getPropertyByName("site_base_url");
        }
        return baseUrl;
    }

    private SweetAlertNotification sweetAlertNotification;
    
    public SweetAlertNotification getSweetAlertNotification(){
        if(null == sweetAlertNotification){
            sweetAlertNotification = new SweetAlertNotification();
            sweetAlertNotification.setAlert(driver.findElement(
                    By.xpath(SweetAlertNotification.alertXpath)));
        }
        return sweetAlertNotification;
    }
    
}
