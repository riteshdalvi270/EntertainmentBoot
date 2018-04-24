package com.entertainment.entertainment.service;

import com.entertainment.entertainment.model.MovieTypeVo;
import com.entertainment.entertainment.model.MovieVo;

import java.util.List;

/**
 * @author Ritesh Dalvi
 **/
public interface MovieTypeService {

    MovieTypeVo createMovieType(MovieTypeVo movieTypeRequest);

    MovieTypeVo getMovieType(int id);

    List<MovieTypeVo> getMovieTypes();
}
