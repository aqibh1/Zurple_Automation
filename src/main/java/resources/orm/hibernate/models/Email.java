package resources.orm.hibernate.models;

import java.util.Date;
import java.util.Set;
import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "emails", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email_id")})
public class Email extends Abstract
        implements java.io.Serializable {

    private Integer email_id;
    private String email_type;
    private String subject;
    private String title;
    private String body;
    private User user;
    private Integer order_number;
    private String dlnk;
    
    private Date sent_datetime;
    private Date open_datetime;

    public Email() {
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "email_id", unique = true, nullable = false)
    public Integer getId() {
        return this.email_id;
    }

    public void setId(Integer email_id) {
        this.email_id = email_id;
    }

    @Column(name = "email_type", unique = false, nullable = false, length = 255)
    public String getEmailType() {
        return this.email_type;
    }

    public void setEmailType(String email_type) {
        this.email_type = email_type;
    }
    
    @Column(name = "subject", unique = false, nullable = false, length = 255)
    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
    
    @Column(name = "title", unique = false, nullable = false, length = 255)
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    @Column(name = "body", unique = false, nullable = false, length = 255)
    public String getBody() {
        return this.body;
    }

    public void setBody(String body) {
        this.body = body;
    }
    
    @Column(name = "dlnk", unique = false, nullable = false, length = 255)
    public String getDlnk() {
        return this.dlnk;
    }

    public void setDlnk(String dlnk) {
        this.dlnk = dlnk;
    }
    
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    @Column(name = "sent_datetime", columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getSendDatetime() {
        return this.sent_datetime;
    }

    public void setSendDatetime(Date sent_datetime) {
        this.sent_datetime = sent_datetime;
    }

    @Column(name = "open_datetime", columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getOpenDatetime() {
        return this.open_datetime;
    }

    public void setOpenDatetime(Date open_datetime) {
        this.open_datetime = open_datetime;
    }

    @Column(name = "order_number")
    public Integer getOrderNumber() {
        return this.order_number;
    }

    public void setOrderNumber(Integer order_number) {
        this.order_number = order_number;
    }

}
