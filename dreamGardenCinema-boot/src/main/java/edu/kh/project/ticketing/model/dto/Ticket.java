package edu.kh.project.ticketing.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Ticket {
	private int ticketNo;
	private String ticketingTime;
	private int payAmount;
	private String userNo;
	private int coupon;
	private String movieTime;
	private String movieTheater;
	private int movieNo;
	private String ticketCancle;
}
