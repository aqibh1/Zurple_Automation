package resources.orm.hibernate.models;

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
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "admins", catalog = "zurple_platform", uniqueConstraints = {
        @UniqueConstraint(columnNames = "admin_id")})
public class Admin
        implements java.io.Serializable {

    private Integer admin_id;
    private String first_name;
    private String last_name;
    private String email;
    private String alt_email;
    private String phone;
    private String password_hash;
    private String bio;
    private Boolean billing_access_flag;
    private Set<Site> sites;
    private Integer netsuite_id;

    public Admin() {
    }

    public Admin(
            String email,
            String alt_email,
            String first_name,
            String last_name,
            String phone,
            String password_hash,
            String bio,
            Integer netsuite_id
    ) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.alt_email = alt_email;
        this.phone = phone;
        this.password_hash = password_hash;
        this.bio = bio;
        this.netsuite_id = netsuite_id;
    }

    @Id
    @Column(name = "admin_id", unique = true, nullable = false)
    public Integer getId() {
        return this.admin_id;
    }

    public void setId(Integer lead_id) {
        this.admin_id = lead_id;
    }

    @Column(name = "last_name", unique = false, nullable = false, length = 255)
    public String getLastName() {
        return this.last_name;
    }

    public void setLastName(String last_name) {
        this.last_name = last_name;
    }

    @Column(name = "first_name", unique = false, nullable = false, length = 255)
    public String getFirstName() {
        return this.first_name;
    }

    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }

    @Column(name = "email", unique = true, nullable = false, length = 255)
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "alt_email", unique = true, nullable = false, length = 255)
    public String getAltEmail() {
        return this.alt_email;
    }

    public void setAltEmail(String alt_email) {
        this.alt_email = alt_email;
    }

    @Column(name = "phone", unique = false, nullable = false, length = 255)
    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Column(name = "password_hash", unique = false, nullable = false, length = 255)
    public String getPasswordHash() {
        return this.password_hash;
    }

    public void setPasswordHash(String password_hash) {
        this.password_hash = password_hash;
    }

    @Column(name = "bio", unique = false, nullable = false, length = 255)
    public String getBio() {
        return this.bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "site_admins", catalog = "zurple_platform", joinColumns = {
            @JoinColumn(name = "admin_id", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "site_id",
                    nullable = false, updatable = false) })
    public Set<Site> getSites() {
        return this.sites;
    }

    public void setSites(Set<Site> sites) {
        this.sites = sites;
    }

    @Column(name = "netsuite_id", unique = false, nullable = false)
    public Integer getNetsuiteId() {
        return this.netsuite_id;
    }

    public void setNetsuiteId(Integer netsuite_id) {
        this.netsuite_id = netsuite_id;
    }

    @Column(name = "billing_access_flag", unique = false, nullable = false)
    public Boolean getBillingAccessFlag() {
        return this.billing_access_flag;
    }

    public void setBillingAccessFlag(Boolean billing_access_flag) {
        this.billing_access_flag = billing_access_flag;
    }

}
