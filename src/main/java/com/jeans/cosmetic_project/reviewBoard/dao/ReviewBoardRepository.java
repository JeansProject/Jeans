package com.jeans.cosmetic_project.reviewBoard.dao;

import com.jeans.cosmetic_project.common.paging.SelectCriteria;
import com.jeans.cosmetic_project.reviewBoard.dto.ReviewBoardDTO;
import com.jeans.cosmetic_project.reviewBoard.dto.ReviewBoardFileDTO;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
@RequiredArgsConstructor
public class ReviewBoardRepository {
	private final SqlSessionTemplate sql;

	//갯수 가져오는 매퍼
	public int selectTotalCount(Map<String, String> searchMap) {
		return sql.selectOne("ReviewBoardRepository.selectTotalCount", searchMap);
	}

	//페이징 정보와 함께 담은 매퍼
	public List<ReviewBoardDTO> selectBoardList(SelectCriteria selectCriteria) {
		return sql.selectList("ReviewBoardRepository.selectBoardList", selectCriteria);
	}

	/*게시글 상세조회 */
	public ReviewBoardDTO selectBoardDetail(int seq) {
		return sql.selectOne("ReviewBoardRepository.selectBoardDetail", seq);
	}

	/*detail 홈페이지의 조회수를 위한 로직*/
	public int incrementBoardCount(int seq) {
		return sql.update("ReviewBoardRepository.incrementBoardCount", seq);
	}

	/*반환값이 int인 경우는 따로 resultmap작업을 해줄 필요 없이 반환 변수의 값이 mapper에서 id가 되고 쿼리문을 작성해준다.
	 * 반환값이 DTO를 쓰는 경우에는 resultmap을 작업해주고, id를 일치시켜준다.
	 *
	 * selectBoardList selectBoardDetail 둘다 resultmap이 같은데 이거를 두번써주는게 맞나? 같은 id로 쓰는구나!!!  */

	/*게시글 작성*/
	public void reviewBoardRegist(ReviewBoardDTO reviewBoardDTO) {
		sql.insert("ReviewBoardRepository.reviewBoardRegist", reviewBoardDTO);
//		return reviewBoardDTO;
	}


	/*첨부파일 insert*/
	public void saveFile(ReviewBoardFileDTO reviewBoardFileDTO) {
		sql.insert("ReviewBoardRepository.saveFile", reviewBoardFileDTO);
	}

	/*게시판 수정*/

	public int modify(ReviewBoardDTO reviewBoardDTO) {
		return sql.update("ReviewBoardRepository.modify", reviewBoardDTO);


	}

}
