package edu.kh.dgc.customerservice.model.dao;

import java.util.List;

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

	// 공지사항 전체목록 조회
	List<Notice> noticeList(Notice notice);

}
