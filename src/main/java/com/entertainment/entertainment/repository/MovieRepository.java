package com.entertainment.entertainment.repository;

import java.util.List;
import java.util.Optional;


import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.entertainment.entertainment.entity.MovieEntity;

@Repository
@Transactional(readOnly=true)
public interface MovieRepository extends org.springframework.data.repository.Repository<MovieEntity, Long> {

    @Modifying
    MovieEntity save(MovieEntity movieEntity);
	
	@Query("SELECT me from MovieEntity me inner join fetch me.movieVersionEntity as mve where me.id = :id and " +
            "me.isDeleted=0 and mve.endDate is null")
	Optional<MovieEntity> getMovie(@Param("id") long id);
	
	@Query("Select me from MovieEntity me inner join fetch me.movieVersionEntity as mve where me.isDeleted=0 and mve.endDate is null")
	List<MovieEntity> getMovies();
}
