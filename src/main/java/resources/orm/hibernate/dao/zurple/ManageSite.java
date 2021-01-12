package resources.orm.hibernate.dao.zurple;

import org.hibernate.Hibernate;
import org.hibernate.Session;

import resources.orm.hibernate.models.zurple.Site;

public class ManageSite
{

    private Session session;

    public ManageSite(Session session) {this.session = session;}

    /* Method to  READ user by id */
    public Site getSite( Integer site_id ){

        Site site = null;
        try {
            site = (Site) session.get(Site.class, site_id);
            Hibernate.initialize(site);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return site;
    }

}
