package resources.orm.hibernate.dao.zurple;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;

import resources.orm.hibernate.models.zurple.Import;

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
