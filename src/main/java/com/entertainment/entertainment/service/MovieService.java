package com.entertainment.entertainment.service;

import java.util.List;
import java.util.Optional;

import com.entertainment.entertainment.model.Movies;
import com.entertainment.entertainment.request.MovieTypeRequest;
import com.entertainment.entertainment.model.MovieType;
import com.entertainment.entertainment.model.MovieVo;

public interface MovieService {

	public List<Movies> persist(MovieVo movieVo);
	
	public List<Movies> getMovies();
	
	public Optional<Movies> getMovie(String name);
	
	public Movies update(String name, Movies updatedMovie);
	
	public MovieType createMovieType(MovieTypeRequest movieTypeRequest);
}
