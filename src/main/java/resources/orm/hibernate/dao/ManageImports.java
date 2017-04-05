package resources.orm.hibernate.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import resources.orm.hibernate.models.Admin;
import resources.orm.hibernate.models.Import;
import resources.orm.hibernate.models.PackageProduct;

public class ManageImports
{

    private Session session;

    public ManageImports(Session session) {this.session = session;}

    public Import getImportByFilename( String filename ){
        Import imp = null;
        try {
            Query q = session.createQuery("FROM Import WHERE file_name='"+filename+"'");
            List leads = q.list();
            for (Iterator iterator =
                    leads.iterator(); iterator.hasNext();){
                imp = (Import) iterator.next();
            }

            Hibernate.initialize(imp);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return imp;
    }
}
