package edu.kh.dgc.main.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.kh.dgc.main.model.dao.MainMapper;
import edu.kh.dgc.movie.model.dto.Movie;

@Service
public class MainServiceImpl implements MainService{

	@Autowired
	private MainMapper mapper;
	
	@Override
	public List<Movie> selectMovieList() {
		return mapper.selectMovieList();
	}

}
