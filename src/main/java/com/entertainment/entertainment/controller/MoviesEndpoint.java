package com.entertainment.entertainment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.entertainment.entertainment.model.MovieVo;
import com.entertainment.entertainment.service.MovieService;

@Controller
@RequestMapping("/movies")
public class MoviesEndpoint {

	@Autowired
	private MovieService movieService;
	
	@PostMapping()
	public void addMovies(@RequestBody MovieVo movies) {
		
		movieService.persist(movies);
	}
}
