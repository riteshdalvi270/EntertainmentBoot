package com.entertainment.entertainment.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entertainment.entertainment.model.Movie;
import com.entertainment.entertainment.model.MovieVo;
import com.entertainment.entertainment.repository.MovieRepository;
import com.entertainment.entertainment.service.MovieService;

@Service
public class MovieServiceImpl implements MovieService{

	@Autowired
	MovieRepository movieRepository;
	
	@Override
	public void persist(MovieVo movieVo) {
		final List<Movie> movies = movieVo.getMovies();
		
		for(Movie movie : movies) {
			
			movieRepository.save(movie);
		}
	}
}
