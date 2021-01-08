package resources.elements;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Select2Dropdown
{
    private WebElement element;
    private WebDriver driver;
    private WebElement variants_container;

    public void openVariantsContainer(){
        element.click();

        Wait<WebDriver> wait = new WebDriverWait(driver, 10, 1000);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//body/span//ul/li"),1));

        this.variants_container = driver.findElement(By.xpath("//body/span//ul"));

    }

    public List<String> getVariants(){

        openVariantsContainer();

        List<WebElement> options = this.variants_container.findElements(By.xpath("./li"));
        List<String> results = new ArrayList<String>();
        for (WebElement option: options)
        {
            results.add(option.getText());
        }

        return results;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public void selectValue(String value) {
        this.variants_container.findElement(By.xpath("./li[contains(text(),\"" + value + "\")]")).click();
    }

    public void setElement(WebElement element) {
        this.element = element;
    }
}
