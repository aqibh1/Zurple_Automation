package com.zurple.my.resources.blocks;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import resources.classes.Transaction;

public class TransactionListBlock
        extends resources.blocks.AbstractBlock
{
    public List<Transaction> getAdminTransactionsList(){

        List<Transaction> list = new ArrayList<Transaction>();

        try{
            Wait<WebDriver> wait = new WebDriverWait(getDriver(), 10, 1000);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[1]")));

            List<WebElement> allTransactionsRows = block.findElements(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr"));
            for (WebElement row: allTransactionsRows) {
                Transaction transaction = new Transaction();

                transaction.setBillingDateString(row.findElement(By.xpath("./td[2]")).getText());
                transaction.setPaymentDateString(row.findElement(By.xpath("./td[6]")).getText());
                DateFormat df = new SimpleDateFormat("dd/mm/yy");
                try
                {
                    transaction.setBillingDate(df.parse(row.findElement(By.xpath("./td[2]")).getText()));
                    transaction.setPaymentDate(df.parse(row.findElement(By.xpath("./td[6]")).getText()));
                }
                catch (ParseException e) {}

                transaction.setProductName(row.findElement(By.xpath("./td[3]")).getText());
                transaction.setAmount(new BigDecimal(row.findElement(By.xpath("./td[4]")).getText()));
                transaction.setStatus(row.findElement(By.xpath("./td[4]")).getText());

                list.add(transaction);
            }
            return list;
        }catch (StaleElementReferenceException e) {}
        catch( TimeoutException e ) {}
        return list;
    }

    public String getSortingColumnName(){
        return block.findElement(By.xpath("./descendant::th[@aria-sort]")).getText();
    }

    public String getSortingOrder(){
        return block.findElement(By.xpath("./descendant::th[@aria-sort]")).getAttribute("aria-sort");
    }

}
