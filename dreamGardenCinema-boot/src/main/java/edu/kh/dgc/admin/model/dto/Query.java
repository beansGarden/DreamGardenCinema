package edu.kh.dgc.admin.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Query {

	private int movieTheater;
	private String date;
	private String selectOpt;
	private String content;
	private String afterCurrent;
}
