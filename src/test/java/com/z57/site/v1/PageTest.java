/**
 * 
 */
package com.z57.site.v1;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import resources.AbstractPageTest;
import resources.alerts.BootstrapModal;
import resources.interfaces.TestHavingHeader;
import resources.interfaces.UsingPage;


/**
 * @author adar
 *
 */
public abstract class PageTest extends AbstractPageTest  implements UsingPage, TestHavingHeader{


    @Test(groups = { "asset" })
    public void testAssetsVersions() {
        assertTrue(checkAssetsVersion(getPage().getAssets()));
    }

    public abstract Page getPage();

    @Test
    public void testTopMenu() {
        assertEquals("Home",getPage().getTopMenu().findElement(By.xpath("//li[1]/a")).getText());
        assertEquals("Listings",getPage().getTopMenu().findElement(By.xpath("//li[2]/a")).getText());
        assertEquals("Home Search",getPage().getTopMenu().findElement(By.xpath("//li[3]/a")).getText());
        assertEquals("Our Community",getPage().getTopMenu().findElement(By.xpath("//li[4]/a")).getText());
        assertEquals("Services",getPage().getTopMenu().findElement(By.xpath("//li[5]/a")).getText().trim());
        assertEquals("Real Estate Updates",getPage().getTopMenu().findElement(By.xpath("//li[6]/a")).getText().trim());
    } 
   
    public void closeBootStrapModal() {
    	BootstrapModal bootstrapModalObj = new BootstrapModal(getPage().getWebDriver());

    	if(bootstrapModalObj.checkBootsrapModalIsShown()){
        	bootstrapModalObj.getBootstrapModal().close();
        	bootstrapModalObj.clearBootstrapModal();
        }
    }

	@Override
	public void testTitle() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void testHeader() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void testBrand() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clearPage() {
		// TODO Auto-generated method stub
		
	}
    

}
