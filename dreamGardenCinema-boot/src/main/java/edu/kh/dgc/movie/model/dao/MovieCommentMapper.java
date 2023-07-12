package edu.kh.dgc.movie.model.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import edu.kh.dgc.movie.model.dto.MovieComment;

@Mapper
public interface MovieCommentMapper {
	
	public int insertMovieComment(MovieComment comment);

	public int insertMovieCommentReport(Map<String, Object> report);

}
