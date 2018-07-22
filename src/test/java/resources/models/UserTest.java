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
    @Parameters({"status_expected"})
    public void testLastUserStatus(@Optional("") String status_expected){
        User lastRegisteredUser = getEnvironment().getLastRegisteredUser();
        assertEquals(lastRegisteredUser.getUserStatus(),status_expected);
    }
    
    @Test
    public void testNewUserBecomesActive1(){
        User lastRegisteredUser = getEnvironment().getLastRegisteredUser();
        
        Date d = new Date();
        d.setTime(d.getTime() - 31L * 24 * 60 * 60 * 1000);
        lastRegisteredUser.setCreateDatetime(d);
        lastRegisteredUser.save();
                        
    }
    
    @Test
    public void testNewUserBecomesProspect1(){
        User lastRegisteredUser = getEnvironment().getLastRegisteredUser();
        
        Date d = new Date();
        d.setTime(d.getTime() - 31L * 24 * 60 * 60 * 1000);
        lastRegisteredUser.setCreateDatetime(d);
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
    public void testProspect1UserBecomesProspect2(){
        
        User lastRegisteredUser = getEnvironment().getLastRegisteredUser();
        
        lastRegisteredUser.setUserStatus("prospect1");
        lastRegisteredUser.save();

        String res = SSHConnector.runRemoteScript("cd /workroot/platform/trunk && cd php/src/main/php/application/scripts && echo '{\"adminId\":"+lastRegisteredUser.getAdminId().getId()+",\"userId\":"+lastRegisteredUser.getId()+",\"status\":\"prospect2\"}' | sudo -u www-data php ChangeUserStatus.php -e dev");
        res = res;
                
    }

}
