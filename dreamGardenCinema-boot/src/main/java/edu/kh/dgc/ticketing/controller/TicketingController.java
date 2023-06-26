package edu.kh.dgc.ticketing.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.dgc.movie.model.dto.Movie;
import edu.kh.dgc.ticketing.model.dto.Schedule;
import edu.kh.dgc.ticketing.model.dto.Ticket;
import edu.kh.dgc.ticketing.model.service.TicketingService;
import edu.kh.dgc.user.model.dto.User;

@RequestMapping("/ticketing")
@SessionAttributes({"loginUser", "room"})
@Controller
public class TicketingController {

	@Autowired
	private TicketingService service;
	
	// 예매 1페이지 (영화목록 조회)
	@GetMapping("/date")
	public String date(Model model
			, @RequestParam(value="saveTicket", required=false, defaultValue="null") String stringTicket
			) { 
		List<Movie> movieList = service.selectMovieList();  // 영화 목록 조회
		model.addAttribute("movieList", movieList);
		return "ticketing/Ticketing1";
	}
	
	// 예매 1페이지 영화시간,상영관 조회 AJAX
	@PostMapping("/time")
	@ResponseBody
	public List<Schedule> movieTime(@RequestBody Map<String, Object> paramMap){
		String dateList = (String)paramMap.get("date");
		String date = dateList.split(" ")[0];
		paramMap.put("date", date);
		return service.movieTime(paramMap);
	}
	
	// 예매 2페이지 선택 시 기존 정보를 갖고 보여주는 창
	@GetMapping("/seat")
	public String seat( Ticket ticket, String date
			, Model model
			, RedirectAttributes ra) {
		
		
		
		return "ticketing/Ticketing2";
	}
	
	
	// 예매 2페이지 선택한 영화정보, 선택or예매완료 좌석 조회 
	@PostMapping("/seat")
	public String seat(Ticket ticket
					, String date
					, Model model  // 모델에 담아서 forward  
					, RedirectAttributes ra
					, @SessionAttribute("loginUser") User loginUser) {
	
		// 2023062414:00
		String movieTime = date.split(" ")[0] + ticket.getMovieTime().split(",")[1];
		String movieTheater = ticket.getMovieTime().split(",")[0];
		
		ticket.setMovieTime(movieTime);
		ticket.setMovieTheater(movieTheater);
		ticket.setUserNo(loginUser.getUserNo());  // 티켓정보에 로그인 회원번호 추가		
		
		Map<String, Object> map = service.seatInfo(ticket); // 티켓인포에 저장(INSERT)(SELECT), 영화정보(SELECT), 좌석정보(SELECT)
		
		// 영화번호, 영화관, 영화시간 => 방으로 구분 (3/1/2023062414:00)
		String changeMovieTime = date.substring(2,4) + "." + date.substring(4,6) + "." + date.substring(6,8) + "(" + date.substring(9)+")";
		
		int runTime = Integer.parseInt((((Movie)map.get("movie")).getRunningTime()));
		
		int hour = Integer.parseInt(movieTime.substring(8,10));
		int minute = Integer.parseInt(movieTime.substring(11, 13));
		
		hour = hour+ ((minute+runTime)/60);
		minute = (minute+runTime)%60;
		
		// 화면단에 보여줄 러닝타임 (14:00 ~ 
		String runningTime = movieTime.substring(8) + "~" + hour +":"+minute;
		
		String room = ticket.getMovieNo() + "/" + ticket.getMovieTheater() + "/" + ticket.getMovieTime();
		
		model.addAttribute("room", room);
		model.addAttribute("map", map);
		model.addAttribute("saveday", changeMovieTime);
		model.addAttribute("runningTime", runningTime);	
		return "ticketing/Ticketing2";
	}
	
	// 예매 3페이지 선택한 영화, 시간, 좌석, 금액정보 삽입, 조회
	@PostMapping("/pay")
	public String pay(Model model,
			int ticketNo, int movieNo, String saveday, String runningTime, String movieTheater,  @SessionAttribute("loginUser") User user, String[] seatList){
		
		// 좌석 리스트로 담기
		List<String> resultSeatList = new ArrayList<>();
		for(String seat : seatList) {
			resultSeatList.add(seat);
		}
		// 좌석 'Y'로 변경 / 정보 조회해오기
		Map<String, Object> map = service.beforePaySeat(ticketNo, movieNo, resultSeatList.size(), user.getUserNo());
		model.addAttribute("ticketNo", ticketNo);
		model.addAttribute("map", map);
		model.addAttribute("saveday", saveday);
		model.addAttribute("runningTime", runningTime);
		model.addAttribute("resultSeatList", resultSeatList);
		model.addAttribute("movieTheater", movieTheater);
		return "ticketing/Ticketing3";
	}
	
	// 예매 3페이지 나갈 때 좌석,티켓정보 삭제
	@ResponseBody
	@PostMapping("/out")
	public void ticketingOut(@SessionAttribute("loginUser") User user, @RequestBody Map<String, Object> paramMap) {
		List<String> seatList = (List<String>) paramMap.get("seatList");
		List<String> newList = new ArrayList<>();
		for(int i=0;i<seatList.size();i++) {
			String seat = seatList.get(i);
			newList.add(seat);
		}
		paramMap.put("seatList", newList);
		service.ticketingOut(paramMap);
	}
	
	// 예매 3페이지 쿠폰 AJAX
	@ResponseBody
	@PostMapping("/coupon")
	public int couponSet(@RequestBody Map<String, Integer> paramMap) {
		
		System.out.println(paramMap);
		
		return service.couponSet(paramMap);
	}
	
	
	
	
	
	
	
	
	
	
	
//	
//	@GetMapping("/pay")
//	public String pay(Model model) {
//		
//		// 페이지 보여질 때 상영관의 좌석 정보 가져와야 함 + 웹 소켓?
//		
//		return "ticketing/Ticketing3";
//	}
//	
//	@GetMapping("/complete")
//	public String complete(Model model) {
//		
//		// 페이지 보여질 때 상영관의 좌석 정보 가져와야 함 + 웹 소켓?
//		
//		return "ticketing/Ticketing4";
//	}
//	
//	@GetMapping("/PaymentPage")
//	public String PaymentPage(Model model) {
//		
//		return "ticketing/PaymentPage";
//	}
	
	
	
	
	
	
	
	
	
	
	
	
}
