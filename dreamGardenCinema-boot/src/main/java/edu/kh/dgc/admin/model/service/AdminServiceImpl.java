package edu.kh.dgc.admin.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.kh.dgc.admin.model.dao.AdminDAO;
import edu.kh.dgc.qna.model.dto.Qna;

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

}
