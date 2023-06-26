package edu.kh.dgc.ticketing.controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
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
import edu.kh.dgc.ticketing.model.dto.SeatCheck;
import edu.kh.dgc.ticketing.model.dto.Ticket;
import edu.kh.dgc.ticketing.model.service.TicketingService;
import edu.kh.dgc.user.model.dto.User;

@RequestMapping("/ticketing")
@SessionAttributes({"loginUser", "room"})
@Controller
public class TicketingController {

	@Autowired
	private TicketingService service;
	
	// 예매 첫 페이지 로드(영화목록)
	@GetMapping("/date")
	public String date(Model model
			, @RequestParam(value="saveTicket", required=false, defaultValue="null") String stringTicket
			) { 
		
		List<Movie> movieList = service.selectMovieList();  // 영화 목록 조회
		List<Schedule> timeList = null;
		
		// 기존 정보를 갖고있도록
//		if(!stringTicket.equals("null")) {
//			String[] parts = stringTicket.replaceAll("Ticket\\(|\\)", "").split(", ");
//			
//			Map<String, Object> saveTicket = new HashMap<>();
//			for (String part : parts) {
//			    String[] keyValue = part.split("=");
//			    saveTicket.put(keyValue[0], keyValue[1]);
//			}
//			
//			model.addAttribute("saveTicket", saveTicket);
//			
//			saveTicket.put("movieTime", ((String) saveTicket.get("movieTime")).split(" ")[0]);
//			
//			timeList = service.selectSaveTimeList(saveTicket);
//			
//			model.addAttribute("checkNo", saveTicket.get("movieNo"));
//		} else {
			Movie firstMovie = movieList.get(0);  // 첫번째 영화
			int movieNo = firstMovie.getMovieNo();  // 첫번째 영화 번호
			timeList = service.selectTimeList(movieNo);
			model.addAttribute("checkNo", movieNo);
//		}
		
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
	@GetMapping("/pay2")
	public String payaaa(Model model) {
		
		// 페이지 보여질 때 상영관의 좌석 정보 가져와야 함 + 웹 소켓?
		
		return "ticketing/Ticketing32";
	}
	
	@GetMapping("/complete")
	public String complete(Model model) {
		
		// 페이지 보여질 때 상영관의 좌석 정보 가져와야 함 + 웹 소켓?
		
		return "ticketing/Ticketing4";
	}
	
	@GetMapping("/PaymentPage")
	public String PaymentPage(Model model) {
		
		return "ticketing/PaymentPage";
	}
	
	
//	@PostMapping("/seat")
//	public String seat(Ticket ticket
//					, String date
//					, Model model  // 모델에 담아서 forward  
//					, RedirectAttributes ra
//					, @SessionAttribute("loginUser") User loginUser) {
//		
//		String movieTheater = ticket.getMovieTime().split(",")[0];
//		String movieTime = ticket.getMovieTime().split(",")[1];
//		
//		ticket.setUserNo(loginUser.getUserNo());  // 티켓정보에 로그인 회원번호 추가
//		ticket.setMovieTheater(movieTheater);
//		ticket.setMovieTime(date+" "+movieTime);
//		String changeMovieTime = date.substring(0, 4) + "." + date.substring(4,6) + "." + date.substring(6,8) + "(" + date.substring(9)+")";
//		Map<String, Object> map = service.seatInfo(ticket);
//		
//		int hour = Integer.parseInt(movieTime.substring(0,2));
//		int minute = Integer.parseInt(movieTime.substring(3,5));
//		Movie movie = (Movie) map.get("movie");
//		int runTime = Integer.parseInt(movie.getRunningTime());
//		
//		String room = ticket.getMovieNo() + "/" + movieTheater + "/" + date.substring(0, 8) + movieTime;
//		
//		model.addAttribute("room", room);
//		
//		hour = hour+ ((minute+runTime)/60);
//		minute = (minute+runTime)%60;
//		
//		String runningTime = movieTime + "~" + hour +":"+minute;
//		
//		model.addAttribute("map", map);
//		model.addAttribute("ticket",ticket);
//		model.addAttribute("saveday", changeMovieTime);
//		model.addAttribute("runningTime", runningTime);	
//		return "ticketing/Ticketing2";
//	}
//	
//	@PostMapping("/pay")
//	public String pay(Model model,
//			@RequestParam Map<String, Object> paramMap, @SessionAttribute("loginUser") User user, String[] seatList) {
//
//		List<String> resultSeatList = new ArrayList<>();
//		for(String seat : seatList) {
//			resultSeatList.add(seat);
//		}
//		
//		
//		
//		
//		// 좌석 'Y'로 변경
//		int result = service.beforePaySeat(user.getUserNo());
//		
//		int movieNo = Integer.parseInt((String)paramMap.get("movieNo"));
//		
//		Movie movie = service.selectMovie(movieNo);
//		String saveday = (String)paramMap.get("saveday");
//		String runningTime = (String)paramMap.get("runningTime");
//		String movieTheater = (String) paramMap.get("movieTheater");
//		
//		model.addAttribute("movie", movie);
//		model.addAttribute("saveday", saveday);
//		model.addAttribute("runningTime", runningTime);
//		model.addAttribute("resultSeatList", resultSeatList);
//		model.addAttribute("movieTheater", movieTheater);
//		
//		return "ticketing/Ticketing3";
//	}
//	
//	
//	@ResponseBody
//	@PostMapping("/out")
//	public void ticketingOut(@SessionAttribute("loginUser") User user, @RequestBody Map<String, Object> paramMap) {
//		
//		String subday = ((String)paramMap.get("saveday")).substring(0, 10);
//		String subtime = ((String)paramMap.get("runningTime")).substring(0,5);
//		String[] splitday = subday.split("\\.");
//		String movieTime = ""+splitday[0]+splitday[1]+splitday[2]+subtime;
//		int movieNo = (int) paramMap.get("movieNo");
//		System.out.println(movieNo);
//		
//		List<String> seatList = (List<String>) paramMap.get("seatList");
//		
//		List<String> newList = new ArrayList<>();
//		
//		for(int i=0;i<seatList.size();i++) {
//			String seat = seatList.get(i);
//			newList.add(seat);
//		}
//		
//		paramMap.put("movieNo", movieNo);
//		paramMap.put("movieTime", movieTime);
//		paramMap.put("seatList", newList);
//		paramMap.put("userNo", user.getUserNo());
//		
//		
//		service.ticketingOut(paramMap);
//	}
	
	
	@GetMapping("/kakaopay")
	@ResponseBody
	public String kakaopay() {
		try {
			URL address = new URL("https://kapi.kakao.com/v1/payment/ready");
			HttpURLConnection serverConnect = (HttpURLConnection) address.openConnection();
			serverConnect.setRequestMethod("POST");
			serverConnect.setRequestProperty("Authorization", "KakaoAK a12cbc0d986752a861fb2b3e46421dc6");
			serverConnect.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
			serverConnect.setDoOutput(true);
			String param = "cid=TC0ONETIME"
					+ "&partner_order_id=partner_order_id"
					+ "&partner_user_id=partner_user_id"
					+ "&item_name=초코파이"
					+ "&quantity=1"
					+ "&vat_amount=200"
					+ "&tax_free_amount=0"
					+ "&approval_url=https://developers.kakao.com/success"
					+ "&fail_url=https://developers.kakao.com/fail"
					+ "&cancel_url=https://developers.kakao.com/cancel";
			OutputStream outputStream = serverConnect.getOutputStream();
			DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
			dataOutputStream.writeBytes(param);
			dataOutputStream.close();
			
			int result = serverConnect.getResponseCode();
			
			InputStream inputStream;
			if(result == 200) {
				inputStream = serverConnect.getInputStream();
			} else {
				inputStream = serverConnect.getErrorStream();
			}
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			return bufferedReader.readLine();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
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
