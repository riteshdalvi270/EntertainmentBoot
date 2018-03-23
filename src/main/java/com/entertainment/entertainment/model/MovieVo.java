package com.entertainment.entertainment.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

@JsonIgnoreProperties(value= {"id","is_deleted"},allowGetters=true)
public class MovieVo {

    @JsonProperty("id")
    private long id;

    @JsonProperty("movie_name")
    private String movieName;

    @JsonProperty("is_deleted")
    private boolean isDeleted;

    @JsonProperty("movie_type")
    private String movieType;

    @JsonProperty("director")
    private String directorName;

    @JsonProperty("description")
    private String description;

    @JsonProperty("end_date")
    private Date endDate;

    @JsonProperty("done_watching")
    private boolean doneWatching;

    @JsonProperty("watch_date")
    private Date watchDate;

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

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public String getMovieType() {
        return movieType;
    }

    public void setMovieType(String movieType) {
        this.movieType = movieType;
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
}
