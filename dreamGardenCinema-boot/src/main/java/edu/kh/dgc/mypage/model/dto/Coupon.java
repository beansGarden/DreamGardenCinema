package edu.kh.dgc.mypage.model.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Coupon {

	private int couponNo;
	private int couponPrice;
	private String couponName;
	
	// USER_COUPON JOIN
	private int userCouponNo;
	private int userNo;
	private String useFl;
}
