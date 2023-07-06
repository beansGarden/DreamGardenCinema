package edu.kh.dgc.admin.model.service;

import java.util.List;
import java.util.Map;

import edu.kh.dgc.movie.model.dto.Movie;

public interface AdminMovieManageService {

	List<Movie> selectList(Map<String, Object> requestData);
	
}
