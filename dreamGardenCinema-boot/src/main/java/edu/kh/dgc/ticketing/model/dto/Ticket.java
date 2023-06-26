package edu.kh.dgc.ticketing.model.dto;

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
	private int userNo;
	private int coupon;
	private String movieTime;
	private String movieTheater;
	private int movieNo;
	private String ticketCancle;
	private String ticketFL;
	private String ticketId;
	private String ticketImpId;
}
