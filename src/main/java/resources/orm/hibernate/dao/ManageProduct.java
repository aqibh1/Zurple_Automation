package resources.orm.hibernate.dao;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import resources.orm.hibernate.models.Admin;
import resources.orm.hibernate.models.Product;

public class ManageProduct
{

    private SessionFactory factory;

    public ManageProduct(SessionFactory factory) {this.factory = factory;}

    /* Method to  READ product by id */
    public Product getProduct( Integer product_id ){
        Session session = factory.openSession();
        Product product = null;
        try {
            product = (Product) session.get(Product.class, product_id);
            Hibernate.initialize(product);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return product;
    }

}
