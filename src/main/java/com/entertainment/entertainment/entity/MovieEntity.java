package com.entertainment.entertainment.entity;

import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.Date;

@Entity(name="MovieEntity")
@Table(name="Movie")
public class MovieEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id")
	private long id;
	
	@Column(name="IsDeleted")
	private boolean isDeleted;
	
	@Column(name="ModifiedDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedDate;

	@OneToOne()
	@JoinColumn(name="MovieTypeId")
	private MovieTypeEntity movieTypeEntity;
	
	@OneToOne(mappedBy="movieEntity",optional = false)
	private MovieVersionEntity movieVersionEntity;

	//@Transient
	@Formula("(select count(*) from movie_version mv where mv.id=id)")
	private int childCount;

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

	public MovieTypeEntity getMovieTypeEntity() {
		return movieTypeEntity;
	}

	public void setMovieTypeEntity(MovieTypeEntity movieTypeEntity) {
		this.movieTypeEntity = movieTypeEntity;
	}

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public MovieVersionEntity getMovieVersionEntity() {
        return movieVersionEntity;
    }

    public void setMovieVersionEntity(MovieVersionEntity movieVersionEntity) {
        this.movieVersionEntity = movieVersionEntity;
    }

	public int getChildCount() {
		return childCount;
	}

	public void setChildCount(int childCount) {
		this.childCount = childCount;
	}
}
