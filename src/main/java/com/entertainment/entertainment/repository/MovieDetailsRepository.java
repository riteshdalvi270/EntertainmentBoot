package com.entertainment.entertainment.repository;

import com.entertainment.entertainment.entity.MovieDetailsEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author Ritesh Dalvi
 **/
@Repository
@Transactional(readOnly = true)
public interface MovieDetailsRepository extends org.springframework.data.repository.Repository<MovieDetailsEntity,Long> {

    @Modifying
    MovieDetailsEntity save(MovieDetailsEntity movieEntity);

    @Query("Select mde from MovieDetailsEntity mde where mde.movieName = :name " +
            "and mde.stopDate is null")
    Optional<MovieDetailsEntity> doesMovieExistWithName(@Param("name") String name);

    @Modifying
    @Query("Update MovieDetailsEntity mde set mde.stopDate = SYSDATE(), mde.modifiedDate = SYSDATE() "
    		+ "where mde.movieId = :id")
    void versionMovieVersionEntity(@Param("id") long id);
}
