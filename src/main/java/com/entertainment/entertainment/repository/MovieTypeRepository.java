package com.entertainment.entertainment.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.entertainment.entertainment.entity.MovieTypeEntity;

@Repository
@Transactional(readOnly=true)
public interface MovieTypeRepository extends JpaRepository<MovieTypeEntity, Integer> {

}
