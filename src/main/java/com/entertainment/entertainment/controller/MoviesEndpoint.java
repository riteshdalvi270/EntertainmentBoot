package com.entertainment.entertainment.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entertainment.entertainment.model.Movies;
import com.entertainment.entertainment.request.MovieTypeRequest;
import com.entertainment.entertainment.model.MovieType;
import com.entertainment.entertainment.model.MovieVo;
import com.entertainment.entertainment.service.MovieService;

@RestController
@RequestMapping("/movies")
public class MoviesEndpoint {

	@Autowired
	private MovieService movieService;
	
	@PostMapping
	public ResponseEntity<?> addMovieType(@Valid @RequestBody MovieTypeRequest movieTypeRequest) {
		
		MovieType movieType = movieService.createMovieType(movieTypeRequest);
		
		return ResponseEntity.ok(movieType);
	}
	
	@GetMapping
	public ResponseEntity<?> getMovies() {
		
		 List<Movies> movies = movieService.getMovies();
		 
		 if(movies == null || movies.isEmpty()) {
			 ResponseEntity.noContent().build();
		 }
		 
		 return ResponseEntity.ok(movies);
	}
	
	@PostMapping
	public ResponseEntity<?> addMovies(@Valid @RequestBody MovieVo movies) {
		
		final MovieVo movieVo = new MovieVo();
		movieVo.setMovies(movieService.persist(movies));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(movieVo);
	}
	
	@GetMapping(value="{name}")
	public ResponseEntity<?> getMovie(@PathVariable String name) {
		
		System.out.println(name);
		Optional<Movies> optionalMovie = movieService.getMovie(name);
		
		if(optionalMovie.isPresent()) {
			
			 return ResponseEntity.ok(optionalMovie.get());
		}
		
		return ResponseEntity.noContent().build();
			
	}
	
	@PutMapping(value="{name}")
	public ResponseEntity<?> updateMovie(@PathVariable String name,@Valid @RequestBody Movies movie) {
		
		Movies updatedMovieRecord = movieService.update(name,movie);
			
		return ResponseEntity.status(HttpStatus.OK).body(updatedMovieRecord);
	}
}
