package com.entertainment.entertainment.entity;

import java.util.Date;

import javax.persistence.*;

import org.hibernate.validator.constraints.NotBlank;

@Entity(name = "MovieVersionEntity")
@Table(name="MovieVersion")
//@EntityListeners(AuditingEntityListener.class)
public class MovieVersionEntity {

	@Id()
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="MovieId")
	private long movieId;
	
	@NotBlank
	@Column(name="MovieName")
	private String movieName;
	
	@NotBlank
	@Column(name="DirectorName")
	private String directorName;
	
	@NotBlank
	@Column(name="Description")
	private String description;
	
	@Column(name="StopDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date stopDate;
	
	@Column(name="DoneWatching")
	private boolean doneWatching;
	
	@Column(name="WatchDate")
	@Temporal(TemporalType.TIMESTAMP)
	//@CreatedDate
	private Date watchDate;
	
	@Column(name="ModifiedDate")
	@Temporal(TemporalType.TIMESTAMP)
	//@LastModifiedDate
	private Date modifiedDate;

	@OneToOne(optional = false)
	@JoinColumn(name="Id")
	private MovieEntity movieEntity;

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

	public long getMovieId() {
		return movieId;
	}

	public void setMovieId(long movieId) {
		this.movieId = movieId;
	}

	public MovieEntity getMovieEntity() {
		return movieEntity;
	}

	public void setMovieEntity(MovieEntity movieEntity) {
		this.movieEntity = movieEntity;
	}
}
