package edu.kh.dgc.movie.model.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.kh.dgc.movie.model.dao.CrawlingMapper;
import edu.kh.dgc.movie.model.dto.Movie;
import edu.kh.dgc.movie.model.dto.Person;

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

	@Override
	public int insertMoviePerson(Person person) {
		return mapper.insertMoviePerson(person);
	}

	@Override
	public int insertMovieStillCut(Map<String, Object> img) {
		return mapper.insertMovieStillCut(img);
	}
	
	

	
}
