package com.jeans.cosmetic_project.test.service;


import com.jeans.cosmetic_project.test.dao.TestDao;
import com.jeans.cosmetic_project.test.dto.FileDto;
import com.jeans.cosmetic_project.test.dto.LoginUser;
import com.jeans.cosmetic_project.test.dto.NoticeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

    private final TestDao testDao;

    @Override
    public List<NoticeDto> getNoticeList() {
        return testDao.getNoticeList();
    }

    @Override
    public NoticeDto noticeContent(int seq) {
        return testDao.noticeContent(seq);
    }

    @Override
    public int noticeInsert(NoticeDto notice) {
        return testDao.noticeInsert(notice);
    }

    @Override
    public int noticeUpdate(NoticeDto notice) {
        return testDao.noticeUpdate(notice);
    }

    @Override
    public int noticeDelete(int seq) {
        return testDao.noticeDelete(seq);
    }

    @Override
    public int fileInsert(FileDto file) {
        return testDao.fileInsert(file);
    }

    @Override
    public FileDto fileNotice(int board_seq) {
        return testDao.fileNotice(board_seq);
    }
}
