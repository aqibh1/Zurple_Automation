package resources.orm.hibernate.models.zurple;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import resources.orm.hibernate.models.Abstract;

@Entity
@Table(name = "session_anonymous", uniqueConstraints = {
        @UniqueConstraint(columnNames = "session_anonymous_id")})
public class SessionAnonymous extends Abstract
        implements java.io.Serializable {

    private Integer session_anonymous_id;
    private String session_id;
    private String session_anonymous_data;

    public SessionAnonymous() {
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "session_anonymous_id", unique = true, nullable = false)
    public Integer getId() {
        return this.session_anonymous_id;
    }

    public void setId(Integer session_anonymous_id) {
        this.session_anonymous_id = session_anonymous_id;
    }

    @Column(name = "session_id", unique = false, nullable = false, length = 255)
    public String getSessionId() {
        return this.session_id;
    }

    public void setSessionId(String session_id) {
        this.session_id = session_id;
    }

    @Column(name = "session_anonymous_data", unique = false, nullable = false, length = 255)
    public String getSessionAnonymousData() {
        return this.session_anonymous_data;
    }

    public void setSessionAnonymousData(String session_anonymous_data) {
        this.session_anonymous_data = session_anonymous_data;
    }

}
