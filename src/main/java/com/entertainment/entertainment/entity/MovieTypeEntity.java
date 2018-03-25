package com.entertainment.entertainment.entity;

import javax.persistence.*;

@Entity(name = "MovieTypeEntity")
@Table(name = "MovieType")
public class MovieTypeEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="MovieTypeId")
	private int movieTypeId;
	
	@Column(name="Type")
	private String type;
	
	@Column(name="IsDeleted")
	private boolean isDeleted;
	
	@OneToOne(mappedBy="movieTypeEntity",optional = false)
	private MovieEntity movieEntity;

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

	public MovieEntity getMovieEntity() {
		return movieEntity;
	}

	public void setMovieEntity(MovieEntity movieEntity) {
		this.movieEntity = movieEntity;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
}
