package resources.orm.hibernate.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import resources.orm.hibernate.models.Admin;
import resources.orm.hibernate.models.DistributionRule;
import resources.orm.hibernate.models.User;

public class ManageDistributionRules
{

    private Session session;

    public ManageDistributionRules(Session session) {this.session = session;}

    /* Method to READ all distribution rules by the admin */
    public List<DistributionRule> getAdminsDistributionRulesById ( Integer admin_id ){

        List<DistributionRule> distributionRules = new ArrayList<DistributionRule>();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List records = session.createQuery("FROM DistributionRule WHERE admin_id="+admin_id).list();

            for (Iterator iterator =
                    records.iterator(); iterator.hasNext();){
                DistributionRule distributionRule = (DistributionRule) iterator.next();
                distributionRules.add(distributionRule);
            }
            tx.commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return distributionRules;

    }

    /* Method to READ all distribution rules by site id */
    public List<DistributionRule> getSitesDistributionRulesById ( Integer site_id ){

        List<DistributionRule> distributionRules = new ArrayList<DistributionRule>();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List records = session.createQuery("FROM DistributionRule WHERE site_id="+site_id).list();

            for (Iterator iterator =
                    records.iterator(); iterator.hasNext();){
                DistributionRule distributionRule = (DistributionRule) iterator.next();
                distributionRules.add(distributionRule);
            }
            tx.commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return distributionRules;

    }

}
