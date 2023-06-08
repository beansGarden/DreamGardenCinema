package edu.project.dreamGardenCinema.movie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/movie")
@Controller
public class MovieController {

	@GetMapping("")
	public String selectBoardList() {
		
		return "movie/movieMain";
	}
	
	
}
