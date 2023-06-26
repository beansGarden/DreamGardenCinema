package edu.kh.dgc.ticketing.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import edu.kh.dgc.movie.model.dto.Movie;
import edu.kh.dgc.mypage.model.dto.Coupon;
import edu.kh.dgc.ticketing.model.dto.Schedule;
import edu.kh.dgc.ticketing.model.dto.SeatCheck;
import edu.kh.dgc.ticketing.model.dto.Ticket;

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
	
	// 예매 2페이지 웹소켓 해제 시 티켓정보 삭제
	// 예매 3페이지 페이지 나갈 시 좌석 정보 삭제
	int deleteEndTicket(int ticketNo);

	// 예매 3페이지 SEAT_CHECK 테이블 Y로 변경
	int beforePaySeat(int ticketNo);
	
	// 예매 3페이지 티켓 가격 삽입
	int beforePayTicket(Map<String, Integer> paramMap);
	
	// 예매 3페이지 로그인 유저의 사용하지 않은 쿠폰 가져오기
	List<Coupon> couponList(int userNo);

	// 예매 3페이지 좌석 정보 삭제
	int ticketingOut(Map<String, Object> paramMap);

	// 예매 3페이지 쿠폰 가격 조회 AJAX
	int selectCouponPrice(Integer couponNo);

	// 예매 3페이지 티켓 가격 업데이트 AJAX
	int updatePrice(Map<String, Integer> paramMap);

	// 예매 3페이지 티켓 가격 재조회 AJAX
	int selectPrice(Map<String, Integer> paramMap);

	



}
