package edu.kh.dgc.admin.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import edu.kh.dgc.movie.model.dto.Movie;
import edu.kh.dgc.notice.model.dto.Notice;
import edu.kh.dgc.qna.model.dto.Qna;
import edu.kh.dgc.qna.model.dto.QnaComment;
import edu.kh.dgc.user.model.dto.User;

@Mapper
public interface AdminMapper {
	
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
	int qnaAnswerUpdate(QnaComment qnaComment);

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



}
