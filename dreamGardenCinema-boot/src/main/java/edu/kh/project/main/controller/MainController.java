package edu.kh.project.main.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.kh.project.main.model.service.MainService;

@Controller
public class MainController {
	
	@Autowired
	private MainService service;
	
	@RequestMapping("/")
	public String mainForward(Model model) {
		List<Map<String, Object>> movieList = service.movie();
		
		model.addAttribute("movieList", movieList);
		return "common/main";
	}
	
}
