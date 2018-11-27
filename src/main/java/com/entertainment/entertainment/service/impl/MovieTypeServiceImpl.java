package com.entertainment.entertainment.service.impl;

import com.entertainment.entertainment.dao.MovieTypeDao;
import com.entertainment.entertainment.entity.MovieTypeEntity;
import com.entertainment.entertainment.model.MovieTypeVo;
import com.entertainment.entertainment.repository.MovieTypeRepository;
import com.entertainment.entertainment.service.MovieTypeService;
import jersey.repackaged.com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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

//    @Override
//    public List<MovieTypeVo> getMovieTypeFromSolr() {
//
//        List<MovieSolrEntity> movieTypeSolrEntities = movieTypeSolrRepository.findByFields("Horror", new PageRequest(0, 10));
//
//        return buildMovieTypeVO(movieTypeSolrEntities);
//    }


    private MovieTypeVo buildMovieType(MovieTypeEntity savedMovieType) {

        final MovieTypeVo movieTypeVo = new MovieTypeVo();

        movieTypeVo.setId(savedMovieType.getId());
        movieTypeVo.setType(savedMovieType.getType());
        movieTypeVo.setDeleted(savedMovieType.isDeleted());

        return movieTypeVo;
    }

//    private List<MovieTypeVo> buildMovieTypeVO(List<MovieSolrEntity> solrEntities) {
//
//        List<MovieTypeVo> movieTypeVos = Lists.newArrayList();
//
//        for(MovieSolrEntity movieTypeSolrEntity : solrEntities) {
//
//            final MovieTypeVo movieTypeVo = new MovieTypeVo();
//            movieTypeVo.setId(Integer.parseInt(movieTypeSolrEntity.getMovieTypeId()));
//            movieTypeVo.setType(movieTypeSolrEntity.getMovieType());
//
//            movieTypeVos.add(movieTypeVo);
//        }
//
//        return movieTypeVos;
//    }
}
