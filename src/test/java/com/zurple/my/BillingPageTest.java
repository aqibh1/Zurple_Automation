package com.zurple.my;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.testng.annotations.Test;
import resources.orm.hibernate.models.PackageProduct;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class BillingPageTest
        extends PageTest
{
    private BillingPage page;

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

        Date today = new Date();

        assertTrue(getPage().checkProdcutsBlock());
        List<PackageProduct> expectedPackageProducts = getEnvironment().getProductsList();
        List<resources.classes.AdminProduct> parsedAdminProducts = getPage().getProdcutsBlock().getAdminProductsList();

        assertEquals(expectedPackageProducts.size(),parsedAdminProducts.size());

        for (PackageProduct packageProduct : expectedPackageProducts) {

            Boolean match_flag=false;

            for (resources.classes.AdminProduct parsedAdminProduct: parsedAdminProducts){

                Pattern p = Pattern.compile("^\\d{2}\\/\\d{2}\\/\\d{4}$");
                Matcher m = p.matcher(parsedAdminProduct.getNextbillDateString());
                assertTrue(m.find());

                SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");

                if(
                    packageProduct.getProductId().getDisplayName().equals(parsedAdminProduct.getDisplayName()) &&
                    packageProduct.getFee().equals(parsedAdminProduct.getFee()) &&
                    df.format(packageProduct.getNextBillDate()).equals(df.format(parsedAdminProduct.getNextBillDate())) &&
                    today.before(packageProduct.getNextBillDate())
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
