package edu.kh.dgc.movie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/movie")
@Controller
public class MovieController {

	@GetMapping("")
	public String forwardMain() {
		
		return "movie/movieMain";
	}
	
	@GetMapping("/current")
	public String forwardCurrent() {
		
		return "movie/movieListCurrent";
	}
	
	@GetMapping("/promise")
	public String forwardPromise() {
		
		return "movie/movieListPromise";
	}
	
	
}
