package edu.kh.dgc.movie.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Movie {

	private int movieNo;
	private String movieTitle;
	private String poster;
	private String synopsis;
	private String runningTime;
	private String rating;
	private String releaseDate;
	private String producer;
	private int moviePrice;
	private String mainPoster;
	private String genre;
	private String screening;
	private int rank;
	private String ratio;
	
	//Movie schedule 조인 상영관
	private String movieTheater;
	private String movieTime;
	
}
