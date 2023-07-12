package edu.kh.dgc.customerservice.model.service;

import java.util.List;

import edu.kh.dgc.customerservice.model.dto.FAQ;

public interface CustomerFAQService {


	List<FAQ> theaterList(String FAQcategory);

	List<FAQ> customList(String FAQCategory);

	List<FAQ> membList(String FAQCategory);

}
