package edu.kh.dgc.movie.model.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import edu.kh.dgc.movie.model.dto.Movie;
import edu.kh.dgc.movie.model.dto.Person;

@Mapper
public interface CrawlingMapper {
	
	int insertMovieInfo(Movie movie);

	int selectMovieNoByTitle(String movieTitle);

	int selectHavingMovieNoByTitle(String movieTitle);

	int insertMoviePerson(Person person);

	int insertMovieStillCut(Map<String, Object> img);
	
}
