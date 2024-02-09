package com.jeans.cosmetic_project.test.dao;

import com.jeans.cosmetic_project.test.dto.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Mapper;

import java.io.File;
import java.util.List;

@Mapper

public interface TestDao {

    LoginUser login(TestDto loginUser);

    int register(TestDto registerUser);

    List<NoticeDto> getNoticeList();

    NoticeDto noticeContent(int seq);

    int noticeInsert(NoticeDto notice);

    int noticeUpdate(NoticeDto notice);

    int noticeDelete(int seq);

    int fileInsert(FileDto file);

    FileDto fileNotice (int board_seq);

    int fileUpdate (FileDto fileDto);

    void fileDelete(int seq);

    void fileUpsert(FileDto fileDto);
}
