package com.jeans.cosmetic_project.test.dto;

import lombok.*;


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
}
