package edu.kh.dgc.main.model.service;

import java.util.List;
import java.util.Map;

public interface MainService {

	Map<String, Object> selectMovieList();
	
	// 메인 슬라이더 이미지 얻어오기
	List<Map<String, String>> selectMainSlideImgList();

}
