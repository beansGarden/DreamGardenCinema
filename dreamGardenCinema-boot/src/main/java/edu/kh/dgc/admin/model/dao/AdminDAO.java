package edu.kh.dgc.admin.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.kh.dgc.qna.model.dto.Qna;

@Repository
public class AdminDAO {
		
	@Autowired
	private AdminMapper mapper;
	
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
	 * @param qnaNo
	 * @return
	 */
	public Qna updateQna(int qnaNo) {
	
		return mapper.updateQna(qnaNo);
	}
	
}
