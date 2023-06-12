package edu.kh.project.admin.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.kh.project.qna.model.dto.Qna;

@Repository
public class AdminDAO {
		
	@Autowired
	private AdminMapper mapper;
	
	/**1:1���� (QNA)����Ʈ �ҷ�����
	 * @return
	 */
	public List<Qna> adminQnaList() {
		
		return mapper.adminQnaList();
	}
	
}
