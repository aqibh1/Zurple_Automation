/**
 * 
 */
package resources.orm.hibernate.models.pp;

import static javax.persistence.GenerationType.IDENTITY;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import resources.orm.hibernate.models.Abstract;

/**
 * @author adar
 *
 */

@Entity
@Table(name = "schedule", uniqueConstraints = {
@UniqueConstraint(columnNames = "schedule_id")})

public class Schedule extends Abstract{

	private Integer postID;
	private Integer scheduleID;
	private Timestamp dateAdded;
	private Timestamp dateProcessed;
	
	@Id
    @GeneratedValue(strategy = IDENTITY)
	@Column(name = "post_id", unique = true, nullable = false, length = 11)
	public Integer getPostID() {
		return postID;
	}
	
	@Column(name = "schedule_id", unique = true, nullable = true, length = 11)
	public Integer getScheduleID() {
		return scheduleID;
	}
	
	@Column(name = "date_added", unique = false, nullable = true, length = 200)
	public Timestamp getDateAdded() {
		return dateAdded;
	}
	
	@Column(name = "date_processed", unique = false, nullable = true, length = 200)
	public Timestamp getDateProcessed() {
		return dateProcessed;
	}

	public void setPostID(Integer postID) {
		this.postID = postID;
	}

	public void setScheduleID(Integer scheduleID) {
		this.scheduleID = scheduleID;
	}

	public void setDateAdded(Timestamp dateAdded) {
		this.dateAdded = dateAdded;
	}

	public void setDateProcessed(Timestamp dateProcessed) {
		this.dateProcessed = dateProcessed;
	}
}
