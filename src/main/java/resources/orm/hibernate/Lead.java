package resources.orm.hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "leads", catalog = "zurple_platform", uniqueConstraints = {
        @UniqueConstraint(columnNames = "lead_id")})
public class Lead
        implements java.io.Serializable {

    private Integer lead_id;
    private String first_name;
    private String last_name;
    private String email;
    private String phone;
    private String cell;
    private String memo;

    public Lead() {
    }

    public Lead(
            String email,
            String first_name,
            String last_name,
            String phone,
            String cell,
            String memo
    ) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.phone = phone;
        this.cell = cell;
        this.memo = memo;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "lead_id", unique = true, nullable = false)
    public Integer getLeadId() {
        return this.lead_id;
    }

    public void setLeadId(Integer lead_id) {
        this.lead_id = lead_id;
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

    @Column(name = "phone", unique = false, nullable = false, length = 255)
    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Column(name = "cell", unique = false, nullable = false, length = 255)
    public String getCell() {
        return this.cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    @Column(name = "memo", unique = false, nullable = false, length = 255)
    public String getMemo() {
        return this.memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

}
