package edu.kh.dgc.ticketing.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.kh.dgc.ticketing.model.dao.TicketingMapper;

@Service
public class TicketingServiceImpl implements TicketingService{
	
	@Autowired
	private TicketingMapper mapper;

}
