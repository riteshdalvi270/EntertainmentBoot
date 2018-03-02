package com.entertainment.entertainment.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.entertainment.entertainment.model.Movies;
import com.entertainment.entertainment.model.MovieType;
import com.entertainment.entertainment.model.MovieVo;
import com.entertainment.entertainment.repository.MovieRepository;
import com.entertainment.entertainment.repository.MovieTypeRepository;
import com.entertainment.entertainment.request.MovieTypeRequest;
import com.entertainment.entertainment.service.MovieService;

@Service
public class MovieServiceImpl implements MovieService{

	@Autowired
	MovieRepository movieRepository;
	
	@Autowired
	MovieTypeRepository movieTypeRepository;
	

	@Override
	public MovieType createMovieType(MovieTypeRequest movieTypeRequest) {
		
		MovieType movieType = new MovieType();
		movieType.setType(movieTypeRequest.getType());
		
		return movieTypeRepository.save(movieType);
	}
	
	@Override
	@Transactional
	public List<Movies> persist(MovieVo movieVo) {
		final List<Movies> movies = movieVo.getMovies();
		
		final List<Movies> result = new ArrayList<>();
		for(Movies movie : movies) {
			
			setDateValuesToNow(movie);

			result.add(movieRepository.save(movie));
		}
		
		return result;
	}

	@Override
	@Transactional(readOnly=true)
	public List<Movies> getMovies() {
		
		List<Movies> movies = movieRepository.getMovies();
		
		return movies;
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<Movies> getMovie(String name) {
		
		Optional<Movies> optionalMovie = movieRepository.getMovie(name);
		
		return optionalMovie;
	}

	@Override
	@Transactional
	public Movies update(String name, Movies updatedMovie) {
	
		setDateValuesToNow(updatedMovie);
		
		if(!name.equals(updatedMovie.getMovieName())) {
			
			throw new UnsupportedOperationException("Trying to update the record which has no matching provided name");
		}
		
		int movieStopDateUpdated = movieRepository.updateMovieStopDate(name);
		
		if(movieStopDateUpdated == 1) {
			
			return movieRepository.save(updatedMovie);
		}else {
			
			throw new RuntimeException("Failed to update the record, the original record did not exist");
		}
	}
	
	private void setDateValuesToNow(final Movies movie) {
		
		if(movie.getWatchDate() == null) {
			movie.setWatchDate(new Date());
		}
		
		if(movie.getModifiedDate() == null) {
			movie.setModifiedDate(new Date());
		}
	}
}
