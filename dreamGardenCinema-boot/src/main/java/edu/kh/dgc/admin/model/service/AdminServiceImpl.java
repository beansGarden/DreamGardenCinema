package edu.kh.dgc.admin.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.kh.dgc.admin.model.dao.AdminMapper;
import edu.kh.dgc.admin.model.dto.Pagination;
import edu.kh.dgc.customerservice.model.dto.FAQ;
import edu.kh.dgc.movie.model.dto.Movie;
import edu.kh.dgc.notice.model.dto.Notice;
import edu.kh.dgc.qna.model.dto.Qna;
import edu.kh.dgc.qna.model.dto.QnaComment;
import edu.kh.dgc.user.model.dto.User;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminMapper mapper;

	// 관리자 사이드바 로그인 보여주기
	@Override
	public List<User> getAdminDetails() {

		return mapper.getAdminDetails();
	}

	// 1:1문의 게시판 조회
	@Override
	public List<Qna> adminQnaList() {

		return mapper.adminQnaList();
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
	public int qnaAnswerInsert(QnaComment qnaComment) {

		return mapper.qnaAnswerInsert(qnaComment);
	}

	// 1:1문의 게시글 답변 등록 확인(업데이트)
	@Override
	public QnaComment updateAnswer(int qnaNo) {

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
	public List<Qna> getSearchList(Qna qnaList) {

		return mapper.getSearchList(qnaList);
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

		List<User> userList = mapper.getUserSearchList(rowBounds);

		Map<String, Object> getUserSearchList = new HashMap<String, Object>();
		getUserSearchList.put("pagination", pagination);
		getUserSearchList.put("userList", userList);

		return getUserSearchList;
	}

	// 영화***************************************************

	// 영화 List 조회
	@Override
	public List<Movie> adminMovieList() {

		return mapper.adminMovieList();
	}

	// 상영관**************************************************

	// 상영관 영화 List 조회
	@Override
	public Map<String, Object> adminCinemaList(int cp) {
		
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

		List<User> MovieScheduleList = mapper.MovieScheduleList(rowBounds);

		Map<String, Object> adminCinemaMap = new HashMap<String, Object>();
		adminCinemaMap.put("pagination", pagination);
		adminCinemaMap.put("cinemaList", MovieScheduleList);


		return adminCinemaMap;
	}

	// 2관 페이지 이동
	@Override
	public List<Movie> adminCinemaTwo(String movieTheaterNo) {

		return mapper.adminCinemaTwo(movieTheaterNo);
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
	public List<Notice> getNoticeSearchList(Notice noticeList) {

		return mapper.getNoticeSearchList(noticeList);
	}

	// FAQ (자주 찾는 질문) List 조회*****************************

	// FAQ 게시판 List 조회
	@Override
	public List<FAQ> adminFaqList() {

		return mapper.adminFaqList();
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
	public List<FAQ> getFaqSearchList(FAQ faqList) {

		return mapper.getFaqSearchList(faqList);
	}

}
