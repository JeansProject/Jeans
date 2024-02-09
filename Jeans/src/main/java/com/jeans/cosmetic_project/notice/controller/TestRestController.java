package com.jeans.cosmetic_project.notice.controller;

import com.jeans.cosmetic_project.notice.dto.FileDto;
import com.jeans.cosmetic_project.notice.dto.LoginUser;
import com.jeans.cosmetic_project.notice.dto.NoticeDto;
import com.jeans.cosmetic_project.notice.dto.TestDto;
import com.jeans.cosmetic_project.notice.service.NoticeService;
import com.jeans.cosmetic_project.notice.service.TestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j

public class TestRestController {

    private final TestService testServiceImpl;
    private final NoticeService noticeServiceImpl;
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody TestDto loginUser) {
        log.info("loginUserId = {}", loginUser.getId());
        log.info("loginUserPassword = {}", loginUser.getPassword());

        LoginUser successLoginUser = testServiceImpl.login(loginUser);
        log.info("successLoginUserSeq = {}", successLoginUser.getSeq());
        log.info("successLoginUserId = {}", successLoginUser.getId());
        log.info("successLoginUserName = {}", successLoginUser.getName());

        return new ResponseEntity(successLoginUser, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody TestDto loginUser) {
        log.info("loginUserId = {}", loginUser.getId());
        log.info("loginUserPassword = {}", loginUser.getPassword());

        int registerResult = testServiceImpl.register(loginUser);
        log.info("result = {}", registerResult);

        return new ResponseEntity(registerResult, HttpStatus.OK);
    }


    @GetMapping("/noticeList")
    public ResponseEntity noticeList(){

        List<NoticeDto> data = new ArrayList();
        data = noticeServiceImpl.getNoticeList();
        return ResponseEntity.status(HttpStatus.OK).body(data);
    }


    @PostMapping(value = "/insertProc",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity fileNoticeInsertProc(@RequestPart(value = "data") NoticeDto notice, LoginUser loginUser, @RequestPart(value = "file") MultipartFile files, HttpSession session) throws IOException {
        int fileNoticeInsertProcResult = 0;
        noticeServiceImpl.noticeInsert(notice);
        if (files != null) {
            FileDto fileDto = changeFile(files, notice.getSeq(), loginUser);
            fileNoticeInsertProcResult = noticeServiceImpl.fileInsert(fileDto);
        }
        return new ResponseEntity(fileNoticeInsertProcResult, HttpStatus.OK);
    }

    @GetMapping("/notice/{seq}")
    private ResponseEntity noticeContent(@PathVariable("seq") Integer seq, HttpSession session)throws IOException {
//        Map<String, Object> result = new HashMap<>();
//        result.put("data", noticeServiceImpl.noticeContent(seq));
//        result.put("count", result.size());
//        if (noticeServiceImpl.fileNotice(seq) == null) {
//            return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
//        } else {
//            result.put("data", noticeServiceImpl.fileNotice(seq).getClient_file_name());
//            result.put("count", result.size());
        NoticeDto data = noticeServiceImpl.noticeContent(seq);
            return ResponseEntity.status(HttpStatus.OK).body(data);
        }

//    }

//    @GetMapping(value = "/notice/{seq}",
//            produces = {
//                    MediaType.APPLICATION_JSON_VALUE})
//    private ResponseEntity<Map<String, Object>> noticeContent(@RequestPart(value="seq") Integer seq, MultipartFile file, HttpSession session)throws IOException {
//        System.out.println("컨트롤러 탐");
//        Map<String, Object> result = new HashMap<>();
//        result.put("data", noticeServiceImpl.noticeContent(seq) + noticeServiceImpl.fileNotice(seq).getClient_file_name());
//        result.put("count", result.size());
//            return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
//    }

    @PutMapping(value = "/updateProc",
            consumes = {
                    MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    private ResponseEntity fileNoticeUpdate(@RequestPart(value = "data") NoticeDto notice, LoginUser loginUser, @RequestPart(value = "file", required = false) MultipartFile files, HttpSession session) throws IOException {
        int fileNoticeUpdateProcResult = 0;

        if(!notice.getFileFlag().isEmpty() && files !=null){
            FileDto fileDto = changeFile(files, notice.getSeq(), loginUser);
            noticeServiceImpl.fileUpdate(fileDto);
        }

        if(notice.getFileFlag().isEmpty()) {
            noticeServiceImpl.fileDelete(notice.getSeq());
        }

        if (files != null) {
            FileDto fileDto = changeFile(files, notice.getSeq(), loginUser);
            noticeServiceImpl.fileUpsert(fileDto);
        }
        fileNoticeUpdateProcResult = noticeServiceImpl.noticeUpdate(notice);


        return new ResponseEntity(fileNoticeUpdateProcResult, HttpStatus.OK);
    }


    @DeleteMapping("/delete/{seq}")
    private ResponseEntity noticeDelete (@PathVariable("seq") int seq){
        int noticeDeleteProcResult = 0;
        noticeServiceImpl.noticeDelete(seq);
        return new ResponseEntity(noticeDeleteProcResult, HttpStatus.OK);
    }

    public FileDto changeFile(MultipartFile files, int seq, LoginUser loginUser) throws IOException {
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

        FileDto file = new FileDto();
        file.setBoard_seq(seq);
        file.setClient_file_name(client_file_name);
        file.setServer_file_name(destinationFileName);
        file.setFile_path(file_path);
        file.setUser_seq(loginUser.getSeq());
        return file;//loginUser.session 세션으로 받기
    }

    @GetMapping ("/fileDown/{seq}")
    public void fileDown(@PathVariable("seq") int seq, HttpServletResponse response){
        try {
            FileDto fileDto = noticeServiceImpl.fileNotice(seq);
            if(ObjectUtils.isEmpty(fileDto) == false){
                String fileName = fileDto.getClient_file_name();
                byte[] files = FileUtils.readFileToByteArray(new File(fileDto.getFile_path() + "/" + fileDto.getServer_file_name()));

                response.setContentType("application/octet-stream");
                response.setContentLength(files.length);
                response.setHeader("Content-Disposition", "attachment; fileName=\" " + URLEncoder.encode(fileName, StandardCharsets.UTF_8)+"\";");
                response.setHeader("Content-Transfer-Encoding", "binary");

                response.getOutputStream().write(files);
                response.getOutputStream().flush();
                response.getOutputStream().close();

            }
        } catch (IOException e){
            log.error(e.getMessage());
            e.getStackTrace();
        }
    }
}
