package edu.kh.dgc.admin.model.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.kh.dgc.admin.model.dao.AdminMovieManageMapper;
import edu.kh.dgc.movie.model.dto.Movie;

@Service
public class AdminMovieManageServiceImpl implements AdminMovieManageService{
	
	@Autowired
	private AdminMovieManageMapper mapper;

	@Override
	public List<Movie> selectList(Map<String, Object> requestData) {
		RowBounds rowBound = new RowBounds(0, 10);
		return mapper.selectList(requestData, rowBound);
	}
	
}
