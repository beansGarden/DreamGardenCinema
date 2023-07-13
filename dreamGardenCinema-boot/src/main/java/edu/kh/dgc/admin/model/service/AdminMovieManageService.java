package edu.kh.dgc.admin.model.service;

import java.io.IOException;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import edu.kh.dgc.movie.model.dto.Movie;

public interface AdminMovieManageService {

	Map<String, Object> selectList(Map<String, Object> requestData);

	Map<String, Object> movieSelectOne(int movieNo);

	int createMovieInfo(Movie createMovieInfo);

	int deleteMovie(int movieNo);
	// 영화정보 수정
	int updateMovie(Movie updateMovie, MultipartFile updatePoster) throws IllegalStateException, IOException ;

//	int updatePoster(int movieNo, String updateMovieTitle, MultipartFile updatePoster);
	
}
