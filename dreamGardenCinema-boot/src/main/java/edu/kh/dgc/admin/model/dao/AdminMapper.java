package edu.kh.dgc.admin.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import edu.kh.dgc.customerservice.model.dto.FAQ;
import edu.kh.dgc.movie.model.dto.Movie;
import edu.kh.dgc.notice.model.dto.Notice;
import edu.kh.dgc.qna.model.dto.Qna;
import edu.kh.dgc.qna.model.dto.QnaComment;
import edu.kh.dgc.user.model.dto.User;

@Mapper
public interface AdminMapper {
	
	/**관리자 사이드바 로그인 보여주기
	 * @return
	 */
	List<User> getAdminDetails();

	
	//1:1문의 게시판 조회
	List<Qna> adminQnaList();
	
	//1:1문의 게시글 읽기 조회
	Qna selectQnaOne(int qnaNo);

	//1:1문의 게시글 수정
	int qnaUpdate(Qna qna);

	//1:1문의 게시글 삭제
	int qnaDelete(int qnaNo);

	//1:1문의 게시글 삽입
	int qnaInsert(Qna qna);

	//1:1문의 게시글 답변 쓰기(삽입)
	int qnaAnswerInsert(QnaComment qnaComment);

	//1:1문의 게시글 답변 등록 확인(업데이트)
	QnaComment updateAnswer(int qnaNo);

	//1:1문의 게시글 답변 불러오기(select)
	QnaComment selectQnaCommentList(QnaComment qnaCommentNo);
	
	//1:1문의 게시글 답변 수정 (update)
	int qnaAnswerUpdate(QnaComment qnaCommentObj);

	//1:1문의 게시글 검색
	List<Qna> getSearchList(Qna qnaList);
	
	//회원관리*****************************************************
	
	/**회원관리 조회
	 * @return
	 */
	List<User> adminUserList();

	/**회원 선택 삭제
	 * @return
	 */
	int userDelete(int userNo);
	
	//영화 관리******************************************************
	
	/**영화 List 조회
	 * @return
	 */
	List<Movie> adminMovieList();

	//공지사항 관리*************************************************
	
	/**공지사항 List 조회
	 * @return
	 */
	List<Notice> adminNoticeList();

	/**공지사항 게시글 조회
	 * @param noticeNo
	 * @return
	 */
	List<Notice> adminNoticeOne(Notice notice);

	/**공지사항 게시글 쓰기
	 * @param notice
	 * @return
	 */
	int noticeWriteInsert(Notice notice);

	/**공지사항 게시글 삭제
	 * @param noticeNo
	 * @return
	 */
	int noticeDelete(int noticeNo);
	
	/**FAQ (자주 찾는 질문) 게시글 검색
	 * @param noticeList
	 * @return
	 */
	List<Notice> getNoticeSearchList(Notice noticeList);


	//FAQ (자주 찾는 질문) List 조회*****************************
	
	/**FAQ 게시판 List 조회
	 * @return
	 */
	List<FAQ> adminFaqList();

	/**FAQ (자주 찾는 질문) 게시글 조회
	 * @param faq
	 * @return
	 */
	List<FAQ> adminFaqOne(FAQ faq);

	/**FAQ (자주 찾는 질문) 게시글 수정 조회
	 * @param fAQNo
	 * @return
	 */
	int updateFaq(FAQ faq);

	/**FAQ (자주 찾는 질문) 게시글 삭제(update)
	 * @param faq
	 * @return
	 */
	int deleteFaq(FAQ faq);


	/**FAQ (자주 찾는 질문) 글 삽입
	 * @param faq
	 * @return
	 */
	int faqInsert(FAQ faq);


	/**FAQ (자주 찾는 질문) 게시글 선택 삭제(update)
	 * @param fAQNo
	 * @return
	 */
	int deleteFaq(int fAQNo);


	/**FAQ 게시글 검색
	 * @param faqList
	 * @return
	 */
	List<FAQ> getFaqSearchList(FAQ faqList);



	








}
