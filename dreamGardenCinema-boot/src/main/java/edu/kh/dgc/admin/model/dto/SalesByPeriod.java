package edu.kh.dgc.admin.model.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SalesByPeriod {
	
	//지난주 매출
	private String ticketingDay;
	private int totalSales;
	private LocalDate ticketingDate;
	
	//분기별 매출
	private String year;
	private String quarter;
	private int quarterlySales;
	
	//년도별 월 매출
	private String yearAndMonth;
	private int monthlySalesByYear;

}
