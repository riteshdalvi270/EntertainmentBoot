package com.entertainment.entertainment.dao;

import com.entertainment.entertainment.entity.MovieTypeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Ritesh Dalvi
 **/
@Repository
public class MovieTypeDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    class MovieTypeEntityRowMapper implements RowMapper<MovieTypeEntity> {

        @Override
        public MovieTypeEntity mapRow(ResultSet rs, int rowNum) throws SQLException {

            final MovieTypeEntity movieTypeEntity = new MovieTypeEntity();

            movieTypeEntity.setId(rs.getInt("movie_type_id"));
            movieTypeEntity.setDeleted(rs.getBoolean("is_deleted"));
            movieTypeEntity.setType(rs.getString("type"));

            return movieTypeEntity;
        }
    }


        public List<MovieTypeEntity> findAll() {

            return jdbcTemplate.query("Select * from movie_type",new MovieTypeEntityRowMapper());
        }

        public MovieTypeEntity findById(int id) {

            return jdbcTemplate.queryForObject("Select * from movie_type where movie_type_id=?",
                    new Object[] {id},new BeanPropertyRowMapper<>(MovieTypeEntity.class));
        }
    }
