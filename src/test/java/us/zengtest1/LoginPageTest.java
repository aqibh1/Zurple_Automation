package us.zengtest1;

public class LoginPageTest
        extends AbstractPageTest
{

    private static LoginPage page;

    public AbstractPage getPage(){
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


    public void testBrand() {
        assertEquals("ZENG\n"
                + "TEST\n"
                + "PROPERTIES", getPage().getBrand().getText());
    }

}
