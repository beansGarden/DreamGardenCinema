package edu.kh.dgc.ticketing.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.kh.dgc.ticketing.model.dao.TicketingMapper;
import edu.kh.dgc.ticketing.model.dto.Schedule;

@Service
public class TicketingServiceImpl implements TicketingService{
	
	@Autowired
	private TicketingMapper mapper;

	@Override
	public List<Schedule> movieTime(Map<String, Integer> paramMap) {
		return mapper.movieTime(paramMap);
	}

	@Override
	public List<Schedule> selectTimeList(Object movieNo) {
		return mapper.selectTimeList(movieNo);
	}



}
