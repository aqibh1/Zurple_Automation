package resources.models;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import resources.AbstractTest;
import resources.SSHConnector;
import resources.orm.hibernate.dao.ManageUser;
import resources.orm.hibernate.models.SessionUser;
import resources.orm.hibernate.models.User;
import resources.orm.hibernate.models.UserAlert;

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
    @Parameters({"status_expected","ignore_automation"})
    public void testLastUserStatus(@Optional("") String status_expected,@Optional("0") Integer ignore_automation){
        User lastRegisteredUser = getEnvironment().getLastRegisteredUser();
        assertEquals(lastRegisteredUser.getUserStatus(),status_expected);

        if (ignore_automation != 0)
        {
            assertEquals(lastRegisteredUser.getUserStatusChanges().get(0).getIgnoreAutomation(),ignore_automation);
        }

    }

    @Test
    @Parameters({"status_initial"})
    public void testPrepareUserBecomesProspect1(@Optional("") String status_initial){
        User lastRegisteredUser = getEnvironment().getLastRegisteredUser();
        
        Date d = new Date();
        d.setTime(d.getTime() - 31L * 24 * 60 * 60 * 1000);
        lastRegisteredUser.setCreateDatetime(d);
        lastRegisteredUser.setUserStatus(status_initial);
        lastRegisteredUser.save();

        List<SessionUser> sessions = getEnvironment().getUserSessions(lastRegisteredUser);
        for (SessionUser session: sessions) {
            session.setSessionEnd(d);
            session.save();
        }
        
        Set<UserAlert> alerts = lastRegisteredUser.getUserAlerts();
        for (UserAlert alert: alerts) {
            alert.setUserAlertTriggered(d);
            alert.save();
        }
                
    }

    @Test
    @Parameters({"status_initial"})
    public void testPrepareUserBecomesProspect2(@Optional("") String status_initial){
        
        User lastRegisteredUser = getEnvironment().getLastRegisteredUser();
        
        lastRegisteredUser.setUserStatus(status_initial);
        lastRegisteredUser.save();

        String res = SSHConnector.runRemoteScript("cd /workroot/platform/trunk && cd php/src/main/php/application/scripts && echo '{\"adminId\":"+lastRegisteredUser.getAdminId().getId()+",\"userId\":"+lastRegisteredUser.getId()+",\"status\":\"prospect2\"}' | sudo -u www-data php ChangeUserStatus.php -e dev");
                
    }

    @Test
    @Parameters({"status_initial"})
    public void testPrepareUserBecomesActive1(@Optional("") String status_initial){
        
        User lastRegisteredUser = getEnvironment().getLastRegisteredUser();

        Date d = new Date();
        d.setTime(d.getTime() - 31L * 24 * 60 * 60 * 1000);
        lastRegisteredUser.setCreateDatetime(d);
        lastRegisteredUser.setUserStatus(status_initial);
        lastRegisteredUser.save();
                        
    }
        
    @Test
    @Parameters({"status_initial"})
    public void testPrepareUserBecomesActive2(@Optional("") String status_initial){
        
        User lastRegisteredUser = getEnvironment().getLastRegisteredUser();

        Date d = new Date();
        d.setTime(d.getTime() - 31L * 24 * 60 * 60 * 1000);
        lastRegisteredUser.setCreateDatetime(d);
        lastRegisteredUser.setUserStatus(status_initial);
        lastRegisteredUser.save();
                        
    }
}
