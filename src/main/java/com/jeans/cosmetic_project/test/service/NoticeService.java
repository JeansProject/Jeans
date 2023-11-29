package com.jeans.cosmetic_project.test.service;

import com.jeans.cosmetic_project.test.dto.NoticeDto;

import java.util.List;

public interface NoticeService {

    List<NoticeDto> getNoticeList();
    NoticeDto noticeContent (int seq);
    int noticeInsert(NoticeDto notice);
    int noticeUpdate(NoticeDto notice);
    int noticeDelete(int seq);
}
