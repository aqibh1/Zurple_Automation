package resources.orm.hibernate.models.z57;

import resources.orm.hibernate.models.Abstract;

import javax.persistence.*;
import java.sql.Timestamp;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "idx_lead_searches", uniqueConstraints = {
        @UniqueConstraint(columnNames = "idx_lead_searches_id")})
public class IdxLeadSearches extends Abstract{
	
	private Integer idxLeadSearchesId;
	private Integer leadId;
	private Integer accountId;
	private String title;
	private Timestamp dateAdded;
	
	@Id
    @GeneratedValue(strategy = IDENTITY)
	@Column(name = "idx_lead_searches_id", unique = true, nullable = false, length = 255)
	public Integer getIdxLeadSearchesId() {
		return idxLeadSearchesId;
	}
	public void setIdxLeadSearchesId(Integer idxLeadSearchesId) {
		this.idxLeadSearchesId = idxLeadSearchesId;
	}
	
	@Column(name = "lead_id", unique = true, nullable = false, length = 255)
	public Integer getLeadId() {
		return leadId;
	}
	public void setLeadId(Integer leadId) {
		this.leadId = leadId;
	}

	@Column(name = "account_id", unique = true, nullable = false, length = 255)
	public Integer getAccountId() {
		return accountId;
	}
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	@Column(name = "title", unique = true, nullable = false, length = 255)
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "date_added", unique = false, nullable = true)
	public Timestamp getDateAdded() {
		return dateAdded;
	}
	public void setDateAdded(Timestamp dateAdded) {
		this.dateAdded = dateAdded;
	}
	

}
