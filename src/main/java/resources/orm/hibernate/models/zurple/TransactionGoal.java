package resources.orm.hibernate.models.zurple;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_transaction_goals", catalog = "zurple_platform")
public class TransactionGoal
        implements java.io.Serializable {

    @Id
    private Integer user_id;
    private Integer buyer;
    private Integer seller;
    private Integer renter;

    public Integer getUser() {
        return this.user_id;
    }

    public void setUser(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getBuyer()
    {
        return buyer;
    }

    public void setBuyer(Integer buyer)
    {
        this.buyer = buyer;
    }

    public Integer getSeller()
    {
        return seller;
    }

    public void setSeller(Integer seller)
    {
        this.seller = seller;
    }

    public Integer getRenter()
    {
        return renter;
    }

    public void setRenter(Integer renter)
    {
        this.renter = renter;
    }

}
