package resources.models;

import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import resources.AbstractTest;
import resources.orm.hibernate.models.Admin;
import resources.orm.hibernate.models.Lead;
import resources.orm.hibernate.models.User;

/**
 * todo
 *
 * @author Vladimir
 */
public class LeadTest extends AbstractTest
{

    @Test
    @Parameters({"email_validation_status"})
    public void setUserEmailValidationValue(@Optional("0") Integer email_validation_status){
        User user = getEnvironment().getUserToCheck();
        Lead lead = user.getLeadId();
        lead.setEmailValidationStatus(email_validation_status);
        lead.save();
    }
}
