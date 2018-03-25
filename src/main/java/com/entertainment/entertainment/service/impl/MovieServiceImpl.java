package com.entertainment.entertainment.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.entertainment.entertainment.model.MovieVo;
import com.entertainment.entertainment.repository.MovieTypeRepository;
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

	@Autowired
	MovieTypeRepository movieTypeRepository;

	@Override
	@Transactional
	public MovieVo persist(MovieVo movie) throws Exception {


		Optional<MovieVersionEntity> movieVersionEntityOptional = movieVersionRepository.doesMovieExistWithName(movie.getMovieName());

		if(movieVersionEntityOptional.isPresent()) {

			throw new Exception("Movie already exist");
		}

		Optional<MovieTypeEntity> movieTypeEntityOptional = movieTypeRepository.getMoveType(movie.getTypeId());

		if(!movieTypeEntityOptional.isPresent()) {

			throw new Exception("Movie already exist");
		}

		final MovieEntity movieEntity = populateMovieEntity(movie,movieTypeEntityOptional.get());

		MovieEntity savedMovieEntity = movieRepository.save(movieEntity);

		final MovieVersionEntity movieVersionEntity = populateMovieVersionEntity(movie,movieEntity);

		MovieVersionEntity savedMovieVersionEntity = movieVersionRepository.save(movieVersionEntity);

		return buildMovieVo(savedMovieEntity,savedMovieVersionEntity);
	}

	private MovieVersionEntity populateMovieVersionEntity(final MovieVo movieVo, final MovieEntity movieEntity) throws ParseException {

		final MovieVersionEntity movieVersionEntity = new MovieVersionEntity();

		movieVersionEntity.setDescription(movieVo.getDescription());
		movieVersionEntity.setMovieName(movieVo.getMovieName());
		movieVersionEntity.setDoneWatching(movieVo.isDoneWatching());
		movieVersionEntity.setDirectorName(movieVo.getDirectorName());

		final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

		movieVersionEntity.setWatchDate(simpleDateFormat.parse(movieVo.getWatchDate()));
		movieVersionEntity.setModifiedDate(new Date());

		if(movieVo.getEndDate()!=null) {
			movieVersionEntity.setEndDate(simpleDateFormat.parse(movieVo.getEndDate()));
		}

		movieVersionEntity.setMovieEntity(movieEntity);

		return movieVersionEntity;
	}

	private MovieEntity populateMovieEntity(MovieVo movieVo, MovieTypeEntity movieTypeEntity)  {

		final MovieEntity movie = new MovieEntity();
		movie.setDeleted(false);
		movie.setMovieTypeEntity(movieTypeEntity);
		movie.setModifiedDate(new Date());

		return movie;
	}

	private MovieVo buildMovieVo(final MovieEntity savedMovieEntity, final MovieVersionEntity savedMovieVersionEntity) {

		final MovieVo movieVo = new MovieVo();
		movieVo.setId(savedMovieEntity.getId());
		movieVo.setDeleted(savedMovieEntity.isDeleted());

		MovieTypeEntity movieType = savedMovieEntity.getMovieTypeEntity();

		movieVo.setTypeId(movieType.getId());

		movieVo.setMovieName(savedMovieVersionEntity.getMovieName());
		movieVo.setDescription(savedMovieVersionEntity.getDescription());
		movieVo.setDirectorName(savedMovieVersionEntity.getDirectorName());
		movieVo.setDoneWatching(savedMovieVersionEntity.isDoneWatching());

		final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

		movieVo.setWatchDate(simpleDateFormat.format(savedMovieVersionEntity.getWatchDate()));

		if(savedMovieVersionEntity.getEndDate()!=null) {
			movieVo.setEndDate(simpleDateFormat.format(savedMovieVersionEntity.getEndDate()));
		}

		return movieVo;
	}

/*	// need to update.
	@Override
	@Transactional
	public Movie update(long id, MovieVersion updatedMovie) {

		Optional<Movie> optionalMovie = getMovieEntity(id);

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

			final MovieVersionEntity movie = movieEntity.getMovieVersionEntity();

			movieVo.setMovieName(movie.getMovieName());
			movieVo.setDescription(movie.getDescription());

			final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

			if(movie.getEndDate()!=null) {
				movieVo.setEndDate(simpleDateFormat.format(movie.getEndDate()));
			}
			movieVo.setWatchDate(simpleDateFormat.format(movie.getWatchDate()));

			movieVo.setDoneWatching(movie.isDoneWatching());

			MovieTypeEntity movieTypeEntity = movieEntity.getMovieTypeEntity();

			movieVo.setTypeId(movieTypeEntity.getId());

			movies.add(movieVo);
		}
		return movies;
	}

	@Override
	@Transactional(readOnly=true)
	public MovieVo getMovie(long id) throws Exception {

		Optional<MovieEntity> optionalMovie = movieRepository.getMovie(id);

		if(!optionalMovie.isPresent()) {
			 throw new Exception("Movie does not exist with the id passed");
		}

		// need to fix
		return buildMovieVo(optionalMovie.get(),optionalMovie.get().getMovieVersionEntity());
	}
}
