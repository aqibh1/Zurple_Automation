package com.zurple.my;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import resources.orm.hibernate.models.AdminProduct;
import resources.orm.hibernate.models.Transaction;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class BillingPageTest
        extends PageTest
{
    private static BillingPage page;

    public BillingPage getPage(){
        if(page == null){
            page = new BillingPage();
            page.setDriver(getDriver());
        }
        return page;
    }


    @Test
    public void testHeader()
    {
        assertEquals(getPage().getHeader(),"Billing");
    }

    public void clearPage(){
        page=null;
    };

    @Test
    public void testProducts(){

        assertTrue(getPage().checkProdcutsBlock());
        List<AdminProduct> expectedAdminProducts = getEnvironment().getProductsList();
        List<resources.classes.AdminProduct> parsedAdminProducts = getPage().getProdcutsBlock().getAdminProductsList();

        assertEquals(expectedAdminProducts.size(),parsedAdminProducts.size());

        for (AdminProduct adminProduct:  expectedAdminProducts) {

            Boolean match_flag=false;
            for (resources.classes.AdminProduct parsedAdminProduct: parsedAdminProducts){

                Pattern p = Pattern.compile("^\\d{2}\\/\\d{2}\\/\\d{4}$");
                Matcher m = p.matcher(parsedAdminProduct.getNextbillDateString());
                assertTrue(m.find());

                SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");

                if(
                    adminProduct.getProductId().getDisplayName().equals(parsedAdminProduct.getDisplayName()) &&
                    adminProduct.getFee().equals(parsedAdminProduct.getFee()) &&
                    df.format(adminProduct.getNextBillDate()).equals(df.format(parsedAdminProduct.getNextBillDate()))
                ){
                    match_flag=true;
                }
            }
            assertTrue(match_flag);
        }

    }

    @Test
    public void testTransactionsList(){

        assertTrue(getPage().checkTransactionListBlock());
        List<resources.classes.Transaction> parsedTransactions = getPage().getTransactionListBlock().getAdminTransactionsList();

        assertTrue(parsedTransactions.size()>0);

        for (resources.classes.Transaction transaction: parsedTransactions){
            Pattern p = Pattern.compile("^\\d{2}\\/\\d{2}\\/\\d{4}$");
            Matcher m = p.matcher(transaction.getBillingDateString());
            assertTrue(m.find());
            m = p.matcher(transaction.getPaymentDateString());
            assertTrue(m.find());
        }
    }

    @Test
    public void testTransactionsListSorting(){
        assertTrue(getPage().checkTransactionListBlock());

        assertEquals(getPage().getTransactionListBlock().getSortingColumnName(),"Billing Date");
        assertEquals(getPage().getTransactionListBlock().getSortingOrder(),"descending");
    }
}
