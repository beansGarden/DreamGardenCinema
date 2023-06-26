package edu.kh.dgc.movie.model.service;

import java.util.Map;

import edu.kh.dgc.movie.model.dto.Movie;
import edu.kh.dgc.movie.model.dto.Person;

public interface CrawlingService {
	
	/** movie를 DB에 넣기
	 * @param movie
	 */
	public int insertMovieInfo(Movie movie);

	/** title로 movieNo찾기
	 * @param movieTitle 
	 * @return
	 */
	public int selectMovieNoByTitle(String movieTitle);

	/** 영화정보가 DB에 있는지 찾아보기
	 * @param movieTitle
	 * @return
	 */
	public int selectHavingMovieNoByTitle(String movieTitle);

	/** 영화인 DB INSERT
	 * @param person
	 */
	public int insertMoviePerson(Person person);

	/** 영화 스틸컷 DB INSERT
	 * @param img
	 * @return
	 */
	public int insertMovieStillCut(Map<String, Object> img);
	
	
	
}
