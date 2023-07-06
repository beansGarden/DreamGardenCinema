package edu.kh.dgc.movie.model.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.kh.dgc.movie.model.dao.MovieCommentMapper;
import edu.kh.dgc.movie.model.dto.MovieComment;

@Service
public class MovieCommentServiceImpl implements MovieCommentService{
	
	@Autowired
	private MovieCommentMapper mapper;
	
	@Override
	public int insertMovieComment(MovieComment comment) {
		return mapper.insertMovieComment(comment);
	}

	@Override
	public int insertMovieCommentReport(Map<String, Object> report) {
		return mapper.insertMovieCommentReport(report);
	}
}
