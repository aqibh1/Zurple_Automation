package com.zurple.selenium;

import junit.framework.TestCase;
import org.junit.AfterClass;
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
        assertEquals(getPage().getTitle(), "San Diego Homes for Sale | zengtest1.us");
    }

    public void testHeader() {
        assertEquals(getPage().getHeader().getText(), "7,483 HOMES FOR SALE IN SAN DIEGO, CA AND NEARBY");
    }

    public void testBrand() {
        assertEquals(getPage().getBrand().getText(), "ZENG TEST PROPERTIES");
    }

}
