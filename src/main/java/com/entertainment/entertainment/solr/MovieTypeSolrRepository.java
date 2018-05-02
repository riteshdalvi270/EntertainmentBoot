/*
package com.entertainment.entertainment.solr;

import com.entertainment.entertainment.solr.entity.MovieTypeSolrEntity;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;

import java.util.List;

*/
/**
 * @author Ritesh Dalvi
 **//*

public interface MovieTypeSolrRepository extends SolrCrudRepository<MovieTypeSolrEntity,String> {

    @Query(fields = {"movieTypeId", "movieType"})
    List<MovieTypeSolrEntity> findByFields();
}
*/
