package com.entertainment.entertainment.service.impl;

import com.entertainment.entertainment.dao.MovieTypeDao;
import com.entertainment.entertainment.entity.MovieTypeEntity;
import com.entertainment.entertainment.model.MovieTypeVo;
import com.entertainment.entertainment.repository.MovieTypeRepository;
import com.entertainment.entertainment.service.MovieTypeService;
import com.entertainment.entertainment.solr.MovieEntertainmentSolr;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
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


    @Autowired
    MovieEntertainmentSolr movieEntertainmentSolr;

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

    @Override
    public List<MovieTypeVo> getMovieTypeFromSolr() throws IOException, SolrServerException {

        SolrDocument entries = movieEntertainmentSolr.retrieveFromSolr();

        Collection<Object> movieTypeIds = entries.getFieldValues("movieTypeId");
        Collection<Object> movieTypes = entries.getFieldValues("movieType");

        return buildMovieTypeVO(movieTypeIds,movieTypes);
    }


    private MovieTypeVo buildMovieType(MovieTypeEntity savedMovieType) {

        final MovieTypeVo movieTypeVo = new MovieTypeVo();

        movieTypeVo.setId(savedMovieType.getId());
        movieTypeVo.setType(savedMovieType.getType());
        movieTypeVo.setDeleted(savedMovieType.isDeleted());

        return movieTypeVo;
    }

    private List<MovieTypeVo> buildMovieTypeVO(Collection<Object> movieTypeIds,Collection<Object> movieTypes) {

        final List<MovieTypeVo> movieTypeVos = new ArrayList<>();

        Iterator<Object> movieTypeIdIterator = movieTypeIds.iterator();

        int counter = 0;

        while(movieTypeIdIterator.hasNext()) {

            String id = (String) movieTypeIdIterator.next();

            final MovieTypeVo movieTypeVo = new MovieTypeVo();
            movieTypeVo.setId(Integer.valueOf(id));

            movieTypeVos.add(movieTypeVo);
            counter++;
        }


        Iterator<Object> movieTypeIterator = movieTypes.iterator();

        int i = 0;
        while (movieTypeIterator.hasNext()) {

            String movieType = (String) movieTypeIdIterator.next();

            if(i > counter) {
                break;
            }

            MovieTypeVo movieTypeVo = movieTypeVos.get(i);
            movieTypeVo.setType(movieType);
            movieTypeVos.add(i,movieTypeVo);

            i++;
        }


        return movieTypeVos;
    }
}
