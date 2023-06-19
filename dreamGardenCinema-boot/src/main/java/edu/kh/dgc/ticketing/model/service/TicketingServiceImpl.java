package edu.kh.dgc.ticketing.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kh.dgc.movie.model.dto.Movie;
import edu.kh.dgc.ticketing.model.dao.TicketingMapper;
import edu.kh.dgc.ticketing.model.dto.Schedule;
import edu.kh.dgc.ticketing.model.dto.Seat;
import edu.kh.dgc.ticketing.model.dto.SeatCheck;
import edu.kh.dgc.ticketing.model.dto.Ticket;

@Service
public class TicketingServiceImpl implements TicketingService{
	
	@Autowired
	private TicketingMapper mapper;

	@Override
	public List<Schedule> movieTime(Map<String, Object> paramMap) {
		return mapper.movieTime(paramMap);
	}

	@Override
	public List<Schedule> selectTimeList(Object movieNo) {
		return mapper.selectTimeList(movieNo);
	}

	@Override
	public List<Schedule> selectSaveTimeList(Map<String, Object> saveTicket) {
		return mapper.selectSaveTimeList(saveTicket);
	}

	@Override
	public List<Movie> selectMovieList() {
		return mapper.selectMovieList();
	}

	@Override
	public Map<String, Object> seatInfo(Ticket ticket) {
		
		Map<String, Object> map = new HashMap<>();
		
		Movie movie = mapper.selectMovie(ticket);
		
		List<Seat> seatList = mapper.selectSeatList(ticket); 
		
		map.put("movie", movie);
		map.put("seatList", seatList);
		
		return map;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public int seatCheck(SeatCheck seatCheck) {
		
		seatCheck.setMovieTime(seatCheck.getMovieTime().split(" ")[0] + seatCheck.getMovieTime().split(" ")[2]);
		
		int result = mapper.insertSeat(seatCheck);
		
		return 0;
	}



}
