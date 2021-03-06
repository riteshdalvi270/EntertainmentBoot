package com.entertainment.entertainment.repository;

import java.util.List;
import java.util.Optional;


import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.entertainment.entertainment.entity.MovieEntity;

import javax.persistence.NamedNativeQuery;

@Repository
@Transactional(readOnly=true)
public interface MovieRepository extends org.springframework.data.repository.Repository<MovieEntity, Long> {

    @Modifying
    MovieEntity save(MovieEntity movieEntity);
	
	@Query("SELECT me from MovieEntity me inner join fetch me.movieDetailsEntity as mde where me.id = :id and " +
            "me.isDeleted=0 and mde.stopDate is null")
	Optional<MovieEntity> getMovie(@Param("id") long id);
	
	@Query("Select me from MovieEntity me inner join fetch me.movieDetailsEntity as mde where me.isDeleted=0 "
			+ "and mde.stopDate is null")
	List<MovieEntity> getMovies();

	@Query("UPDATE MovieEntity me set me.isDeleted=1 where me.id=:id")
	void versionMovie(@Param("id") long id);

	@Query(value = "select m.*, ct.ChildCount" +
			"from (" +
			"select mv.id, count(movie_id) as ChildCount " +
			"from movie_details mde " +
			"group by mde.id" +
			") as ct join movie m " +
			"on ct.id = m.id;",nativeQuery = true)
	List<MovieEntity> getMoviesWithCount();

}
