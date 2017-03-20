package com.zurple.my.resources.blocks;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import resources.classes.AdminProduct;

public class AdminProductsBlock
        extends resources.blocks.AbstractBlock
{
    public List<AdminProduct> getAdminProductsList(){
        try{
            Wait<WebDriver> wait = new WebDriverWait(getDriver(), 10, 1000);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"products-grid\"]/table/tbody/tr[1]")));

            List<WebElement> allProductsRows = block.findElements(By.xpath("//*[@id=\"products-grid\"]/table/tbody/tr"));
            List<AdminProduct> list = new ArrayList<AdminProduct>();
            for (WebElement row: allProductsRows) {
                AdminProduct adminProduct = new AdminProduct();
                adminProduct.setDisplayName(row.findElement(By.xpath("./td[1]")).getText());
                adminProduct.setFee(new BigDecimal(row.findElement(By.xpath("./td[2]")).getText().replaceAll("\\$", "")));
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                try
                {
                    adminProduct.setNextBillDate(df.parse(row.findElement(By.xpath("./td[3]")).getText()));
                }
                catch (ParseException e) {}
                catch(NoSuchElementException e){}
                list.add(adminProduct);
            }
            return list;
        }catch (StaleElementReferenceException e) {
            List<AdminProduct> emptyList = new ArrayList<AdminProduct>();
            return emptyList;
        } catch( TimeoutException e )
        {
            List<AdminProduct> emptyList = new ArrayList<AdminProduct>();
            return emptyList;
        }
    }

}
