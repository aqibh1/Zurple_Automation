package resources.orm.hibernate.models.z57;

import resources.orm.hibernate.models.AbstractLead;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "leads", uniqueConstraints = {
        @UniqueConstraint(columnNames = "lead_id")})
public class Lead extends AbstractLead
        implements java.io.Serializable {

    private Integer lead_id;
    private String name_full;
    private String email;
    private String phone;

    public Lead() {
    }

    public Lead(
            String email,
            String name_full,
            String phone
    ) {
        this.name_full = name_full;
        this.email = email;
        this.phone = phone;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "lead_id", unique = true, nullable = false)
    public Integer getId() {
        return this.lead_id;
    }

    public void setId(Integer lead_id) {
        this.lead_id = lead_id;
    }

    @Column(name = "email", unique = true, nullable = false, length = 255)
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "phone", unique = false, nullable = false, length = 255)
    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Column(name = "name_full", unique = false, nullable = false, length = 255)
    public String getNameFull() {
        return this.name_full;
    }

    public void setNameFull(String name_full) {
        this.name_full = name_full;
    }

}
