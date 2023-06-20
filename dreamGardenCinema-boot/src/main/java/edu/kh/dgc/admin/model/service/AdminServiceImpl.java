package edu.kh.dgc.admin.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.kh.dgc.admin.model.dao.AdminMapper;
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
	
	//관리자 사이드바 로그인 보여주기
	@Override
	public List<User> getAdminDetails() {
		
		return mapper.getAdminDetails();
	}
	
	
	//1:1문의 게시판 조회
	@Override
	public List<Qna> adminQnaList() {

		return mapper.adminQnaList();
	}

	//1:1문의 게시글 읽기 조회
	@Override
	public Qna selectQnaOne(int qnaNo) {
		
		return mapper.selectQnaOne(qnaNo);
	}

	//1:1문의 게시글 수정
	@Override
	public int qnaUpdate(Qna qna) {
		return mapper.qnaUpdate(qna);
	}

	//1:1문의 게시글 삭제
	@Override
	public int qnaDelete(int qnaNo) {
			return mapper.qnaDelete(qnaNo);
	}

	//1:1 문의 게시글 삽입
	@Override
	public int qnaInsert(Qna qna) {
		
		return mapper.qnaInsert(qna);
	}

	//1:1문의 게시글 답변 쓰기(삽입)
	@Override
	public int qnaAnswerInsert(QnaComment qnaComment) {
		
		return  mapper.qnaAnswerInsert(qnaComment);
	}

	//1:1문의 게시글 답변 등록 확인(업데이트)
	@Override
	public QnaComment updateAnswer(int qnaNo) {

		return mapper.updateAnswer(qnaNo);
	}

	//1:1문의 게시글 답변 불러오기(select)
	@Override
	public QnaComment selectQnaCommentList(QnaComment qnaCommentNo) {
		
		return mapper.selectQnaCommentList(qnaCommentNo);
	}
	//1:1문의 게시글 답변 수정 (update)
	@Override
	public int qnaAnswerUpdate(QnaComment qnaComment) {

		return mapper.qnaAnswerUpdate(qnaComment);
	}
	
	//1:1 문의사항 검색
		@Override
		public List<Qna> getSearchList(Qna qnaList) {
			
			return mapper.getSearchList(qnaList);
		}


	//회원****************************************************
	
	//회원관리 조회
	@Override
	public List<User> adminUserList() {
		
		return mapper.adminUserList();
	}

	//회원 선택 삭제
	@Override
	public int userDelete(int userNo) {
		
		return mapper.userDelete(userNo);
	}
	//영화***************************************************
	
	//영화 List 조회
	@Override
	public List<Movie> adminMovieList() {
	
		return mapper.adminMovieList();
	}

	//공지사항************************************************
	
	//공지사항 List 조회
	@Override
	public List<Notice> adminNoticeList() {
	
		return mapper.adminNoticeList();
	}

	//공지사항 게시글 조회
	@Override
	public List<Notice> adminNoticeOne(Notice notice) {
		
		return mapper.adminNoticeOne(notice);
	}

	//공지사항 게시글 쓰기
	@Override
	public int noticeWriteInsert(Notice notice) {
		
		return mapper.noticeWriteInsert(notice);
	}

	//공지사항 게시글 삭제
	@Override
	public int noticeDelete(int noticeNo) {
		
		return mapper.noticeDelete(noticeNo);
	}

	
	//FAQ (자주 찾는 질문) List 조회*****************************
	
	//FAQ 게시판 List 조회
	@Override
	public List<FAQ> adminFaqList() {
	
		return mapper.adminFaqList();
	}

	//FAQ (자주 찾는 질문) 게시글 조회
	@Override
	public List<FAQ> adminFaqOne(FAQ faq) {

		return  mapper.adminFaqOne(faq);
	}
 
	//FAQ (자주 찾는 질문) 글 수정
	@Override
	public int updateFaq(FAQ faq) {
		
		return mapper.updateFaq(faq);
	}

	//FAQ (자주 찾는 질문) 글 삭제
	@Override
	public int deleteFaq(FAQ faq) {
		
		return mapper.deleteFaq(faq);
	}

	//FAQ (자주 찾는 질문) 글 삽입
	@Override
	public int faqInsert(FAQ faq) {
	
		return mapper.faqInsert(faq);
	}

	





	


}
	
	


	


