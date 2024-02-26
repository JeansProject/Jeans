package com.jeans.cosmetic_project.reviewBoard.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReviewBoardFileDTO {
    private long seq;
    private Long reviewBoardSeq;
    private String originalFileName;
    private String storedFileName;

}
