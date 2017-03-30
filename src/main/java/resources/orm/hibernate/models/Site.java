package resources.orm.hibernate.models;

import java.util.Date;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "sites", catalog = "zurple_platform", uniqueConstraints = {
        @UniqueConstraint(columnNames = "site_id")})
public class Site
        implements java.io.Serializable {

    private Integer site_id;
    private String domain_name;
    private Set<Admin> admins;
    private Date create_datetime;
    private Date update_datetime;

    public Site() {
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "site_id", unique = true, nullable = false)
    public Integer getId() {
        return this.site_id;
    }

    public void setId(Integer site_id) {
        this.site_id = site_id;
    }

    @Column(name = "domain_name", unique = false, nullable = false, length = 255)
    public String getDomainName() {
        return this.domain_name;
    }

    public void setDomainName(String domain_name) {
        this.domain_name = domain_name;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "site_admins", catalog = "zurple_platform", joinColumns = {
            @JoinColumn(name = "site_id", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "admin_id",
                    nullable = false, updatable = false) })
    public Set<Admin> getAdmins() {
        return this.admins;
    }

    public void setAdmins(Set<Admin> admins) {
        this.admins = admins;
    }

    @Column(name = "create_datetime", columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreateDatetime() {
        return this.create_datetime;
    }

    public void setCreateDatetime(Date create_datetime) {
        this.create_datetime = create_datetime;
    }

    @Column(name = "update_datetime", columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getUpdateDatetime() {
        return this.update_datetime;
    }

    public void setUpdateDatetime(Date update_datetime) {
        this.update_datetime = update_datetime;
    }

}