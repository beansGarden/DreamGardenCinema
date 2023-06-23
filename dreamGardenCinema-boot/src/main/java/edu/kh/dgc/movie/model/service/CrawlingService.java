package edu.kh.dgc.movie.model.service;

import edu.kh.dgc.movie.model.dto.Movie;

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

}
