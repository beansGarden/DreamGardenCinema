package edu.kh.dgc.admin.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import edu.kh.dgc.movie.model.dto.Movie;

@Mapper
public interface AdminMovieManageMapper {

	int getListCount(String screenType);
	
	List<Movie> selectList(Map<String, Object> requestData);
	
	List<Movie> selectList(Map<String, Object> requestData, RowBounds rowBound);

	Movie movieSelectOne(int movieNo);



	
}
