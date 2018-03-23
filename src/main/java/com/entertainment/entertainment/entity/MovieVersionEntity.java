package com.entertainment.entertainment.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="MovieVersion")
//@EntityListeners(AuditingEntityListener.class)
public class MovieVersionEntity {

	@Id()
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private long movieId;
	
	@NotBlank
	@Column(name="movieName")
	private String movieName;
	
	@NotBlank
	@Column(name="directorName")
	private String directorName;
	
	@NotBlank
	@Column(name="description")
	private String description;
	
	@Column(name="stop_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;
	
	@Column(name="doneWatching")
	private boolean doneWatching;
	
	@Column(name="watchDate")
	@Temporal(TemporalType.TIMESTAMP)
	//@CreatedDate
	private Date watchDate;
	
	@Column(name="modifiedDate")
	@Temporal(TemporalType.TIMESTAMP)
	//@LastModifiedDate
	private Date modifiedDate;
	
	@JoinColumn(name="id")
	private MovieEntity movie;

	public long getId() {
		return movieId;
	}

	public void setId(long id) {
		this.movieId = id;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getDirectorName() {
		return directorName;
	}

	public void setDirectorName(String directorName) {
		this.directorName = directorName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public boolean isDoneWatching() {
		return doneWatching;
	}

	public void setDoneWatching(boolean doneWatching) {
		this.doneWatching = doneWatching;
	}
	
	public Date getWatchDate() {
		return watchDate;
	}

	public void setWatchDate(Date watchDate) {
		this.watchDate = watchDate;
	}
	
	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
}
