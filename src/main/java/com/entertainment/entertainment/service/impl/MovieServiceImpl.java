package com.entertainment.entertainment.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.entertainment.entertainment.model.MovieVo;
import com.entertainment.entertainment.repository.MovieVersionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.entertainment.entertainment.entity.MovieEntity;
import com.entertainment.entertainment.entity.MovieTypeEntity;
import com.entertainment.entertainment.entity.MovieVersionEntity;
import com.entertainment.entertainment.repository.MovieRepository;
import com.entertainment.entertainment.service.MovieService;

@Service
public class MovieServiceImpl implements MovieService{

	@Autowired
	MovieRepository movieRepository;

	@Autowired
	MovieVersionRepository movieVersionRepository;

	@Override
	@Transactional
	public MovieVo persist(MovieVo movie) throws Exception {

		long doesExist = movieRepository.doesMovieExistWithName(movie.getMovieName());

		if(doesExist != 0) {

			throw new Exception("Movie already exist");
		}

		final MovieEntity movieEntity = populateMovieEntity(movie);

		MovieEntity savedMovieEntity = movieRepository.save(movieEntity);

		return buildMovieVo(savedMovieEntity);
	}

	private MovieEntity populateMovieEntity(MovieVo movieVo) {

		final MovieEntity movie = new MovieEntity();
		movie.setDeleted(false);

		final MovieVersionEntity movieVersionEntity = new MovieVersionEntity();

		movieVersionEntity.setDescription(movieVo.getDescription());
		movieVersionEntity.setMovieName(movieVo.getMovieName());
		movieVersionEntity.setDoneWatching(movieVo.isDoneWatching());
		movieVersionEntity.setWatchDate(movieVo.getWatchDate());
		movieVersionEntity.setModifiedDate(new Date());

		movie.setMovie(movieVersionEntity);

		return movie;
	}

	private MovieVo buildMovieVo(final MovieEntity savedMovieEntity) {

		final MovieVo movieVo = new MovieVo();
		movieVo.setId(savedMovieEntity.getId());
		movieVo.setDeleted(savedMovieEntity.isDeleted());

		MovieTypeEntity movieType = savedMovieEntity.getMovieType();

		movieVo.setMovieType(movieType.getType());

		MovieVersionEntity movie = savedMovieEntity.getMovie();

		movieVo.setMovieName(movie.getMovieName());
		movieVo.setDescription(movie.getDescription());
		movieVo.setDirectorName(movie.getDirectorName());
		movieVo.setDoneWatching(movie.isDoneWatching());
		movieVo.setWatchDate(movie.getWatchDate());
		movieVo.setEndDate(movie.getEndDate());

		return movieVo;
	}

/*	// need to update.
	@Override
	@Transactional
	public Movie update(long id, MovieVersion updatedMovie) {

		Optional<Movie> optionalMovie = getMovie(id);

		if(!optionalMovie.isPresent()) {

			// throw an exception
		}

		Movie existingMovie = optionalMovie.get();

		movieRepository.updateMovieEndDate(existingMovie.getId());

		MovieVersion movieVersion = new MovieVersion();

	}*/

	@Override
	@Transactional(readOnly=true)
	public List<MovieVo> getMovies() {

		List<MovieEntity> moviesEntity = movieRepository.getMovies();

		final List<MovieVo> movies = new ArrayList<>();

		for(final MovieEntity movieEntity : moviesEntity) {

			final MovieVo movieVo = new MovieVo();

			movieVo.setId(movieEntity.getId());
			movieVo.setDeleted(movieEntity.isDeleted());

			final MovieVersionEntity movie = movieEntity.getMovie();

			movieVo.setMovieName(movie.getMovieName());
			movieVo.setDescription(movie.getDescription());
			movieVo.setEndDate(movie.getEndDate());
			movieVo.setWatchDate(movie.getWatchDate());
			movieVo.setDoneWatching(movie.isDoneWatching());

			MovieTypeEntity movieTypeEntity = movieEntity.getMovieType();

			movieVo.setMovieType(movieTypeEntity.getType());
		}
		return movies;
	}

	@Override
	@Transactional(readOnly=true)
	public MovieVo getMovie(long id) {

		Optional<MovieEntity> optionalMovie = movieRepository.getMovie(id);

		if(!optionalMovie.isPresent()) {
			// throw an exception
		}

		return buildMovieVo(optionalMovie.get());
	}
}
