package edu.kh.dgc.movie.model.service;

import edu.kh.dgc.movie.model.dto.Movie;

public interface CrawlingService {
	
	/** movie를 DB에 넣기
	 * @param movie
	 */
	public int insertMovieInfo(Movie movie);

}
