package edu.kh.dgc.ticketing.controller;

import java.util.HashMap;
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
	
	@GetMapping("/date")
	public String date(Model model
			, @RequestParam(value="saveTicket", required=false, defaultValue="null") String stringTicket
			) { 
		
		List<Movie> movieList = service.selectMovieList();
		List<Schedule> timeList = null;
		
		if(!stringTicket.equals("null")) {
			String[] parts = stringTicket.replaceAll("Ticket\\(|\\)", "").split(", ");
			
			Map<String, Object> saveTicket = new HashMap<>();
			for (String part : parts) {
			    String[] keyValue = part.split("=");
			    saveTicket.put(keyValue[0], keyValue[1]);
			}
			
			model.addAttribute("saveTicket", saveTicket);
			
			saveTicket.put("movieTime", ((String) saveTicket.get("movieTime")).split(" ")[0]);
			
			timeList = service.selectSaveTimeList(saveTicket);
			
			model.addAttribute("checkNo", saveTicket.get("movieNo"));
		} else {
			Movie firstMovie = movieList.get(0);
			int movieNo = firstMovie.getMovieNo();
			timeList = service.selectTimeList(movieNo);
			model.addAttribute("checkNo", movieNo);
		}
		
		model.addAttribute("timeList",timeList);
		model.addAttribute("movieList", movieList);
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
	
	
	@PostMapping("/seat")
	public String seat(Ticket ticket
					, String date
					, Model model  // 모델에 담아서 forward  
					, RedirectAttributes ra
					, @SessionAttribute("loginUser") User loginUser) {
		
		String movieTheater = ticket.getMovieTime().split(",")[0];
		String movieTime = ticket.getMovieTime().split(",")[1];
		
		ticket.setUserNo(loginUser.getUserNo());  // 티켓정보에 로그인 회원번호 추가
		ticket.setMovieTheater(movieTheater);
		ticket.setMovieTime(date+" "+movieTime);
		String changeMovieTime = date.substring(0, 4) + "." + date.substring(4,6) + "." + date.substring(6,8) + "(" + date.substring(9)+")";
		Map<String, Object> map = service.seatInfo(ticket);
		
		int hour = Integer.parseInt(movieTime.substring(0,2));
		int minute = Integer.parseInt(movieTime.substring(3,5));
		Movie movie = (Movie) map.get("movie");
		int runTime = Integer.parseInt(movie.getRunningTime());
		
		String room = ticket.getMovieNo() + "/" + movieTheater + "/" + date.substring(0, 8) + movieTime;
		
		model.addAttribute("room", room);
		
				
		System.out.println((minute + runTime)/60);
		
		hour = hour+ ((minute+runTime)/60);
		minute = (minute+runTime)%60;
		
		String runningTime = movieTime + "~" + hour +":"+minute;
		System.out.println(runningTime);
		
		model.addAttribute("map", map);
		model.addAttribute("ticket",ticket);
		model.addAttribute("saveday", changeMovieTime);
		model.addAttribute("runningTime", runningTime);	
		return "ticketing/Ticketing2";
	}
	
	@PostMapping("/time")
	@ResponseBody
	public List<Schedule> movieTime(@RequestBody Map<String, Object> paramMap){
		
		String dateList = (String)paramMap.get("date");
		
		String date = dateList.split(" ")[0];
		
		paramMap.put("date", date);
		
		return service.movieTime(paramMap);
	}
	
	
}
