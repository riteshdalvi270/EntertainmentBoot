package com.entertainment.entertainment.repository;

import com.entertainment.entertainment.entity.MovieVersionEntity;
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
public interface MovieVersionRepository extends org.springframework.data.repository.Repository<MovieVersionEntity,Long> {

    @Modifying
    MovieVersionEntity save(MovieVersionEntity movieEntity);

    @Query("Select mve from MovieVersionEntity mve where mve.movieName = :name " +
            "and mve.endDate is null")
    Optional<MovieVersionEntity> doesMovieExistWithName(@Param("name") String name);
}
