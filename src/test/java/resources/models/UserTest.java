package resources.models;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import resources.AbstractTest;
import resources.orm.hibernate.dao.ManageUser;
import resources.orm.hibernate.models.User;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * todo
 *
 * @author Vladimir
 */
public class UserTest extends AbstractTest
{

    @Test
    @Parameters({"status_expected"})
    public void testLastUserStatus(@Optional("") String status_expected){
        User lastRegisteredUser = getEnvironment().getLastRegisteredUser();
        assertEquals(status_expected, lastRegisteredUser.getUserStatus());
    }

}
