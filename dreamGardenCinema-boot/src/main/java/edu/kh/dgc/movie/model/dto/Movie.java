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
	private String runningTime; //러닝타임
	private String rating;
	private String releaseDate;
	private String producer;
	private int moviePrice;
	private String genre;
	private String screening;
	private int rank;
	private float ratio;
	private float score;
	private int ticketCount;
	
	//Movie schedule 조인 상영관
	private String movieTheater;
	private String movieTime; //상영시간
	private String movieday; //상영일
	

	//검색필터
	private  String type;
	private String keyword;
	
}
