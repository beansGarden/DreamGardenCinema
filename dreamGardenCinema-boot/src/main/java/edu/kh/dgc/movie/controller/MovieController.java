package edu.kh.dgc.movie.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.kh.dgc.movie.model.dto.Movie;
import edu.kh.dgc.movie.model.service.MovieService;

@RequestMapping("/movie")
@Controller
public class MovieController {

	@Autowired
	private MovieService service;
	
	@GetMapping("")
	public String forwardMain(Model model) {
		
		List<Movie> MovieListCurrentMain = service.selectMovieListCurrentMain();
		
		List<Movie> MovieListPromiseMain = service.selectMovieListPromiseMain();
		
		model.addAttribute("MovieListMainC", MovieListCurrentMain);
		model.addAttribute("MovieListMainP", MovieListPromiseMain);
		
		return "movie/movieMain";
	}
	
	@GetMapping("/current")
	public String forwardCurrent(Model model) {
		
		List<Movie> MovieListCurrent = service.selectMovieListCurrent();
		
		model.addAttribute("MovieListC", MovieListCurrent);
		
		return "movie/movieListCurrent";
	}
	
	@GetMapping("/promise")
	public String forwardPromise(Model model) {
		
		List<Movie> MovieListPromise = service.selectMovieListPromise();
		
		model.addAttribute("MovieListP", MovieListPromise);
		
		return "movie/movieListPromise";
	}
	
	
}
