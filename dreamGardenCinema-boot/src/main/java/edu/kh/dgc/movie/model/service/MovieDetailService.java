package edu.kh.dgc.movie.model.service;

import java.util.List;
import java.util.Map;

import edu.kh.dgc.movie.model.dto.Movie;
import edu.kh.dgc.movie.model.dto.MovieComment;
import edu.kh.dgc.movie.model.dto.Person;

public interface MovieDetailService {
	
	/** 영화 상세 정보 불러오기
	 * @param movieNo
	 * @param screen 
	 * @return
	 */
	Movie selectMovieDetail(int movieNo, String screen);

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

	/** 영화 댓글(리뷰) 불러오기
	 * @param movieNo
	 * @return
	 */
	List<MovieComment> selectMovieComment(int movieNo);
	
}
