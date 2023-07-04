package edu.kh.dgc.movie.model.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.kh.dgc.movie.model.dao.MovieDetailMapper;
import edu.kh.dgc.movie.model.dto.Movie;
import edu.kh.dgc.movie.model.dto.MovieComment;
import edu.kh.dgc.movie.model.dto.Person;

@Service
public class MovieDetailServiceImpl implements MovieDetailService {

	@Autowired
	private MovieDetailMapper mapper;

	// 영화 상세 정보 불러오기
	@Override
	public Movie selectMovieDetail(int movieNo, String screen) {
		
		if(screen.equals("current")) return mapper.selectMovieDetailCurrent(movieNo);
		else return mapper.selectMovieDetailPromise(movieNo);
		
	}

	// 영화인 정보 불러오기
	@Override
	public List<Person> selectMoviePerson(int movieNo) {
		return mapper.selectMoviePerson(movieNo);
	}

	@Override
	public List<String> selectMovieDirectorName(int movieNo) {
		return mapper.selectMovieDirectorName(movieNo);
	}

	@Override
	public List<String> selectMovieActorName(int movieNo) {
		RowBounds rowBounds = new RowBounds(0, 4);
		return mapper.selectMovieActorName(movieNo, rowBounds);
	}

	@Override
	public List<String> selectMovieStillCut(int movieNo) {
		return mapper.selectMovieStillCut(movieNo);
	}

	@Override
	public List<MovieComment> selectMovieComment(int movieNo) {
		return mapper.selectMovieComment(movieNo);
	}



}
