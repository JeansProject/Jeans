package com.jeans.cosmetic_project.test.controller;

import com.jeans.cosmetic_project.test.dto.FileDto;
import com.jeans.cosmetic_project.test.dto.LoginUser;
import com.jeans.cosmetic_project.test.dto.NoticeDto;
import com.jeans.cosmetic_project.test.service.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
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


    @RequestMapping("/noticeList")
    private String noticeList(Model model, HttpServletRequest request) {

        List<NoticeDto> testList = new ArrayList<>();
        testList = noticeServiceImpl.getNoticeList();
        log.info("리스트 받음");
        model.addAttribute("testList", testList);
        return "/test/noticeList";
    }

    @RequestMapping("/notice/{seq}")
    private String noticeContent(@PathVariable("seq") Integer seq, Model model) {

        model.addAttribute("notice", noticeServiceImpl.noticeContent(seq));

        if(noticeServiceImpl.fileNotice(seq) == null){
            return "test/notice";
        } else {
            model.addAttribute("file", noticeServiceImpl.fileNotice(seq));
            return "test/notice";
        }

    }

    @RequestMapping("/insert")
    private String fileNoticeInsert(@ModelAttribute("NoticeDto") NoticeDto notice) {
        return "test/insert";
    }

    @RequestMapping(value = "/insert/new", method = {RequestMethod.GET, RequestMethod.POST})
    public String createInsertForm(Model model) {
        model.addAttribute("NoticeDto", new NoticeDto());
        return "test/insert";
    }

    @RequestMapping("/insertProc")
    private String fileNoticeInsertProc(@ModelAttribute NoticeDto notice, LoginUser loginUser, @RequestPart MultipartFile files, HttpServletRequest request) throws IllegalStateException, IOException, Exception {

        if (files.isEmpty()) {
            noticeServiceImpl.noticeInsert(notice);
        } else {
            String client_file_name = files.getOriginalFilename(); // 사용자 컴퓨터에 저장된 파일명 그대로 받아옴
            //확장자
            String client_file_nameExtension = FilenameUtils.getExtension(client_file_name).toLowerCase();
            File destinationFile; //DB에 저장할 파일 고유명
            String destinationFileName; //절대경로 설정
            String file_path = "/Users/jaeho/Desktop/study/Jeans/files";

            do { //우선 실행 후
                //고유명 생성
                destinationFileName = RandomStringUtils.randomAlphanumeric(32) + "." + client_file_nameExtension;
                destinationFile = new File(file_path + "/" + destinationFileName); //합쳐주기
            } while (destinationFile.exists());

            destinationFile.getParentFile().mkdirs(); //디렉토리
            files.transferTo(destinationFile);

            noticeServiceImpl.noticeInsert(notice);

            FileDto file = new FileDto();
            file.setBoard_seq(notice.getSeq());
            file.setClient_file_name(client_file_name);
            file.setServer_file_name(destinationFileName);
            file.setFile_path(file_path);
            file.setUser_seq(loginUser.getSeq());

            noticeServiceImpl.fileInsert(file);
        }
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

    @RequestMapping("/fileDown/{board_seq}")
    private void fileDown(@PathVariable("board_seq") int board_seq, HttpServletRequest request,
                          HttpServletResponse response) throws UnsupportedEncodingException, Exception {

        request.setCharacterEncoding("UTF-8");
        FileDto fileDto = noticeServiceImpl.fileNotice(board_seq);

        //파일 업로드 경로
        try {
            String file_path = fileDto.getFile_path();
            System.out.println(file_path);
            file_path += "/";
            String savePath = file_path;
            String fileName = fileDto.getClient_file_name();

            //실제 내보낼 파일명
            String server_file_name = fileDto.getServer_file_name();
            InputStream in = null;
            OutputStream os = null;
            File file = null;
            Boolean skip = false;
            String client = "";

            //파일을 읽어 스트림에 담기
            try {
                file = new File(savePath, server_file_name);
                in = new FileInputStream(file);
            } catch (FileNotFoundException fe) {
                skip = true;
            }

            client = request.getHeader("User_Agent");

            //파일 다운로드 헤더 지정
            response.reset();
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Description", "HTML Generated Data");

            if (!skip) {
//                //IE
//                if (client.indexOf("MSIE") != -1) {
//                    response.setHeader("Content-Disposition", "attachment; filename=\""
//                            + java.net.URLEncoder.encode(server_file_name, "UTF-8").replaceAll("\\+", "// ") + "\"");
//                    //IE 11 이상
//                } else if (client.indexOf("Trident") != -1) {
//                    response.setHeader("Content-Disposition", "attachment; filename=\""
//                            + java.net.URLEncoder.encode(server_file_name, "UTF-8").replaceAll("\\+", "\\") + "\"");
//                    //한글 파일명 처리
//                } else {
//                    response.setHeader("Content-Disposition", "attachment; filename=\"" +
//                            new String(server_file_name.getBytes("UTF-8"), "ISO8859_1") + "\"");
//                    response.setHeader("Cotent-Type", "application/octet-stream; charset-utf-8");
//                }

                response.setHeader("Content-Length", "" + file.length());
                os = response.getOutputStream();
                byte b[] = new byte[(int) file.length()];
                int leng = 0;

                while ((leng = in.read(b)) > 0) {
                    os.write(b, 0, leng);
                }
            } else {
                response.setContentType("text/html; charset=UTF-8");
                PrintWriter out = response.getWriter();
                out.println("<script> alert('파일을 찾을 수 없습니다.'); history.back(); </script>");
                out.flush();
            }

            in.close();
            os.close();
        } catch (Exception e) {
            System.out.println("ERROR : " + e.getStackTrace());
        }

    }

}
