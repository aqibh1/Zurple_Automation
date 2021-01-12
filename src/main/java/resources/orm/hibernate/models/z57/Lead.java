package resources.orm.hibernate.models.z57;

import static javax.persistence.GenerationType.IDENTITY;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import resources.orm.hibernate.models.AbstractLead;

@Entity
@Table(name = "leads", uniqueConstraints = {
        @UniqueConstraint(columnNames = "lead_id")})
public class Lead extends AbstractLead
        implements java.io.Serializable {

    private Integer lead_id;
    private String name_full;
    private String email;
    private String phone;
    private String account_id;
    private String date_added;
    private Timestamp dob;
    private String address;
    private String city;
    private String state;
    private String zip;
    private Integer leadStatusId;
    
    public Lead() {
    }

    public Lead(String email,String name_full,String phone){
        this.name_full = name_full;
        this.email = email;
        this.phone = phone;
    }
    
    @Column(name = "account_id", unique = false, nullable = false, length = 255)
    public String getAccount_id() {
		return account_id;
	}

	public void setAccount_id(String account_id) {
		this.account_id = account_id;
	}
	
	@Column(name = "date_added", unique = false, nullable = false, length = 255)
	public String getDate_added() {
		return date_added;
	}

	public void setDate_added(String date_added) {
		this.date_added = date_added;
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

    @Column(name = "name_full", unique = false, nullable = false, length = 255)
    public String getNameFull() {
        return this.name_full;
    }

    public void setNameFull(String name_full) {
        this.name_full = name_full;
    }
    
    @Column(name = "dob", unique = false, nullable = true, length = 255)
	public Timestamp getDob() {
		return dob;
	}

	public void setDob(Timestamp dob) {
		this.dob = dob;
	}

	 @Column(name = "address", unique = false, nullable = true, length = 255)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	 @Column(name = "city", unique = false, nullable = true, length = 255)
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	 @Column(name = "state", unique = false, nullable = true, length = 2)
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	 @Column(name = "zip", unique = false, nullable = true, length = 11)
	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}
	
	 @Column(name = "lead_status_id", unique = true, nullable = false, length = 11)
	public Integer getLeadStatusId() {
		return leadStatusId;
	}

	public void setLeadStatusId(Integer leadStatusId) {
		this.leadStatusId = leadStatusId;
	}
    

}
