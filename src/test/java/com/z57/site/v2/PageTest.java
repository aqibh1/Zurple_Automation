package com.z57.site.v2;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import resources.AbstractPageTest;
import resources.interfaces.TestHavingHeader;
import resources.interfaces.UsingPage;
import resources.orm.hibernate.models.AbstractLead;
import resources.orm.hibernate.models.z57.Lead;

import java.util.UUID;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public abstract class PageTest extends AbstractPageTest  implements UsingPage, TestHavingHeader
{

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

    @Test
    public void testRegisterNewRegularLead() {
        assertEquals("Sign In",getPage().getUserMenu().getText());

        getPage().getUserMenu().click();
        assertTrue(getPage().checkBootsrapModalIsShown());

        String username = "test_regular_lead_" + UUID.randomUUID().toString();
        String email = username + "@test.com";
        String phone = "(212) 435-8762";

        getPage().getRegisterForm().setInputValue("top_bar_lead_reg_name",username);
        getPage().getRegisterForm().setInputValue("top_bar_lead_reg_email",email);
        getPage().getRegisterForm().setInputValue("top_bar_lead_reg_phone",phone);
        getPage().getRegisterForm().submit();
        //Browser will be redirected
        // And user info will be shown
        Wait<WebDriver> wait = new WebDriverWait(getDriver(), 10, 1000);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"user_menu_u\"]/div[contains(concat(\" \",normalize-space(@class),\" \"),\" menu_user_picture \")]")));

        Cookie cks = getDriver().manage().getCookieNamed("zfs_lead_id");
        Integer lead_id = Integer.parseInt(cks.getValue());

        //Checking created lead source
        //Checking DB record body
        AbstractLead newLead = getEnvironment().getLeadObject(lead_id);
        assertEquals(email,newLead.getEmail());

    }

}
