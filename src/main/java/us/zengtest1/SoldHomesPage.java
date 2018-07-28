package us.zengtest1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import us.zengtest1.resources.forms.SearchForm;

public class SoldHomesPage
        extends Page
{

    public SoldHomesPage(){
        url = "/sold-homes";
    }

    //TODO not standart page layout
    public WebElement getHeader(){
        return driver.findElement(By.xpath("//*[@id=\"wrap\"]/div[1]/div/div/div[1]/div/h3"));
    }

    public WebElement getBrand(){
        return driver.findElement(By.xpath("//*[@id=\"wrap\"]/nav/div/div[1]/a"));
    }

    public WebElement getTopMenu(){
        return driver.findElement(By.xpath("//*[@id=\"wrap\"]/nav/div/div[2]/ul"));
    }

}
