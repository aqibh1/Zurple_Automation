package com.zurple.my;

import com.zurple.my.resources.blocks.AdminProductsBlock;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;

public class TransactionsPage
        extends Page
{

    AdminProductsBlock adminProductsBlock;

    public TransactionsPage(){
        url = "https://my.dev.zurple.com/transactions/";
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
}
