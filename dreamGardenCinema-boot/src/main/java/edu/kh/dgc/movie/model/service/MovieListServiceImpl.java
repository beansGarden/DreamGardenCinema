package edu.kh.dgc.movie.model.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.kh.dgc.movie.model.dao.MovieListMapper;
import edu.kh.dgc.movie.model.dto.Movie;

@Service
public class MovieListServiceImpl implements MovieListService{
	
	@Autowired
	private MovieListMapper mapper;
	
	// 사이트 메인 슬라이드 이미지 조회
	@Override
	public List<Map<String, String>> selectMainSlideImgList() {
		return mapper.selectMainSlideImgList();
	}

	// 영화 메인페이지에 슬라이드에 쓰일 이미지 조회
	@Override
	public List<Map<String, String>> selectMovieMainSlideImgList() {
		return mapper.selectMovieMainSlideImgList() ;
	}
	
	// 영화 메인페이지에 쓰일 현재 상영작 조회 5개
	@Override
	public List<Movie> selectMovieListCurrentMain() {
		RowBounds rowBounds = new RowBounds(0, 5);
		
		return mapper.selectMovieListCurrent(null, rowBounds);
	}
	
	// 영화 메인페이지에 쓰일 상영 예정작 조회 5개
	@Override
	public List<Movie> selectMovieListPromiseMain() {
		RowBounds rowBounds = new RowBounds(0, 5);
		
		return mapper.selectMovieListPromise(null, rowBounds);
	}
	
	// 현재 상영작 조회
	@Override
	public List<Movie> selectMovieListCurrent() {
		return mapper.selectMovieListCurrent();
	}
	
	// 상영 예정작 조회
	@Override
	public List<Movie> selectMovieListPromise() {
		return mapper.selectMovieListPromise();
	}
	
	// 광고 포스터 조회(랜덤하게 하나)
	@Override
	public Map<String, String> selectAdvertisePoster() {
		
		
		
		return mapper.selectAdvertisePoster();
	}


	// 영화 정보 불러오기 (비동기)
	@Override
	public List<Movie> selectMovieList(String releaseType, String sortType) {
		if(releaseType.equals("current")) return mapper.selectMovieListCurrent(sortType);
		else return mapper.selectMovieListPromise();
	}

	// 예고편 가져오기 (비동기)
	@Override
	public String selectTrailer(String movieNo) {
		return mapper.selectTrailer(movieNo);
	}


	
	
}
