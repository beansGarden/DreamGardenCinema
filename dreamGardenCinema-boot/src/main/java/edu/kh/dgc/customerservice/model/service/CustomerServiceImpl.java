package edu.kh.dgc.customerservice.model.service;

import java.util.List;

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

	// 공지사항 전체목록 조회 서비스
	@Override
	public List<Notice> noticeList(Notice notice) {
		return mapper.noticeList(notice);
	}

	
}
