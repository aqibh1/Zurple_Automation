package resources.orm.hibernate.models;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "user_transaction_goals", catalog = "zurple_platform")
public class TransactionGoal
        implements java.io.Serializable {

    @Id
    @OneToOne
    @PrimaryKeyJoinColumn
    private User user;
    private Integer buyer;
    private Integer seller;
    private Integer renter;


    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
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
