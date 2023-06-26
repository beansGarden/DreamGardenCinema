package edu.kh.dgc.ticketing.model.service;

import java.util.List;
import java.util.Map;

import edu.kh.dgc.movie.model.dto.Movie;
import edu.kh.dgc.ticketing.model.dto.Schedule;
import edu.kh.dgc.ticketing.model.dto.Ticket;

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
	
	// 예매 3페이지 SEAT_CHECK 테이블 Y로 변경
	Map<String, Object> beforePaySeat(int ticketNo, int movieNo, int seatListSize, int userNo);
	
	// 예매 3페이지 나갈 때 좌석,티켓정보 삭제
	void ticketingOut(Map<String, Object> paramMap);

	// 예매 3페이지 쿠폰 AJAX
	int couponSet(Map<String, Integer> paramMap);
	
	// 티켓 중복 확인
	int checkTicketId(String ticketId);

	// 티켓 업데이트
	int updateTicketId(String createTicketId, int ticketNo);

	// 결제 취소 사유
	int updateReasonCancellationPayment(String reasonCancellationPayment, String ticketId);

	// 결제 성공 imp_uid, TICKET_FL 수정
	int updategetTicketImpUid(String ticketImpId, String ticketId);

}
