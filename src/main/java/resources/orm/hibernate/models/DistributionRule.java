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
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "lead_distribution_rules", catalog = "zurple_platform", uniqueConstraints = {
        @UniqueConstraint(columnNames = "rule_id")})
public class DistributionRule
        implements java.io.Serializable {

    private Integer rule_id;
    private Admin admin;
    private Site site;
    private Integer weight;

    @Id
    @Column(name = "rule_id", unique = true, nullable = false)
    public Integer getId() {
        return this.rule_id;
    }

    public void setId(Integer rule_id) {
        this.rule_id = rule_id;
    }

    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "admin_id")
    public Admin getAdmin() {
        return this.admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "site_id")
    public Site getSite() {
        return this.site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public Integer getWeight()
    {
        return weight;
    }

    public void setWeight(Integer weight)
    {
        this.weight = weight;
    }
}
