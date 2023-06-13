package edu.kh.dgc.admin.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import edu.kh.dgc.qna.model.dto.Qna;

@Mapper
public interface AdminMapper {
	
	//1:1문의 게시판 조회
	List<Qna> adminQnaList();
	
	//1:1문의 게시글 읽기 조회
	Qna selectQnaOne(int qnaNo);

}
