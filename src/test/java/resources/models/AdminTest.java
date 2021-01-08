package resources.models;

import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import resources.AbstractTest;
import resources.orm.hibernate.models.zurple.Admin;
import resources.orm.hibernate.models.zurple.User;

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
