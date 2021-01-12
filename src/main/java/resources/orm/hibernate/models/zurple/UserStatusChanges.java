package resources.orm.hibernate.models.zurple;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import resources.orm.hibernate.models.Abstract;

@Entity
@Table(name = "user_status_changes", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id")})
public class UserStatusChanges extends Abstract
        implements java.io.Serializable  {

    private Integer id;
    private User user;
    private Admin status_changed_by;
    private String prev_user_status;
    private String new_user_status;
    private Date status_change_datetime;
    private Boolean is_automation;
    private String triggered_by;
    private Integer ignore_automation;

    public UserStatusChanges() {
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "status_changed_by")
    public Admin getStatusChangedBy() {
        return this.status_changed_by;
    }

    public void setStatusChangedBy(Admin status_changed_by) {
        this.status_changed_by = status_changed_by;
    }

    @Column(name = "prev_user_status", unique = false, nullable = false, length = 255)
    public String getPrevUserStatus() {
        return this.prev_user_status;
    }

    public void setPrevUserStatus(String prev_user_status) {
        this.prev_user_status = prev_user_status;
    }

    @Column(name = "new_user_status", unique = false, nullable = false, length = 255)
    public String getNewUserStatus() {
        return this.new_user_status;
    }

    public void setNewUserStatus(String new_user_status) {
        this.new_user_status = new_user_status;
    }
    
    @Column(name = "status_change_datetime", columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getStatusChangeDatetime() {
        return this.status_change_datetime;
    }

    public void setStatusChangeDatetime(Date status_change_datetime) {
        this.status_change_datetime = status_change_datetime;
    }

    @Column(name = "is_automation", unique = false)
    public Boolean getIsAutomation() {
        return this.is_automation;
    }

    public void setIsAutomation(Boolean is_automation) {
        this.is_automation = is_automation;
    }

    @Column(name = "triggered_by", unique = false, nullable = true, length = 255)
    public String getTriggeredBy() {
        return this.triggered_by;
    }

    public void setTriggeredBy(String triggered_by) {
        this.triggered_by = triggered_by;
    }

    @Column(name = "ignore_automation", unique = false)
    public Integer getIgnoreAutomation() {
        return this.ignore_automation;
    }

    public void setIgnoreAutomation(Integer ignore_automation) {
        this.ignore_automation = ignore_automation;
    }

}
