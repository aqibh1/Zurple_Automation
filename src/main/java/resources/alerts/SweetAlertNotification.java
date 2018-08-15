package resources.alerts;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class SweetAlertNotification extends AbstractAlert
{

    public static String alertXpath = "//div[contains(@class,\"sweet-alert\")]";
    
    public void setAlert(WebElement object) {
        alert = object;
        try {
            okButton = alert.findElement(By.xpath("./descendant::button[contains(@class,\"confirm\")]"));
            cancelButton = alert.findElement(By.xpath("./descendant::button[contains(@class,\"cancel\")]"));
            message = alert.findElement(By.xpath("./descendant::h2")).getText();
        } catch (NoSuchElementException e) {
        }
    }
}
