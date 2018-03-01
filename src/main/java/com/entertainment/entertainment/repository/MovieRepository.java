package com.entertainment.entertainment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entertainment.entertainment.model.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
	

}
