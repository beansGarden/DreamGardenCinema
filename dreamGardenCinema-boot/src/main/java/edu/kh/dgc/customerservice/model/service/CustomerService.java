package edu.kh.dgc.customerservice.model.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import edu.kh.dgc.customerservice.model.dto.FAQ;
import edu.kh.dgc.notice.model.dto.Notice;
import edu.kh.dgc.qna.model.dto.Qna;

public interface CustomerService {

	// FAQ 게시글 전체 목록 조회
	List<FAQ> main(FAQ faq);

	// 검색어와 일치하는 FAQ 게시글 목록 조회
	List<FAQ> searchFAQList(String searchQuery);

	// 공지사항 게시글 조회한 경우 해당 게시글 번호와 일치하는 내용 페이지로 이동
	List<Notice> noticeSelect(String noticeNo);

	// 공지사항 전체 목록 조회
	List<Notice> noticeList(Notice notice);

	// 검색어 조회
	List<Notice> noticeSearchList(Map<String, Object> param);

	// 1:1문의 등록(회원)
	int cusQAInsert(Qna qna, List<MultipartFile> images, String selectedValue)throws IllegalStateException, IOException;



}
