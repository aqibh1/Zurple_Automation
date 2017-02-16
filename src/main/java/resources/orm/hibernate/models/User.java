package resources.orm.hibernate.models;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "users", catalog = "zurple_platform", uniqueConstraints = {
        @UniqueConstraint(columnNames = "user_id")})
public class User
        implements java.io.Serializable {

    private Integer user_id;
    private Lead lead_id;
    private Admin admin_id;
    private String user_name;
    private String user_first_name;
    private String user_status;
    private String traffic_source;
    private Date create_datetime;
    private Date update_datetime;

    public User() {
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "user_id", unique = true, nullable = false)
    public Integer getId() {
        return this.user_id;
    }

    public void setId(Integer user_id) {
        this.user_id = user_id;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "lead_id")
    public Lead getLeadId() {
        return this.lead_id;
    }

    public void setLeadId(Lead lead_id) {
        this.lead_id = lead_id;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "admin_id")
    public Admin getAdminId() {
        return this.admin_id;
    }

    public void setAdminId(Admin admin_id) {
        this.admin_id = admin_id;
    }

    @Column(name = "user_name", unique = false, nullable = false, length = 255)
    public String getUserName() {
        return this.user_name;
    }

    public void setUserName(String user_name) {
        this.user_name = user_name;
    }

    @Column(name = "user_first_name", unique = false, nullable = false, length = 255)
    public String getUserFirstName() {
        return this.user_first_name;
    }

    public void setUserFirstName(String user_first_name) {
        this.user_first_name = user_first_name;
    }

    @Column(name = "user_status", unique = true, nullable = false, length = 255)
    public String getUserStatus() {
        return this.user_status;
    }

    public void setUserStatus(String user_status) {
        this.user_status = user_status;
    }

    @Column(name = "traffic_source", unique = false, nullable = false, length = 255)
    public String getTrafficSource() {
        return this.traffic_source;
    }

    public void setTrafficSource(String traffic_source) {
        this.traffic_source = traffic_source;
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