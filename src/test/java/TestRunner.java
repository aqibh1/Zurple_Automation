import com.google.common.base.Charsets;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.LogManager;
import org.apache.commons.io.IOUtils;
import org.openqa.selenium.remote.ProtocolHandshake;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import org.testng.collections.Lists;
import org.testng.log4testng.Logger;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

/**
 * todo
 *
 * @author Vladimir
 */
public class TestRunner
{

    public static void main(String[] args) {

        List<String> suites = Lists.newArrayList();

        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);

        try
        {

            List<String> files = IOUtils.readLines(TestRunner.class.getClassLoader()
                    .getResourceAsStream("TestSuites/zengtest1/"), Charsets.UTF_8);
            for(String f:files){

                TestNG testng = new TestNG();
                suites.add("src/test/resources/TestSuites/zengtest1/"+f);
                testng.setTestSuites(suites);
                testng.run();
                suites.clear();

            }

            files = IOUtils.readLines(TestRunner.class.getClassLoader()
                    .getResourceAsStream("TestSuites/zurple/"), Charsets.UTF_8);
            for(String f:files){

                TestNG testng = new TestNG();
                suites.add("src/test/resources/TestSuites/zurple/"+f);
                testng.setTestSuites(suites);
                testng.run();
                suites.clear();

            }

        }
        catch (IOException e) {}

    }

}
