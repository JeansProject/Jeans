package com.jeans.cosmetic_project.notice.service;

import com.jeans.cosmetic_project.notice.dto.FileDto;
import com.jeans.cosmetic_project.notice.dto.NoticeDto;

import java.util.List;

public interface NoticeService {

    List<NoticeDto> getNoticeList();

    NoticeDto noticeContent(int seq);

    int noticeInsert(NoticeDto notice);

    int noticeUpdate(NoticeDto notice);

    int noticeDelete(int seq);

    int fileInsert(FileDto file);

    FileDto fileNotice (int board_seq);

    FileDto fileUpdate (FileDto fileDto);

    void fileDelete(int seq);

    void fileUpsert(FileDto fileDto);
}
