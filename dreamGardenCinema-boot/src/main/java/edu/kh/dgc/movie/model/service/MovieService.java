package edu.kh.dgc.movie.model.service;

import java.util.List;
import java.util.Map;

import edu.kh.dgc.movie.model.dto.Movie;
import edu.kh.dgc.movie.model.dto.Person;

public interface MovieService {
	
	/** 사이트 메인 페이지 슬라이드 이미지 조회
	 * @return
	 */
	List<Map<String, String>> selectMainSlideImgList();
	
	List<Movie> selectMovieListCurrent();
	
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
	List<Movie> selectMovieListCurrent(int currentPage);

	/** 상영 예정작 조회
	 * @return List<Movie>
	 */
	List<Movie> selectMovieListPromise(int currentPage);

	/** 광고 포스터 조회(랜덤하게 하나)
	 * @return
	 */
	Map<String, String> selectAdvertisePoster();

	/** 영화 정보 불러오기(비동기)
	 * @return
	 */
	List<Movie> selectMovieList(int currentPage, String movieType);
	
	
	/** 영화 상세 정보 불러오기
	 * @param movieNo
	 * @return
	 */
	Movie selectMovieDetail(int movieNo);

	/** 영화와 관련된 영화인 정보 불러오기
	 * @param movieNo
	 * @return
	 */
	List<Person> selectMoviePerson(int movieNo);

	/** 영화 감독 이름 얻어오기
	 * @param movieNo
	 * @return
	 */
	List<String> selectMovieDirectorName(int movieNo);

	/** 영화 출연진 이름 얻어오기
	 * @param movieNo
	 * @return
	 */
	List<String> selectMovieActorName(int movieNo);

	/** 영화 스틸컷 불러오기
	 * @param movieNo
	 * @return
	 */
	List<String> selectMovieStillCut(int movieNo);

	


}
