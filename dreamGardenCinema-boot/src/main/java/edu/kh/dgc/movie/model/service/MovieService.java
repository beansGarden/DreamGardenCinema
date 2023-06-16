package edu.kh.dgc.movie.model.service;

import java.util.List;
import java.util.Map;

import edu.kh.dgc.movie.model.dto.Movie;

public interface MovieService {

	/** 영화 메인페이지에 슬라이드에 쓰일 이미지 조회
	 * @return Map<String, String>
	 */
	List<Map<String, String>> selectMovieMainSlideImgList();
	
	/** 영화 메인페이지에 쓰일 현재 상영작 조회 5개
	 * @return List<Movie>
	 */
	List<Movie> selectMovieListCurrentMain();
	
	/** 영화 메인페이지에 쓰일 상영 예정작 조회 5개
	 * @return List<Movie>
	 */
	List<Movie> selectMovieListPromiseMain();
	
	
	/** 현재 상영작 조회
	 * @return List<Movie>
	 */
	List<Movie> selectMovieListCurrent();

	/** 상영 예정작 조회
	 * @return List<Movie>
	 */
	List<Movie> selectMovieListPromise();

	


}
