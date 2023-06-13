package edu.kh.project.admin.model.service;

import java.util.List;

import edu.kh.project.qna.model.dto.Qna;

public interface AdminService {

	//1:1문의 게시판 조회
	List<Qna> adminQnaList();

	//1:1 문의 게시글 읽기 조회
	Qna selectQnaOne(int qnaNo);

	
}
