package com.zurple.my;

import com.zurple.my.resources.blocks.AdminProductsBlock;
import com.zurple.my.resources.blocks.TransactionListBlock;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

public class BillingPage
        extends Page
{

    AdminProductsBlock adminProductsBlock;
    TransactionListBlock transactionListBlock;

    public BillingPage(){
        url = "/billing/";
    }

    public String getHeader(){
        return driver.findElement(By.xpath("/html/body/div[2]/div[1]/h3")).getText();
    }

    public boolean checkProdcutsBlock(){
        try{
            getProdcutsBlock();
            return true;
        }catch(StaleElementReferenceException e){
            return false;
        }
    }

    public AdminProductsBlock getProdcutsBlock(){
        adminProductsBlock = new AdminProductsBlock();
        adminProductsBlock.setDriver(driver);
        adminProductsBlock.setBlock(driver.findElement(By.xpath("//*[@id=\"products-grid\"]")));
        return adminProductsBlock;
    }

    public boolean checkTransactionListBlock(){
        try{
            getTransactionListBlock();
            return true;
        }catch(StaleElementReferenceException e){
            return false;
        }
    }

    public TransactionListBlock getTransactionListBlock(){
        transactionListBlock = new TransactionListBlock();
        transactionListBlock.setDriver(driver);
        transactionListBlock.setBlock(driver.findElement(By.xpath("//*[@id=\"DataTables_Table_0_wrapper\"]")));
        return transactionListBlock;
    }
}
