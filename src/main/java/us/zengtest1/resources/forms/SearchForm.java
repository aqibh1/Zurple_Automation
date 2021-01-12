package us.zengtest1.resources.forms;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import resources.elements.Select2Dropdown;

public class SearchForm
        extends resources.forms.RegisterForm
{

    Select2Dropdown featuresInput;
    Select2Dropdown stylesInput;
    Select2Dropdown viewsInput;

    public void expand()
    {
        form.findElement(By.xpath("./descendant::*[@id=\"expand-search-fields\"]")).click();
        Wait<WebDriver> wait = new WebDriverWait(getDriver(), 10, 1000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(concat(\" \",normalize-space(@class),\" \"),\" hideShow \")]")));

        //Workaround
        try {
            Thread.sleep(1000);
        } catch (InterruptedException qq) {
            qq.printStackTrace();
        }

    }

    public Select2Dropdown getFeaturesInput() {

        if (featuresInput == null){
            WebElement element = form.findElement(By.xpath("//descendant::select[@id=\"feature\"]/following-sibling::span"));
            featuresInput = new Select2Dropdown();
            featuresInput.setDriver(getDriver());
            featuresInput.setElement(element);
        }

        return featuresInput;
    }

    public Select2Dropdown getStylesInput() {

        if (stylesInput == null){
            WebElement element = form.findElement(By.xpath("//descendant::select[@id=\"style\"]/following-sibling::span"));
            stylesInput = new Select2Dropdown();
            stylesInput.setDriver(getDriver());
            stylesInput.setElement(element);
        }

        return stylesInput;
    }

    public Select2Dropdown getViewsInput() {

        if (viewsInput == null){
            WebElement element = form.findElement(By.xpath("//descendant::select[@id=\"view\"]/following-sibling::span"));
            viewsInput = new Select2Dropdown();
            viewsInput.setDriver(getDriver());
            viewsInput.setElement(element);
        }

        return viewsInput;
    }
}
