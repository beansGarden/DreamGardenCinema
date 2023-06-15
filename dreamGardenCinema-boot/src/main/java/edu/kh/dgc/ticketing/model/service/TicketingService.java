package edu.kh.dgc.ticketing.model.service;

import java.util.List;
import java.util.Map;

import edu.kh.dgc.ticketing.model.dto.Schedule;

public interface TicketingService {

	List<Schedule> movieTime(Map<String, Integer> paramMap);

	List<Schedule> selectTimeList(Object movieNo);

}
