package edu.kh.dgc.ticketing.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import edu.kh.dgc.movie.model.dto.Movie;
import edu.kh.dgc.ticketing.model.dto.Schedule;
import edu.kh.dgc.ticketing.model.dto.Seat;
import edu.kh.dgc.ticketing.model.dto.SeatCheck;
import edu.kh.dgc.ticketing.model.dto.Ticket;
import edu.kh.dgc.user.model.dto.User;

@Mapper
public interface TicketingMapper {

	List<Schedule> movieTime(Map<String, Object> paramMap);

	List<Schedule> selectTimeList(Object movieNo);

	List<Schedule> selectSaveTimeList(Map<String, Object> saveTicket);

	List<Movie> selectMovieList();

	Movie selectMovie(int i);

	int insertSeat(SeatCheck seatCheck);

	int deleteSeat(SeatCheck seatCheck);

	int deleteEndSeat(int userNo);

	List<SeatCheck> selectChkSeatList(Ticket ticket);

	int selectSeat(SeatCheck seatCheck);

	List<SeatCheck> selectEndSeat(int userNo);

	int beforePaySeat(int userNo);

	void ticketingOut(Map<String, Object> paramMap);

}
