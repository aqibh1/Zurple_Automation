package us.zengtest1;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import resources.AbstractPage;
import resources.ConfigReader;
import resources.alerts.BootstrapModal;
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
            baseUrl = configReader.getPropertyByName("zurple_site_base_url");
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

//    protected BootstrapModal bootstrapModal;
//
//    public BootstrapModal getBootstrapModal(){
//        if(null == bootstrapModal){
//            bootstrapModal = new BootstrapModal();
//            bootstrapModal.setAlert(driver.findElement(
//                    By.xpath(BootstrapModal.alertXpath)));
//            bootstrapModal.setDriver(driver);
//        }
//        return bootstrapModal;
//    }
//
//    public boolean checkBootsrapModalIsShown(){
//        try{
//            getBootstrapModal();
//            return bootstrapModal.isVisible();
//        }catch(StaleElementReferenceException e){
//            return false;
//        }catch(NoSuchElementException e){
//            return false;
//        }
//    }
    
}
