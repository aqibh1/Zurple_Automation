package com.zurple.selenium;

import junit.framework.TestCase;
import org.junit.AfterClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class HomePageTest extends TestCase
{

    private static WebDriver driver;
    private static HomePage page;

    protected static WebDriver getDriver(){
        if(driver == null){
            driver = new FirefoxDriver();
        }
        return driver;
    }

    protected static HomePage getPage(){
        if(page == null){
            page = new HomePage();
            page.setDriver(getDriver());
        }
        return page;
    }

    @AfterClass
    public static void cleanup(){
        getDriver().quit();
    }

    public void testTitle() {
        assertEquals("San Diego Homes for Sale | zengtest1.us", getPage().getTitle());
    }

    public void testHeader() {
        assertEquals("7,483 HOMES FOR SALE IN SAN DIEGO, CA AND NEARBY", getPage().getHeader().getText());
    }

    public void testBrand() {
        assertEquals("ZENG TEST PROPERTIES", getPage().getBrand().getText());
    }

    public void testTopMenu() {
        assertEquals("",getPage().getTopMenu().findElement(By.xpath("//li[1]/a")).getText());
        assertEquals("SEARCH",getPage().getTopMenu().findElement(By.xpath("//li[2]/a")).getText());
        assertEquals("REAL ESTATE NOTES",getPage().getTopMenu().findElement(By.xpath("//li[3]/a")).getText());
        assertEquals("SOLD HOMES",getPage().getTopMenu().findElement(By.xpath("//li[4]/a")).getText());
        assertEquals(" LOG IN",getPage().getTopMenu().findElement(By.xpath("//li[5]/a")).getText());
    }

}
