package edu.kh.dgc.admin.model.service;

import java.util.Map;

import edu.kh.dgc.movie.model.dto.Movie;

public interface AdminMovieManageService {

	Map<String, Object> selectList(Map<String, Object> requestData);

	Movie movieSelectOne(int movieNo);
	
}
