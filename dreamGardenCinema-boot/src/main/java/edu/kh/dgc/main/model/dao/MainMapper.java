package edu.kh.dgc.main.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import edu.kh.dgc.movie.model.dto.Movie;
import edu.kh.dgc.notice.model.dto.Notice;

@Mapper
public interface MainMapper {

	List<Movie> selectMovieList();

	List<Notice> selectNoticeList();
	
	List<Map<String, String>> selectMainSlideImgList();
}
