package com.entertainment.entertainment.service.impl;

import com.entertainment.entertainment.entity.MovieTypeEntity;
import com.entertainment.entertainment.model.MovieTypeVo;
import com.entertainment.entertainment.repository.MovieTypeRepository;
import com.entertainment.entertainment.service.MovieTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Ritesh Dalvi
 **/
@Service
public class MovieTypeServiceImpl implements MovieTypeService {

    @Autowired
    MovieTypeRepository movieTypeRepository;

    @Override
    @Transactional
    public MovieTypeVo createMovieType(MovieTypeVo movieTypeRequest) {

        MovieTypeEntity movieType = new MovieTypeEntity();
        movieType.setType(movieTypeRequest.getType());

        MovieTypeEntity savedMovieType = movieTypeRepository.save(movieType);

        return buildMovieType(savedMovieType);
    }


    private MovieTypeVo buildMovieType(MovieTypeEntity savedMovieType) {

        final MovieTypeVo movieTypeVo = new MovieTypeVo();

        movieTypeVo.setId(savedMovieType.getId());
        movieTypeVo.setType(savedMovieType.getType());
        movieTypeVo.setDeleted(savedMovieType.isDeleted());

        return movieTypeVo;
    }
}
