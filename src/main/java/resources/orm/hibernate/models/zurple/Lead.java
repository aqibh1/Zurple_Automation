package resources.orm.hibernate.models.zurple;

import resources.orm.hibernate.models.Abstract;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "leads", uniqueConstraints = {
        @UniqueConstraint(columnNames = "lead_id")})
public class Lead extends Abstract
        implements java.io.Serializable {

    private Integer lead_id;
    private String first_name;
    private String last_name;
    private String email;
    private String phone;
    private String cell;
    private String memo;
    private Admin owner_id;
    private Date create_datetime;
    private Date update_datetime;
    private Integer email_validation_id;
    private Integer email_validation_status;

    public Lead() {
    }

    public Lead(
            String email,
            String first_name,
            String last_name,
            String phone,
            String cell,
            String memo,
            Admin owner_id
    ) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.phone = phone;
        this.cell = cell;
        this.memo = memo;
        this.owner_id = owner_id;
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

    @OneToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    public Admin getOwnerId() {
        return this.owner_id;
    }

    public void setOwnerId(Admin owner_id) {
        this.owner_id = owner_id;
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

    @Column(name = "email_validation_id", unique = false, nullable = true)
    public Integer getEmailValidationId() {
        return this.email_validation_id;
    }

    public void setEmailValidationId(Integer email_validation_id) {
        this.email_validation_id = email_validation_id;
    }

    @Column(name = "email_validation_status", unique = false, nullable = true)
    public Integer getEmailValidationStatus() {
        return this.email_validation_status;
    }

    public void setEmailValidationStatus(Integer email_validation_status) {
        this.email_validation_status = email_validation_status;
    }

}
