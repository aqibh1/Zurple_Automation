import java.util.Arrays;
import org.testng.TestNG;

/**
 * todo
 *
 * @author Vladimir
 */
public class AllTestRunner
{
    public static void main(String[] args) {

        TestNG tng = new TestNG();
        tng.setXmlSuites(Arrays.asList(suite));
        tng.run();
    }
}
