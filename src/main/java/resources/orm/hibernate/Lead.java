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

    private Integer leadId;
    private String leadName;
    private String leadEmail;

    public Lead() {
    }

    public Lead(String leadName, String leadEmail) {
        this.leadName = leadName;
        this.leadEmail = leadEmail;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "lead_id", unique = true, nullable = false)
    public Integer getLeadId() {
        return this.leadId;
    }

    public void setLeadId(Integer leadId) {
        this.leadId = leadId;
    }

    @Column(name = "first_name", unique = true, nullable = false, length = 20)
    public String getLeadName() {
        return this.leadName;
    }

    public void setLeadName(String leadName) {
        this.leadName = leadName;
    }

    @Column(name = "email", unique = true, nullable = false, length = 20)
    public String getLeadEmail() {
        return this.leadEmail;
    }

    public void setLeadEmail(String leadEmail) {
        this.leadEmail = leadEmail;
    }

}
