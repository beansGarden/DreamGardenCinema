package edu.kh.dgc.admin.model.service;

import java.util.Map;

import edu.kh.dgc.movie.model.dto.Movie;

public interface AdminMovieManageService {
	
	//영화 개수 가져오기
	int movieListCount();
	
	//영화 List 조회
	Map<String, Object> adminMovieList(int cp);

	//영화 검색
	Map<String, Object> getMovieSearchList(Movie condition, int cp);
	
}
