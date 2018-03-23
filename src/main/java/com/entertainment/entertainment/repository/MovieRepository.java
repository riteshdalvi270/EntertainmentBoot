package com.entertainment.entertainment.repository;

import java.util.List;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.entertainment.entertainment.entity.MovieEntity;

@Repository
@Transactional(readOnly=true)
public interface MovieRepository extends JpaRepository<MovieEntity, Long> {
	
	@Query("Select m.id from Movie m inner join fetch m.movies ms where ms.name = :name and m.isDeleted = 0 and m.endDate is null")
	long doesMovieExistWithName(@Param("name") String name);
	
	/*@Modifying
	@Query("Update Movie m inner join m.movies ms set ms.endDate = NOW() and m.modifiedDate = NOW() and " +
			"ms.modifiedDate = NOW() and where m.id = :id and ms.endDate is null")
	int updateMovieEndDate(@Param("id") long id);*/
	
	@Query("Select m from Movie m inner join fetch m.movies ms where m.id = :id and m.isDeleted=0 and ms.endDate is null")
	Optional<MovieEntity> getMovie(@Param("id") long id);
	
	@Query("Select m from Movie m inner join fetch m.movies ms where m.isDeleted=0 and ms.endDate is null")
	List<MovieEntity> getMovies();
}
