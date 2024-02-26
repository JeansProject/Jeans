package com.jeans.cosmetic_project.reviewBoard.dto;

import java.sql.Date;
import java.util.List;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ReviewBoardDTO {

	//id
 	private long seq;
 	private String title;
 	private String content;
 	private String writer;
 	private Date write_time;
 	private Date updated_time;
 	private int count;
 	private int grade;

	/*파일 첨부를 위한 필드*/
	//0과 1로 파일 첨부 유무 확인, <MultipartFile>은 컬렉션
	private int fileAttached;
	private List <MultipartFile> boardFile;

 	
 	
	
	
}
