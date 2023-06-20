package edu.kh.dgc.admin.model.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.kh.dgc.customerservice.model.dto.FAQ;
import edu.kh.dgc.movie.model.dto.Movie;
import edu.kh.dgc.notice.model.dto.Notice;
import edu.kh.dgc.qna.model.dto.Qna;
import edu.kh.dgc.qna.model.dto.QnaComment;
import edu.kh.dgc.user.model.dto.User;

@Repository
public class AdminDAO {
		
	@Autowired
	private AdminMapper mapper;
	
	
	/** 관리자 사이드바 로그인 보여주기
	 * @return
	 */
	public List<User> getAdminDetails() {
		
		return mapper.getAdminDetails();
	}

	
	
	/**1:1문의사항 (QNA) 게시글 조회 리스트
	 * @return
	 */
	public List<Qna> adminQnaList() {
		
		return mapper.adminQnaList();
	}

	/**1:1 문의사항 (QNA) 게시글 읽기 조회
	 * @param qnaNo
	 * @return
	 */
	public Qna selectQnaOne(int qnaNo) {

		return mapper.selectQnaOne(qnaNo);
	}

	
	/**1:1 문의사항 (QNA) 게시글 수정
	 * @param qna
	 * @return
	 */
	public int qnaUpdate(Qna qna) {
		
		return mapper.qnaUpdate(qna);
	}

	
	/**1:1 문의사항 (QNA) 게시글 삭제
	 * @param qna
	 * @return
	 */
	public int qnaDelete(int qnaNo) {
		
		return mapper.qnaDelete(qnaNo);
	}

	//1:1 문의 게시글 삽입
	public int qnaInsert(Qna qna) {
		return mapper.qnaInsert(qna);
	}

	//1:1문의 게시글 답변 쓰기(삽입)
	public int qnaAnswerInsert(QnaComment qnaComment) {
		
		return mapper.qnaAnswerInsert(qnaComment);
	}

	//1:1문의 게시글 답변 등록 확인(업데이트)
	public QnaComment updateAnswer(int qnaNo) {
		
		return mapper.updateAnswer(qnaNo);
	}

	//1:1문의 게시글 답변 불러오기(select)
	public QnaComment selectQnaCommentList(QnaComment qnaCommentNo) {
		
		return mapper.selectQnaCommentList(qnaCommentNo);
	}
	//1:1문의 게시글 답변 수정 (update)
	public int qnaAnswerUpdate(QnaComment qnaComment) {
		
	return mapper.qnaAnswerUpdate(qnaComment);
	}
	
	/**1:1문의 게시글 검색
	 * @param qnaList
	 * @return
	 */
	public List<Qna> getSearchList(Qna qnaList) {

		return mapper.getSearchList(qnaList);
	}

	//회원*******************************************************
	
	/**회원관리 조회
	 * @return
	 */
	public List<User> adminUserList() {
		
		return mapper.adminUserList();
	}

	/**회원 선택 삭제
	 * @param userNo
	 * @return
	 */
	public int userDelete(int userNo) {
		
		return mapper.userDelete(userNo);
	}
	
	//영화 관리***************************************
	
	/**영화 List 조회
	 * @return
	 */
	public List<Movie> adminMovieList() {
		
		return mapper.adminMovieList();
	}

	//공지사항 관리***************************************
	
	/**공지사항 List 조회
	 * @return
	 */
	public List<Notice> adminNoticeList() {
	
		return mapper.adminNoticeList();
	}

	
	/**공지사항 게시글 조회
	 * @param noticeNo
	 * @return
	 */
	public List<Notice> adminNoticeOne(Notice notice) {
		
		return mapper.adminNoticeOne(notice);
	}

	
	/**공지사항 게시글 쓰기
	 * @param notice
	 * @return
	 */
	public int noticeWriteInsert(Notice notice) {
		
		return mapper.noticeWriteInsert(notice);
	}

	/**공지사항 게시글 삭제
	 * @param noticeNo
	 * @return
	 */
	public int noticeDelete(int noticeNo) {
		
		return mapper.noticeDelete(noticeNo);
	}

	//FAQ (자주 찾는 질문) List 조회*****************************
	
	
	/**FAQ 게시판 List 조회
	 * @return
	 */
	public List<FAQ> adminFaqList() {
	
		return mapper.adminFaqList();
	}

	
	/**FAQ (자주 찾는 질문) 게시글 조회
	 * @param faq
	 * @return
	 */
	public List<FAQ> adminFaqOne(FAQ faq) {
		
		return mapper.adminFaqOne(faq);
	}

	
	/**FAQ (자주 찾는 질문) 게시글 수정 조회
	 * @param fAQNo
	 * @return
	 */
	public int updateFaq(FAQ faq) {
		
		return mapper.updateFaq(faq);
	}

	/**FAQ (자주 찾는 질문) 게시글 삭제(update)
	 * @param faq
	 * @return
	 */
	public int deleteFaq(FAQ faq) {
		
		return mapper.deleteFaq(faq);
	}

	

	


	

	
}
