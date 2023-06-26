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

	// 예매 1페이지 (영화목록 조회)
	List<Movie> selectMovieList();
	
	// 예매 1페이지 영화시간,상영관 조회 AJAX
	List<Schedule> movieTime(Map<String, Object> paramMap);

	// 예매 2페이지 영화정보 가져오기
	Movie selectMovie(int movieNo);

	// 예매 2페이지 티켓정보 삽입
	int insertTicket(Ticket ticket);
	
	// 예매 2페이지 선택or예매완료 좌석 확인
	List<SeatCheck> selectChkSeatList(Ticket ticket);
	
	// 예매 2페이지 좌석 선택 Web Socket
	Ticket selectSeat(Ticket ticket);
	
	// 예매 2페이지 좌석 선택 Web Socket -> 좌석 INSERT
	int insertSeat(Ticket ticket);
	
	// 예매 2페이지 좌석 선택 Web Socket -> 좌석 DELETE
	int deleteSeat(Ticket ticket);
	
	// 예매 2페이지 웹소켓 해제 시 선택한 좌석 조회
	List<Ticket> selectEndSeat(int userNo);
	
	// 예매 2페이지 웹소켓 해제 시 선택했던 좌석 삭제
	int deleteEndSeat(Ticket ticket);






	int beforePaySeat(int userNo);

	void ticketingOut(Map<String, Object> paramMap);


	Ticket selectTicket(int ticketNo);

}
