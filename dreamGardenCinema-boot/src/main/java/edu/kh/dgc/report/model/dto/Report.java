package edu.kh.dgc.report.model.dto;

import groovy.transform.ToString;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Report {

	
	private int reportNo; 
	private String reportTitle;
	private String reportContent;
	private String enrollDate;
	private String reportFl;
	private String reportDeleteFl;
	private int reviewNo; //리뷰번호
	private int writerNo; //신고한 유저
	private int reportedNo; //신고된 유저
	
}
