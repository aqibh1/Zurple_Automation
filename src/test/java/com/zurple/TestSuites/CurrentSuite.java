package com.zurple.TestSuites;

import com.zurple.DashboardPageTest;
import com.zurple.LoginPageTest;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
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

public class CurrentSuite
{

    @AfterClass
    public static void tearDown() {
        AbstractPageTest.getDriver().close();
    }

}
