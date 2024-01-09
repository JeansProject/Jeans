package com.jeans.cosmetic_project.reviewBoard.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeans.cosmetic_project.common.paging.Pagenation;
import com.jeans.cosmetic_project.common.paging.SelectCriteria;
import com.jeans.cosmetic_project.reviewBoard.dao.ReviewBoardMapper;
import com.jeans.cosmetic_project.reviewBoard.dto.ReviewBoardDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional // 커밋 롤백을 위한 어노테이션
public class ReviewBoardService {

	private final ReviewBoardMapper reviewBoardMapper;

	public ReviewBoardService(ReviewBoardMapper reviewBoardMapper) {
		this.reviewBoardMapper = reviewBoardMapper;

	}

	public Map<String, Object> selectReviewBoardList(Map<String, String> searchMap, int page) {
		/*
		 * 1.검색과 페이징 처리를 해야한다. '1. 전체 게시글 수 확인(검색어가 있는 경우 포함)=> 페이징 처리 계산을 위해서
		 * selectTotalCount전체 게시글 수 인데, 그중에 검색어 기준으로도 계산해야해서 검색어 searchMap을 받아온다.
		 */
		/* 즉 다시 말해서 검색 조건에 대한 db검색을 했을때 쿼리를 통해 총 몇개가 검색되는지 int를 통해 반환한다. */
		int totalCount = reviewBoardMapper.selectTotalCount(searchMap); // int 갯수가 핵심
		log.info("[ReviewBoardService] totalcount:{}", totalCount);

		/* 한 페이지에 보여줄 게시물의 수 */
		int limit = 10;
		/* 한 번에 보여질 페이징 버튼의 수 */
		int buttonAmount = 5;
		/* 2. 페이징 처리와 연관 된 값을 계산하여 SelectCriteria 타입의 객체에 담는다. */
		// 클래스:조회시 필요한 조건들을 한번에 담으려고 만든 클래스
		SelectCriteria selectCriteria = Pagenation.getSelectCriteria(page, totalCount, limit, buttonAmount, searchMap);
		log.info("[ReviewBoardService] selectCriteria:{}", selectCriteria);

		/* 3. 요청 페이지와 검색 기준에 맞는 게시글을 조회해온다. */
		// start row와 endrow기준으로 조회해오겠다.
		List<ReviewBoardDTO> boardList = reviewBoardMapper.selectBoardList(selectCriteria);
		log.info("[ReviewBoardService] boardList:{}", boardList);

		/*
		 * 뷰를 표현할 댸 selectCriteria(페이징바 처리값) boardList(조회 해온 값) 이 두 값을 한꺼번에 처리해서 나가야해서
		 * 반환을 map으로 처리했다. Map<String, Object>
		 */

		Map<String, Object> boardListAndPaging = new HashMap<>();
		boardListAndPaging.put("paging", selectCriteria);
		boardListAndPaging.put("boardList", boardList);
		return boardListAndPaging;
	}

	public ReviewBoardDTO selectBoardDetatil(int seq) {

		/* 조회수 업데이트 로직 추가 예정 */
		/*1. 조회수 증가 로직 */
		int result= reviewBoardMapper.incrementBoardCount(seq);

		/* select 절
		 * 2. 게시글 상세 내용 조회 후 리턴  */
		return reviewBoardMapper.selectBoardDetail(seq);
		
	}
	
	/*게시글 작성 insert 반환값을 딱히 없다.*/
	public void reviewBoardRegist(ReviewBoardDTO reviewBoardDTO) {
		
		reviewBoardMapper.reviewBoardRegist(reviewBoardDTO);
	}

}
