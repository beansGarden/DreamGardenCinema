package edu.kh.dgc.report.model.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@lombok.ToString
public class Report {

	
	private int reportNo; 
	private String reportTitle;
	private String reportContent;
	private String enrollDate;
	private String reportFl; //신고된 글 처리 여부
	private String reportDeleteFl; //신고글 삭제여부
	private int reviewNo; //리뷰번호
	private int writerNo; //신고한 유저
	private int reportedNo; //신고된 유저
	
	//reivew join
	private String reviewContent;
	private String reviewDeleteFl;
	private String reportWriter; //신고한 유저
	private String reportedId; //신고된 유저
	
	//검색필터
	private  String type;
	private String keyword;
}
	

