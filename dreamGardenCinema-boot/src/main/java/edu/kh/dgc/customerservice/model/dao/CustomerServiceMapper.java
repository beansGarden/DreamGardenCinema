package edu.kh.dgc.customerservice.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import edu.kh.dgc.customerservice.model.dto.FAQ;

@Mapper
public interface CustomerServiceMapper {

	List<FAQ> Main(FAQ faq);

}
