package com.jeans.cosmetic_project.test.controller;

import com.jeans.cosmetic_project.test.dto.NoticeDto;
import com.jeans.cosmetic_project.test.service.NoticeService;
import com.jeans.cosmetic_project.test.service.TestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/test")
public class TestController {

    private final TestService testServiceImpl;
    private final NoticeService noticeServiceImpl;

    @GetMapping("/test")
    public String test() {
        return "test/test";
    }

    @GetMapping("/myPage")
    public String myPage() {
        return "test/myPage";
    }


    @RequestMapping("/noticeList")
    private String noticeList(Model model, HttpServletRequest request) {

        List<NoticeDto> testList = new ArrayList<>();
        testList = noticeServiceImpl.getNoticeList();
        log.info("리스트 받음");
        model.addAttribute("testList", testList);
        return "/test/noticeList";
    }

    @RequestMapping("/notice/{seq}")
    private String noticeContent(@PathVariable("seq") int seq, Model model) {
        model.addAttribute("notice", noticeServiceImpl.noticeContent(seq));
        return "test/notice";
    }

    @RequestMapping("/insert")
    private String fileNoticeInsert(@ModelAttribute("NoticeDto") NoticeDto notice) {
        return "test/insert";
    }

    @RequestMapping(value="/insert/new", method = {RequestMethod.GET, RequestMethod.POST})
    public String createInsertForm(Model model) {
        model.addAttribute("NoticeDto", new NoticeDto());
        return "test/insert";
    }

    @RequestMapping("/insertProc")
    private String fileNoticeInsertProc(@ModelAttribute NoticeDto notice, HttpServletRequest request) {
        noticeServiceImpl.noticeInsert(notice);
        return "forward:/test/noticeList";
    }

    @RequestMapping("delete/{seq}")
    private String noticeDelete(@PathVariable("seq") int seq) {
        noticeServiceImpl.noticeDelete(seq);
        return "redirect:/test/noticeList";
    }

    @RequestMapping("/update/{seq}")
    private String fileNoticeUpdateForm(@PathVariable("seq") int seq, Model model) {
        model.addAttribute("notice", noticeServiceImpl.noticeContent(seq));
        return "test/update";
    }

    @RequestMapping("/updateProc")
    private String fileNoticeUpdate(@ModelAttribute NoticeDto notice) {

        noticeServiceImpl.noticeUpdate(notice);
        int seq = notice.getSeq();
        String noticeNumber = Integer.toString(seq);
        return "redirect:/test/notice/" + seq;
    }

}
