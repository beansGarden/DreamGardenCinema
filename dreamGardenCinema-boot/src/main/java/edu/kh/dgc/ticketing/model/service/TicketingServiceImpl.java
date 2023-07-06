package edu.kh.dgc.ticketing.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.kh.dgc.movie.model.dto.Movie;
import edu.kh.dgc.mypage.model.dao.MypageMapper;
import edu.kh.dgc.mypage.model.dto.Coupon;
import edu.kh.dgc.ticketing.model.dao.TicketingMapper;
import edu.kh.dgc.ticketing.model.dto.Schedule;
import edu.kh.dgc.ticketing.model.dto.SeatCheck;
import edu.kh.dgc.ticketing.model.dto.Ticket;
import edu.kh.dgc.user.model.dto.User;

@Service
public class TicketingServiceImpl implements TicketingService {

	@Autowired
	private TicketingMapper mapper;

	// 예매 1페이지 (영화목록 조회)
	@Override
	public List<Movie> selectMovieList() {
		return mapper.selectMovieList();	
	}
	
	// 예매 1페이지 영화시간,상영관 조회 AJAX
	@Override
	public List<Schedule> movieTime(Map<String, Object> paramMap) {
		return mapper.movieTime(paramMap);
	}

	// 예매 1페이지 별점순 AJAX
	@Override
	public List<Movie> sortRating() {
		return mapper.sortRating();
	}
	
	// 예매 2페이지 선택한 영화정보, 선택or예매완료 좌석 조회 
	@Override
	public Map<String, Object> seatInfo(Ticket ticket) {
		Map<String, Object> map = new HashMap<>();
		// 영화정보 가져오기
		Movie movie = mapper.selectMovie(ticket.getMovieNo());
		map.put("movie", movie);
		// 티켓정보 삽입
		
		int result = mapper.insertTicket(ticket);
		map.put("ticket", ticket);
		// 예매좌석리스트 가져오기
		List<SeatCheck> chkSeatList = mapper.selectChkSeatList(ticket);
		map.put("chkSeatList", chkSeatList);
		return map;
	}
	
	// 예매 2페이지 좌석 선택 Web Socket
	@Transactional(rollbackFor = Exception.class)
	@Override
	public String seatCheck(Ticket ticket) {
		
		Ticket result = mapper.selectSeat(ticket);
		
		String seatResult = null;
		if(result == null) {  // 조회된 좌석정보가 없으면
			int insertResult = mapper.insertSeat(ticket); // 좌석 INSERT
			if (insertResult == 1) {
				seatResult = "예매성공";
				// 좌석 인서트 추가
			} else {
				seatResult = "예매실패";
			}
		} else {  // 조회된 좌석정보가 있으면
			if(result.getUserNo() == ticket.getUserNo()) {  // 내가 예매한 정보이면
				
				int insertResult = mapper.deleteSeat(ticket); // 좌석 DELETE
				if (insertResult == 1) {
					seatResult = "예매취소성공";
				} else {
					seatResult = "예매취소실패";
				}
			} else {
				seatResult = "이미선택";
			}
		}
		return seatResult;
	}
	
	// 예매 2페이지 Web Socket 연결 해제 시 데이터 삭제
	@Override
	public Map<String, Object> seatDelete(int userNo) {
		
		Map<String, Object> seatResultMap = new HashMap<>();
		
		// 예매 넘어가지 않은 좌석리스트
		List<Ticket> seatCheckList = mapper.selectEndSeat(userNo);
		
		if(seatCheckList.size() > 0) {
			int result = mapper.deleteEndSeat(seatCheckList.get(0));
			
			int ticketResult = 0;
			if(result>0) {
				ticketResult = mapper.deleteEndTicket(seatCheckList.get(0).getTicketNo());			
			}
			String seatResult = null;
			if (ticketResult > 0) {
				seatResult = "예매취소성공";
			} else {
				seatResult = "예매취소실패";
			}
			seatResultMap.put("seatResult", seatResult);
		}
		seatResultMap.put("seatCheckList", seatCheckList);
		
		return seatResultMap;
	}
	
	// 예매 3페이지 SEAT_CHECK 테이블 Y로 변경 / 영화, 좌석 정보 조회
	@Override
	public Map<String, Object> beforePaySeat(int ticketNo, int movieNo, int seatListSize, int userNo) {
		
		// Y로 변경
		int a = mapper.beforePaySeat(ticketNo);
		
		// Ticket 가격 넣기
		Map<String, Integer> paramMap = new HashMap<>();
		paramMap.put("ticketNo", ticketNo);
		paramMap.put("seatListSize", seatListSize);
		int result = mapper.beforePayTicket(paramMap);
		
		// 영화정보 조회
		Movie movie = mapper.selectMovie(movieNo);
		List<Coupon> couponList = mapper.couponList(userNo);
		
		Map<String, Object> map = new HashMap<>();
		map.put("movie", movie);
		map.put("couponList", couponList);
		
		return map;
	}

	// 예매 3페이지 나갈 때 티켓정보 삭제
	@Override
	public void ticketingOut(Map<String, Object> paramMap) {
		// 좌석 정보 삭제
		int result = mapper.ticketingOut(paramMap);
		
		if(result > 0) {
			// 티켓 정보 삭제
			mapper.deleteEndTicket((int)paramMap.get("ticketNo"));
		}
	}
	
	// 예매 3페이지 쿠폰 AJAX
	@Override
	public int couponSet(Map<String, Integer> paramMap, User user) {
		
		paramMap.put("userNo", user.getUserNo());
		
		int couponPrice = mapper.selectCouponPrice(paramMap);
			
		int seatSize = mapper.selectSeatSize(paramMap.get("ticketNo"));
		
		// 티켓 가격 업데이트
		paramMap.put("newPrice", 12000 * seatSize - couponPrice);
		int result = mapper.updatePrice(paramMap);
		
		int resultPrice = 999999;
		if(result>0) {
			resultPrice = mapper.selectPrice(paramMap);
		}
		return resultPrice;
	}

	// 예매 3페이지 티켓ID 중복 확인
	@Override
	public int checkTicketId(String ticketId) {
		return mapper.checkTicketId(ticketId);
	}

	// 예매 3페이지 생성된 티켓ID 삽입
	@Override
	public int updateTicketId(String createTicketId, int ticketNo) {
		
		Map<String, String> updateTicket = new HashMap<>();
		updateTicket.put("createTicketId", createTicketId);
		updateTicket.put("ticketNo", String.valueOf(ticketNo));
		
		return mapper.updateTicketId(updateTicket);
	}

	// 결제 실패 사유 삽입, TICKET_FL C 수정
	@Override
	public int updateReasonCancellationPayment(Ticket ticket) {
		
		int result = mapper.updateReasonCancellationPayment(ticket);
		return result;
	}

	// 결제 성공 imp_uid 삽입, TICKET_FL Y 수정, SEAT_CHECK TABLE C(Complete)로 변경
	@Override
	public int updategetTicketImpUid(Ticket ticket) {
		int result = mapper.updategetTicketImpUid(ticket);
		System.out.println("서비스 임플의 ticket imp 성공 시" + result);
		if(result>0) {
			// 사용한 쿠폰 Y 처리
			mapper.updateUserCoupon(ticket);
			result = mapper.updateSeatList(ticket);			
		} else {
			result = 0;
		}
		return result;
	}

	// 예매 3페이지 결제 시 티켓 정보 가져오는 AJAX
	@Override
	public Ticket ticketInfo(Integer ticketNo) {
		return mapper.ticketInfo(ticketNo);
	}

	// 예매 4페이지 결제완료된 티켓 정보 가져오기
	@Override
	public Map<String, Object> selectResultTicket(int ticketNo) {
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("ticket", mapper.selectResultTicket(ticketNo));
		map.put("seatList", mapper.selectResultSeatList(ticketNo));
		
		return map;
	}

	// 결제 완료 시 누적 금액 업데이트
	@Override
	public int updateAmount(User updateUser) {
		return mapper.updateAmount(updateUser);
	}

	// 누적 금액 4<=?<10 일 때 
	@Override
	public int updateSilver(int userNo) {
		return mapper.updateSilver(userNo);
	}
	// 누적 금액 10<=?<20 일 때 
	@Override
	public int updateGold(int userNo) {
		return mapper.updateGold(userNo);
	}
	// 누적 금액 20<? 일 때 
	@Override
	public int updatePlatinum(int userNo) {
		return mapper.updatePlatinum(userNo);
	}

	// 유저가 보유한 실버 쿠폰 카운트
	@Override
	public int silverCouponCount(int userNo) {
		return mapper.silverCouponCount(userNo);
	}

	// 실버 쿠폰 insert
	@Override
	public int insertSilverCoupon(int userNo) {
		return mapper.insertSilverCoupon(userNo);
	}

	// 유저가 보유한 골드 쿠폰 카운트
	@Override
	public int goldCouponCount(int userNo) {
		return mapper.goldCouponCount(userNo);
	}

	// 골드 쿠폰 insert
	@Override
	public int insertGoldCoupon(int userNo) {
		return mapper.insertGoldCoupon(userNo);
	}

	// 유저가 보유한 플래티넘 쿠폰 카운트
	@Override
	public int platinumCouponCount(int userNo) {
		return mapper.platinumCouponCount(userNo);
	}

	// 플래티넘 쿠폰 insert
	@Override
	public int insertPlatinumCoupon(int userNo) {
		return mapper.insertPlatinumCoupon(userNo);
	}

	// 취소한 티켓 좌석 정보 삭제
	@Override
	public int deleteTicketSeat(String ticketNo) {
		return mapper.deleteTicketSeat(ticketNo);
	}
	
	
	

}
