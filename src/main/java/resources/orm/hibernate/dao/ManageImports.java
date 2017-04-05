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

    public List<Import> getImportByFilename( String filename ){

        List<Import> imports = new ArrayList<Import>();

        try {
            Criteria cr = session.createCriteria(Import.class);
                    //.setProjection(Projections.projectionList()
//                            .add(Projections.property("file_name"), filename))
//                    .setResultTransformer(Transformers.aliasToBean(Import.class));

            List<Import> list = cr.list();
            System.out.println(list.size());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("&&&&&");
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return imports;
    }
}
