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
import resources.ConfigReader;
import resources.SSHConnector;
import resources.orm.hibernate.dao.ManageUser;
import resources.orm.hibernate.models.*;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertFalse;

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
            pattern = "Respectfully,";
        }else{
            pattern = user.getAdminId().getEmailUniqueSignOff();
        }
        assertTrue(lastEmail.getBody().contains(pattern));

        if(user.getAdminId().getEmailDisplayName().isEmpty()){
            pattern = user.getAdminId().getFirstName();
        }else{
            pattern = user.getAdminId().getEmailDisplayName();
        }
        assertTrue(lastEmail.getBody().contains(pattern));

    }

    @Test
    @Parameters({"received"})
    public void testUserReceivedMassEmail(@Optional("") String received){
        User user = getEnvironment().getUserToCheck();
        assertEquals(user.getEmails().size() > 0,Boolean.parseBoolean(received));
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
    public void setUserLastStatusChangeDate30DaysAgo() {

        Date d = new Date();
        d.setTime(d.getTime() - 30L * 24 * 60 * 60 * 1000);

        User user = getEnvironment().getUserToCheck();
        assertFalse(user.getUserStatusChanges().isEmpty());
        user.getUserStatusChanges().get(0).setStatusChangeDatetime(d);
        user.getUserStatusChanges().get(0).save();

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

        ConfigReader configReader = ConfigReader.load();
        User user = getEnvironment().getUserToCheck();
        
        user.setUserStatus(status_initial);
        user.save();

        String res = SSHConnector.runRemoteScript("cd /var/www/zurple.com/current/php/application/scripts && echo '{\"adminId\":"+user.getAdminId().getId()+",\"userId\":"+user.getId()+",\"status\":\"prospect2\"}' | sudo -u www-data php ChangeUserStatus.php -e "+configReader.getPropertyByName("jobs_environment_title"));

        assertEquals(res,"OK");// Check SSH server is running and accessible

    }

    @Test
    public void testUserStatusAutomationIconCacheRebuild(){

        ConfigReader configReader = ConfigReader.load();

        String res = SSHConnector.runRemoteScript("sudo -u www-data bash -c \"export SERVER_NAME="+configReader.getPropertyByName("jobs_server_name")+"; export ZURPLE_ENVIRONMENT="+configReader.getPropertyByName("jobs_environment_title")+"; php /var/www/zurple.com/current/webapp/index.php --zfc=job_userstatus  --zfa=userstatusautomationiconcacherebuild --zfp=packages:57\"");

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
