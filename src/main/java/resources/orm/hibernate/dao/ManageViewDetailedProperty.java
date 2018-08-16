package resources.orm.hibernate.dao;

import org.hibernate.*;
import resources.orm.hibernate.models.Property;

public class ManageViewDetailedProperty
{

    private Session session;

    public ManageViewDetailedProperty(Session session) {this.session = session;}

    /* Method to  READ user by id */
    public Property getDetailedProperty(Integer prop_id ){

        Property property = null;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            property = (Property) session.get(Property.class, prop_id);
            Hibernate.initialize(property);
            tx.commit();
        } catch (Exception e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return property;
    }

}
