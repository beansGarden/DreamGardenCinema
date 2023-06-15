package edu.kh.dgc.movie.model.service;

import java.util.List;

import edu.kh.dgc.movie.model.dto.Movie;

public interface MovieService {

	
	List<Movie> selectMovieListCurrentMain();
	
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
