package resources.orm.hibernate.dao.zurple;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import resources.orm.hibernate.models.zurple.Package;
import resources.orm.hibernate.models.zurple.PackageProduct;

public class ManagePackageProducts
{

    private Session session;

    public ManagePackageProducts(Session session) {this.session = session;}


    /* Method to  READ products list by admin id */
    public List<PackageProduct> getPackageProductsList( Package pkg ){

        List<PackageProduct> packageProducts = new ArrayList<PackageProduct>();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List records = session.createQuery("FROM "+PackageProduct.class.getName()+" WHERE package_id = " + pkg.getNetsuiteId()).list();

            for (Iterator iterator =
                    records.iterator(); iterator.hasNext();){
                PackageProduct packageProduct = (PackageProduct) iterator.next();
                packageProducts.add(packageProduct);
            }
            tx.commit();
            //Hibernate.initialize(lead);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return packageProducts;
    }

}
