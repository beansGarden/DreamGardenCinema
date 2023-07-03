package edu.kh.dgc.movie.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import edu.kh.dgc.movie.model.dto.Movie;

@Mapper
public interface MovieListMapper {
	
	public List<Map<String, String>> selectMainSlideImgList();
	
	public List<Map<String, String>> selectMovieMainSlideImgList();
	
	public List<Movie> selectMovieListCurrent();
	
	public List<Movie> selectMovieListCurrent(Object object, RowBounds rowBounds);
	
	public List<Movie> selectMovieListPromise();
	
	public List<Movie> selectMovieListPromise(Object object, RowBounds rowBounds);

	public Map<String, String> selectAdvertisePoster();

}
