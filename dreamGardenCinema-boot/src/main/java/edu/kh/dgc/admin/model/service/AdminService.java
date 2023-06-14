package edu.kh.dgc.admin.model.service;

import java.util.List;

import edu.kh.dgc.qna.model.dto.Qna;

public interface AdminService {

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



	
}
