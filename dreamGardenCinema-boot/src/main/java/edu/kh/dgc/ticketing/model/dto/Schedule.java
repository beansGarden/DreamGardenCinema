package edu.kh.dgc.ticketing.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Schedule {
	private String movieTime;
	private String movieTheater;
	private int movieNo;
	private String checkTime;
}
