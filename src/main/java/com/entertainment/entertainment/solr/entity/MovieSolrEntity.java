package com.entertainment.entertainment.solr.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

/**
 * @author Ritesh Dalvi
 **/
@SolrDocument(solrCoreName = "entertainmentboot")
public class MovieSolrEntity {

    @Id
    @Indexed(name = "movieTypeId", type = "string")
    private String movieTypeId;

    @Indexed(name = "movieType", type = "string")
    private String movieType;

    public String getMovieTypeId() {
        return movieTypeId;
    }

    public void setMovieTypeId(String movieTypeId) {
        this.movieTypeId = movieTypeId;
    }

    public String getMovieType() {
        return movieType;
    }

    public void setMovieType(String movieType) {
        this.movieType = movieType;
    }
}
