package com.entertainment.entertainment.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.entertainment.entertainment.model.MovieTypeVo;
import com.entertainment.entertainment.model.MovieVo;
import com.entertainment.entertainment.service.MovieTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.entertainment.entertainment.entity.MovieEntity;
import com.entertainment.entertainment.service.MovieService;

@RestController
@RequestMapping("/movies")
public class MoviesEndpoint {

	@Autowired
	private MovieService movieService;

	@Autowired
	private MovieTypeService movieTypeService;
	
	@PostMapping(value = "movie_type")
	public ResponseEntity<?> addMovieType(@Valid @RequestBody MovieTypeVo movieTypeRequest) {
		
		MovieTypeVo movieType = movieTypeService.createMovieType(movieTypeRequest);
		
		return ResponseEntity.ok(movieType);
	}

	@GetMapping(value = "movie_type")
	public ResponseEntity<?> getMovieTypes() {

		List<MovieTypeVo> movieTypes = movieTypeService.getMovieTypes();

		return ResponseEntity.ok(movieTypes);
	}

	@GetMapping(value = "movie_type/{id}")
	public ResponseEntity<?> getMovieType(@PathVariable int id) {

		MovieTypeVo movieType = movieTypeService.getMovieType(id);

		return ResponseEntity.ok(movieType);
	}


	@PostMapping(value = "movie")
	public ResponseEntity<?> addMovies(@Valid @RequestBody MovieVo movie) {

		try {
			MovieVo newMovie = movieService.persist(movie);

			return ResponseEntity.status(HttpStatus.CREATED).body(newMovie);
		}catch(Exception e) {
			e.getStackTrace();
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

		MovieVo movie = null;
		try {
			movie = movieService.getMovie(id);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

		return ResponseEntity.ok(movie);
			
	}
	
	@PutMapping(value="{id}")
	public ResponseEntity<?> updateMovie(@PathVariable long id,@Valid @RequestBody MovieVo movieVo) {

		MovieVo updatedMovieRecord = null;

		try {
			updatedMovieRecord = movieService.update(id, movieVo);

			return ResponseEntity.status(HttpStatus.OK).body(updatedMovieRecord);
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
			

	}
}
