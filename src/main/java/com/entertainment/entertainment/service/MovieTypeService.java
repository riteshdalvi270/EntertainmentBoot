package com.entertainment.entertainment.service;

import com.entertainment.entertainment.model.MovieTypeVo;
import com.entertainment.entertainment.model.MovieVo;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrDocument;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

/**
 * @author Ritesh Dalvi
 **/
public interface MovieTypeService {

    MovieTypeVo createMovieType(MovieTypeVo movieTypeRequest);

    MovieTypeVo getMovieType(int id);
    
    List<MovieTypeVo> getMovieTypes();

//    List<MovieTypeVo> getMovieTypeFromSolr() throws IOException, SolrServerException;
}
