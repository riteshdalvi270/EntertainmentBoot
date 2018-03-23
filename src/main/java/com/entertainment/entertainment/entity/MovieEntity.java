package com.entertainment.entertainment.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Movie")
public class MovieEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private long id;
	
	@Column(name="isDeleted")
	private boolean isDeleted;
	
	@Column(name="modifiedDate")
	private Date modifiedDate;
	
	@JoinColumn(name="id")
	private MovieTypeEntity movieType;
	
	@OneToOne(mappedBy="movieVersion")
	private MovieVersionEntity movieVersion;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public MovieTypeEntity getMovieType() {
		return movieType;
	}

	public void setMovieType(MovieTypeEntity movieType) {
		this.movieType = movieType;
	}

	public MovieVersionEntity getMovie() {
		return movieVersion;
	}

	public void setMovie(MovieVersionEntity movie) {
		this.movieVersion = movie;
	}
}
