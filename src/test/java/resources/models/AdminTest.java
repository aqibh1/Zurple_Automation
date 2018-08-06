package resources.models;

import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import resources.AbstractTest;
import resources.SSHConnector;
import resources.orm.hibernate.models.*;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * todo
 *
 * @author Vladimir
 */
public class AdminTest extends AbstractTest
{

    @Test
    @Parameters({"sign_off","display_name"})
    public void setAdminSignOffAndDisplayName(@Optional("") String sign_off,@Optional("") String display_name){

        User user = getEnvironment().getUserToCheck();
        Admin admin = user.getAdminId();
        admin.setEmailUniqueSignOff(sign_off);
        admin.setEmailDisplayName(display_name);
        admin.save();

    }
}
