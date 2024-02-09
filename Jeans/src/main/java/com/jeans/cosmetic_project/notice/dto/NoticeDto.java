package com.jeans.cosmetic_project.notice.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;


import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NoticeDto {
   private int seq;
   private String title;
   private String content;
   private String writer;
   private Date write_time;
   private Date update_time;
   private MultipartFile files;
   private String client_file_name;
   private String fileFlag;
}
