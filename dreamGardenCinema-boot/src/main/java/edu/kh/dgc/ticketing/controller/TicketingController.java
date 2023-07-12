package edu.kh.dgc.ticketing.controller;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
import jakarta.servlet.http.HttpSession;

@RequestMapping("/ticketing")
@SessionAttributes({ "loginUser", "room" })
@Controller
public class TicketingController {

	@Autowired
	private TicketingService service;

	private int amountPaid;

	// 예매 1페이지 (영화목록 조회)
	@GetMapping("/date/{movieNo}")
	public String date(Model model,
			@RequestParam(value = "saveTicket", required = false, defaultValue = "null") String stringTicket,
			@PathVariable(value = "movieNo", required=false) Integer movieNo) {
		
		List<Movie> movieList = service.selectMovieList(); // 영화 목록 조회
		
		model.addAttribute("movieList", movieList);
		if(movieNo != 0) {
			model.addAttribute("movieNo", movieNo);
		} else {
			model.addAttribute("movieNo", movieList.get(0).getMovieNo());
		}
		return "ticketing/Ticketing1";
	}

	// 예매 1페이지 영화시간,상영관 조회 AJAX
	@PostMapping("/time")
	@ResponseBody
	public List<Schedule> movieTime(@RequestBody Map<String, Object> paramMap) {
		String dateList = (String) paramMap.get("date");
		String date = dateList.split(" ")[0];
		paramMap.put("date", date);
		return service.movieTime(paramMap);
	}
	
	// 예매 1페이지 예매순, 별점순 AJAX
	@ResponseBody
	@PostMapping("/sort")
	public Map<String, Object> sortList(@RequestBody Map<String, String> paramMap) {
		
		Map<String, Object> map = new HashMap<>();
		
		List<Movie> movieList = null;
		
		if(paramMap.get("sortOption").equals("ticketing")) {   // 예매순일 때
			movieList = service.selectMovieList();
		}
		if(paramMap.get("sortOption").equals("rating")) {  // 별점순일 때
			movieList = service.sortRating();
		}
		
		map.put("movieList", movieList);
		
		map.put("movieNo", paramMap.get("movieNo"));
		
		return map;
	}

	// 예매 2페이지 선택 시 기존 정보를 갖고 보여주는 창
//	@GetMapping("/seat")
//	public String seat(Ticket ticket, String date, Model model, RedirectAttributes ra) {
//
//		return "ticketing/Ticketing2";
//	}

	// 예매 2페이지 선택한 영화정보, 선택or예매완료 좌석 조회
	@PostMapping("/seat")
	public String seat(Ticket ticket, Model model // 모델에 담아서 forward
			, RedirectAttributes ra, @SessionAttribute("loginUser") User loginUser, HttpSession session) {

		// 2023062414:00
		String movieTime = ticket.getDate().split(" ")[0] + ticket.getMovieTime().split(",")[1];
		String movieTheater = ticket.getMovieTime().split(",")[0];

		ticket.setMovieTime(movieTime);
		ticket.setMovieTheater(movieTheater);
		session.setAttribute("movieTheater", movieTheater);
		ticket.setUserNo(loginUser.getUserNo()); // 티켓정보에 로그인 회원번호 추가

		Map<String, Object> map = service.seatInfo(ticket); // 티켓인포에 저장(INSERT)(SELECT), 영화정보(SELECT), 좌석정보(SELECT)

		// 영화번호, 영화관, 영화시간 => 방으로 구분 (3/1/2023062414:00)
		String changeMovieTime = ticket.getDate().substring(2, 4) + "." + ticket.getDate().substring(4, 6) + "." + ticket.getDate().substring(6, 8) + "("
				+ ticket.getDate().substring(9) + ")";

		int runTime = Integer.parseInt((((Movie) map.get("movie")).getRunningTime()));

		int hour = Integer.parseInt(movieTime.substring(8, 10));
		int minute = Integer.parseInt(movieTime.substring(11, 13));

		hour = hour + ((minute + runTime) / 60);
		minute = (minute + runTime) % 60;
		
		String resultHour = null;
		String resultMinute = null;
		if(hour<10) {
			resultHour = "0" + hour;
		} else {
			resultHour = "" + hour;
		}
		if(minute<10) {
			resultMinute = "0" + minute;
		} else {
			resultMinute = "" + minute;
		}
		
		// 화면단에 보여줄 러닝타임 (14:00 ~
		String runningTime = movieTime.substring(8) + "~" + resultHour + ":" + resultMinute;

		String room = ticket.getMovieNo() + "/" + ticket.getMovieTheater() + "/" + ticket.getMovieTime();

		model.addAttribute("room", room);
		model.addAttribute("map", map);
		model.addAttribute("saveday", changeMovieTime);
		model.addAttribute("runningTime", runningTime);
		return "ticketing/Ticketing2";
	}

	// 예매 3페이지 선택한 영화, 시간, 좌석, 금액정보 삽입, 조회
	@PostMapping("/pay")
	public String pay(Model model, int ticketNo, int movieNo, String saveday, String runningTime, String movieTheater,
			@SessionAttribute("loginUser") User user, String[] seatList, HttpSession session) {

		// 좌석 리스트로 담기
		List<String> resultSeatList = new ArrayList<>();
		for (String seat : seatList) {
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

//		amountPaid = resultSeatList.size() * 12000;
//		model.addAttribute("amountPaid", amountPaid);

		String createTicketId = createTicketId((String) (session.getAttribute("movieTheater")), resultSeatList);
		model.addAttribute("createTicketId", createTicketId);

		int updateTicketId = service.updateTicketId(createTicketId, ticketNo);

		System.out.println("createTicketId : " + createTicketId);
		System.out.println("updateTicketId : " + updateTicketId);

		return "ticketing/Ticketing3";
	}

	public int getAmountPaid() {
		return amountPaid;
	}

	// 예매 3페이지 나갈 때 좌석,티켓정보 삭제
	@ResponseBody
	@PostMapping("/out")
	public void ticketingOut(@SessionAttribute("loginUser") User user, @RequestBody Map<String, Object> paramMap) {

		List<String> seatList = (List<String>) paramMap.get("seatList");
		List<String> newList = new ArrayList<>();
		for (int i = 0; i < seatList.size(); i++) {
			String seat = seatList.get(i);
			newList.add(seat);
		}
		paramMap.put("seatList", newList);
		service.ticketingOut(paramMap);
	}

	// 예매 3페이지 쿠폰 AJAX
	@ResponseBody
	@PostMapping("/coupon")
	public int couponSet(@RequestBody Map<String, Integer> paramMap, @SessionAttribute("loginUser") User user) {
		
		return service.couponSet(paramMap, user);
	}

	
	public String createTicketId(String movieTheater, List<String> resultSeatList) {

		if (movieTheater.length() < 2) {
			movieTheater = "0" + movieTheater;
		}

		String firstSeat = resultSeatList.get(0);

		char seatLetter = firstSeat.charAt(0);
		int seatNumber = Integer.parseInt(firstSeat.substring(1));
		char uppercaseLetter = Character.toUpperCase(seatLetter);
		int alphabetIndex = uppercaseLetter - 'A' + 1;

		String convertedSeat;
		if (seatNumber < 10) {
			convertedSeat = String.format("%02d", alphabetIndex) + "0" + seatNumber;
		} else {
			convertedSeat = String.format("%02d", alphabetIndex) + String.format("%02d", seatNumber);
		}

		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMdd");
		String formattedDate = currentDate.format(formatter);

		SecureRandom secureRandom = new SecureRandom();
		int randomNumber;
		String searchTicketId;
		String searchTicketIdMapper;
		do {
			randomNumber = secureRandom.nextInt(90000) + 10000;
			searchTicketId = String.valueOf(randomNumber + "-" + formattedDate);
			searchTicketIdMapper = searchTicketId + "%";
		} while (service.checkTicketId(searchTicketIdMapper) > 0);

		String realTicketId = searchTicketId + "-" + movieTheater + convertedSeat;

		return realTicketId;
	}

	// 예매 3페이지 결제 시 티켓 정보 가져오는 AJAX
	@ResponseBody
	@PostMapping("/info")
	public Ticket ticketInfo(@RequestBody Map<String, Integer> paramMap) {
		return service.ticketInfo(paramMap.get("ticketNo"));
	}
	
	@GetMapping("/complete/{ticketNo}")
	public String complete(Model model, @PathVariable(value = "ticketNo", required = false) int ticketNo) {
		
		Map<String, Object> map = service.selectResultTicket(ticketNo);
		
		model.addAttribute("map", map);
		
		return "ticketing/Ticketing4";
	}

}
