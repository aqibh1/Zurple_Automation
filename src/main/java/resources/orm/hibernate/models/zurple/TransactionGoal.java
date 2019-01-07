package resources.orm.hibernate.models.zurple;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

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
