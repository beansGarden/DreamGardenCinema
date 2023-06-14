package edu.kh.dgc.movie.model.dao;

import org.apache.ibatis.annotations.Mapper;

import edu.kh.dgc.movie.model.dto.Movie;
import jakarta.transaction.Transactional;

@Mapper
public interface CrawlingMapper {
	
	@Transactional
	int insertMovieInfo(Movie movie);
	
	
	
}
