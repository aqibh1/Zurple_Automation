package resources.orm.hibernate.models.zurple;

import java.util.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import resources.orm.hibernate.models.Abstract;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "users",uniqueConstraints = {
        @UniqueConstraint(columnNames = "user_id")})
public class User extends Abstract
        implements java.io.Serializable {

    private Integer user_id;
    private Lead lead_id;

    private Admin admin_id;
    private Site site_id;
    private String user_name;
    private String user_first_name;
    private String user_last_name;
    private String user_phone;
    private String user_cell;
    private String user_status;
    private String user_memo;
    private String traffic_source;
    private String password_seed;
    private String hashed_password;
    private Integer user_lead_score;
    private Date create_datetime;
    private Date update_datetime;

    private Set<UserAlert> user_alerts;
    private List<UserStatusChanges> user_status_changes;
    private Set<Email> emails;

    public User() {
    }

    public User(String user_name, String user_first_name, String user_status) {
        this.user_name = user_name;
        this.user_first_name = user_first_name;
        this.user_status = user_status;
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

    @OneToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "lead_id")
    public Lead getLeadId() {
        return this.lead_id;
    }

    public void setLeadId(Lead lead_id) {
        this.lead_id = lead_id;
    }

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "admin_id")
    public Admin getAdminId() {
        return this.admin_id;
    }

    public void setAdminId(Admin admin_id) {
        this.admin_id = admin_id;
    }

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "site_id")
    public Site getSiteId() {
        return this.site_id;
    }

    public void setSiteId(Site site_id) {
        this.site_id = site_id;
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

    @Column(name = "user_last_name", unique = false, nullable = false, length = 255)
    public String getUserLastName() {
        return this.user_last_name;
    }

    public void setUserLastName(String user_last_name) {
        this.user_last_name = user_last_name;
    }

    @Column(name = "user_phone", unique = false, nullable = false, length = 255)
    public String getUserPhone() {
        return this.user_phone;
    }

    public void setUserPhone(String user_phone) {
        this.user_phone = user_phone;
    }

    @Column(name = "user_cell", unique = false, nullable = false, length = 255)
    public String getUserCell() {
        return this.user_cell;
    }

    public void setUserCell(String user_cell) {
        this.user_cell = user_cell;
    }

    @Column(name = "user_memo", unique = false, nullable = false, length = 255)
    public String getUserMemo() {
        return this.user_memo;
    }

    public void setUserMemo(String user_memo) {
        this.user_memo = user_memo;
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

    @Column(name = "password_seed", unique = false, nullable = false, length = 2)
    public String getPasswordSeed() {
        return this.password_seed;
    }

    public void setPasswordSeed(String password_seed) {
        this.password_seed = password_seed;
    }

    @Column(name = "hashed_password", unique = false, nullable = false, length = 255)
    public String getHashedPassword() {
        return this.hashed_password;
    }

    public void setHashedPassword(String hashed_password) {
        this.hashed_password = hashed_password;
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

    @OneToMany(fetch=FetchType.LAZY, mappedBy="user")
    public Set<UserAlert> getUserAlerts() {
        return this.user_alerts;
    }

    public void setUserAlerts(Set<UserAlert> user_alerts)
    {
        this.user_alerts = user_alerts;
    }

    @OneToMany(fetch=FetchType.LAZY, mappedBy="user")
    public List<UserStatusChanges> getUserStatusChanges() {
        Collections.sort(this.user_status_changes, new Comparator<UserStatusChanges>() {
            public int compare(UserStatusChanges o1, UserStatusChanges o2) {
                if (o1.getStatusChangeDatetime() == null || o2.getStatusChangeDatetime() == null)
                    return 0;
                return o1.getStatusChangeDatetime().compareTo(o2.getStatusChangeDatetime());
            }
        });
        return this.user_status_changes;
    }

    public void setUserStatusChanges(List<UserStatusChanges> user_status_changes)
    {
        this.user_status_changes = user_status_changes;
    }

    @Column(name = "user_lead_score")
    public Integer getUserLeadScore() {
        return this.user_lead_score;
    }

    public void setUserLeadScore(Integer user_lead_score) {
        this.user_lead_score = user_lead_score;
    }

    @OneToMany(fetch=FetchType.LAZY, mappedBy="user")
    public Set<Email> getEmails() {
        return this.emails;
    }

    public void setEmails(Set<Email> emails)
    {
        this.emails = emails;
    }

}
