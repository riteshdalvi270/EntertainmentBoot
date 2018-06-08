
package com.entertainment.entertainment.solr;

import com.entertainment.entertainment.solr.entity.MovieSolrEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;

import java.util.List;

/**
 * @author Ritesh Dalvi
 **/

public interface MovieTypeSolrRepository extends SolrCrudRepository<MovieSolrEntity,String> {

    List<MovieSolrEntity> findByName(String name);

    @Query(name = "MovieType.findByNamedQuery")
    List<MovieSolrEntity> findByFields(String searchTerm, Pageable pageable);
}
