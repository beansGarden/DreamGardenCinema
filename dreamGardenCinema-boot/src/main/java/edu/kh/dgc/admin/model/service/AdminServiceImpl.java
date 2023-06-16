package edu.kh.dgc.admin.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.kh.dgc.admin.model.dao.AdminDAO;
import edu.kh.dgc.movie.model.dto.Movie;
import edu.kh.dgc.notice.model.dto.Notice;
import edu.kh.dgc.qna.model.dto.Qna;
import edu.kh.dgc.qna.model.dto.QnaComment;
import edu.kh.dgc.user.model.dto.User;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminDAO dao;
	
	//1:1문의 게시판 조회
	@Override
	public List<Qna> adminQnaList() {

		return dao.adminQnaList();
	}

	//1:1문의 게시글 읽기 조회
	@Override
	public Qna selectQnaOne(int qnaNo) {
		
		return dao.selectQnaOne(qnaNo);
	}

	//1:1문의 게시글 수정
	@Override
	public int qnaUpdate(Qna qna) {
		return dao.qnaUpdate(qna);
	}

	//1:1문의 게시글 삭제
	@Override
	public int qnaDelete(int qnaNo) {
			return dao.qnaDelete(qnaNo);
	}

	//1:1 문의 게시글 삽입
	@Override
	public int qnaInsert(Qna qna) {
		
		return dao.qnaInsert(qna);
	}

	//1:1문의 게시글 답변 쓰기(삽입)
	@Override
	public int qnaAnswerInsert(QnaComment qnaComment) {
		
		return  dao.qnaAnswerInsert(qnaComment);
	}

	//1:1문의 게시글 답변 등록 확인(업데이트)
	@Override
	public QnaComment updateAnswer(int qnaNo) {

		return dao.updateAnswer(qnaNo);
	}

	//1:1문의 게시글 답변 불러오기(select)
	@Override
	public QnaComment selectQnaCommentList(QnaComment qnaCommentNo) {
		
		return dao.selectQnaCommentList(qnaCommentNo);
	}
	//1:1문의 게시글 답변 수정 (update)
	@Override
	public int qnaAnswerUpdate(QnaComment qnaComment) {

		return dao.qnaAnswerUpdate(qnaComment);
	}

	//회원****************************************************
	
	//회원관리 조회
	@Override
	public List<User> adminUserList() {
		
		return dao.adminUserList();
	}

	//회원 선택 삭제
	@Override
	public int userDelete(int userNo) {
		
		return dao.userDelete(userNo);
	}
	//영화***************************************************
	
	//영화 List 조회
	@Override
	public List<Movie> adminMovieList() {
	
		return dao.adminMovieList();
	}

	//공지사항************************************************
	
	//공지사항 List 조회
	@Override
	public List<Notice> adminNoticeList() {
	
		return dao.adminNoticeList();
	}

	//공지사항 게시글 조회
	@Override
	public List<Notice> adminNoticeOne(Notice notice) {
		
		return dao.adminNoticeOne(notice);
	}

	//공지사항 게시글 쓰기
	@Override
	public int noticeWriteInsert(Notice notice) {
		
		return dao.noticeWriteInsert(notice);
	}

	//공지사항 게시글 삭제
	@Override
	public int noticeDelete(int noticeNo) {
		
		return dao.noticeDelete(noticeNo);
	}
	
	


	

}
