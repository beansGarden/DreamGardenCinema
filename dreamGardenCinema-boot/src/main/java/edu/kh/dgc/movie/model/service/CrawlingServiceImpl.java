package edu.kh.dgc.movie.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.kh.dgc.movie.model.dao.CrawlingMapper;
import edu.kh.dgc.movie.model.dto.Movie;

@Service
public class CrawlingServiceImpl implements CrawlingService {
	
	@Autowired
	private CrawlingMapper mapper;
	
	@Override
	public int insertMovieInfo(Movie movie) {
		return mapper.insertMovieInfo(movie);
	}

	@Override
	public int selectMovieNoByTitle(String movieTitle) {
		return mapper.selectMovieNoByTitle(movieTitle);
	}

	@Override
	public int selectHavingMovieNoByTitle(String movieTitle) {
		return mapper.selectHavingMovieNoByTitle(movieTitle);
	}
	
	

	
}
