package resources.orm.hibernate.models;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import resources.orm.hibernate.HibernateUtil;

public abstract class AbstractLead extends Abstract{

    public abstract Integer getId();

    public abstract String getEmail();

    public abstract String getPhone();

}
