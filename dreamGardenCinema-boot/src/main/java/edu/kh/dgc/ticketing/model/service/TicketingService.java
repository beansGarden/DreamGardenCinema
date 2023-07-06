package edu.kh.dgc.ticketing.model.service;

import java.util.List;
import java.util.Map;

import edu.kh.dgc.movie.model.dto.Movie;
import edu.kh.dgc.mypage.model.dto.Coupon;
import edu.kh.dgc.ticketing.model.dto.Schedule;
import edu.kh.dgc.ticketing.model.dto.Ticket;
import edu.kh.dgc.user.model.dto.User;

public interface TicketingService {

	// 예매 1페이지 (영화목록 조회)
	List<Movie> selectMovieList();
	
	// 예매 1페이지 영화시간,상영관 조회 AJAX
	List<Schedule> movieTime(Map<String, Object> paramMap);
	
	// 예매 1페이지 별점순 AJAX
	List<Movie> sortRating();
	
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
	int couponSet(Map<String, Integer> paramMap, User user);
	
	// 예매 3페이지 티켓ID 중복 확인
	int checkTicketId(String ticketId);

	// 예매 3페이지 생성된 티켓ID 삽입
	int updateTicketId(String createTicketId, int ticketNo);

	// 결제 취소 사유
	int updateReasonCancellationPayment(Ticket ticket);

	// 결제 성공 imp_uid, TICKET_FL 수정
	int updategetTicketImpUid(Ticket ticket);

	// 예매 3페이지 결제API 시 티켓 정보 가져오는 AJAX
	Ticket ticketInfo(Integer ticketNo);

	// 예매 4페이지 결제완료된 티켓 정보 가져오기
	Map<String, Object> selectResultTicket(int ticketNo);

	// 결제 완료 시 누적 금액 업데이트
	int updateAmount(User updateUser);

	// 누적 금액 4<=?<10 등급 실버 업데이트
	int updateSilver(int userNo);

	// 누적 금액 10<=?<20
	int updateGold(int userNo);

	// 누적 금액 20<?
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

	// 예매 취소 시 좌석 정보 삭제
	int deleteTicketSeat(String ticketNo);


}
