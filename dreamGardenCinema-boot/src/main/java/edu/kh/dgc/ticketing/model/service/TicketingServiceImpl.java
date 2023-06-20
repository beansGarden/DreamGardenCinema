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

@Service
public class TicketingServiceImpl implements TicketingService{
	
	@Autowired
	private TicketingMapper mapper;

	@Override
	public List<Schedule> movieTime(Map<String, Object> paramMap) {
		return mapper.movieTime(paramMap);
	}

	@Override
	public List<Schedule> selectTimeList(Object movieNo) {
		return mapper.selectTimeList(movieNo);
	}

	@Override
	public List<Schedule> selectSaveTimeList(Map<String, Object> saveTicket) {
		return mapper.selectSaveTimeList(saveTicket);
	}

	@Override
	public List<Movie> selectMovieList() {
		return mapper.selectMovieList();
	}

	@Override
	public Map<String, Object> seatInfo(Ticket ticket) {
		
		Map<String, Object> map = new HashMap<>();
		
		Movie movie = mapper.selectMovie(ticket);
		ticket.setMovieTime(ticket.getMovieTime().split(" ")[0] + ticket.getMovieTime().split(" ")[2]);
		List<Ticket> chkSeatList = mapper.selectChkSeatList(ticket); 
		
		map.put("movie", movie);
		map.put("chkSeatList", chkSeatList);
		
		return map;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public String seatCheck(SeatCheck seatCheck) {
		
		int selectResult = mapper.selectSeat(seatCheck);
		
		String seatResult = null;
		if(selectResult > 0) {
			return "이미선택";
		}
		if(seatCheck.getChecked().equals("N")) {  // 선택하지 않았으면  
			int result = mapper.insertSeat(seatCheck);  // 좌석 INSERT
			if(result == 1) {
				seatResult = "예매성공";
			} else {
				seatResult = "예매실패";
			}
		} else {  // 선택한 좌석이면
			int result = mapper.deleteSeat(seatCheck);  // 좌석 DELETE
			if(result == 1) {
				seatResult = "예매취소성공";
			} else {
				seatResult = "예매취소실패";
			}
		}
		
		return seatResult;
	}






}
