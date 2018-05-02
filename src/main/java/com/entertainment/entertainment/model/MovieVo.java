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

    @JsonProperty("movie_type_id")
    private int typeId;

    @JsonProperty("director")
    private String directorName;

    @JsonProperty("description")
    private String description;

    @JsonProperty("end_date")
    private String endDate;

    @JsonProperty("done_watching")
    private boolean doneWatching;

    @JsonProperty("watch_date")
    private String watchDate;

    @JsonProperty("child_count")
    private int childCount;

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

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public boolean isDoneWatching() {
        return doneWatching;
    }

    public void setDoneWatching(boolean doneWatching) {
        this.doneWatching = doneWatching;
    }

    public String getWatchDate() {
        return watchDate;
    }

    public void setWatchDate(String watchDate) {
        this.watchDate = watchDate;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public int getChildCount() {
        return childCount;
    }

    public void setChildCount(int childCount) {
        this.childCount = childCount;
    }
}
