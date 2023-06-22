package edu.kh.dgc.ticketing.model.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Order {

	private Long orderNum;
	private Long productNum;
	private Long num;
	private String productName;
	private Date orderDate;
	private Long totalPrice;
	private String imp_uid;
	private Long reserNum;
	
}
