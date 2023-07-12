package edu.kh.dgc.admin.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kh.dgc.admin.model.dao.AdminMapper;
import edu.kh.dgc.admin.model.dto.SalesByPeriod;
import edu.kh.dgc.admin.model.dto.Pagination;
import edu.kh.dgc.admin.model.dto.Query;
import edu.kh.dgc.customerservice.model.dto.FAQ;
import edu.kh.dgc.movie.model.dto.Movie;
import edu.kh.dgc.notice.model.dto.Notice;
import edu.kh.dgc.qna.model.dto.Qna;
import edu.kh.dgc.qna.model.dto.QnaComment;
import edu.kh.dgc.report.model.dto.Report;
import edu.kh.dgc.review.model.dto.Review;
import edu.kh.dgc.ticketing.model.dto.Schedule;
import edu.kh.dgc.ticketing.model.dto.Ticket;
import edu.kh.dgc.user.model.dto.User;

/**
 * @author user1
 *
 */
@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminMapper mapper;

	
	//대시보드
	
	//대시보드 - 영화 현재 상영작 불러오기
	@Override
	public List<Movie> cinemaCurrentList() {
	
		return mapper.cinemaCurrentList();
	}

	
	//영화별 매출 불러오기
	@Override
	public Long ticketList(String movieNo) {
		
		return mapper.ticketList(movieNo);
	}
	
	//1:1 문의사항 qna 대시보드 불러오기
	@Override
	public List<Qna> adminQnaList5() {

		return mapper.adminQnaList5();
	}
	
	
	// 관리자 사이드바 로그인 보여주기
	@Override
	public List<User> getAdminDetails() {

		return mapper.getAdminDetails();
	}

	//QNA 1:1문의하기 전체 게시글 ---------------------------------------------------------
	@Override
	public Map<String, Object> adminQnaAllList(int cp) {
		
		int qnalistCount = mapper.qnaListAllCount();

		Pagination pagination = new Pagination(qnalistCount, cp);


		// 1) offset 계산
		int offset = (pagination.getCurrentPage() - 1) * pagination.getLimit();

		// 2) RowBounds 객체 생성
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());

		List<Qna> qnaList = mapper.adminQnaAllList(rowBounds);

		Map<String, Object> adminQnamap = new HashMap<String, Object>();
		adminQnamap.put("pagination", pagination);
		adminQnamap.put("qnaList", qnaList);

		return adminQnamap;

	}

	//QNA 1:1문의하기 삭제 게시글 
	@Override
	public Map<String, Object> adminQnaDeletedList(Qna condition,int cp) {
		
		int qnalistCount = mapper.qnaListDeletedCount(condition);

		Pagination pagination = new Pagination(qnalistCount, cp);


		// 1) offset 계산
		int offset = (pagination.getCurrentPage() - 1) * pagination.getLimit();

		// 2) RowBounds 객체 생성
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());

		List<Qna> qnaList = mapper.adminQnaDeletedList(condition,rowBounds);

		Map<String, Object> adminQnamap = new HashMap<String, Object>();
		adminQnamap.put("pagination", pagination);
		adminQnamap.put("qnaList", qnaList);

		return adminQnamap;

	}
	
	// 1:1문의 게시판 조회
	@Override
	public Map<String, Object> adminQnaList(Qna condition,int cp) {
		
		int qnalistCount = mapper.qnaListCount(condition);

		Pagination pagination = new Pagination(qnalistCount, cp);


		// 1) offset 계산
		int offset = (pagination.getCurrentPage() - 1) * pagination.getLimit();

		// 2) RowBounds 객체 생성
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());

		List<Qna> qnaList = mapper.adminQnaList(condition,rowBounds);

		Map<String, Object> adminQnamap = new HashMap<String, Object>();
		adminQnamap.put("pagination", pagination);
		adminQnamap.put("qnaList", qnaList);

		return adminQnamap;

	}

	// 1:1문의 게시글 읽기 조회
	@Override
	public Qna selectQnaOne(int qnaNo) {

		return mapper.selectQnaOne(qnaNo);
	}

	// 1:1문의 게시글 수정
	@Override
	public int qnaUpdate(Qna qna) {
		return mapper.qnaUpdate(qna);
	}

	// 1:1문의 게시글 삭제
	@Override
	public int qnaDelete(int qnaNo) {
		return mapper.qnaDelete(qnaNo);
	}

	//1:1문의 게시글 복구
	@Override
	public int qnaRestore(int qnaNo) {
		
		return mapper.qnaRestore(qnaNo);
	}
	
	// 1:1 문의 게시글 삽입
	@Override
	public int qnaInsert(Qna qna) {

		return mapper.qnaInsert(qna);
	}

	// 1:1문의 게시글 답변 쓰기(삽입)
	@Override
	public int qnaAnswerInsert(QnaComment qnaCommentObj) {

		return mapper.qnaAnswerInsert(qnaCommentObj);
	}

	// 1:1문의 게시글 답변 등록 확인(업데이트)
	@Override
	public int updateAnswer(int qnaNo) {

		return mapper.updateAnswer(qnaNo);
	}

	// 1:1문의 게시글 답변 불러오기(select)
	@Override
	public QnaComment selectQnaCommentList(QnaComment qnaCommentNo) {

		return mapper.selectQnaCommentList(qnaCommentNo);
	}

	// 1:1문의 게시글 답변 수정 (update)
	@Override
	public int qnaAnswerUpdate(QnaComment qnaCommentObj) {

		return mapper.qnaAnswerUpdate(qnaCommentObj);
	}

	// qna 1:1 문의사항 삭제 안 한 게시글 검색
	@Override
	public Map<String, Object> getSearchList(Qna condition, int cp) {
		
		int qnalistCount = mapper.qnaFilterListCount(condition);

		Pagination pagination = new Pagination(qnalistCount, cp);

		// 1) offset 계산
		int offset = (pagination.getCurrentPage() - 1) * pagination.getLimit();

		// 2) RowBounds 객체 생성
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());

		List<Qna> qnaList = mapper.adminQnaList(condition,rowBounds);

		Map<String, Object> getQnaSearchMap = new HashMap<String, Object>();
		getQnaSearchMap.put("pagination", pagination);
		getQnaSearchMap.put("qnaList", qnaList);

		return getQnaSearchMap;
	}
	
	// qna 1:1 문의사항 전체 게시글 검색
	@Override
	public Map<String, Object> getAllSearchList(Qna condition, int cp) {

		int qnalistCount = mapper.qnaFilterListCount(condition);

		Pagination pagination = new Pagination(qnalistCount, cp);

		// 1) offset 계산
		int offset = (pagination.getCurrentPage() - 1) * pagination.getLimit();

		// 2) RowBounds 객체 생성
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());

		List<Qna> qnaList = mapper.getSearchList(condition,rowBounds);

		Map<String, Object> getQnaSearchMap = new HashMap<String, Object>();
		getQnaSearchMap.put("pagination", pagination);
		getQnaSearchMap.put("qnaList", qnaList);

		return getQnaSearchMap;
	}


	// qna 1:1 문의사항 삭제한 게시글 검색
	@Override
	public Map<String, Object> getDeletedSearchList(Qna condition, int cp) {

		int qnalistCount = mapper.qnaListDeletedCount(condition);

		Pagination pagination = new Pagination(qnalistCount, cp);

		// 1) offset 계산
		int offset = (pagination.getCurrentPage() - 1) * pagination.getLimit();

		// 2) RowBounds 객체 생성
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());

		List<Qna> qnaList = mapper.adminQnaDeletedList(condition,rowBounds);

		Map<String, Object> getQnaSearchMap = new HashMap<String, Object>();
		getQnaSearchMap.put("pagination", pagination);
		getQnaSearchMap.put("qnaList", qnaList);

		return getQnaSearchMap;
	}

	
	

	//1:1문의 Qna 게시판 전체 개수 세기
	@Override
	public int qnaListAllCount() {
		
		int qnalistCount = mapper.qnaListAllCount();
		
		return qnalistCount;
	}


	@Override
	public int qnaListCount() {
		
		return  mapper.qnaListCount();
	}


	@Override
	public int qnaListDeletedCount() {
		
		return mapper.qnaListDeletedCount();
	}

	//-------------------------------------------------------------------------------------------------------------------------
	@Override
	public Map<String, Object> adminAnswer(int cp) {
		
		int qnalistCount = mapper.qnaAnswerListCount();

		Pagination pagination = new Pagination(qnalistCount, cp);

		// 1) offset 계산
		int offset = (pagination.getCurrentPage() - 1) * pagination.getLimit();

		// 2) RowBounds 객체 생성
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());

		List<Qna> qnaList = mapper.adminQnaAnswerdList(rowBounds);

		Map<String, Object> getQnaSearchMap = new HashMap<String, Object>();
		getQnaSearchMap.put("pagination", pagination);
		getQnaSearchMap.put("qnaList", qnaList);

		return getQnaSearchMap;
	}



	@Override
	public Map<String, Object> adminNomember(int cp) {
		
		int qnalistCount = mapper.qnaNomemberListCount();

		Pagination pagination = new Pagination(qnalistCount, cp);

		// 1) offset 계산
		int offset = (pagination.getCurrentPage() - 1) * pagination.getLimit();

		// 2) RowBounds 객체 생성
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());

		List<Qna> qnaList = mapper.adminQnaNomemberList(rowBounds);

		Map<String, Object> getQnaSearchMap = new HashMap<String, Object>();
		getQnaSearchMap.put("pagination", pagination);
		getQnaSearchMap.put("qnaList", qnaList);

		return getQnaSearchMap;
	}


	@Override
	public Map<String, Object> adminMember(int cp) {
		
		int qnalistCount = mapper.qnaMemberListCount();

		Pagination pagination = new Pagination(qnalistCount, cp);

		// 1) offset 계산
		int offset = (pagination.getCurrentPage() - 1) * pagination.getLimit();

		// 2) RowBounds 객체 생성
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());

		List<Qna> qnaList = mapper.adminQnaMemberList(rowBounds);

		Map<String, Object> getQnaSearchMap = new HashMap<String, Object>();
		getQnaSearchMap.put("pagination", pagination);
		getQnaSearchMap.put("qnaList", qnaList);

		return getQnaSearchMap;
	}


	
	// 회원****************************************************

	// 회원관리 조회
	@Override
	public Map<String, Object> adminUserList(int cp) {

		int userlistCount = mapper.userListCount();

		Pagination pagination = new Pagination(userlistCount, cp);

		// 1) offset 계산
		int offset = (pagination.getCurrentPage() - 1) * pagination.getLimit();

		// 2) RowBounds 객체 생성
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());

		List<User> userList = mapper.adminUserList(rowBounds);

		Map<String, Object> adminUserList = new HashMap<String, Object>();
		adminUserList.put("pagination", pagination);
		adminUserList.put("userList", userList);

		return adminUserList;
	}

	//탈퇴하지 않은 회원 조회
	@Override
	public Map<String, Object> adminUserInList(User condition, int cp) {
		
		int userlistCount = mapper.userInListCount(condition);

		Pagination pagination = new Pagination(userlistCount, cp);

		// 1) offset 계산
		int offset = (pagination.getCurrentPage() - 1) * pagination.getLimit();

		// 2) RowBounds 객체 생성
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());

		List<User> userList = mapper.adminUserInList(condition,rowBounds);

		Map<String, Object> adminUserList = new HashMap<String, Object>();
		adminUserList.put("pagination", pagination);
		adminUserList.put("userList", userList);

		return adminUserList;
	}

	
	
	// 회원 선택 삭제
	@Override
	public int userDelete(int userNo) {

		return mapper.userDelete(userNo);
	}
	
	//회원 선택 복구
	@Override
	public int restoreUserList(int userNo) {

		return mapper.restoreUserList(userNo);
	}


	

	// 회원 검색
	@Override
	public Map<String, Object> getUserSearchList(User condition, int cp) {

		int userlistCount = mapper.userfilterListCount(condition);

		Pagination pagination = new Pagination(userlistCount, cp);
		// 1) offset 계산
		int offset = (pagination.getCurrentPage() - 1) * pagination.getLimit();

		// 2) RowBounds 객체 생성
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());

		List<User> userList = mapper.getUserSearchList(condition,rowBounds);

		Map<String, Object> getUserSearchList = new HashMap<String, Object>();
		getUserSearchList.put("pagination", pagination);
		getUserSearchList.put("userList", userList);

		return getUserSearchList;
	}


	//회원 전체 개수 가져오기
	@Override
	public int userListCount() {
		
		int userlistCount = mapper.userListCount();
		
		return userlistCount;
	}

	//탈퇴하지 않은 일반 회원 수 불러오기
	@Override
	public int userInListCount() {
		
        return mapper.userInListCount();
	}

	//탈퇴한 회원 수 불러오기
	@Override
	public int userOutListCount() {
		
        return mapper.userOutListCount();
	}

	//탈퇴한 회원 조회
		@Override
		public Map<String, Object> adminUserOutList(User condition, int cp) {

	        int userOutlistCount = mapper.userOutListCount(condition);
	        
	        Pagination pagination = new Pagination(userOutlistCount, cp);
	        
	        int offset = (pagination.getCurrentPage() - 1) * pagination.getLimit();
	        
	        RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());
	        
			List<User> userList = mapper.adminUserOutList(condition,rowBounds);
	        
	        Map<String, Object> adminUserList = new HashMap<String, Object>();
	        adminUserList.put("pagination", pagination);
	        adminUserList.put("userList", userList);
			
	        return adminUserList;
		}

		//회원 게시글 검색 일반회원
		@Override
		public Map<String, Object> getUserInSearchList(User condition, int cp) {
			
			int userlistCount = mapper.userInListCount(condition);
	        
	        Pagination pagination = new Pagination(userlistCount, cp);
	        
	        int offset = (pagination.getCurrentPage() - 1) * pagination.getLimit();
	        
	        RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());
	        
	        List<User> userList = mapper.adminUserInList(condition,rowBounds);
	        
	        Map<String, Object> adminUserList = new HashMap<String, Object>();
	        adminUserList.put("pagination", pagination);
	        adminUserList.put("userList", userList);
			
	        return adminUserList;
		}

		//회원 게시글 검색 탈퇴회원
		@Override
		public Map<String, Object> getUserOutSearchList(User condition, int cp) {
			
			int userOutlistCount = mapper.userOutListCount(condition);
	        
	        Pagination pagination = new Pagination(userOutlistCount, cp);
	        
	        int offset = (pagination.getCurrentPage() - 1) * pagination.getLimit();
	        
	        RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());
	        
	        List<User> userList = mapper.adminUserOutList(condition,rowBounds);
	        
	        Map<String, Object> adminUserList = new HashMap<String, Object>();
	        adminUserList.put("pagination", pagination);
	        adminUserList.put("userList", userList);
			
	        return adminUserList;
		}

		
	
	// 영화***************************************************

//	// 영화 List 조회
//	@Override
//	public Map<String, Object> adminMovieList(int cp) {
//		
//		int movieListCount = mapper.movieListCount();
//		
//		Pagination pagination = new Pagination(movieListCount, cp);
//
//		// 1) offset 계산
//		int offset = (pagination.getCurrentPage() - 1) * pagination.getLimit();
//
//		// 2) RowBounds 객체 생성
//		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());
//
//		List<Movie> adminMovieList = mapper.adminMovieList(rowBounds);
//
//		Map<String, Object> adminMovieMap = new HashMap<String, Object>();
//		adminMovieMap.put("pagination", pagination);
//		adminMovieMap.put("adminMovieList", adminMovieList);
//
//
//		return adminMovieMap;
//	}
//	
//	//영화전체 개수 가져오기
//	@Override
//	public int movieListCount() {
//		
//		int movieListCount = mapper.movieListCount();
//		
//		return movieListCount;
//	}
//
//
//	
//	//영화검색
//	@Override
//	public Map<String, Object> getMovieSearchList(Movie condition, int cp) {
//
//		int movieListCount = mapper.movieListCount();
//
//		Pagination pagination = new Pagination(movieListCount, cp);
//
//		// 3. 특정 게시판에서
//		// 현재 페이지에 해당하는 부분에 대한 게시글 목록 조회
//		// (어떤 게시판(boarCode)에서
//		// 몇 페이지(pagination.currentPage)에 대한
//		// 게시글 몇 개(pagination.limit) 조회)
//
//		// 1) offset 계산
//		int offset = (pagination.getCurrentPage() - 1) * pagination.getLimit();
//
//		// 2) RowBounds 객체 생성
//		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());
//
//		List<Movie> adminMovieList = mapper.getMovieSearchList(condition,rowBounds);
//
//		Map<String, Object> adminMovieMap = new HashMap<String, Object>();
//		adminMovieMap.put("pagination", pagination);
//		adminMovieMap.put("adminMovieList", adminMovieList);
//
//		return adminMovieMap;
//	}
//		

		//영화 검색에 따른 개수 불러오기
		//int movieFilterListCount(Movie condition);	

		
	// 상영관**************************************************

	// 상영관 영화 List 조회
	@Override
	public Map<String, Object> adminCinemaList(Movie condition,int cp) {
		
		int movieScheduleListCount = mapper.movieScheduleListCount();

		Pagination pagination = new Pagination(movieScheduleListCount, cp);

		// 3. 특정 게시판에서
		// 현재 페이지에 해당하는 부분에 대한 게시글 목록 조회
		// (어떤 게시판(boarCode)에서
		// 몇 페이지(pagination.currentPage)에 대한
		// 게시글 몇 개(pagination.limit) 조회)

		// 1) offset 계산
		int offset = (pagination.getCurrentPage() - 1) * pagination.getLimit();

		// 2) RowBounds 객체 생성
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());

		List<Movie> MovieScheduleList = mapper.MovieScheduleList(condition,rowBounds);

		System.out.println(MovieScheduleList);
		
		Map<String, Object> adminCinemaMap = new HashMap<String, Object>();
		adminCinemaMap.put("pagination", pagination);
		adminCinemaMap.put("cinemaList", MovieScheduleList);

	
		

		return adminCinemaMap;
	}

	// 2관 페이지 이동
	@Override
	public Map<String, Object> adminCinemaTwo(String movieTheaterNo,int cp) {
		
		int movieScheduleListCount = mapper.movieScheduleListCount();

		Pagination pagination = new Pagination(movieScheduleListCount, cp);

		// 3. 특정 게시판에서
		// 현재 페이지에 해당하는 부분에 대한 게시글 목록 조회
		// (어떤 게시판(boarCode)에서
		// 몇 페이지(pagination.currentPage)에 대한
		// 게시글 몇 개(pagination.limit) 조회)

		// 1) offset 계산
		int offset = (pagination.getCurrentPage() - 1) * pagination.getLimit();

		// 2) RowBounds 객체 생성
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());

		List<Movie> adminCinemaTwo = mapper.adminCinemaTwo(movieTheaterNo,rowBounds);

		System.out.println(adminCinemaTwo);
		
		Map<String, Object> adminCinemaMap = new HashMap<String, Object>();
		adminCinemaMap.put("pagination", pagination);
		adminCinemaMap.put("cinemaList", adminCinemaTwo);

	
		

		return adminCinemaMap;
	}

	// 상영관 등록 영화 불러오기
	@Override
	public List<Movie> cinemaList() {

		return mapper.cinemaList();
	}

	// 상영관 영화,상영시간 등록
	@Override
	public int cinemaListInsert(Movie movie) {

		return mapper.cinemaListInsert(movie);
	}
	
	//상영관 스케쥴 개수
	@Override
	public int movieScheduleListCount() {
		
		int movieScheduleListCount = mapper.movieScheduleListCount();
		
		return movieScheduleListCount;
	}

	//상영관 영화 스케쥴 삽입(insert)
	@Override
	public int adminCinemaInsert(Movie movie) {
		
		return mapper.adminCinemaInsert(movie);
	}

	

	// 공지사항************************************************

	// 공지사항 List 조회
	@Override
	public Map<String, Object> adminNoticeList(int cp) {

		int noticeListCount = mapper.noticeListCount();

		Pagination pagination = new Pagination(noticeListCount, cp);

		// 3. 특정 게시판에서
		// 현재 페이지에 해당하는 부분에 대한 게시글 목록 조회
		// (어떤 게시판(boarCode)에서
		// 몇 페이지(pagination.currentPage)에 대한
		// 게시글 몇 개(pagination.limit) 조회)

		// 1) offset 계산
		int offset = (pagination.getCurrentPage() - 1) * pagination.getLimit();

		// 2) RowBounds 객체 생성
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());

		List<Notice> adminNoticeList = mapper.adminNoticeList(rowBounds);

		Map<String, Object> adminNoticeMap = new HashMap<String, Object>();
		
		adminNoticeMap.put("pagination", pagination);
		adminNoticeMap.put("adminNoticeList", adminNoticeList);


		return adminNoticeMap;
	}
	//삭제 안 된 공지사항 게시판 조회
	@Override
	public Map<String, Object> adminNoticeInList(Notice condition,int cp) {
		
		int noticeListCount = mapper.noticeFilterInListCount(condition);

		Pagination pagination = new Pagination(noticeListCount, cp);

		// 1) offset 계산
		int offset = (pagination.getCurrentPage() - 1) * pagination.getLimit();

		// 2) RowBounds 객체 생성
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());

		List<Notice> adminNoticeList = mapper.adminNoticeInList(condition,rowBounds);

		Map<String, Object> adminNoticeMap = new HashMap<String, Object>();
		
		adminNoticeMap.put("pagination", pagination);
		adminNoticeMap.put("adminNoticeList", adminNoticeList);


		return adminNoticeMap;
	}

	
	
	//삭제된 공지사항 조회
	@Override
	public Map<String, Object> adminNoticeDeletedList(Notice condition,int cp) {

		int noticeListCount = mapper.noticeDeletedListCount(condition);

		Pagination pagination = new Pagination(noticeListCount, cp);

		// 1) offset 계산
		int offset = (pagination.getCurrentPage() - 1) * pagination.getLimit();

		// 2) RowBounds 객체 생성
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());

		List<Notice> adminNoticeList = mapper.adminNoticeDeletedList(condition,rowBounds);

		Map<String, Object> adminNoticeMap = new HashMap<String, Object>();
		
		adminNoticeMap.put("pagination", pagination);
		adminNoticeMap.put("adminNoticeList", adminNoticeList);


		return adminNoticeMap;
	}

	// 공지사항 게시글 조회
	@Override
	public List<Notice> adminNoticeOne(Notice notice) {

		return mapper.adminNoticeOne(notice);
	}

	// 공지사항 게시글 쓰기
	@Override
	public int noticeWriteInsert(Notice notice) {

		return mapper.noticeWriteInsert(notice);
	}
	

	//공지사항 수정
	@Override
	public int noticeUpdate(Notice notice) {

        return mapper.noticeUpdate(notice);
	}


	// 공지사항 게시글 삭제
	@Override
	public int noticeDelete(int noticeNo) {

		return mapper.noticeDelete(noticeNo);
	}
	
	// 공지사항 게시글 복구
	@Override
	public int noticeRestore(int noticeNo) {
	
		return mapper.noticeRestore(noticeNo);
	}


	// 공지사항 게시글 검색
	@Override
	public Map<String, Object> getNoticeAllSearchList(Notice condition, int cp) 
	{
		int noticeListCount = mapper.noticeFilterListCount(condition);

		Pagination pagination = new Pagination(noticeListCount, cp);

		// 1) offset 계산
		int offset = (pagination.getCurrentPage() - 1) * pagination.getLimit();

		// 2) RowBounds 객체 생성
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());

		List<Notice> adminNoticeList = mapper.getNoticeSearchList(condition,rowBounds);

		Map<String, Object> getNoticeSearchMap = new HashMap<String, Object>();
		getNoticeSearchMap.put("pagination", pagination);
		getNoticeSearchMap.put("adminNoticeList", adminNoticeList);

		return getNoticeSearchMap;
	}

	
	// 공지사항 삭제 안 한 게시글 검색
	@Override
	public 	Map<String, Object> getNoticeSearchList(Notice condition, int cp) {

		int noticeListCount = mapper.noticeFilterInListCount(condition);

		Pagination pagination = new Pagination(noticeListCount, cp);

		// 1) offset 계산
		int offset = (pagination.getCurrentPage() - 1) * pagination.getLimit();

		// 2) RowBounds 객체 생성
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());

		List<Notice> adminNoticeList = mapper.adminNoticeInList(condition,rowBounds);

		Map<String, Object> getNoticeSearchMap = new HashMap<String, Object>();
		getNoticeSearchMap.put("pagination", pagination);
		getNoticeSearchMap.put("adminNoticeList", adminNoticeList);

		return getNoticeSearchMap;
	}



	// 공지사항 삭제 한 게시글 검색
	@Override
	public Map<String, Object> getNoticeDeletedSearchList(Notice condition, int cp) {
		
		int noticeListCount = mapper.noticeDeletedListCount(condition);

		Pagination pagination = new Pagination(noticeListCount, cp);

		// 1) offset 계산
		int offset = (pagination.getCurrentPage() - 1) * pagination.getLimit();

		// 2) RowBounds 객체 생성
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());

		List<Notice> adminNoticeList = mapper.adminNoticeDeletedList(condition,rowBounds);

		Map<String, Object> getNoticeSearchMap = new HashMap<String, Object>();
		getNoticeSearchMap.put("pagination", pagination);
		getNoticeSearchMap.put("adminNoticeList", adminNoticeList);

		return getNoticeSearchMap;
	}

	

	//공지사항 삭제 안 한 게시글 
	@Override
	public int noticeInListCount() {
		
		return mapper.noticeInListCount();
	}

	@Override
	public int noticeListCount() {
		
		return  mapper.noticeListCount();
	}

	// FAQ (자주 찾는 질문) List 조회*****************************

	// FAQ 게시판 List 조회
	@Override
	public Map<String , Object> adminFaqAllList(int cp) {

		int faqListCount = mapper.faqListAllCount();

		Pagination pagination = new Pagination(faqListCount, cp);

		// 1) offset 계산
		int offset = (pagination.getCurrentPage() - 1) * pagination.getLimit();

		// 2) RowBounds 객체 생성
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());

		List<FAQ> adminFaqList = mapper.adminFaqAllList(rowBounds);

		Map<String, Object> adminFaqMap = new HashMap<String, Object>();
		
		adminFaqMap.put("pagination", pagination);
		adminFaqMap.put("adminFaqList", adminFaqList);


		return adminFaqMap;
	}
	
	//FAQ 삭제 안 한 게시글 불러오기
	@Override
	public Map<String, Object> adminFaqList(FAQ condition,int cp) {
		
		int faqListCount = mapper.faqListCount(condition);

		Pagination pagination = new Pagination(faqListCount, cp);

		// 1) offset 계산
		int offset = (pagination.getCurrentPage() - 1) * pagination.getLimit();

		// 2) RowBounds 객체 생성
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());

		List<FAQ> adminFaqList = mapper.adminFaqList(condition,rowBounds);

		Map<String, Object> adminFaqMap = new HashMap<String, Object>();
		
		adminFaqMap.put("pagination", pagination);
		adminFaqMap.put("adminFaqList", adminFaqList);


		return adminFaqMap;
	}
	
	//FAQ 삭제한 게시글 불러오기
	@Override
	public Map<String, Object> adminFaqDeletedList(FAQ condition,int cp) {
		int faqListCount = mapper.faqListFilterDeletedCount(condition);

		Pagination pagination = new Pagination(faqListCount, cp);

		// 1) offset 계산
		int offset = (pagination.getCurrentPage() - 1) * pagination.getLimit();

		// 2) RowBounds 객체 생성
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());

		List<FAQ> adminFaqList = mapper.adminFaqDeletedList(condition,rowBounds);

		Map<String, Object> adminFaqMap = new HashMap<String, Object>();
		
		adminFaqMap.put("pagination", pagination);
		adminFaqMap.put("adminFaqList", adminFaqList);


		return adminFaqMap;
	}


	// FAQ (자주 찾는 질문) 게시글 조회
	@Override
	public List<FAQ> adminFaqOne(FAQ faq) {

		return mapper.adminFaqOne(faq);
	}

	// FAQ (자주 찾는 질문) 글 수정
	@Override
	public int updateFaq(FAQ faq) {

		return mapper.updateFaq(faq);
	}

	// FAQ (자주 찾는 질문) 글 삭제
	@Override
	public int deleteFaq(FAQ faq) {

		return mapper.deleteFaq(faq);
	}

	// FAQ (자주 찾는 질문) 글 삽입
	@Override
	public int faqInsert(FAQ faq) {

		return mapper.faqInsert(faq);
	}

	// FAQ (자주 찾는 질문) 글 선택 삭제
	@Override
	public int deleteFaq(int fAQNo) {

		return mapper.deleteFaq(fAQNo);
	}

	// FAQ 게시글 검색
	@Override
	public Map<String, Object> getFaqSearchList(FAQ condtion, int cp) {

		int faqListCount = mapper.faqListCount(condtion);

		Pagination pagination = new Pagination(faqListCount, cp);
		// 1) offset 계산
		int offset = (pagination.getCurrentPage() - 1) * pagination.getLimit();

		// 2) RowBounds 객체 생성
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());

		List<FAQ> adminFaqList =  mapper.getFaqSearchList(condtion,rowBounds);

		Map<String, Object> adminFaqMap = new HashMap<String, Object>();
		
		adminFaqMap.put("pagination", pagination);
		adminFaqMap.put("adminFaqList", adminFaqList);
		
		return adminFaqMap;
	}
	
	@Override
	public Map<String, Object> getFaqAllSearchList(FAQ condition, int cp) {
		
		int faqListCount = mapper.faqFilterAllListCount(condition);

		Pagination pagination = new Pagination(faqListCount, cp);
		// 1) offset 계산
		int offset = (pagination.getCurrentPage() - 1) * pagination.getLimit();

		// 2) RowBounds 객체 생성
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());

		List<FAQ> adminFaqList =  mapper.getFaqAllSearchList(condition,rowBounds);

		Map<String, Object> adminFaqMap = new HashMap<String, Object>();
		
		adminFaqMap.put("pagination", pagination);
		adminFaqMap.put("adminFaqList", adminFaqList);
		
		return adminFaqMap;
	}

	//FAQ 삭제한 글 검색
	@Override
	public Map<String, Object> getFaqDeletedSearchList(FAQ condition, int cp) {
		
		int faqListCount = mapper.faqListDeletedCount(condition);

		Pagination pagination = new Pagination(faqListCount, cp);
		// 1) offset 계산
		int offset = (pagination.getCurrentPage() - 1) * pagination.getLimit();

		// 2) RowBounds 객체 생성
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());

		List<FAQ> adminFaqList =  mapper.getFaqDeletedSearchList(condition,rowBounds);

		Map<String, Object> adminFaqMap = new HashMap<String, Object>();
		
		adminFaqMap.put("pagination", pagination);
		adminFaqMap.put("adminFaqList", adminFaqList);
		
		return adminFaqMap;
	}

	
	
	// 지난 주 요일별 매출
	@Override
	public List<SalesByPeriod> getSalesByDay() {
		return mapper.getSalesByDay();
	}

	//FAQ 전체 개수
	@Override
	public int faqListAllCount() {
		
		return mapper.faqListAllCount();
	}
	//FAQ 삭제 안 한 게시글 수
	@Override
	public int faqListCount() {
		
		return mapper.faqListCount();
	}

	//FAQ 삭제한 게시글 수
	@Override
	public int faqListDeletedCount() {

		return  mapper.faqListDeletedCount();
	}

	
	//FAQ 선택 복구
	@Override
	public int restoreFaq(int FAQNo) {
		
		return mapper.restoreFaq(FAQNo);
	}

	//신고하기*************************************************************************************
	
	//신고하기 Map 불러오기
	@Override
	public Map<String, Object> adminReportList(int cp) {
		
		int reportListCount = mapper.reportListCount();

		Pagination pagination = new Pagination(reportListCount, cp);
		// 1) offset 계산
		int offset = (pagination.getCurrentPage() - 1) * pagination.getLimit();

		// 2) RowBounds 객체 생성
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());

		List<Report> adminReportList = mapper.adminReportList(rowBounds);

		Map<String, Object> adminReportMap = new HashMap<String, Object>();
		
		adminReportMap.put("pagination", pagination);
		adminReportMap.put("adminReportList", adminReportList);


		return adminReportMap;
	}
	
	@Override
	public Map<String, Object> adminReportInList(int cp) {
		
		int reportListCount = mapper.reportInListCount();

		Pagination pagination = new Pagination(reportListCount, cp);
		// 1) offset 계산
		int offset = (pagination.getCurrentPage() - 1) * pagination.getLimit();

		// 2) RowBounds 객체 생성
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());

		List<Report> adminReportList = mapper.adminReportInList(rowBounds);

		Map<String, Object> adminReportMap = new HashMap<String, Object>();
		
		adminReportMap.put("pagination", pagination);
		adminReportMap.put("adminReportList", adminReportList);


		return adminReportMap;
	}


	@Override
	public Map<String, Object> adminReportOuList(int cp) {
		
		int reportListCount = mapper.reportOutListCount();

		Pagination pagination = new Pagination(reportListCount, cp);
		// 1) offset 계산
		int offset = (pagination.getCurrentPage() - 1) * pagination.getLimit();

		// 2) RowBounds 객체 생성
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());

		List<Report> adminReportList = mapper.adminReportOutList(rowBounds);

		Map<String, Object> adminReportMap = new HashMap<String, Object>();
		
		adminReportMap.put("pagination", pagination);
		adminReportMap.put("adminReportList", adminReportList);


		return adminReportMap;
	}

	

	//신고하기 게시글 불러오기
	@Override
	public List<Report> adminReportOne(int reportNo) {
	
		return mapper.adminReportOne(reportNo);
	}

	//신고관리에서 리뷰 삭제하기
	@Override
	public int deleteReview(int reviewNo) {
	
		return mapper.deleteReview(reviewNo);
	}

	//신고글 처리여부 
	@Override
	public int updateDeleteReport(int reportNo) {
		
		return mapper.updateDeleteReport(reportNo);
	}

	//신고관리 검색
	@Override
	public Map<String, Object> getReportSearchList(Report condition, int cp) {
		
		int reportListCount = mapper.reportFilterListCount(condition);

		Pagination pagination = new Pagination(reportListCount, cp);

		// 1) offset 계산
		int offset = (pagination.getCurrentPage() - 1) * pagination.getLimit();

		// 2) RowBounds 객체 생성
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());

		List<Report> adminReportList =  mapper.getReportSearchList(condition,rowBounds);

		Map<String, Object> adminReportMap = new HashMap<String, Object>();
		
		adminReportMap.put("pagination", pagination);
		adminReportMap.put("adminReportList", adminReportList);
		
		return adminReportMap;
	}
	
	@Override
	public Map<String, Object> getReportInSearchList(Report condition, int cp) {
		
		int reportListCount = mapper.reportInFilterListCount(condition);

		Pagination pagination = new Pagination(reportListCount, cp);

		// 1) offset 계산
		int offset = (pagination.getCurrentPage() - 1) * pagination.getLimit();

		// 2) RowBounds 객체 생성
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());

		List<Report> adminReportList =  mapper.getReportInSearchList(condition,rowBounds);

		Map<String, Object> adminReportMap = new HashMap<String, Object>();
		
		adminReportMap.put("pagination", pagination);
		adminReportMap.put("adminReportList", adminReportList);
		
		return adminReportMap;
	}


	@Override
	public Map<String, Object> getReportOutSearchList(Report condition, int cp) {
		
		int reportListCount = mapper.reportOutFilterListCount(condition);

		Pagination pagination = new Pagination(reportListCount, cp);

		// 1) offset 계산
		int offset = (pagination.getCurrentPage() - 1) * pagination.getLimit();

		// 2) RowBounds 객체 생성
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());

		List<Report> adminReportList =  mapper.getReportOutSearchList(condition,rowBounds);

		Map<String, Object> adminReportMap = new HashMap<String, Object>();
		
		adminReportMap.put("pagination", pagination);
		adminReportMap.put("adminReportList", adminReportList);
		
		return adminReportMap;
	}


	
	//신고하기 개수 가져오기
	@Override
	public int reportListCount() {
		
		return  mapper.reportListCount();
	}
	
	@Override
	public int reportInListCount() {
		
		return  mapper.reportInListCount();
	}


	@Override
	public int reportOutListCount() {
		
		return  mapper.reportOutListCount();
	}
	

	//리뷰관리**********************************************************************************************************
	
	
	//리뷰하기 불러오기
	@Override
	public Map<String, Object> adminReviewList(int cp) {
	
		int reviewListCount = mapper.reviewListCount();

		Pagination pagination = new Pagination(reviewListCount, cp);

		// 1) offset 계산
		int offset = (pagination.getCurrentPage() - 1) * pagination.getLimit();

		// 2) RowBounds 객체 생성
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());

		List<Review> adminReviewList = mapper.adminReviewList(rowBounds);

		Map<String, Object> adminReviewMap = new HashMap<String, Object>();
		
		adminReviewMap.put("pagination", pagination);
		adminReviewMap.put("adminReviewList", adminReviewList);


		return adminReviewMap;
	}


	//리뷰관리 삭제 안 게시글 게시판 조회
	@Override
	public Map<String, Object> adminReviewInList(int cp) {
		
		int reviewListCount = mapper.reviewInListCount();

		Pagination pagination = new Pagination(reviewListCount, cp);

		// 1) offset 계산
		int offset = (pagination.getCurrentPage() - 1) * pagination.getLimit();

		// 2) RowBounds 객체 생성
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());

		List<Review> adminReviewList = mapper.adminReviewInList(rowBounds);

		Map<String, Object> adminReviewMap = new HashMap<String, Object>();
		
		adminReviewMap.put("pagination", pagination);
		adminReviewMap.put("adminReviewList", adminReviewList);


		return adminReviewMap;
	}

	//리뷰관리 삭제한 게시글 게시판 조회
	@Override
	public Map<String, Object> adminReviewOutList(int cp) {
	
		int reviewListCount = mapper.reviewOutListCount();

		Pagination pagination = new Pagination(reviewListCount, cp);

		// 1) offset 계산
		int offset = (pagination.getCurrentPage() - 1) * pagination.getLimit();

		// 2) RowBounds 객체 생성
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());

		List<Review> adminReviewList = mapper.adminReviewOutList(rowBounds);

		Map<String, Object> adminReviewMap = new HashMap<String, Object>();
		
		adminReviewMap.put("pagination", pagination);
		adminReviewMap.put("adminReviewList", adminReviewList);


		return adminReviewMap;
	}
	
	//리뷰관리 삭제 안 한 게시글 개수
	@Override
	public int reviewInListCount() {
	
		return mapper.reviewInListCount();
	}

	//리뷰관리 삭제 한 게시글 개수
	@Override
	public int reviewOutListCount() {
		
		return mapper.reviewOutListCount();
	}


	
	
	
	//리뷰 개수 조회
	@Override
	public int reviewListCount() {
		
		return mapper.reviewListCount();
	}

	//리뷰 관리 검색
	@Override
	public Map<String, Object> getReviewSearchList(Review condition, int cp) {
		
		int reviewListCount = mapper.reviewFilterListCount(condition);

		Pagination pagination = new Pagination(reviewListCount, cp);

			// 1) offset 계산
		int offset = (pagination.getCurrentPage() - 1) * pagination.getLimit();

		// 2) RowBounds 객체 생성
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());

		List<Review> adminReviewList = mapper.getReviewSearchList(condition,rowBounds);

		Map<String, Object> adminReviewMap = new HashMap<String, Object>();
		
		adminReviewMap.put("pagination", pagination);
		adminReviewMap.put("adminReviewList", adminReviewList);


		return adminReviewMap;
	}
	@Override
	public Map<String, Object> getReviewInSearchList(Review condition, int cp) {
		
		int reviewListCount = mapper.reviewFilterInListCount(condition);

		Pagination pagination = new Pagination(reviewListCount, cp);

			// 1) offset 계산
		int offset = (pagination.getCurrentPage() - 1) * pagination.getLimit();

		// 2) RowBounds 객체 생성
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());

		List<Review> adminReviewList = mapper.getReviewInSearchList(condition,rowBounds);

		Map<String, Object> adminReviewMap = new HashMap<String, Object>();
		
		adminReviewMap.put("pagination", pagination);
		adminReviewMap.put("adminReviewList", adminReviewList);


		return adminReviewMap;
	}

	@Override
	public Map<String, Object> getReviewOutSearchList(Review condition, int cp) {
		
		int reviewListCount = mapper.reviewFilterOutListCount(condition);

		Pagination pagination = new Pagination(reviewListCount, cp);

			// 1) offset 계산
		int offset = (pagination.getCurrentPage() - 1) * pagination.getLimit();

		// 2) RowBounds 객체 생성
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());

		List<Review> adminReviewList = mapper.getReviewOutSearchList(condition,rowBounds);

		Map<String, Object> adminReviewMap = new HashMap<String, Object>();
		
		adminReviewMap.put("pagination", pagination);
		adminReviewMap.put("adminReviewList", adminReviewList);


		return adminReviewMap;
	}


	

	
	// 상영관 리스트 조회(찬희)
	@Override
	public Map<String, Object> selectCinemaList(Map<String, Object> paramMap, String beforecp) {
		
		int listCount = mapper.getListCount(paramMap);
		
		int cp = Integer.parseInt(beforecp);

		Pagination pagination = new Pagination(listCount, cp);
		
		int offset = (pagination.getCurrentPage() - 1) * pagination.getLimit();
		
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());
		
		List<Movie> movieList = mapper.selectCinemaList(paramMap, rowBounds);
		
		Map<String, Object> map = new HashMap<>();
		map.put("movieList", movieList);
		map.put("pagination", pagination);
		map.put("listCount", listCount);
		
		return map;
	}

	// 상영관 세부 시간 조회 AJAX(찬희)
	@Override
	public List<String> selectDetailTime(Map<String, Object> paramMap) {
		return mapper.selectDetailTime(paramMap);
	}

	// 상영관 세부 시간 삭제(찬희)
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int deleteDetailTime(Schedule schedule) {
		System.out.println(schedule);
		int count = mapper.selectTicketing(schedule);
		System.out.println(count);
		int result = 0;
		if(count==0) {
			result = mapper.deleteDetailTime(schedule);
		}
		
		return result;
	}

	// 체크한 상영정보 삭제하기(찬희)
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int deleteTotalTime(List<Map<String, String>> dataList) {
		int count = 0;
		for(int i=0;i<dataList.size();i++) {
			count = count + mapper.selectToTalTicketing(dataList.get(i));
		}
		int result = 0;
		if(count==0) {
			result = mapper.deleteTotalTime(dataList);
		} return result;
	} 


	//리뷰 선택 복구하기
	@Override
	public int restoreReview(int reviewNo) {
		
		return mapper.restoreReview(reviewNo);
	}

	//리뷰 게시글 불러오기
	@Override
	public List<Review> adminReviewOne(int reviewNo) {
		
		return mapper.adminReviewOne(reviewNo);
	}

	@Override
	public int noticeOutListCount() {
		
		return mapper.noticeOutListCount();
	}

	// 년도별 분기 매출
	@Override
	public List<SalesByPeriod> quarterlySales(String selectedYear) {
		return mapper.quarterlySales(selectedYear);
	}

	// 년도별 분기 매출(첫 접속)
	@Override
	public List<SalesByPeriod> firstLoadingQuarterlySales(String currentYear) {
		return mapper.quarterlySales(currentYear);
	}

	// 년도별 월 매출(첫 접속)
	@Override
	public List<SalesByPeriod> firstLoadingMonthlySalesByYear(String currentYear) {
		
		Map<String, String> year = new HashMap<>();
		int lastYear = Integer.parseInt(currentYear)-1;
		year.put("currentYear", currentYear);
		year.put("lastYear", lastYear+"");
		
		return mapper.monthlySalesByYear(year);
	}

	// 년도별 월 매출
	@Override
	public List<SalesByPeriod> monthlySalesByYear(String selectedYear) {
		
		Map<String, String> year = new HashMap<>();
		int lastYear = Integer.parseInt(selectedYear)-1;
		year.put("currentYear", selectedYear);
		year.put("lastYear", lastYear+"");
		
		return mapper.monthlySalesByYear(year);
	}


	// 근 3개월 영화별 예매건수
	@Override
	public List<SalesByPeriod> reservationsEachMovieLast3Months() {
		return mapper.reservationsEachMovieLast3Months();
	}

	// 영화별 예매건수(선택형)
	@Override
	public List<SalesByPeriod> reservationsByMovieOnSelectedDate(String dtFrInput, String dtBkInput) {
		
		Map<String, String> date = new HashMap<>();
		date.put("dtFrInput", dtFrInput);
		date.put("dtBkInput", dtBkInput);
		
		return mapper.reservationsByMovieOnSelectedDate(date);
	}


















	
	
}


	
	



	

