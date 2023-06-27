package edu.kh.dgc.movie.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class MovieComment {
	private int reviewNo;
	private int score;
	private String reviewDate;
	private String reviewContent;
	private int userNo;
	private int movieNo;
}
