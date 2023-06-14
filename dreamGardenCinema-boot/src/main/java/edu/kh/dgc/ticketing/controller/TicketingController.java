package edu.kh.dgc.ticketing.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.dgc.movie.model.dto.Movie;
import edu.kh.dgc.ticketing.model.dto.Schedule;
import edu.kh.dgc.ticketing.model.dto.Ticket;
import edu.kh.dgc.ticketing.model.service.TicketingService;
import jakarta.servlet.http.HttpSession;

@RequestMapping("/ticketing")
@Controller
public class TicketingController {

	@Autowired
	private TicketingService service;
	
	@GetMapping("/date")
	public String date(Model model, HttpSession session) {
		
		List<Movie> movieList = (List<Movie>) session.getAttribute("movieList");
		System.out.println(movieList.get(0));
		// 페이지 보여질 때 영화 정보들(포스터, 제목, 등급, 순위, 별점, 개봉일, 예매율) 가져와야 함 + 영화별 상영 날짜/시간
		
		
		return "ticketing/Ticketing1";
	}
	
	@GetMapping("/seat")
	public String seat(Model model
					, RedirectAttributes ra) {
		
		// 페이지 보여질 때 상영관의 좌석 정보 가져와야 함 + 웹 소켓?
		
		return "ticketing/Ticketing2";
	}
	
	@GetMapping("/pay")
	public String pay(Model model) {
		
		// 페이지 보여질 때 상영관의 좌석 정보 가져와야 함 + 웹 소켓?
		
		return "ticketing/Ticketing3";
	}
	
	@GetMapping("/complete")
	public String complete(Model model) {
		
		// 페이지 보여질 때 상영관의 좌석 정보 가져와야 함 + 웹 소켓?
		
		return "ticketing/Ticketing4";
	}
	
	
	@PostMapping("/date")
	public String seat(Ticket ticket
					, Model model  // 모델에 담아서 forward  
					, RedirectAttributes ra
					/*, @SessionAttribute("loginMember") Member loginMember*/) {
		// ticket dto에 저장되면서 다음 페이지로 요청 보내야 함
		
		
//		ticket.setUserNo(loginMember.getMemberNo);  // 티켓정보에 로그인 회원번호 추가
		model.addAttribute("ticket",ticket);
		
		
		return "ticketing/Ticketing2";
	}
	
	@PostMapping("/time")
	@ResponseBody
	public List<Schedule> movieTime(@RequestBody Map<String, Integer> paramMap){
		return service.movieTime(paramMap);
	}
	
	
}
