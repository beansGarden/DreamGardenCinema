package edu.kh.dgc.customerservice.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.kh.dgc.customerservice.model.dao.CustomerServiceMapper;
import edu.kh.dgc.customerservice.model.dto.FAQ;
import edu.kh.dgc.notice.model.dto.Notice;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerServiceMapper mapper;
	

	// FAQ 게시글 전체목록 조회 서비스
	@Override
	public List<FAQ> main(FAQ faq) {
		return mapper.main(faq);
	}

	// 검색어와 일치하는 FAQ 게시글 목록 조회
	@Override
	public List<FAQ> searchFAQList(String searchQuery) {
		return mapper.searchFAQList(searchQuery);
	}

	// 공지사항 게시글 조회한 경우 해당 게시글 번호와 일치하는 내용 페이지로 이동
	@Override
	public List<Notice> noticeSelect(Notice notice) {
		return mapper.noticeSelect(notice);
	}
	
	// 공지사항 전체 목록 조회
	@Override
	public List<Notice> noticeList(Notice notice) {
		return mapper.noticeList(notice);
	}


	// 공지사항 검색어 있을 경우 목록 조회 서비스
//	@Override
//	public Map<String, Object> noticeSearchList(Map<String, Object> paramMap) {
//		return mapper.noticeSearchList(paramMap);
//	}

	
}
