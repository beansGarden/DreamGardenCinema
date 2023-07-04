package edu.kh.dgc.admin.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import edu.kh.dgc.admin.model.dto.Query;
import edu.kh.dgc.admin.model.dto.SalesByPeriod;
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
@Mapper
public interface AdminMapper {

	/**
	 * 관리자 사이드바 로그인 보여주기
	 * 
	 * @return
	 */
	List<User> getAdminDetails();

	// *****대시보드********************************************************

	//영화 상역작 불러오기
	List<Movie> cinemaCurrentList();

	
	// 영화별 매출 불러오기
	List<Ticket> ticketList(String movieNo);

	List<Qna> adminQnaList5();

	// ***1:1문의 Qnq*********************************************************

	// 1:1문의 Qna 게시글 개수 불러오기
	int qnaListCount();

	// 1:1문의 게시판 조회
	List<Qna> adminQnaList(RowBounds rowBounds);

	// 1:1문의 게시글 읽기 조회
	Qna selectQnaOne(int qnaNo);

	// 1:1문의 게시글 수정
	int qnaUpdate(Qna qna);

	// 1:1문의 게시글 삭제
	int qnaDelete(int qnaNo);

	// 1:1문의 게시글 삽입
	int qnaInsert(Qna qna);

	// 1:1문의 게시글 답변 등록 확인(업데이트)
	int updateAnswer(int qnaNo);

	// 1:1문의 게시글 답변 불러오기(select)
	QnaComment selectQnaCommentList(QnaComment qnaCommentNo);

	// 1:1문의 게시글 답변 수정 (update)
	int qnaAnswerUpdate(QnaComment qnaCommentObj);

	// 1:1문의 게시글 답변 쓰기(삽입)
	int qnaAnswerInsert(QnaComment qnaCommentObj);

	// 1:1문의 게시글 검색
	List<Qna> getSearchList(Qna condition, RowBounds rowBounds);

	// 회원관리*****************************************************

	/**
	 * 회원 조회
	 * 
	 * @return
	 */
	int userListCount();

	/**
	 * 회원관리 조회
	 * 
	 * @return
	 */
	List<User> adminUserList(RowBounds rowBounds);

	/**
	 * 회원 선택 삭제
	 * 
	 * @return
	 */
	int userDelete(int userNo);

	/**
	 * 회원 검색
	 * 
	 * @param condition
	 * @param userList
	 * @return
	 */
	List<User> getUserSearchList(User condition, RowBounds rowBounds);

	/**회원 검색에 따른 회원 수 불러오기
	 * @param condition
	 * @return
	 */
	int userfilterListCount(User condition);

	// 영화 관리******************************************************

	/**
	 * 영화 개수
	 * 
	 * @return
	 */
	int movieListCount();

	/**
	 * 영화 List 조회
	 * 
	 * @param rowBounds
	 * @return
	 */
	List<Movie> adminMovieList(RowBounds rowBounds);

	/**
	 * 영화 검색
	 * 
	 * @param condition
	 * @param rowBounds
	 * @return
	 */
	List<Movie> getMovieSearchList(Movie condition, RowBounds rowBounds);

	// 상영관 List 조회************************************************
	/**
	 * 상영관 영화 List 조회
	 * 
	 * @param condition
	 * @return
	 */
	List<Movie> MovieScheduleList(Movie condition, RowBounds rowBounds);

	/**
	 * 2관 페이지 이동
	 * 
	 * @param movieTheaterNo
	 * @param rowBounds
	 * @return
	 */
	List<Movie> adminCinemaTwo(String movieTheaterNo, RowBounds rowBounds);

	/**
	 * 상영관 등록 영화 불러오기
	 * 
	 * @return
	 */
	List<Movie> cinemaList();

	/**
	 * 상영관 영화,상영시간 등록
	 * 
	 * @param movie
	 * @return
	 */
	int cinemaListInsert(Movie movie);

	/**
	 * 상영관 스케쥴 개수
	 * 
	 * @return
	 */
	int movieScheduleListCount();

	/**
	 * 상영관 영화 스케쥴 삽입(insert)
	 * 
	 * @param movie
	 * @return
	 */
	int adminCinemaInsert(Movie movie);

	// 공지사항 관리*************************************************

	/**
	 * 공지사항 개수 세기
	 * 
	 * @return
	 */
	int noticeListCount();

	/**
	 * 공지사항 List 조회
	 * 
	 * @param rowBounds
	 * @return
	 */
	List<Notice> adminNoticeList(RowBounds rowBounds);

	/**
	 * 공지사항 게시글 조회
	 * 
	 * @param noticeNo
	 * @return
	 */
	List<Notice> adminNoticeOne(Notice notice);

	/**
	 * 공지사항 게시글 쓰기
	 * 
	 * @param notice
	 * @return
	 */
	int noticeWriteInsert(Notice notice);

	
	/**공지사항 수정
	 * @param notice
	 * @return
	 */
	int noticeUpdate(Notice notice);

	
	/**
	 * 공지사항 게시글 삭제
	 * 
	 * @param noticeNo
	 * @return
	 */
	int noticeDelete(int noticeNo);
	
	
	/**공지사항 검색에 따른 게시글 개수
	 * @param condition
	 * @return
	 */
	int noticeFilterListCount(Notice condition);

	/**
	 * FAQ (자주 찾는 질문) 게시글 검색
	 * 
	 * @param condition
	 * @param noticeList
	 * @return
	 */
	List<Notice> getNoticeSearchList(Notice condition, RowBounds rowBounds);

	// FAQ (자주 찾는 질문) List 조회*****************************

	/**
	 * FAQ 게시판 개수
	 * 
	 * @return
	 */
	int faqListCount();

	/**
	 * FAQ 게시판 List 조회
	 * 
	 * @param rowBounds
	 * @return
	 */
	List<FAQ> adminFaqList(RowBounds rowBounds);

	/**
	 * FAQ (자주 찾는 질문) 게시글 조회
	 * 
	 * @param faq
	 * @return
	 */
	List<FAQ> adminFaqOne(FAQ faq);

	/**
	 * FAQ (자주 찾는 질문) 게시글 수정 조회
	 * 
	 * @param fAQNo
	 * @return
	 */
	int updateFaq(FAQ faq);

	/**
	 * FAQ (자주 찾는 질문) 게시글 삭제(update)
	 * 
	 * @param faq
	 * @return
	 */
	int deleteFaq(FAQ faq);

	/**
	 * FAQ (자주 찾는 질문) 글 삽입
	 * 
	 * @param faq
	 * @return
	 */
	int faqInsert(FAQ faq);

	/**
	 * FAQ (자주 찾는 질문) 게시글 선택 삭제(update)
	 * 
	 * @param fAQNo
	 * @return
	 */
	int deleteFaq(int fAQNo);

	/**
	 * FAQ 게시글 검색
	 * 
	 * @param condtion
	 * @param rowBounds
	 * @return
	 */
	List<FAQ> getFaqSearchList(FAQ condtion, RowBounds rowBounds);

	List<SalesByPeriod> getSalesByDay();

//신고하기 관리******************************************************************************************

	/**
	 * 신고하기 글 개수 불러오기
	 * 
	 * @return
	 */
	int reportListCount();

	/**
	 * 신고하기 게시판 불러오기
	 * 
	 * @param rowBounds
	 * @return
	 */
	List<Report> adminReportList(RowBounds rowBounds);

	/**
	 * 신고하기 게시글 읽기
	 * 
	 * @param report
	 * @return
	 */
	List<Report> adminReportOne(int reportNo);

	/*
	 * 신고하기에서 리뷰삭제하기
	 * 
	 * @param review
	 * 
	 * @return
	 */
	int deleteReview(int reviewNo);

	/**신고글 처리여부 
	 * @param reportNo
	 * @return
	 */
	int updateDeleteReport(int reportNo);

	/**신고관리 검색
	 * @param condition
	 * @param rowBounds
	 * @return
	 */
	List<Report> getReportSearchList(Report condition, RowBounds rowBounds);

	//리뷰관리하기**************************************************************************************************************
	
	//리뷰 게시글 개수 불러오기
	int reviewListCount();

	//리뷰 게시판 불러오기
	List<Review> adminReviewList(RowBounds rowBounds);

	//리뷰 검색
	List<Review> getReviewSearchList(Review condition, RowBounds rowBounds);

	//영화 관 별 개수
	int movieCinemaCount();

	int movieCinemaCount(String movieTheaterNo);

	//Qna 문의관리 검색에 따른 개수 불러오기
	int qnaFilterListCount(Qna condition);

	//FAQ 검색에 따른 개수 불러오기
	int faqFilterListCount(FAQ condition);

	int reviewFilterListCount(Review condition);

	//신고하기 검색에 따른 개수 불러오기
	int reportFilterListCount(Report condition);

	//탈퇴한 회원 수 불러오기
	int userOutListCount(User condition);

	//탈퇴한 회원 리스트 불러오기
	List<User> adminUserOutList(User condtion,RowBounds rowBounds);

	int userInListCount();

	int userOutListCount();

	//리뷰 복구
	int restoreReview(int reviewNo);

	//영화 검색에 따른 개수 불러오기
	int movieFilterListCount(Movie condition);

	//리뷰 게시글 읽어오기
	List<Review> adminReviewOne(int reviewNo);

	
	

	

	



	
	// 상영관 리스트 조회(찬희)
	List<Movie> selectCinemaList(Map<String, Object> paramMap, RowBounds rowBounds);

	// 상영관 리스트 갯수 조회(페이지네이션용)(찬희)
	int getListCount(Map<String, Object> paramMap);

	// 상영관 세부 시간 조회 AJAX(찬희)
	List<String> selectDetailTime(Map<String, Object> paramMap);
	
	// 상영관 세부 시간 삭제(찬희)
	int deleteDetailTime(Schedule schedule);

	// 예약된 좌석이 있는지 확인(찬희)
	int selectTicketing(Schedule schedule);

}
