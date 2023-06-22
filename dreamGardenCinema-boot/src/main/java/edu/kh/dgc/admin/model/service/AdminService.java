package edu.kh.dgc.admin.model.service;

import java.util.List;

import edu.kh.dgc.customerservice.model.dto.FAQ;
import edu.kh.dgc.movie.model.dto.Movie;
import edu.kh.dgc.notice.model.dto.Notice;
import edu.kh.dgc.qna.model.dto.Qna;
import edu.kh.dgc.qna.model.dto.QnaComment;
import edu.kh.dgc.user.model.dto.User;

public interface AdminService {

	//관리자 사이드바 로그인 보여주기
	List<User> getAdminDetails();
	
	//1:1문의 게시판 조회
	List<Qna> adminQnaList();

	//1:1 문의 게시글 읽기 조회
	Qna selectQnaOne(int qnaNo);
	
	//1:1 문의 게시글 수정
	int qnaUpdate(Qna qna);

	//1:1 문의 게시글 삭제
	int qnaDelete(int qnaNo);

	//1:1 문의 게시글 쓰기(삽입)
	int qnaInsert(Qna qna);


	//1:1문의 게시글 답변 등록 확인(업데이트)
	QnaComment updateAnswer(int qnaNo);

	//1:1문의 게시글 답변 불러오기(select)
	QnaComment selectQnaCommentList(QnaComment qnaComment);

	//1:1문의 게시글 답변 수정 (update)
	int qnaAnswerUpdate(QnaComment qnaCommentObj);
	
	//1:1문의 게시글 답변 쓰기 (insert)
	int qnaAnswerInsert(QnaComment qnaComment);

	//1:1문의 게시글검색
	List<Qna> getSearchList(Qna qnaList);

	//회원*****************************************
	
	//회원관리 List 조회
	List<User> adminUserList();

	//회원 선택 삭제
	int userDelete(int userNo);

	//영화 관리***********************************
	
	//영화 List 조회
	List<Movie> adminMovieList();

	
	//상영관 관리***************************
	
	//상영관 영화 List 조회
	List<Movie> adminCinemaList();
	
	//2관 페이지 이동
	List<Movie> adminCinemaTwo(String movieTheaterNo);

	//상영관 등록 영화 불러오기
	List<Movie> cinemaList();

	//상영관 영화,상영시간 등록
	int cinemaListInsert(Movie movie);
	
	//공지사항 관리*******************************
	
	//공지사항 List 조회
	List<Notice> adminNoticeList();

	//공지사항 게시글 조회
	List<Notice> adminNoticeOne(Notice notice);

	//공지사항 게시글 쓰기
	int noticeWriteInsert(Notice notice);

	//공지사항 게시글 삭제
	int noticeDelete(int noticeNo);
	
	//공지사항 게시글 검색
	List<Notice> getNoticeSearchList(Notice noticeList);



	
	//FAQ (자주 찾는 질문) 관리*****************************
	
	//FAQ (자주 찾는 질문) List 조회
	List<FAQ> adminFaqList();

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

	//FAQ (자주 찾는 질문) 게시글 검색
	List<FAQ> getFaqSearchList(FAQ faqList);

	

	

	
	






	



	
}
