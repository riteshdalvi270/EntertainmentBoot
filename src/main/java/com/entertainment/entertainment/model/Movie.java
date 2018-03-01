package com.entertainment.entertainment.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="Movie")
@JsonIgnoreProperties({"id","watch_date"})
public class Movie {

	@Id()
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private long id;
	
	@NotBlank
	@Column(name="movie_name")
	private String movieName;
	
	@NotBlank
	@Column(name="director_name")
	private String directorName;
	
	@NotBlank
	@Column(name = "description")
	private String description;
	
	@Column(name="watch_date")
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date watchDate;
	
	@Column(name="stop_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date stopDate;
	
	@Column(name="modified_date")
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date modifiedDate;
	
	@Column(name="done_watching")
	private boolean doneWatching;

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

	public Date getWatchDate() {
		return watchDate;
	}

	public void setWatchDate(Date watchDate) {
		this.watchDate = watchDate;
	}

	public Date getStopDate() {
		return stopDate;
	}

	public void setStopDate(Date stopDate) {
		this.stopDate = stopDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public boolean isDoneWatching() {
		return doneWatching;
	}

	public void setDoneWatching(boolean doneWatching) {
		this.doneWatching = doneWatching;
	}
}
