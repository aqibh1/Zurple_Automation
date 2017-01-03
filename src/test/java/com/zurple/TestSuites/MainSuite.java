package com.zurple.TestSuites;

import com.zurple.DashboardPageTest;
import com.zurple.LoginPageTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        LoginPageTest.class,
        DashboardPageTest.class
})

public class MainSuite {
}
