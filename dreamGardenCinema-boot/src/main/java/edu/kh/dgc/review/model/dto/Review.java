package edu.kh.dgc.review.model.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Review {

	private int reviewNo;
	private int score;
	private String reviewDate;
	private String reviewContent;
	private int userNo;
	private int movieNo;
	private String reviewDeleteFl;
	
	//JOIN
	private String movieTitle;
	
	//검색필터
		private  String type;
		private String keyword;
}
