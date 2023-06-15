package edu.kh.dgc.main.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import edu.kh.dgc.main.model.service.MainService;
import edu.kh.dgc.movie.model.dto.Movie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {
	
	@Autowired
	private MainService service;
	
	@RequestMapping("/")
	public String mainForward(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
		Map<String, Object> map = service.selectMovieList();
		
		session.setAttribute("movieList", map.get("movieList"));
		session.setAttribute("noticeList", map.get("noticeList"));
		return "common/main";
	}
	
}
