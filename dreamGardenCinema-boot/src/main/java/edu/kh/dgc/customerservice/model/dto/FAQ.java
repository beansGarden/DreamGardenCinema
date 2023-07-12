package edu.kh.dgc.customerservice.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class FAQ {
	
	private int FAQNo;
	private String FAQTitle;
	private String FAQContent;
	private String FAQDeleteFl;
	private String FAQCategory;
	
	// FAQ_CATEGORY 테이블 조인
	private String categoryTitle;
	
	//검색필터
	private  String type;
	private String keyword;
}
