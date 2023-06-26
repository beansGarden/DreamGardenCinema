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
		
		int result = mapper.deleteEndSeat(seatCheckList.get(0));
		
		String seatResult = null;
		if (result > 0) {
			seatResult = "예매취소성공";
		} else {
			seatResult = "예매취소실패";
		}
		
		seatResultMap.put("seatCheckList", seatCheckList);
		seatResultMap.put("seatResult", seatResult);
		
		return seatResultMap;
	}
	
	
	
	
	


	@Override
	public int beforePaySeat(int userNo) {
		return mapper.beforePaySeat(userNo);
	}

	@Override
	public Movie selectMovie(int movieNo) {
		return mapper.selectMovie(movieNo);
	}

	@Override
	public void ticketingOut(Map<String, Object> paramMap) {
		mapper.ticketingOut(paramMap);
	}

}
