package resources.orm.hibernate.models.zurple;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import resources.orm.hibernate.models.Abstract;

@Entity
@Table(name = "admins", uniqueConstraints = {
        @UniqueConstraint(columnNames = "admin_id")})
public class Admin extends Abstract
        implements java.io.Serializable {

    private Integer admin_id;
    private Package pkg;
    private String first_name;
    private String last_name;
    private String email;
    private String alt_email;
    private String phone;
    private String password_hash;
    private String bio;
    private Boolean billing_access_flag;
    private Boolean lead_flag;
    private Boolean property_flag;
    private String email_unique_sign_off;
    private String email_display_name;
    private Set<Site> sites;
    private Set<Import> imports;

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

    @ManyToMany(fetch=FetchType.LAZY, mappedBy="admins")
    public Set<Site> getSites() {
        return this.sites;
    }

    public void setSites(Set<Site> sites) {
        this.sites = sites;
    }

    @Column(name = "billing_access_flag", unique = false, nullable = false)
    public Boolean getBillingAccessFlag() {
        return this.billing_access_flag;
    }

    public void setBillingAccessFlag(Boolean billing_access_flag) {
        this.billing_access_flag = billing_access_flag;
    }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "package_id")
    public Package getPackage() {
        return this.pkg;
    }

    public void setPackage(Package pkg) {
        this.pkg = pkg;
    }

    @OneToMany(fetch=FetchType.LAZY, mappedBy="admin",cascade=CascadeType.ALL)
    public Set<Import> getImports()
    {
        return imports;
    }

    public void setImports(Set<Import> imports)
    {
        this.imports = imports;
    }

    @Column(name = "email_unique_sign_off", unique = false, nullable = true, length = 255)
    public String getEmailUniqueSignOff() {
        return email_unique_sign_off;
    }

    public void setEmailUniqueSignOff(String email_unique_sign_off) {
        this.email_unique_sign_off = email_unique_sign_off;
    }

    @Column(name = "email_display_name", unique = false, nullable = true, length = 255)
    public String getEmailDisplayName() {
        return email_display_name;
    }

    public void setEmailDisplayName(String email_display_name) {
        this.email_display_name = email_display_name;
    }
}
