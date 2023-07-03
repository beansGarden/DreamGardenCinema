package edu.kh.dgc.movie.model.service;

import java.util.Map;

import edu.kh.dgc.movie.model.dto.MovieComment;

public interface MovieCommentService {
	
	int insertMovieComment(MovieComment comment);

	int insertMovieCommentReport(Map<String, Object> report);

}
