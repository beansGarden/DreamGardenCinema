package edu.kh.dgc.movie.model.dao;

import org.apache.ibatis.annotations.Mapper;

import edu.kh.dgc.movie.model.dto.Movie;

@Mapper
public interface CrawlingMapper {
	
	int insertMovieInfo(Movie movie);

	int selectMovieNoByTitle(String movieTitle);

	int selectHavingMovieNoByTitle(String movieTitle);
	
}
