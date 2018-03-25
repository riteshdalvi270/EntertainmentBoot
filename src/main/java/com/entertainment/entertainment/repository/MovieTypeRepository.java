package com.entertainment.entertainment.repository;


import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.entertainment.entertainment.entity.MovieTypeEntity;

import java.util.Optional;

@Repository
@Transactional(readOnly=true)
public interface MovieTypeRepository extends org.springframework.data.repository.Repository<MovieTypeEntity, Integer> {

    @Modifying
    MovieTypeEntity save(MovieTypeEntity movieTypeEntity);

    @Query("Select mt from MovieTypeEntity mt where mt.movieTypeId = :id")
    Optional<MovieTypeEntity> getMoveType(@Param("id") int id);
}
