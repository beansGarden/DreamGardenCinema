package edu.kh.dgc.review.model.dto;

import groovy.transform.ToString;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
	
}
