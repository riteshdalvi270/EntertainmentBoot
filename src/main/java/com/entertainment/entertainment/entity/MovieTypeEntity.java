package com.entertainment.entertainment.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "MovieType")
public class MovieTypeEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int movieTypeId;
	
	@Column(name="type")
	private String type;
	
	@Column(name="isDeleted")
	private boolean isDeleted;
	
	@OneToOne(mappedBy="movieType")
	private MovieEntity movie;

	public int getId() {
		return movieTypeId;
	}

	public void setId(int id) {
		this.movieTypeId = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public MovieEntity getMovie() {
		return movie;
	}

	public void setMovie(MovieEntity movie) {
		this.movie = movie;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
}
