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

@Service
public class AdminMovieManageServiceImpl implements AdminMovieManageService{
	
	@Autowired
	private AdminMovieManageMapper mapper;
	
	// 영화 List 조회
		@Override
		public Map<String, Object> adminMovieList(int cp) {
			
			int movieListCount = mapper.movieListCount();
			
			Pagination pagination = new Pagination(movieListCount, cp);

			// 1) offset 계산
			int offset = (pagination.getCurrentPage() - 1) * pagination.getLimit();

			// 2) RowBounds 객체 생성
			RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());

			List<Movie> adminMovieList = mapper.adminMovieList(rowBounds);

			Map<String, Object> adminMovieMap = new HashMap<String, Object>();
			adminMovieMap.put("pagination", pagination);
			adminMovieMap.put("adminMovieList", adminMovieList);


			return adminMovieMap;
		}
		
		//영화전체 개수 가져오기
		@Override
		public int movieListCount() {
			
			int movieListCount = mapper.movieListCount();
			
			return movieListCount;
		}


		
		//영화검색
		@Override
		public Map<String, Object> getMovieSearchList(Movie condition, int cp) {

			int movieListCount = mapper.movieListCount();

			Pagination pagination = new Pagination(movieListCount, cp);

			// 3. 특정 게시판에서
			// 현재 페이지에 해당하는 부분에 대한 게시글 목록 조회
			// (어떤 게시판(boarCode)에서
			// 몇 페이지(pagination.currentPage)에 대한
			// 게시글 몇 개(pagination.limit) 조회)

			// 1) offset 계산
			int offset = (pagination.getCurrentPage() - 1) * pagination.getLimit();

			// 2) RowBounds 객체 생성
			RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());

			List<Movie> adminMovieList = mapper.getMovieSearchList(condition,rowBounds);

			Map<String, Object> adminMovieMap = new HashMap<String, Object>();
			adminMovieMap.put("pagination", pagination);
			adminMovieMap.put("adminMovieList", adminMovieList);

			return adminMovieMap;
		}
			
	
}
