package edu.kh.dgc.admin.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import edu.kh.dgc.movie.model.dto.Movie;

@Mapper
public interface AdminMovieManageMapper {

	List<Movie> selectMovieListCurrent();
	
//	List<Movie> selectMovieListCurrent(Object object, RowBounds rowBound);

	List<Movie> selectList(String screenType);


	
}
