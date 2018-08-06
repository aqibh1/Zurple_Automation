package resources.models;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import resources.AbstractTest;
import resources.SSHConnector;
import resources.orm.hibernate.dao.ManageUser;
import resources.orm.hibernate.models.*;

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
        User user = getEnvironment().getUserToCheck();
        assertEquals(user.getUserStatus(),status_expected);

        if (ignore_automation != 0)
        {
            assertEquals(user.getUserStatusChanges().get(0).getIgnoreAutomation(),ignore_automation);
        }

    }

    @Test
    public void testWelcomeEmailSignOff(){
        User user = getEnvironment().getUserToCheck();
        Email lastEmail = user.getEmails().iterator().next();
        assertEquals(lastEmail.getEmailType(),"auto_responder");
        assertEquals(lastEmail.getSubject(),"Quick Question");

        String pattern = "";
        if(user.getAdminId().getEmailUniqueSignOff().isEmpty()){
            pattern = "Respectfuly";
        }else{
            pattern = user.getAdminId().getEmailUniqueSignOff();
        }
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(lastEmail.getBody());
        assertTrue(m.find());

        if(user.getAdminId().getEmailDisplayName().isEmpty()){
            pattern = user.getAdminId().getFirstName();
        }else{
            pattern = user.getAdminId().getEmailDisplayName();
        }
        p = Pattern.compile(pattern);
        m = p.matcher(lastEmail.getBody());
        assertTrue(m.find());
    }

    @Test
    @Parameters({"status_initial"})
    public void testPrepareUserBecomesProspect1(@Optional("") String status_initial){
        User user = getEnvironment().getUserToCheck();
        
        Date d = new Date();
        d.setTime(d.getTime() - 31L * 24 * 60 * 60 * 1000);
        user.setCreateDatetime(d);
        user.setUserStatus(status_initial);
        user.save();

        List<SessionUser> sessions = getEnvironment().getUserSessions(user);
        for (SessionUser session: sessions) {
            session.setSessionEnd(d);
            session.save();
        }
        
        Set<UserAlert> alerts = user.getUserAlerts();
        for (UserAlert alert: alerts) {
            alert.setUserAlertTriggered(d);
            alert.save();
        }
                
    }

    @Test
    @Parameters({"status","ignore_automation"})
    public void testChangeUserStatus(@Optional("") String status,@Optional("0") Integer ignore_automation) {
        User user = getEnvironment().getUserToCheck();
        user.setUserStatus(status);
        if (ignore_automation != 0){
            user.getUserStatusChanges().get(0).setIgnoreAutomation(ignore_automation);
            user.getUserStatusChanges().get(0).save();
        }
        user.save();
    }

    @Test
    @Parameters({"score"})
    public void testChangeUserLeadScore(@Optional("0") Integer score) {
        User user = getEnvironment().getUserToCheck();
        user.setUserLeadScore(score);
        user.save();
    }

        @Test
    @Parameters({"status_initial"})
    public void testPrepareUserBecomesProspect2(@Optional("") String status_initial){

        User user = getEnvironment().getUserToCheck();
        
        user.setUserStatus(status_initial);
        user.save();

        String res = SSHConnector.runRemoteScript("cd /workroot/platform/trunk && cd php/src/main/php/application/scripts && echo '{\"adminId\":"+user.getAdminId().getId()+",\"userId\":"+user.getId()+",\"status\":\"prospect2\"}' | sudo -u www-data php ChangeUserStatus.php -e dev");

        assertEquals(res,"OK");// Check SSH server is running and accessible

    }

    @Test
    @Parameters({"status_initial"})
    public void testPrepareUserBecomesActive1(@Optional("") String status_initial){

        User user = getEnvironment().getUserToCheck();

        Date d = new Date();
        d.setTime(d.getTime() - 31L * 24 * 60 * 60 * 1000);
        user.setCreateDatetime(d);
        user.setUserStatus(status_initial);
        user.save();
                        
    }
        
    @Test
    @Parameters({"status_initial"})
    public void testPrepareUserBecomesActive2(@Optional("") String status_initial){

        User user = getEnvironment().getUserToCheck();

        Date d = new Date();
        d.setTime(d.getTime() - 31L * 24 * 60 * 60 * 1000);
        user.setCreateDatetime(d);
        user.setUserStatus(status_initial);
        user.save();
                        
    }
}
