package edu.kh.dgc.admin.model.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SalesByPeriod {
	
	private String ticketingDay;
	private int totalSales;
	private LocalDate ticketingDate;

}
