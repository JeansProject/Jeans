package com.jeans.cosmetic_project.test.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileDto {
    private int seq; // 파일 번호
    private int board_seq; //게시판 번호와 동기화
    private String board_type;
    private String client_file_name; //저장할 때
    private String server_file_name; // 받아올 때 파일 이름
    private String file_path; // 저장 및 불러올 경우
    private String file_size;
    private int user_seq;
}
