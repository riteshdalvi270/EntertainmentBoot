package com.entertainment.entertainment.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.entertainment.entertainment.model.MovieTypeVo;
import com.entertainment.entertainment.model.MovieVo;
import com.entertainment.entertainment.service.MovieTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entertainment.entertainment.entity.MovieEntity;
import com.entertainment.entertainment.service.MovieService;

@RestController
@RequestMapping("/movies")
public class MoviesEndpoint {

	@Autowired
	private MovieService movieService;

	@Autowired
	private MovieTypeService movieTypeService;
	
	@PostMapping
	public ResponseEntity<?> addMovieType(@Valid @RequestBody MovieTypeVo movieTypeRequest) {
		
		MovieTypeVo movieType = movieTypeService.createMovieType(movieTypeRequest);
		
		return ResponseEntity.ok(movieType);
	}

	@PostMapping
	public ResponseEntity<?> addMovies(@Valid @RequestBody MovieVo movie) {

		try {
			MovieVo newMovie = movieService.persist(movie);

			return ResponseEntity.status(HttpStatus.CREATED).body(newMovie);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GetMapping
	public ResponseEntity<?> getMovies() {
		
		 List<MovieVo> movies = movieService.getMovies();
		 
		 if(movies == null || movies.isEmpty()) {
			 ResponseEntity.noContent().build();
		 }
		 
		 return ResponseEntity.ok(movies);
	}
	
	@GetMapping(value="{id}")
	public ResponseEntity<?> getMovie(@PathVariable long id) {
		
		MovieVo movie = movieService.getMovie(id);
		
		return ResponseEntity.ok(movie);
			
	}
	
/*	@PutMapping(value="{id}")
	public ResponseEntity<?> updateMovie(@PathVariable long id,@Valid @RequestBody MovieVersion movieVersion) {
		
		Movie updatedMovieRecord = movieService.update(id,movieVersion);
			
		return ResponseEntity.status(HttpStatus.OK).body(updatedMovieRecord);
	}*/
}
