package edu.kh.dgc.admin.model.service;

import java.util.List;
import java.util.Map;

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

public interface AdminService {

	
	//대시보드 
	
	//영화 상역작만 불러오기
	List<Movie> cinemaCurrentList();
	
	//영화별 매출 리스트 불러오기
	List<Ticket> ticketList(String movieNo);
	
	
	//대시보드 1:1문의 최신 5개만 오게하기
	List<Qna> adminQnaList5();
	
	/*
	 * //영화 개수 가져오기 int movieListCount();
	 */
	//------------------------------------------
	//관리자 사이드바 로그인 보여주기
	List<User> getAdminDetails();
	
	//1:1문의 게시판 조회
	Map<String, Object> adminQnaList(Qna condition,int cp);

	//1:1 문의 게시글 읽기 조회
	Qna  selectQnaOne(int qnaNo);
	
	//1:1 문의 게시글 수정
	int qnaUpdate(Qna qna);

	//1:1 문의 게시글 삭제
	int qnaDelete(int qnaNo);

	//1:1 문의 게시글 쓰기(삽입)
	int qnaInsert(Qna qna);


	//1:1문의 게시글 답변 등록 확인(업데이트)
	int updateAnswer(int qnaNo);

	//1:1문의 게시글 답변 불러오기(select)
	QnaComment selectQnaCommentList(QnaComment qnaComment);

	//1:1문의 게시글 답변 수정 (update)
	int qnaAnswerUpdate(QnaComment qnaCommentObj);
	
	//1:1문의 게시글 답변 쓰기 (insert)
	int qnaAnswerInsert(QnaComment qnaCommentObj);

	//1:1문의 게시글검색
	Map<String, Object> getSearchList(Qna condition, int cp);

	//1:1문의 Qna 전체 개수 가져오기
	int qnaListCount();

	//QNA 1:1 문의 게시글 전체 리스트 불러오기
	Map<String, Object> adminQnaAllList(int cp);

	//QNA 1:1 문의 삭제되 게시글 전체 리스트 불러오기
	Map<String, Object> adminQnaDeletedList(Qna condition,int cp);

	//QNA 게시글 전체 수
	int qnaListAllCount();

	//QNA 삭제된 게시글 수
	int qnaListDeletedCount();

	//QNA 전체 검색된 게시글 
	Map<String, Object> getAllSearchList(Qna condition, int cp);

	//QNA 삭제된 게시글 검색
	Map<String, Object> getDeletedSearchList(Qna condition, int cp);


	
	//회원*****************************************
	
	//전체 회원관리 List 조회
	Map<String, Object> adminUserList(int cp);
	
	//탈퇴한 회원을 제외한 회원 조회
	Map<String, Object> adminUserInList(User condition, int cp);
	
	//탈퇴한 회원 조회
	Map<String, Object> adminUserOutList(User condition, int cp);

	
	//회원 선택 삭제
	int userDelete(int userNo);

	//회원 선택 복구
	int restoreUserList(int userNo);
	
	//회원 검색
	Map<String, Object> getUserSearchList(User condition,int cp);
	
	//회원 전체 개수 가져오기
	int userListCount();

	//회원 게시글 검색 일반 회원
	Map<String, Object> getUserInSearchList(User condition, int cp);

	//회원 게시글 검색 탈퇴 회원
	Map<String, Object> getUserOutSearchList(User condition, int cp);



	
	//영화 관리***********************************
	
//	//영화 List 조회
//	Map<String, Object> adminMovieList(int cp);
//
//	//영화 검색
//	Map<String, Object> getMovieSearchList(Movie condition, int cp);


	
	//상영관 관리***************************
	
	//상영관 영화 List 조회
	Map<String, Object> adminCinemaList(Movie condition, int cp);
	
	//2관 페이지 이동
	Map<String, Object> adminCinemaTwo(String movieTheaterNo, int cp);

	//상영관 등록 영화 불러오기
	List<Movie> cinemaList();

	//상영관 영화,상영시간 등록
	int cinemaListInsert(Movie movie);
	
	//상영관 스케쥴 개수
	int movieScheduleListCount();

	//상영관 영화 스케쥴 삽입(insert)
	int adminCinemaInsert(Movie movie);
	
	//공지사항 관리*******************************
	
	//공지사항 List 조회
	Map<String, Object>  adminNoticeList(int cp);

	//삭제된 공지사항 조회
	Map<String, Object> adminNoticeDeletedList(int cp);

	//삭제 안 된 공지사항 조회
	Map<String, Object> adminNoticeInList(int cp);
	
	//공지사항 게시글 조회
	List<Notice> adminNoticeOne(Notice notice);

	//공지사항 게시글 쓰기
	int noticeWriteInsert(Notice notice);
	
	//공지사항 수정
	int noticeUpdate(Notice notice);


	//공지사항 게시글 삭제
	int noticeDelete(int noticeNo);
	
	//공지사항 게시글 선택 복구
	int noticeRestore(int noticeNo);

	
	//공지사항 게시글 검색
	Map<String, Object> getNoticeSearchList(Notice condition, int cp);

	//공지사항 전체 개수
	int noticeListCount();

	
	//FAQ (자주 찾는 질문) 관리*****************************
	
	//FAQ (자주 찾는 질문) List 조회
	Map<String , Object> adminFaqAllList(int cp);
	
	//FAQ (자주 찾는 질문) 삭제 안 한 게시글 List 조회
	Map<String, Object> adminFaqList(FAQ condition, int cp);
	
	//FAQ (자주 찾는 질문) 삭제 한 게시글 List 조회
	Map<String, Object> adminFaqDeletedList(FAQ condition, int cp);


	//FAQ (자주 찾는 질문) 게시글 조회
	List<FAQ> adminFaqOne(FAQ faq);

	//FAQ (자주 찾는 질문) 게시글 수정
	int updateFaq(FAQ faq);

	//FAQ (자주 찾는 질문) 게시글 삭제
	int deleteFaq(FAQ faq);
	

	//FAQ (자주 찾는 질문) 게시글 삽입(insert)
	int faqInsert(FAQ faq);

	//FAQ (자주 찾는 질문) 게시글 선택 삭제
	int deleteFaq(int fAQNo);

	//FAQ (자주 찾는 질문) 게시글 전체 검색
	Map<String, Object> getFaqAllSearchList(FAQ condition, int cp);
	
	//FAQ (자주 찾는 질문) 게시글 검색
	Map<String, Object> getFaqSearchList(FAQ condtion, int cp);

	// 지난 주 요일별 매출
	List<SalesByPeriod> getSalesByDay();

	//FAQ 삭제 안 한 개수
	int faqListCount();

	//FAQ 전체 게시글 수
	int faqListAllCount();

	//FAQ 삭제 한 게시글 수
	int faqListDeletedCount();

	//FAQ 선택 복구
	int restoreFaq(int FAQNo);
	
	//FAQ 삭제한 글 검색
	Map<String, Object> getFaqDeletedSearchList(FAQ condition, int cp);



	
	//신고하기***********************************************************************************
	
	//신고하기 Map 불러오기
	Map<String, Object> adminReportList(int cp);

	//신고하기 게시글 불러오기
	List<Report> adminReportOne(int reportNo);

	//신고관리에서 리뷰 삭제하기
	int deleteReview(int reviewNo);

	//신고글 처리여부 
	int updateDeleteReport(int reportNo);

	//신고관리 검색
	Map<String, Object> getReportSearchList(Report condition, int cp);

	//신고하기 개수 조회
	int reportListCount();

	

	
	
	//리뷰관리***********************************************************************************************
	
	//리뷰 게시글 조회
	Map<String, Object> adminReviewList(int cp);

	//리뷰 개수 조회
	int reviewListCount();

	//리뷰관리 검색
	Map<String, Object> getReviewSearchList(Review condition, int cp);

	int userInListCount();

	int userOutListCount();

	//리뷰 복구
	int restoreReview(int reviewNo);

	//리뷰 게시글 읽어오기
	List<Review> adminReviewOne(int reviewNo);




	
	

	
	// 상영관 리스트 조회(찬희)
	Map<String, Object> selectCinemaList(Map<String, Object> paramMap, String cp);

	// 상영관 세부 시간 조회 AJAX(찬희)
	List<String> selectDetailTime(Map<String, Object> paramMap);

	// 상영관 세부 시간 삭제(찬희)
	int deleteDetailTime(Schedule schedule);

	// 체크한 상영정보 삭제하기
	int deleteTotalTime(List<Map<String, String>> dataList);

	// 공지사항 삭제 안 한 게시글
	int noticeInListCount();
	
	// 공지사항 삭제 한 게시글
	int noticeOutListCount();
	
	// 년도별 분기 매출
	List<SalesByPeriod> quarterlySales(String selectedYear);

	// 년도별 분기 매출(첫 접속)
	List<SalesByPeriod> firstLoadingQuarterlySales(String currentYear);

	// 년도별 월 매출(첫 접속)
	List<SalesByPeriod> firstLoadingMonthlySalesByYear(String currentYear);

	// 년도별 월 매출
	List<SalesByPeriod> monthlySalesByYear(String selectedYear);




	












	




	





	

	

	

	
	






	



	
}
