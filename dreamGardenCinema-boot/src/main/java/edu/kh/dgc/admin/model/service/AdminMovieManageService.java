package edu.kh.dgc.admin.model.service;

import java.util.Map;

public interface AdminMovieManageService {

	Map<String, Object> selectList(Map<String, Object> requestData);

	Map<String, Object> movieSelectOne(int movieNo);

//	int updatePoster(int movieNo, String updateMovieTitle, MultipartFile updatePoster);
	
}
