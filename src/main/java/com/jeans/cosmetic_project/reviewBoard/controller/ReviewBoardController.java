package com.jeans.cosmetic_project.reviewBoard.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jeans.cosmetic_project.reviewBoard.dto.ReviewBoardDTO;
import com.jeans.cosmetic_project.reviewBoard.service.ReviewBoardService;

import lombok.extern.slf4j.Slf4j;


//빈등록
@Slf4j
@Controller
@RequestMapping("/reviewBoard")
public class ReviewBoardController {
	
	//의존성 주입: 일일히 객체 생성을 하지 않아도 된다. 
	
	private final ReviewBoardService reviewBoardService;
	
	public ReviewBoardController(ReviewBoardService reviewBoardService) {
		this.reviewBoardService = reviewBoardService;
	}
	
	
	//페이징바로 처리하든, 검색으로 처리하든 이 요청을 통해 처리할 것이다. 
	//현재 검색어, 페이징 처리에 대한 처리가 필요하다. 
	@GetMapping("/list")  //optional하게 넘어오는 페이지는 @RequestParam의 역할 즉 기본값 세팅에 필요
	//modelAttribute 어노테이션 붙이고 DTO를 만들어 data를 붙이면, 해당
	public String reviewBoard(@RequestParam(defaultValue="1") int page, 
		@RequestParam(required= false) String searchCondition,  
		@RequestParam(required= false) String searchValue,
		Model model){
		
		log.info("[ReviewBoardController[ page: {}", page);
		
		Map<String,String> searchMap= new HashMap<>();
		searchMap.put("searchCondition",  searchCondition);
		searchMap.put("searchValue", searchValue);
		
		log.info("[ReviewBoardController[ serchMap: {}", searchMap);

		//검색기준과 page에 대한 정보를 한번에 담아오기 위해 map을 사용
		
		Map<String, Object> reviewBoardListAndPaging= reviewBoardService.selectReviewBoardList(searchMap, page);
		model.addAttribute("paging",reviewBoardListAndPaging.get("paging"));
		model.addAttribute("boardList",reviewBoardListAndPaging.get("boardList"));
				
				;
		return "reviewBoard/reviewBoardList";
	}
	

	 @GetMapping("/new")
    public String newReviewBoard() {
    	
    	return "reviewBoard/newReviewBoard";
    	
    }
	 @GetMapping("detatil")
	 public String ReviewBoardDetatil(
			 @RequestParam int seq, Model model ) {
		 
		 ReviewBoardDTO ReviewBoardDetail= reviewBoardService.selectBoardDetatil(seq);
		 
		 
		 return null;
	 }

}
