package edu.kh.dgc.movie.model.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.kh.dgc.movie.model.dao.MovieMapper;
import edu.kh.dgc.movie.model.dto.Movie;

@Service
public class MovieServiceImpl implements MovieService{
	
	@Autowired
	private MovieMapper mapper;
	
	@Override
	public List<Movie> selectMovieListCurrentMain() {
		RowBounds rowBounds = new RowBounds(0, 4);
		
		return mapper.selectMovieListCurrent(null, rowBounds);
	}
	
	@Override
	public List<Movie> selectMovieListPromiseMain() {
		RowBounds rowBounds = new RowBounds(0, 4);
		
		return mapper.selectMovieListPromise(null, rowBounds);
	}
	
	@Override
	public List<Movie> selectMovieListCurrent() {
		return mapper.selectMovieListCurrent();
	}

	@Override
	public List<Movie> selectMovieListPromise() {
		return mapper.selectMovieListPromise();
	}

	
	
}
