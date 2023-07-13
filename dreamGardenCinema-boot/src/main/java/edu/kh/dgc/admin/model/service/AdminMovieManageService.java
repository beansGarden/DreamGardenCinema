package edu.kh.dgc.admin.model.service;

import java.util.Map;

import edu.kh.dgc.movie.model.dto.Movie;

public interface AdminMovieManageService {

	Map<String, Object> selectList(Map<String, Object> requestData);

	Map<String, Object> movieSelectOne(int movieNo);

	int createMovieInfo(Movie createMovieInfo);

	int deleteMovie(int movieNo);

//	int updatePoster(int movieNo, String updateMovieTitle, MultipartFile updatePoster);
	
}
