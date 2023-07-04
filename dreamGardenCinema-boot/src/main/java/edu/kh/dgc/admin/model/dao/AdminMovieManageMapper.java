package edu.kh.dgc.admin.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import edu.kh.dgc.movie.model.dto.Movie;

@Mapper
public interface AdminMovieManageMapper {
	
	/**
	 * 영화 개수
	 * 
	 * @return
	 */
	int movieListCount();

	/**
	 * 영화 List 조회
	 * 
	 * @param rowBounds
	 * @return
	 */
	List<Movie> adminMovieList(RowBounds rowBounds);

	/**
	 * 영화 검색
	 * 
	 * @param condition
	 * @param rowBounds
	 * @return
	 */
	List<Movie> getMovieSearchList(Movie condition, RowBounds rowBounds);

	
}
