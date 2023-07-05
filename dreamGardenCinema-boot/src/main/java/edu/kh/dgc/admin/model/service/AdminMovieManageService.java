package edu.kh.dgc.admin.model.service;

import java.util.List;

import edu.kh.dgc.movie.model.dto.Movie;

public interface AdminMovieManageService {

	List<Movie> selectmovieListCurrent();

	List<Movie> selectList(String screenType);
	
}
