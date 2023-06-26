package edu.kh.dgc.movie.model.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.kh.dgc.movie.model.dao.MovieMapper;
import edu.kh.dgc.movie.model.dto.Movie;
import edu.kh.dgc.movie.model.dto.Person;

@Service
public class MovieServiceImpl implements MovieService{
	
	@Autowired
	private MovieMapper mapper;
	
	// 사이트 메인 슬라이드 이미지 조회
	@Override
	public List<Map<String, String>> selectMainSlideImgList() {
		return mapper.selectMainSlideImgList();
	}
	
	

	@Override
	public List<Movie> selectMovieListCurrent() {
		// TODO Auto-generated method stub
		return mapper.selectMovieListCurrent();
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
	public List<Movie> selectMovieListCurrent(int currentPage) {
		RowBounds rowBounds = new RowBounds(currentPage, 10);
		return mapper.selectMovieListCurrent(null, rowBounds);
	}
	
	// 상영 예정작 조회
	@Override
	public List<Movie> selectMovieListPromise(int currentPage) {
		RowBounds rowBounds = new RowBounds(currentPage, 10);
		return mapper.selectMovieListPromise(null, rowBounds);
	}
	
	// 광고 포스터 조회(랜덤하게 하나)
	@Override
	public Map<String, String> selectAdvertisePoster() {
		return mapper.selectAdvertisePoster();
	}


	// 영화 정보 불러오기 (비동기)
	@Override
	public List<Movie> selectMovieList(int currentPage, String movieType) {
		
		RowBounds rowBounds = new RowBounds(currentPage*10, 10);
		
		if(movieType.equals("current")) return mapper.selectMovieListCurrent(null, rowBounds);
		else return mapper.selectMovieListPromise(null, rowBounds);
		
		
	}


	// 영화 상세 정보 불러오기
	@Override
	public Movie selectMovieDetail(int movieNo) {
		return mapper.selectMovieDetail(movieNo);
	}


	// 영화인 정보 불러오기
	@Override
	public List<Person> selectMoviePerson(int movieNo) {
		return mapper.selectMoviePerson(movieNo);
	}



	@Override
	public String selectMovieDirectorName(int movieNo) {
		return mapper.selectMovieDirectorName(movieNo);
	}



	@Override
	public List<String> selectMovieActorName(int movieNo) {
		RowBounds rowBounds = new RowBounds(0, 4);
		return mapper.selectMovieActorName(movieNo, rowBounds);
	}



	@Override
	public List<String> selectMovieStillCut(int movieNo) {
		return mapper.selectMovieStillCut(movieNo);
	}

	
	
	

	
	
}
