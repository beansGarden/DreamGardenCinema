package edu.kh.dgc.main.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.kh.dgc.main.model.dao.MainMapper;
import edu.kh.dgc.movie.model.dto.Movie;
import edu.kh.dgc.notice.model.dto.Notice;

@Service
public class MainServiceImpl implements MainService{

	@Autowired
	private MainMapper mapper;
	
	@Override
	public Map<String, Object> selectMovieList() {
		
		Map<String, Object> map = new HashMap<>();
		
		/* List<Movie> movieList = mapper.selectMovieList(); */
		List<Notice> noticeList = mapper.selectNoticeList();
		/* map.put("movieList", movieList); */
		map.put("noticeList", noticeList);
		return map;
	}	
	
	// 메인 슬라이더 이미지 얻어오기
	@Override
	public List<Map<String, String>> selectMainSlideImgList() {
		return mapper.selectMainSlideImgList();
	}

}
