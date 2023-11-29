package com.jeans.cosmetic_project.test.dao;

import com.jeans.cosmetic_project.test.dto.*;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface TestDao {



    int register(TestDto registerUser);

    UserInfoDto getUserInfoById(String id);

    List<NoticeDto> getNoticeList();
    NoticeDto noticeContent(int seq);
    int noticeInsert(NoticeDto notice);
    int noticeUpdate(NoticeDto notice);
    int noticeDelete(int seq);

    int updateUserInfoById(UpdateUserInfoRequestDto updateUserInfoRequestDto);
}
