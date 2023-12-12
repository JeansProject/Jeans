package com.jeans.cosmetic_project.reviewBoard.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class ReviewBoardDTO {
	
 	private int seq;
 	private String title;
 	private String content;
 	private String writer;
 	private Date write_time;
 	
	
	
}
