package edu.kh.dgc.customerservice.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import edu.kh.dgc.customerservice.model.dto.FAQ;

@Mapper
public interface CustomerServiceMapper {
	
	// FAQ상영관 전체 목록 조회
	List<FAQ> main(FAQ faq);
	
	// FAQ(상영관 이용 관련) 목록 조회
	List<FAQ> theaterList(String fAQCategory);

}
