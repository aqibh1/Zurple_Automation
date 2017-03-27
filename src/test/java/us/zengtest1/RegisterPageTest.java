package us.zengtest1;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import resources.orm.hibernate.models.User;

import static org.testng.Assert.assertEquals;

public class RegisterPageTest
        extends PageTest
{

    private static RegisterPage page;

    public RegisterPage getPage(){
        if(page == null){
            page = new RegisterPage();
            page.setDriver(getDriver());
        }
        return page;
    }

    public void clearPage(){
        page=null;
    };

    @Test(priority=10)
    public void testTitle() {
        assertEquals("Search for Homes in San Diego, CA", getPage().getTitle());
    }

    @Test(priority=10)
    public void testHeader() {
        assertEquals("THE #1 SEARCH ENGINE FOR SAN DIEGO REAL ESTATE", getPage().getHeader().getText());
    }

    @Test(priority=20)
    public void testBrand() {
        assertEquals(getPage().getBrand().getText(), "ZENG TEST PROPERTIES");
    }

    @Test(priority=30)
    public void testSubmittingEmptyRegisterForm(){
        getPage().getRegisterForm().clearFields();
        getPage().getRegisterForm().submit();
        getPage().getRegisterForm().getRequiredInputs();
        assertTrue(getPage().getRegisterForm().getFormErrorMessagesList().isEmpty());
    }

    @Test(priority=40,groups = "register")
    public void testRegisterNewRegularLead(){
        String username = "test_regular_lead_" + UUID.randomUUID().toString();
        String email = username + "_zurpleqa@test.com";
        String phone = "(212) 435-8762";
        getPage().getRegisterForm().setInputValue("first_name",username);
        getPage().getRegisterForm().setInputValue("email",email);
        getPage().getRegisterForm().setInputValue("phone",phone);
        getPage().getRegisterForm().submit();
        // We must be redirected
        // Checking URL, should be like this http://dev.zengtest1.us/thankyou?lead_id=102758
        Pattern pattern = Pattern.compile("http://dev\\.zengtest1\\.us/thankyou\\?lead_id=(\\d+)");
        Matcher matcher = pattern.matcher(getDriver().getCurrentUrl());
        assertTrue(matcher.find());
    }

    @Test(priority=50)
    public void testRegisterNewPersonalLead(){

        clearPage();

        String username = "test_personal_lead_" + UUID.randomUUID().toString();
        String email = username + "_zurpleqa@test.com";
        String phone = "(212) 435-8762";
        getPage().getRegisterForm().setInputValue("first_name",username);
        getPage().getRegisterForm().setInputValue("email",email);
        getPage().getRegisterForm().setInputValue("phone",phone);
        getPage().getRegisterForm().submit();
        // We must be redirected
        // Checking URL, should be like this http://dev.zengtest1.us/thankyou?lead_id=102758
        Pattern pattern = Pattern.compile("http://dev\\.zengtest1\\.us/thankyou\\?lead_id=(\\d+)");
        Matcher matcher = pattern.matcher(getDriver().getCurrentUrl());
        assertTrue(matcher.find());
        Integer lead_id = Integer.parseInt(matcher.group(1));

        //Checking created lead source
        //Checking DB record body
        User newUser = getEnvironment().getUserById(lead_id);

        assertEquals(newUser.getUserFirstName(),username.substring(0, 1).toUpperCase() + username.substring(1));
        assertEquals(newUser.getLeadId().getEmail(),email);
        assertEquals(newUser.getTrafficSource(),"personal");
        assertEquals(newUser.getUserStatus(),"new");


    }

    @Test(priority=60)
    public void testRegisterNewPersonalSellerLead(){

        String username = "test_personal_seller_lead_" + UUID.randomUUID().toString();
        String email = username + "_zurpleqa@test.com";
        String phone = "(212) 435-8762";
        getPage().getRegisterForm().setInputValue("first_name",username);
        getPage().getRegisterForm().setInputValue("email",email);
        getPage().getRegisterForm().setInputValue("phone",phone);
        getPage().getRegisterForm().submit();
        // We must be redirected
        // Checking URL, should be like this http://dev.zengtest1.us/thankyou?lead_id=102758
        Pattern pattern = Pattern.compile("http://dev\\.zengtest1\\.us/thankyou\\?lead_id=(\\d+)");
        Matcher matcher = pattern.matcher(getDriver().getCurrentUrl());
        assertTrue(matcher.find());
        Integer lead_id = Integer.parseInt(matcher.group(1));

        //Checking created lead source
        //Checking DB record body
        User newUser = getEnvironment().getUserById(lead_id);

        assertEquals(newUser.getUserFirstName(),username.substring(0, 1).toUpperCase() + username.substring(1));
        assertEquals(newUser.getLeadId().getEmail(),email);
        assertEquals(newUser.getTrafficSource(),"personal-seller");
        assertEquals(newUser.getUserStatus(),"new");
        assertEquals(newUser.getTransactionGoal().getBuyer(), "1");


    }

}
