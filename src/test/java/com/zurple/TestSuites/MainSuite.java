package com.zurple.TestSuites;

import com.zurple.*;
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
        //LeadDetailPageTest.class,
        //LeadEditPageTest.class,
        //LeadListPageTest.class,
        //AgentsPageTest.class,
        //AgentPageTest.class,
        //MarketingMessemailPageTest.class,
        //MarketingTextmessagePageTest.class,
        //StatisticsPageTest.class,
        //SupportPageTest.class
        //TransactionsPageTest.class
})

public class MainSuite {

    @AfterClass
    public static void tearDown() {
        AbstractPageTest.getDriver().close();
    }

}
