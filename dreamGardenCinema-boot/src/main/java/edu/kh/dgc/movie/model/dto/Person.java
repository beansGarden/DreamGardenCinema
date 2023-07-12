package edu.kh.dgc.movie.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Person {
	
	private int personNo;
	private int movieNo;
	private String name;
	private String role;
	private String img;
	
}
