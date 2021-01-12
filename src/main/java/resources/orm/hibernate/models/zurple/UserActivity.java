package resources.orm.hibernate.models.zurple;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "user_activity", uniqueConstraints = {
        @UniqueConstraint(columnNames = "user_activity_id")})
public class UserActivity
        implements java.io.Serializable {

    private Integer user_activity_id;
    private User user_id;
    private Site site_id;
    private SessionUser session_user_id;
    private Date user_activity_time;
    private String user_activity_type;
    private Integer user_activity_target;
    private Email email_id;
    
    public UserActivity() {
    }

    public UserActivity(User user_id, Site site_id, SessionUser session_user_id, Date user_activity_time, String user_activity_type, Integer user_activity_target) {
        this.user_id = user_id;
        this.site_id = site_id;
        this.session_user_id = session_user_id;
        this.user_activity_time = user_activity_time;
        this.user_activity_type = user_activity_type;
        this.user_activity_target = user_activity_target;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "user_activity_id", unique = true, nullable = false)
    public Integer getId() {
        return this.user_activity_id;
    }

    public void setId(Integer user_activity_id) {
        this.user_activity_id = user_activity_id;
    }

    @OneToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public User getUserId() {
        return this.user_id;
    }

    public void setUserId(User user_id) {
        this.user_id = user_id;
    }

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "site_id")
    public Site getSiteId() {
        return this.site_id;
    }

    public void setSiteId(Site site_id) {
        this.site_id = site_id;
    }

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "session_user_id")
    public SessionUser getSessionUserId() {
        return this.session_user_id;
    }

    public void setSessionUserId(SessionUser session_user_id) {
        this.session_user_id = session_user_id;
    }

    @Column(name = "user_activity_type", unique = false, nullable = false, length = 255)
    public String getUserActivityType() {
        return this.user_activity_type;
    }

    public void setUserActivityType(String user_activity_type) {
        this.user_activity_type = user_activity_type;
    }

    @Column(name = "user_activity_target", unique = false, nullable = false, length = 255)
    public Integer getUserActivityTarget() {
        return this.user_activity_target;
    }

    public void setUserActivityTarget(Integer user_activity_target) {
        this.user_activity_target = user_activity_target;
    }
    
    @Column(name = "user_activity_time", columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getUserActivityTime() {
        return this.user_activity_time;
    }

    public void setUserActivityTime(Date user_activity_time) {
        this.user_activity_time = user_activity_time;
    }
    
}
