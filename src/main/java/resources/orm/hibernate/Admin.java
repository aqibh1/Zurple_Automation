package resources.orm.hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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

    public Admin() {
    }

    public Admin(
            String email,
            String alt_email,
            String first_name,
            String last_name,
            String phone,
            String password_hash,
            String bio
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
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "admin_id", unique = true, nullable = false)
    public Integer getLeadId() {
        return this.admin_id;
    }

    public void setLeadId(Integer lead_id) {
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

}
