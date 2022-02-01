/**
 * 
 */
package resources.orm.hibernate.models.zurple;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * @author darrraqi
 *
 */

@Entity
@Table(name = "netsuite_sync_tasks", uniqueConstraints = { @UniqueConstraint(columnNames = "netsuite_sync_task_id")})
public class NetSuiteSyncTasks implements java.io.Serializable{

	@Id
	@Column(name="netsuite_sync_task_id")
	private Integer netsuite_sync_task_id;
	
	@Column(name="admin_id")
	private Integer admin_id;
	
	@Column(name="action")
	private String action;
	
	@Column(name="payload")
	private String payload;
	
	@Column(name="results")
	private String results;
	
	@Column(name="date_added")
	private Date date_added;
	
	@Column(name="date_processed")
	private Date date_processed;
	
	@Column(name="priority")
	private Integer priority;

	public Integer getNetsuite_sync_task_id() {
		return netsuite_sync_task_id;
	}

	public void setNetsuite_sync_task_id(Integer netsuite_sync_task_id) {
		this.netsuite_sync_task_id = netsuite_sync_task_id;
	}

	public Integer getAdmin_id() {
		return admin_id;
	}

	public void setAdmin_id(Integer admin_id) {
		this.admin_id = admin_id;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

	public String getResults() {
		return results;
	}

	public void setResults(String results) {
		this.results = results;
	}

	public Date getDate_added() {
		return date_added;
	}

	public void setDate_added(Date date_added) {
		this.date_added = date_added;
	}

	public Date getDate_processed() {
		return date_processed;
	}

	public void setDate_processed(Date date_processed) {
		this.date_processed = date_processed;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}


}
