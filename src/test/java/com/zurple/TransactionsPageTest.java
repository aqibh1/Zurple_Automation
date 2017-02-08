package com.zurple;

import java.text.SimpleDateFormat;
import java.util.List;
import org.testng.annotations.Test;
import resources.orm.hibernate.models.AdminProduct;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class TransactionsPageTest
        extends PageTest
{
    private static TransactionsPage page;

    public TransactionsPage getPage(){
        if(page == null){
            page = new TransactionsPage();
            page.setDriver(getDriver());
        }
        return page;
    }

    @Test
    public void testProducts(){

        assertTrue(getPage().checkProdcutsBlock());
        List<AdminProduct> expectedAdminProducts = getEnvironment().getProductsList();
        List<resources.classes.AdminProduct> parsedAdminProducts = getPage().getProdcutsBlock().getAdminProductsList();

        assertEquals(expectedAdminProducts.size(),parsedAdminProducts.size());

        for (AdminProduct adminProduct:  expectedAdminProducts) {
            for (resources.classes.AdminProduct parsedAdminProduct: parsedAdminProducts){
                assertEquals(adminProduct.getProductId().getDisplayName(),parsedAdminProduct.getDisplayName());
                assertEquals(adminProduct.getFee(),parsedAdminProduct.getFee());

                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                assertEquals(df.format(adminProduct.getNextBillDate()),df.format(parsedAdminProduct.getNextBillDate()));
            }
        }

    }
}
