package resources.TestSuites;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import resources.AbstractPageTest;

//show the use of @BeforeSuite and @BeforeTest
public class TestConfig {

    @BeforeSuite
    public void testBeforeSuite() {
        System.out.println("testBeforeSuite()");
    }

    @AfterSuite
    public void testAfterSuite() {
        System.out.println("testAfterSuite()");
        AbstractPageTest.getDriver().close();
    }

    @BeforeTest
    public void testBeforeTest() {
        System.out.println("testBeforeTest()");
    }

    @AfterTest
    public void testAfterTest() {
        System.out.println("testAfterTest()");
    }

}
