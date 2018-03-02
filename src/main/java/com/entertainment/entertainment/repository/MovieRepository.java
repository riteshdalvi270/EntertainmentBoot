package com.entertainment.entertainment.repository;

import java.util.List;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.entertainment.entertainment.model.Movies;

@Repository
@Transactional(readOnly=true)
public interface MovieRepository extends JpaRepository<Movies, Long> {
	
	@Query("Select m.id from Movie m where m.movieName = :name")
	public Optional<Movies> doesMovieExistWithId(@Param("name") String name);
	
	@Modifying
	@Query("Update Movie m set m.stopDate = NOW() where m.movieName = :name and m.stopDate=null")
	public int updateMovieStopDate(@Param("name") String name);
	
	@Query("Select m from Movie m where m.movieName = :name and m.stopDate=null")
	public Optional<Movies> getMovie(@Param("name") String name);
	
	@Query("Select m from Movie m where m.stopDate = null")
	public List<Movies> getMovies();
}
