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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "packages", catalog = "zurple_platform", uniqueConstraints = {
        @UniqueConstraint(columnNames = "package_id")})
public class Package
        implements java.io.Serializable {

    private Integer package_id;
    private Integer netsuite_id;
    private Set<Admin> admins;

    @Id
    @Column(name = "package_id", unique = true, nullable = false)
    public Integer getId() {
        return this.package_id;
    }

    public void setId(Integer package_id) {
        this.package_id = package_id;
    }


    @Column(name = "netsuite_id", unique = false, nullable = false)
    public Integer getNetsuiteId() {
        return this.netsuite_id;
    }

    public void setNetsuiteId(Integer netsuite_id) {
        this.netsuite_id = netsuite_id;
    }

    @OneToMany(fetch=FetchType.EAGER, mappedBy="package")
    public Set<Admin> getAdmins() {
        return this.admins;
    }

    public void setAdmins(Set<Admin> admins)
    {
        this.admins = admins;
    }
}
