package com.zurple.my;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import resources.elements.Select2Dropdown;
import resources.orm.hibernate.models.zurple.User;

import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertFalse;

/**
 * todo
 *
 * @author Vladimir
 */
public class LeadCreatePageTest extends PageTest
{
    private LeadCreatePage page;

    public LeadCreatePage getPage(){
        if(page == null){
            page = new LeadCreatePage();
            page.setDriver(getDriver());
        }
        return page;
    }

    public void clearPage(){
        page=null;
    };

    @Test
    @Parameters({"email_domain"})
    public void testCreateNewRegularLead(@Optional("") String email_domain){
        String username = "test_regular_lead_" + UUID.randomUUID().toString();
        String email = username + "_zurpleqa@" + email_domain;

        getPage().getLeadCreateForm().setDriver(getDriver());
        getPage().getLeadCreateForm().setInputValue("first_name",username);
        getPage().getLeadCreateForm().setInputValue("email",email);

        Select2Dropdown cityInput = getPage().getLeadCreateForm().getCityInput();
        List<String> cities = cityInput.getVariants();
        assertFalse(cities.isEmpty());

        cityInput.selectValue(cities.get(0));

        getPage().getLeadCreateForm().toggleWelcomeEmail();

        getPage().getLeadCreateForm().submit();
        // We must be redirected
        Pattern pattern = Pattern.compile("/lead/index/user_id/(\\d+)");
        Matcher matcher = pattern.matcher(getDriver().getCurrentUrl());
        assertTrue(matcher.find());

        Integer user_id = Integer.parseInt(matcher.group(1));

        //Waiting for redirect to search page
        WebDriverWait wait = new WebDriverWait(getDriver(), 10); //seconds
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[contains(text(),\"Lead Detail\")]")));

        //Checking created lead source
        //Checking DB record body
        User newUser = getEnvironment().getUserById(user_id);
        getEnvironment().setUserToCheck(newUser);
    }
}
