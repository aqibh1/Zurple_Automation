package resources.orm.hibernate.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "email_queue", catalog = "zurple_platform", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email_queue_id")})
public class EmailQueue
        implements java.io.Serializable {

    private Integer email_queue_id;
    private String subject;
    private String body_html;

    public EmailQueue() {
    }

    public EmailQueue(
            Integer email_queue_id,
            String subject,
            String body_html
    ) {
        this.email_queue_id = email_queue_id;
        this.subject = subject;
        this.body_html = body_html;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "email_queue_id", unique = true, nullable = false)
    public Integer getId() {
        return this.email_queue_id;
    }

    public void setId(Integer email_queue_id) {
        this.email_queue_id = email_queue_id;
    }

    @Column(name = "subject", unique = false, nullable = false, length = 250)
    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Column(name = "body_html", unique = false, nullable = false, length = 1500)
    public String getBodyHtml() {
        return this.body_html;
    }

    public void setBodyHtml(String body_html) {
        this.body_html = body_html;
    }

}
