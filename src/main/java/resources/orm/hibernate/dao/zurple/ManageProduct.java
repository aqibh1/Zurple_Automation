package resources.orm.hibernate.dao.zurple;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import resources.orm.hibernate.models.zurple.Product;

public class ManageProduct
{

    private Session session;

    public ManageProduct(Session session) {this.session = session;}

    /* Method to  READ product by id */
    public Product getProduct( Integer product_id ){

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
