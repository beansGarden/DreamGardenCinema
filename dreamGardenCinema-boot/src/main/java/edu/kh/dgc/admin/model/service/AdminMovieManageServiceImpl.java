package edu.kh.dgc.admin.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.kh.dgc.admin.model.dao.AdminMovieManageMapper;
import edu.kh.dgc.admin.model.dto.Pagination;
import edu.kh.dgc.movie.model.dto.Movie;
import edu.kh.dgc.movie.model.dto.Person;

@Service
public class AdminMovieManageServiceImpl implements AdminMovieManageService{
	
	@Autowired
	private AdminMovieManageMapper mapper;

	@Override
	public Map<String, Object> selectList(Map<String, Object> requestData) {
		
		String screenType = (String) requestData.get("screenType");
		int currentPage = (int) requestData.get("currentPage");
		
		int listCount = mapper.getListCount(screenType);
		
		Pagination pagination = new Pagination(listCount, currentPage);
		
		int offset 
		= (pagination.getCurrentPage() - 1) * pagination.getLimit();
		
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());
		
		List<Movie> selectList = mapper.selectList(requestData, rowBounds);
		
		Map<String, Object> responseData = new HashMap<>();
		responseData.put("pagination", pagination);
		responseData.put("selectList", selectList);
		
		return responseData;
	}

	@Override
	public Map<String, Object> movieSelectOne(int movieNo) {
		
		Map<String, Object> resp = new HashMap<>();
		
		Movie movieInfo = mapper.movieSelectOne(movieNo);
		List<String> movieStillcut= mapper.selectMovieStillCut(movieNo);
		List<Person> moviePerson = mapper.selectMoviePerson(movieNo);
		
		resp.put("movieInfo", movieInfo);
		resp.put("movieStillcut", movieStillcut);
		resp.put("moviePerson", moviePerson);
		
		return resp;
	}
	
	
	
}
