package com.jeans.cosmetic_project.reviewBoard.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jeans.cosmetic_project.reviewBoard.dto.ReviewBoardDTO;
import com.jeans.cosmetic_project.reviewBoard.service.ReviewBoardService;
import com.jeans.cosmetic_project.user.dto.User;

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
	public String reviewBoard(@RequestParam(defaultValue = "1") int page,
							  @RequestParam(required = false) String searchCondition,
							  @RequestParam(required = false) String searchValue,
							  Model model) {

		log.info("[ReviewBoardController[ page: {}", page);
		log.info("[ReviewBoardController[ searchValue: {}", searchValue);

		Map<String, String> searchMap = new HashMap<>();
		searchMap.put("searchCondition", searchCondition);
		searchMap.put("searchValue", searchValue);

		log.info("[ReviewBoardController[ serchMap: {}", searchMap);

		//검색기준과 page에 대한 정보를 한번에 담아오기 위해 map을 사용

		Map<String, Object> reviewBoardListAndPaging = reviewBoardService.selectReviewBoardList(searchMap, page);
		model.addAttribute("paging", reviewBoardListAndPaging.get("paging"));
		model.addAttribute("boardList", reviewBoardListAndPaging.get("boardList"));


		return "reviewBoard/reviewBoardList";
	}


	@GetMapping("/regist")
	public String goRegistReviewBoard() {

		return "reviewBoard/reviewBoardRegist";

	}

	@PostMapping("/regist")
	public String registReviewBoard(ReviewBoardDTO reviewBoardDTO, HttpSession session) throws IOException {
		/*작성자 session 객체에서 가져가는 것을 넣어야함*/
		/* object가 반환되고, user 객체로 형변환이 필요 */
		User user = (User) session.getAttribute("loginUser");//user.get
		//reviewBoardDTO.setWriter(user.getId());
		// String name= user.getId();
		log.info("User 객체{}", user);
		/*로그인을 안해서 세션이 없어서 일단 입력*/
		/*질문! getName 했는데 id가 나오는데?
		 * User라는 객체에 대한 정보는 알았지만 session에 어떻게 담기는지 질문*/
		reviewBoardDTO.setWriter(user.getName());

		/*grade 뷰완성 */
		/*grade 에 대한 테스트(뷰를 아직 못만들어서) */
//		 reviewBoardDTO.setGrade(1);
		/*서비스단에 생성자를 담아 호출한다.*/


		reviewBoardService.reviewBoardRegist(reviewBoardDTO);

		return "redirect:/reviewBoard/list";
	}

	@GetMapping("/detail")
	public String ReviewBoardDetatil(@RequestParam int seq, Model model, HttpSession session) {
		//게시판 클릭했을떄 seq 를 타고 db에 요청할 예정,.
		log.info("[reviewBoardController]seq:{}", seq);
		User user = (User) session.getAttribute("loginUser");
		ReviewBoardDTO ReviewBoardDetail = reviewBoardService.selectBoardDetatil(seq);
		ReviewBoardDetail.setSeq(seq);
		log.info("[reviewBoardController]ReviewBoardDetail:{}", ReviewBoardDetail);

		model.addAttribute("reviewBoard", ReviewBoardDetail);
		model.addAttribute("loginUser",user.getName());
		//서비스 로직에서 연산하고 나온값에 대한 처리

		/*DTO 까지 넘어오는거 확인 뷰 템플릿 그리고 거기다가 뿌리는거 연습해야함 .*/

		return "reviewBoard/reviewBoardDetail";
	}

	@GetMapping("/modify")
	public String ReviewBoardModify(@RequestParam int seq, Model model) {

		log.info("[reviewBoardController. modify]seq:{}", seq);

		/*1. 수정 버튼을 눌렀을때,  수정페이지 이동*/
		ReviewBoardDTO ReviewBoardDetail = reviewBoardService.selectBoardDetatil(seq);
		log.info("[reviewBoardController. modify]ReviewBoardDetail:{}", ReviewBoardDetail);
		ReviewBoardDetail.setSeq(seq);
		model.addAttribute("reviewBoard", ReviewBoardDetail);


		return "reviewBoard/reviewBoardModify";

	}

	@PostMapping("/modify")
	public String ReviewBoardModify(ReviewBoardDTO reviewBoardDTO) {
		log.info("[reviewBoardController. modify.post]reviewBoardDTO:{}", reviewBoardDTO);
		reviewBoardService.modify(reviewBoardDTO);

		/*redirect를 반드시 명시해줘야하는 이유?*/
		return "redirect:/reviewBoard/list";
	}
}