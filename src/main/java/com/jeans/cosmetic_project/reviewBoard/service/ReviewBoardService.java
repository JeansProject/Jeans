package com.jeans.cosmetic_project.reviewBoard.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jeans.cosmetic_project.reviewBoard.dto.ReviewBoardFileDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeans.cosmetic_project.common.paging.Pagenation;
import com.jeans.cosmetic_project.common.paging.SelectCriteria;
import com.jeans.cosmetic_project.reviewBoard.dao.ReviewBoardMapper;
import com.jeans.cosmetic_project.reviewBoard.dto.ReviewBoardDTO;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

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
	public void reviewBoardRegist(ReviewBoardDTO reviewBoardDTO) throws IOException {
		if(reviewBoardDTO.getBoardFile().get(0).isEmpty()){
			//파일이 없다.
			reviewBoardDTO.setFileAttached(0);
			reviewBoardMapper.reviewBoardRegist(reviewBoardDTO);

		}else{
			//파일 있다.(1)
			reviewBoardDTO.setFileAttached(1);
			//게시글 저장 후 id값 활용을 위해 리턴 받음. -> id가 reviewBoard의 고유값 //반환형으로 바꿔야함.(2)
			ReviewBoardDTO savedReviewBoard=reviewBoardMapper.reviewBoardRegist(reviewBoardDTO);

			//파일만 따로 가져 오기 (multipartFile 객체에 대해서 잘 알아야 할듯) (3)
			for(MultipartFile boardFile: reviewBoardDTO.getBoardFile()){
				//파일만 따로 가져오기
				String originalFilename = boardFile.getOriginalFilename();
				System.out.println("originalFilename = " + originalFilename);
				// 저장용 이름 만들기
				System.out.println(System.currentTimeMillis());
				String storedFileName = System.currentTimeMillis() + "-" + originalFilename;
				System.out.println("storedFileName = " + storedFileName);

				//boardFileDTO 세팅
				//MULTIPARTfILE에 파일이 들어오면, 그것에 다른 것이 담기기 전에, db에 옮기는거구나!
				//MULTIPARTfILE자체는 dB에 저장이 될 수 없는 것이기 떄문에.
				ReviewBoardFileDTO reviewBoardFileDTO = new ReviewBoardFileDTO();
				//필요한게 originalFilename, storedFilename, saveBoard.getId() "고유값을 가져온단말이야, 왜 가져오는거야?"
				reviewBoardFileDTO.setOriginalFileName(originalFilename);
				reviewBoardFileDTO.setStoredFileName(storedFileName);
				reviewBoardFileDTO.setReviewBoardSeq(savedReviewBoard.getSeq());
				// 파일 저장용 폴더에 파일 저장 처리
				//String savePath = "/Users/codingrecipe/development/intellij_community/spring_upload_files/" + storedFileName; // mac
                 String savePath = "C:/Users/LEEHYUNJAE/pring_upload_files/" + storedFileName;
				boardFile.transferTo(new File(savePath));
				// board_file_table 저장 처리
				reviewBoardMapper.saveFile(reviewBoardFileDTO);



			}
		}
		reviewBoardMapper.reviewBoardRegist(reviewBoardDTO);
	}

	/*게시르 update */
	public int modify(ReviewBoardDTO reviewBoardDTO) {

		return reviewBoardMapper.modify(reviewBoardDTO);


	}


}
