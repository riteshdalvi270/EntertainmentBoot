package com.entertainment.entertainment.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="Movie")
//@EntityListeners(AuditingEntityListener.class)
//@JsonIgnoreProperties(value={"id","watch_date","modified_date"},allowGetters = true)
public class Movies {

	@Id()
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private long id;
	
	@NotBlank
	@Column(name="movie_name")
	private String movieName;
	
	@NotBlank
	@Column(name="director_name")
	private String directorName;
	
	@NotBlank
	@Column(name="description")
	private String description;
	
	@Column(name="stop_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date stopDate;
	
	@Column(name="done_watching")
	private boolean doneWatching;
	
	@Column(name="watch_date")
	@Temporal(TemporalType.TIMESTAMP)
	//@CreatedDate
	private Date watchDate;
	
	@Column(name="modified_date")
	@Temporal(TemporalType.TIMESTAMP)
	//@LastModifiedDate
	private Date modifiedDate;
	
	@JoinColumn(name="id")
	private Movie movie;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public Date getStopDate() {
		return stopDate;
	}

	public void setStopDate(Date stopDate) {
		this.stopDate = stopDate;
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
