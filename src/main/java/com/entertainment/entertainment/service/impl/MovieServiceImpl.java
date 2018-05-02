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
import com.entertainment.entertainment.solr.MovieEntertainmentSolr;
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

		Optional<MovieTypeEntity> movieTypeEntityOptional = movieTypeRepository.getMoveTypeEntity(movie.getTypeId());

		if(!movieTypeEntityOptional.isPresent()) {

			throw new Exception("Movie already exist");
		}

		final MovieEntity movieEntity = populateMovieEntity(movieTypeEntityOptional.get());

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
			movieVersionEntity.setStopDate(simpleDateFormat.parse(movieVo.getEndDate()));
		}

		movieVersionEntity.setMovieEntity(movieEntity);

		return movieVersionEntity;
	}

	private MovieEntity populateMovieEntity(MovieTypeEntity movieTypeEntity)  {

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

		if(savedMovieVersionEntity.getStopDate()!=null) {
			movieVo.setEndDate(simpleDateFormat.format(savedMovieVersionEntity.getStopDate()));
		}

		return movieVo;
	}

	@Override
	@Transactional
	public MovieVo update(long id, MovieVo updatedMovie) throws ParseException {

        Optional<MovieTypeEntity> optionalMovieTypeEntity = movieTypeRepository.getMoveTypeEntity(updatedMovie.getTypeId());

        if(!optionalMovieTypeEntity.isPresent()) {

            throw new RuntimeException("Movie type does not exist");
        }

        Optional<MovieEntity> optionalMovieEntity = movieRepository.getMovie(id);

        if(!optionalMovieEntity.isPresent()) {
            throw new RuntimeException("Movie does not exist");
        }

        MovieEntity movieEntity = optionalMovieEntity.get();

        if(movieEntity.getMovieTypeEntity().getId()!=updatedMovie.getTypeId()) {

            MovieTypeEntity movieTypeEntity = optionalMovieTypeEntity.get();
            movieRepository.versionMovie(movieEntity.getId());

            final MovieEntity updatedMovieEntity = populateMovieEntity(movieTypeEntity);
            movieRepository.save(updatedMovieEntity);

            MovieVersionEntity movieVersionEntity = movieEntity.getMovieVersionEntity();
            movieVersionRepository.versionMovieVersionEntity(movieVersionEntity.getMovieId());

            return updateMovieVersion(updatedMovie,updatedMovieEntity);
        }

        MovieVersionEntity movieVersionEntity = movieEntity.getMovieVersionEntity();
        movieVersionRepository.versionMovieVersionEntity(movieVersionEntity.getMovieId());

        return updateMovieVersion(updatedMovie,movieEntity);
    }

    private MovieVo updateMovieVersion(final MovieVo updatedMovie, final MovieEntity movieEntity) throws ParseException {

        final MovieVersionEntity updatedMovieVersionEntity = populateMovieVersionEntity(updatedMovie,movieEntity);

        movieVersionRepository.save(updatedMovieVersionEntity);

        return buildMovieVo(movieEntity,updatedMovieVersionEntity);
    }

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

			if(movie.getStopDate()!=null) {
				movieVo.setEndDate(simpleDateFormat.format(movie.getStopDate()));
			}
			movieVo.setWatchDate(simpleDateFormat.format(movie.getWatchDate()));

			movieVo.setDoneWatching(movie.isDoneWatching());

			MovieTypeEntity movieTypeEntity = movieEntity.getMovieTypeEntity();

			movieVo.setTypeId(movieTypeEntity.getId());

			movieVo.setChildCount(movieEntity.getChildCount());

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
