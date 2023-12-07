package com.jeans.cosmetic_project.ReviewBoardController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reviewBoard")
public class ReviewBoardController {
	
	@GetMapping("/list")
	public String reviewBoard() {
		
		return "reboard/reboardList";
	}

}
