package us.zengtest1;

public class LoginPageTest
        extends HomePageTest
{

    private static LoginPage page;

    protected static AbstractPage getPage(){
        if(page == null){
            page = new LoginPage();
            page.setDriver(getDriver());
        }
        return page;
    }

    public void testHeader() {
        assertEquals("MEMBER LOGIN", getPage().getHeader().getText());
    }

    public void testTitle() {
        assertEquals("Login | zengtest1.us", getPage().getTitle());
    }

}
