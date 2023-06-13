package edu.kh.project.main.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import edu.kh.project.movie.model.dto.Movie;

@Mapper
public interface MainMapper {

	List<Movie> selectMovieList();
}
