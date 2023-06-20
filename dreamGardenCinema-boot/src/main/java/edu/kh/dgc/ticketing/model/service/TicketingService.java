package edu.kh.dgc.ticketing.model.service;

import java.util.List;
import java.util.Map;

import edu.kh.dgc.movie.model.dto.Movie;
import edu.kh.dgc.ticketing.model.dto.Schedule;
import edu.kh.dgc.ticketing.model.dto.SeatCheck;
import edu.kh.dgc.ticketing.model.dto.Ticket;

public interface TicketingService {

	List<Schedule> movieTime(Map<String, Object> paramMap);

	List<Schedule> selectTimeList(Object movieNo);

	List<Schedule> selectSaveTimeList(Map<String, Object> saveTicket);

	List<Movie> selectMovieList();

	Map<String, Object> seatInfo(Ticket ticket);

	String seatCheck(SeatCheck seatCheck);

}
