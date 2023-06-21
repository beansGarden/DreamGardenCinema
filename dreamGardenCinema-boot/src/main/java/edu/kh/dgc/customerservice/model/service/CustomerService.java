package edu.kh.dgc.customerservice.model.service;

import java.util.List;

import edu.kh.dgc.customerservice.model.dto.FAQ;
import edu.kh.dgc.notice.model.dto.Notice;

public interface CustomerService {

	// FAQ 게시글 전체 목록 조회
	List<FAQ> main(FAQ faq);

	// 검색어와 일치하는 FAQ 게시글 목록 조회
	List<FAQ> searchFAQList(String searchQuery);

	// 공지사항 전체 목록 조회
	List<Notice> noticeList(Notice notice);

}
