package edu.kh.dgc.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.kh.project.main.model.service.MainService;
import edu.kh.project.movie.model.dto.Movie;

@Controller
public class MainController {
	
	@Autowired
	private MainService service;
	
	@RequestMapping("/")
	public String mainForward(Model model) {
		List<Movie> movieList = service.selectMovieList();
		System.out.println(movieList);
		model.addAttribute("movieList", movieList);
		return "common/main";
	}
	
}
