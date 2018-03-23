package com.entertainment.entertainment.repository;

import com.entertainment.entertainment.entity.MovieVersionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Ritesh Dalvi
 **/
public interface MovieVersionRepository extends JpaRepository<MovieVersionEntity,Long> {

  /*  @Modifying
    @Query("Update MovieVersion mv set mv.endDate = NOW() and " +
            "mv.modifiedDate = NOW() and where mv.id = :id and ms.endDate is null")
    int updateMovieEndDate(@Param("id") long id);*/
}
