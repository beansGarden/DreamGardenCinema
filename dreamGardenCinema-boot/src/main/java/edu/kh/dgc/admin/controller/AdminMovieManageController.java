	package edu.kh.dgc.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.kh.dgc.admin.model.service.AdminMovieManageService;
import edu.kh.dgc.movie.model.dto.Movie;

@Controller
public class AdminMovieManageController {
	
	@Autowired
	private AdminMovieManageService service;
	
	@GetMapping("/adminMovieManage")
	public String movieManage(Model model) {
		
		List<Movie> movieListCurrent = service.selectmovieListCurrent();
		
		model.addAttribute("movieList", movieListCurrent);

		return "admin/admin_movieManage";
	}
	
	@PostMapping("/adminMovieManage/selectList")
	@ResponseBody
	public List<Movie> selectList(Model model, @RequestBody Map<String,String> data) {
		
		String screenType = data.get("screenType");
		
		Map<String, Object> requestData = new HashMap<>();
		requestData.put("screenType", screenType);
		
		return service.selectList(requestData);
	}
	
	
}
