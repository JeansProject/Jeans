package com.jeans.cosmetic_project.reviewBoard.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.jeans.cosmetic_project.common.paging.SelectCriteria;
import com.jeans.cosmetic_project.reviewBoard.dto.ReviewBoardDTO;

@Mapper
public interface ReviewBoardMapper {
//갯수 가져오는 매퍼
	int selectTotalCount(Map<String, String> searchMap);
//페이징 정보와 함께 담은 매퍼 
	List<ReviewBoardDTO> selectBoardList(SelectCriteria selectCriteria);
//디테일 매퍼 이거는 차근차근 다시
	/*게시글 상세조회 */
	ReviewBoardDTO selectBoardDetail(int seq);  //mapper 작업
	
	/*detail 홈페이지의 조회수를 위한 로직*/
	int incrementBoardCount(int seq);
	
	/*반환값이 int인 경우는 따로 resultmap작업을 해줄 필요 없이 반환 변수의 값이 mapper에서 id가 되고 쿼리문을 작성해준다.
	 * 반환값이 DTO를 쓰는 경우에는 resultmap을 작업해주고, id를 일치시켜준다.
	 * 
	 * selectBoardList selectBoardDetail 둘다 resultmap이 같은데 이거를 두번써주는게 맞나? 같은 id로 쓰는구나!!!  */

}
