package resources.alerts;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BootstrapModal extends AbstractAlert
{

    public static String alertXpath = "//div[contains(@class,\"modal fade in\")]";

    public void close() {
        alert.findElement(By.xpath("./descendant::button[@class=\"close\"]")).click();

        List<WebElement> els = new ArrayList<>();
        els.add(alert);

        Wait<WebDriver> wait = new WebDriverWait(driver, 10, 1000);
        wait.until(ExpectedConditions.invisibilityOfAllElements(els));

    }

}
