package edu.kh.dgc.movie.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import edu.kh.dgc.movie.model.dto.Movie;

@Mapper
public interface MovieMapper {
	
	public List<Map<String, String>> selectMovieMainSlideImgList();
	
	public List<Movie> selectMovieListCurrent(Object object, RowBounds rowBounds);
	
	public List<Movie> selectMovieListPromise(Object object, RowBounds rowBounds);

	public List<Movie> selectMovieListCurrent();

	public List<Movie> selectMovieListPromise();
	
	public Map<String, String> selectAdvertisePoster();

	


}
