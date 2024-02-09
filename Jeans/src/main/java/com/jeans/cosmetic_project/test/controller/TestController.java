package com.jeans.cosmetic_project.test.controller;

import com.jeans.cosmetic_project.test.dto.FileDto;
import com.jeans.cosmetic_project.test.dto.LoginUser;
import com.jeans.cosmetic_project.test.dto.NoticeDto;
import com.jeans.cosmetic_project.test.service.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/test")
public class TestController {

    //    private final TestService testServiceImpl;
    private final NoticeService noticeServiceImpl;

    @GetMapping("/test")
    public String test() {
        return "test/test";
    }

    @GetMapping("/myPage")
    public String myPage() {
        return "test/myPage";
    }


    @GetMapping("/noticeList")
    private String noticeList(Model model, HttpServletRequest request) {

        List<NoticeDto> testList = new ArrayList<>();
        testList = noticeServiceImpl.getNoticeList();
        log.info("리스트 받음");
        model.addAttribute("testList", testList);
        return "/test/noticeList";
    }

//    @GetMapping("/notice/{seq}")
//    private String noticeContent(@PathVariable("seq") Integer seq, Model model) {
//
//        model.addAttribute("notice", noticeServiceImpl.noticeContent(seq));
//
//        if(noticeServiceImpl.fileNotice(seq) == null){
//            return "test/notice";
//        } else {
//            model.addAttribute("file", noticeServiceImpl.fileNotice(seq));
//            return "test/notice";
//        }
//
//    }

    @PostMapping("/insert")
    private String fileNoticeInsert(@RequestBody NoticeDto notice) {
        return "test/noticeList";
    }

    @PostMapping("/insert/new")
    public String createInsertForm(@ModelAttribute("NoticeDto") NoticeDto notice, Model model) {
        model.addAttribute("NoticeDto", new NoticeDto());
        return "test/noticeList";
    }

//    @PostMapping("/insertProc")
//    private String fileNoticeInsertProc(@ModelAttribute NoticeDto notice, LoginUser loginUser, @RequestPart MultipartFile files, HttpServletRequest request) throws IllegalStateException, IOException, Exception {
//
//        log.info("인서트프로세스 실행");
//        if (files.isEmpty()) {
//            noticeServiceImpl.noticeInsert(notice);
//        } else {
//            String client_file_name = files.getOriginalFilename(); // 사용자 컴퓨터에 저장된 파일명 그대로 받아옴
//            //확장자
//            String client_file_nameExtension = FilenameUtils.getExtension(client_file_name).toLowerCase();
//            File destinationFile; //DB에 저장할 파일 고유명
//            String destinationFileName; //절대경로 설정
//            String file_path = "/Users/jaeho/Desktop/study/Jeans/files";
//
//            do { //우선 실행 후
//                //고유명 생성
//                destinationFileName = RandomStringUtils.randomAlphanumeric(32) + "." + client_file_nameExtension;
//                destinationFile = new File(file_path + "/" + destinationFileName); //합쳐주기
//            } while (destinationFile.exists());
//
//            destinationFile.getParentFile().mkdirs(); //디렉토리
//            files.transferTo(destinationFile);
//
//            noticeServiceImpl.noticeInsert(notice);
//
//            FileDto file = new FileDto();
//            file.setBoard_seq(notice.getSeq());
//            file.setClient_file_name(client_file_name);
//            file.setServer_file_name(destinationFileName);
//            file.setFile_path(file_path);
//            file.setUser_seq(loginUser.getSeq());
//
//            noticeServiceImpl.fileInsert(file);
//        }
//        return "forward:/test/noticeList";
//    }

    @DeleteMapping("delete/{seq}")
    private String noticeDelete(@PathVariable("seq") int seq) {
        noticeServiceImpl.noticeDelete(seq);
        return "redirect:/test/noticeList";
    }

    @PutMapping("/update/{seq}")
    private String fileNoticeUpdateForm(@PathVariable("seq") int seq, Model model) {
        model.addAttribute("notice", noticeServiceImpl.noticeContent(seq));
        return "test/update";
    }
}
//    @PutMapping(value = "/updateProc",
//    consumes = {
//        MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
//    private String fileNoticeUpdate(@RequestPart(value="data") NoticeDto notice, LoginUser loginUser, @RequestPart(value="file") MultipartFile files, HttpSession session) {
//
//        if(files != null){
//            notice.setFiles(files);
//        }
//        noticeServiceImpl.noticeUpdate(notice);
//        int seq = notice.getSeq();
//        String noticeNumber = Integer.toString(seq);
//        return "redirect:/test/notice/" + seq;
//    }

//    @GetMapping ("/fileDown")
//    public void fileDown(@RequestParam("seq") int seq, @RequestParam("board_seq") int board_seq, HttpServletResponse response){
//    try {
//            FileDto fileDto = noticeServiceImpl.fileNotice(board_seq);
//            if(ObjectUtils.isEmpty(fileDto) == false){
//                String fileName = fileDto.getClient_file_name();
//                byte[] files = FileUtils.readFileToByteArray(new File(fileDto.getFile_path() + "/" + fileDto.getServer_file_name()));
//
//                response.setContentType("application/octet-stream");
//                response.setContentLength(files.length);
//                response.setHeader("Content-Disposition", "attachment; fileName=\" " + URLEncoder.encode(fileName, StandardCharsets.UTF_8)+"\";");
//                response.setHeader("Content-Transfer-Encoding", "binary");
//
//                response.getOutputStream().write(files);
//                response.getOutputStream().flush();
//                response.getOutputStream().close();
//
//            }
//        } catch (IOException e){
//            log.error(e.getMessage());
//            e.getStackTrace();
//        }
//    }

//}
