package edu.kh.dgc.admin.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.kh.dgc.admin.model.dao.AdminMapper;
import edu.kh.dgc.admin.model.dto.SalesByPeriod;
import edu.kh.dgc.admin.model.dto.Pagination;
import edu.kh.dgc.customerservice.model.dto.FAQ;
import edu.kh.dgc.movie.model.dto.Movie;
import edu.kh.dgc.notice.model.dto.Notice;
import edu.kh.dgc.qna.model.dto.Qna;
import edu.kh.dgc.qna.model.dto.QnaComment;
import edu.kh.dgc.ticketing.model.dto.Ticket;
import edu.kh.dgc.user.model.dto.User;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminMapper mapper;

	
	//대시보드
	//영화별 매출 불러오기
	@Override
	public List<Ticket> ticketList(String movieNo) {
		
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

	// 1:1문의 게시판 조회
	@Override
	public Map<String, Object> adminQnaList(int cp) {
		
		int qnalistCount = mapper.qnaListCount();

		Pagination pagination = new Pagination(qnalistCount, cp);

		// 3. 특정 게시판에서
		// 현재 페이지에 해당하는 부분에 대한 게시글 목록 조회
		// (어떤 게시판(boarCode)에서
		// 몇 페이지(pagination.currentPage)에 대한
		// 게시글 몇 개(pagination.limit) 조회)

		// 1) offset 계산
		int offset = (pagination.getCurrentPage() - 1) * pagination.getLimit();

		// 2) RowBounds 객체 생성
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());

		List<Qna> qnaList = mapper.adminQnaList(rowBounds);

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

	// 1:1 문의사항 검색
	@Override
	public Map<String, Object> getSearchList(Qna conditon, int cp) {
		
		int qnalistCount = mapper.qnaListCount();

		Pagination pagination = new Pagination(qnalistCount, cp);

		// 3. 특정 게시판에서
		// 현재 페이지에 해당하는 부분에 대한 게시글 목록 조회
		// (어떤 게시판(boarCode)에서
		// 몇 페이지(pagination.currentPage)에 대한
		// 게시글 몇 개(pagination.limit) 조회)

		// 1) offset 계산
		int offset = (pagination.getCurrentPage() - 1) * pagination.getLimit();

		// 2) RowBounds 객체 생성
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());

		List<Qna> qnaList = mapper.getSearchList(rowBounds);

		Map<String, Object> getQnaSearchMap = new HashMap<String, Object>();
		getQnaSearchMap.put("pagination", pagination);
		getQnaSearchMap.put("qnaList", qnaList);

		return getQnaSearchMap;
	}
	

	//1:1문의 Qna 게시판 
	@Override
	public int qnaListCount() {
		
		int qnalistCount = mapper.qnaListCount();
		
		return qnalistCount;
	}


	// 회원****************************************************

	// 회원관리 조회
	@Override
	public Map<String, Object> adminUserList(int cp) {

		int userlistCount = mapper.userListCount();

		Pagination pagination = new Pagination(userlistCount, cp);

		// 3. 특정 게시판에서
		// 현재 페이지에 해당하는 부분에 대한 게시글 목록 조회
		// (어떤 게시판(boarCode)에서
		// 몇 페이지(pagination.currentPage)에 대한
		// 게시글 몇 개(pagination.limit) 조회)

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

	// 회원 선택 삭제
	@Override
	public int userDelete(int userNo) {

		return mapper.userDelete(userNo);
	}

	// 회원 검색
	@Override
	public Map<String, Object> getUserSearchList(User condition, int cp) {

		int userlistCount = mapper.userListCount();

		Pagination pagination = new Pagination(userlistCount, cp);

		// 3. 특정 게시판에서
		// 현재 페이지에 해당하는 부분에 대한 게시글 목록 조회
		// (어떤 게시판(boarCode)에서
		// 몇 페이지(pagination.currentPage)에 대한
		// 게시글 몇 개(pagination.limit) 조회)

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

	
	
	// 영화***************************************************

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

	// 공지사항 게시글 삭제
	@Override
	public int noticeDelete(int noticeNo) {

		return mapper.noticeDelete(noticeNo);
	}

	// 공지사항 게시글 검색
	@Override
	public 	Map<String, Object> getNoticeSearchList(Notice condition, int cp) {

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

		List<Notice> adminNoticeList = mapper.getNoticeSearchList(condition,rowBounds);

		Map<String, Object> getNoticeSearchMap = new HashMap<String, Object>();
		getNoticeSearchMap.put("pagination", pagination);
		getNoticeSearchMap.put("adminNoticeList", adminNoticeList);

		return getNoticeSearchMap;
	}

	//공지사항 전체 개수
	@Override
	public int noticeListCount() {
		
		int noticeListCount = mapper.noticeListCount();
	
		return noticeListCount;
	}
	
	// FAQ (자주 찾는 질문) List 조회*****************************

	// FAQ 게시판 List 조회
	@Override
	public Map<String , Object> adminFaqList(int cp) {

		int faqListCount = mapper.faqListCount();

		Pagination pagination = new Pagination(faqListCount, cp);

		// 3. 특정 게시판에서
		// 현재 페이지에 해당하는 부분에 대한 게시글 목록 조회
		// (어떤 게시판(boarCode)에서
		// 몇 페이지(pagination.currentPage)에 대한
		// 게시글 몇 개(pagination.limit) 조회)

		// 1) offset 계산
		int offset = (pagination.getCurrentPage() - 1) * pagination.getLimit();

		// 2) RowBounds 객체 생성
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());

		List<FAQ> adminFaqList = mapper.adminFaqList(rowBounds);

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

		int faqListCount = mapper.faqListCount();

		Pagination pagination = new Pagination(faqListCount, cp);

		// 3. 특정 게시판에서
		// 현재 페이지에 해당하는 부분에 대한 게시글 목록 조회
		// (어떤 게시판(boarCode)에서
		// 몇 페이지(pagination.currentPage)에 대한
		// 게시글 몇 개(pagination.limit) 조회)

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

	// 지난 주 요일별 매출
	@Override
	public List<SalesByPeriod> getSalesByDay() {
		return mapper.getSalesByDay();
	}

	//FAQ 개수
	@Override
	public int faqListCount() {
		
		int faqListCount = mapper.faqListCount();
		
		return faqListCount;
	}


	

	





	
}
