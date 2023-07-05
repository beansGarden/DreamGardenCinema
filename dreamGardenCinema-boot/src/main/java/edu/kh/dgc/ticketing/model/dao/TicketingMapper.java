package edu.kh.dgc.ticketing.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import edu.kh.dgc.movie.model.dto.Movie;
import edu.kh.dgc.mypage.model.dto.Coupon;
import edu.kh.dgc.ticketing.model.dto.Schedule;
import edu.kh.dgc.ticketing.model.dto.SeatCheck;
import edu.kh.dgc.ticketing.model.dto.Ticket;
import edu.kh.dgc.user.model.dto.User;

@Mapper
public interface TicketingMapper {

	// 예매 1페이지 (영화목록 조회)
	List<Movie> selectMovieList();
	
	// 예매 1페이지 영화시간,상영관 조회 AJAX
	List<Schedule> movieTime(Map<String, Object> paramMap);

	// 예매 1페이지 별점순 AJAX
	List<Movie> sortRating();

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
	int selectCouponPrice(Map<String, Integer> paramMap);
	
	// 예매 3페이지 티켓 좌석 갯수 조회 AJAX
	int selectSeatSize(Integer integer);

	// 예매 3페이지 티켓 가격 업데이트 AJAX
	int updatePrice(Map<String, Integer> paramMap);

	// 예매 3페이지 티켓 가격 재조회 AJAX
	int selectPrice(Map<String, Integer> paramMap);
	
	// 예매 3페이지 결제 시 티켓 정보 가져오는 AJAX
	Ticket ticketInfo(Integer ticketNo);

	// 티켓 중복 확인
	int checkTicketId(String ticketId);

	// 티켓 업데이트
	int updateTicketId(Map<String, String> updateTicket);

	// 결제 성공 imp_uid
	int updategetTicketImpUid(Ticket ticket);

	// 결제 취소 사유
	int updateReasonCancellationPayment(Ticket ticket);

	// TICKET_FL 수정 (C)
	void updateTictetFLC(String ticketId);
	
	// TICKET_FL 수정 (Y)
	void updateTictetFLY(String ticketId);

	// 예매 3페이지 결제API 시 SEAT_CHECK 테이블 C로 변경
	int updateSeatList(Ticket ticket);

	// 예매 4페이지 결제완료된 티켓 정보 가져오기
	Ticket selectResultTicket(int ticketNo);
	
	// 예매 4페이지 결제완료된 좌석 정보 가져오기
	List<SeatCheck> selectResultSeatList(int ticketNo);
	
	// 예매 3페이지 결제API 시 USER_COUPON 테이블 Y로 변경
	void updateUserCoupon(Ticket ticket);

	// 결제 완료시 회원 누적 금액 업데이트
	int updateAmount(User updateUser);

	// 누적 금액이 4<=?<10
	int updateSilver(int userNo);
	
	// 누적 금액이 10<=?<20
	int updateGold(int userNo);
	
	// 누적 금액이 20<?
	int updatePlatinum(int userNo);

	// 유저가 보유한 실버 쿠폰 카운트
	int silverCouponCount(int userNo);

	// 실버 쿠폰 insert
	int insertSilverCoupon(int userNo);

	// 유저가 보유한 골드 쿠폰 카운트
	int goldCouponCount(int userNo);

	// 골드 쿠폰 insert
	int insertGoldCoupon(int userNo);

	// 유저가 보유한 플래티넘 쿠폰 카운트
	int platinumCouponCount(int userNo);

	// 플래티넘 쿠폰 insert
	int insertPlatinumCoupon(int userNo);

	// 취소한 티켓 좌석 정보 삭제
	int deleteSeat(String ticketNo);

}
