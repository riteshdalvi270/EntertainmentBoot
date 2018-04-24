package com.entertainment.entertainment.service.impl;

import com.entertainment.entertainment.dao.MovieTypeDao;
import com.entertainment.entertainment.entity.MovieTypeEntity;
import com.entertainment.entertainment.model.MovieTypeVo;
import com.entertainment.entertainment.model.MovieVo;
import com.entertainment.entertainment.repository.MovieTypeRepository;
import com.entertainment.entertainment.service.MovieTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ritesh Dalvi
 **/
@Service
public class MovieTypeServiceImpl implements MovieTypeService {

    @Autowired
    MovieTypeRepository movieTypeRepository;

    @Autowired
    MovieTypeDao movieTypeDao;

    @Override
    @Transactional
    public MovieTypeVo createMovieType(MovieTypeVo movieTypeRequest) {

        MovieTypeEntity movieType = new MovieTypeEntity();
        movieType.setType(movieTypeRequest.getType());

        MovieTypeEntity savedMovieType = movieTypeRepository.save(movieType);

        return buildMovieType(savedMovieType);
    }

    @Override
    public MovieTypeVo getMovieType(int id) {

        final MovieTypeEntity movieTypeEntity = movieTypeDao.findById(id);

        return buildMovieType(movieTypeEntity);
    }

    @Override
    public List<MovieTypeVo> getMovieTypes() {
        List<MovieTypeEntity> movieTypeEntities = movieTypeDao.findAll();

        final List<MovieTypeVo> movieTypeVos = new ArrayList<>();

        for(MovieTypeEntity movieTypeEntity: movieTypeEntities) {

            MovieTypeVo movieTypeVo = buildMovieType(movieTypeEntity);

            movieTypeVos.add(movieTypeVo);
        }

        return movieTypeVos;
    }


    private MovieTypeVo buildMovieType(MovieTypeEntity savedMovieType) {

        final MovieTypeVo movieTypeVo = new MovieTypeVo();

        movieTypeVo.setId(savedMovieType.getId());
        movieTypeVo.setType(savedMovieType.getType());
        movieTypeVo.setDeleted(savedMovieType.isDeleted());

        return movieTypeVo;
    }
}
