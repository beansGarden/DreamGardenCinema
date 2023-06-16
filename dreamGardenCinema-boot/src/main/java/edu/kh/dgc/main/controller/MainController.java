package edu.kh.dgc.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.kh.dgc.movie.model.dto.Movie;
import edu.kh.dgc.notice.model.dto.Notice;
import edu.kh.dgc.notice.model.service.NoticeService;
import edu.kh.dgc.ticketing.model.service.TicketingService;

@Controller
public class MainController {
	
	@Autowired
	private TicketingService ticketingService;
	
	@Autowired
	private NoticeService noticeService;
	
	@RequestMapping("/")
	public String mainForward(Model model) {
		
		List<Movie> movieList = ticketingService.selectMovieList();
		List<Notice> noticeList = noticeService.selectNoticeList();
		
		model.addAttribute("movieList", movieList);
		model.addAttribute("noticeList", noticeList);
		return "common/main";
	}
	
}
