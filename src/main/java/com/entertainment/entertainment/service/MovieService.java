package com.entertainment.entertainment.service;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import com.entertainment.entertainment.model.MovieVo;

public interface MovieService {

	MovieVo persist(MovieVo movie) throws Exception;
	
	List<MovieVo> getMovies();
	
	MovieVo getMovie(long id) throws Exception;

	MovieVo update(long id, MovieVo movie) throws ParseException;

}
