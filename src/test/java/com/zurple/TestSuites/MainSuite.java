package com.zurple.TestSuites;

import com.zurple.DashboardPageTest;
import com.zurple.LeadDetailPageTest;
import com.zurple.LoginPageTest;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Suite;
import resources.AbstractPageTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        LoginPageTest.class,
        DashboardPageTest.class,
        LeadDetailPageTest.class
})

public class MainSuite {

    @AfterClass
    public static void tearDown() {
        AbstractPageTest.getDriver().close();
    }

}
