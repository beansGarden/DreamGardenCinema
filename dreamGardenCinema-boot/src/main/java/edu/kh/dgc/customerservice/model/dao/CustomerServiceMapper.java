package edu.kh.dgc.customerservice.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import edu.kh.dgc.customerservice.model.dto.FAQ;
import edu.kh.dgc.notice.model.dto.Notice;

@Mapper
public interface CustomerServiceMapper {
	
	// FAQ 전체 목록 조회
	List<FAQ> main(FAQ faq);
	
	// FAQ(상영관 이용 관련) 목록 조회
	List<FAQ> theaterList(String FAQCategory);

	// FAQ(회원 관련) 목록 조회
	List<FAQ> customList(String FAQCategory);
	
	// FAQ(멤버십 관련) 목록 조회
	List<FAQ> membList(String FAQCategory);

	// 검색어와 일치하는 FAQ 목록 조회
	List<FAQ> searchFAQList(String searchQuery);

	// 공지사항 게시글 조회한 경우 해당 게시글 번호와 일치하는 내용 페이지로 이동
	List<Notice> noticeSelect(String noticeNo);

	// 공지사항 전체 목록 조회
	List<Notice> noticeList(Notice notice);

	// 검색 목록 조회
	List<Notice> noticeSearchList(Map<String, Object> param);


//	// 검색어 있을 경우 공지사항 목록 조회
//	Map<String, Object> noticeSearchList(Map<String, Object> paramMap);

}
