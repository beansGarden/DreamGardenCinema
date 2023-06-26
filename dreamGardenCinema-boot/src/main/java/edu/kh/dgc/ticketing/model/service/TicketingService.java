package edu.kh.dgc.ticketing.model.service;

import java.util.List;
import java.util.Map;

import edu.kh.dgc.movie.model.dto.Movie;
import edu.kh.dgc.ticketing.model.dto.Schedule;
import edu.kh.dgc.ticketing.model.dto.SeatCheck;
import edu.kh.dgc.ticketing.model.dto.Ticket;
import edu.kh.dgc.user.model.dto.User;

public interface TicketingService {

	// 예매 1페이지 (영화목록 조회)
	List<Movie> selectMovieList();
	
	// 예매 1페이지 영화시간,상영관 조회 AJAX
	List<Schedule> movieTime(Map<String, Object> paramMap);

	// 예매 2페이지 선택한 영화정보, 선택or예매완료 좌석 조회 
	Map<String, Object> seatInfo(Ticket ticket);
	
	// 예매 2페이지 좌석 선택 Web Socket
	String seatCheck(Ticket ticket);
	
	// 예매 2페이지 Web Socket 연결 해제 시 데이터 삭제
	Map<String, Object> seatDelete(int userNo);
	
	
	
	
	
	
	
	


	int beforePaySeat(int userNo);

	Movie selectMovie(int movieNo);

	void ticketingOut(Map<String, Object> paramMap);

}
