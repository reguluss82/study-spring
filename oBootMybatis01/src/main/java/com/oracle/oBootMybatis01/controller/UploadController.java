package com.oracle.oBootMybatis01.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class UploadController {

	// upLoadForm 시작 화면
	@RequestMapping(value = "upLoadFormStart")
	public String upLoadFormStart(Model model) {
		return "upLoadFormStart";
	}
	
	@RequestMapping(value = "uploadForm" , method = RequestMethod.GET)
	public void uploadForm() {
		
	}
	
	@RequestMapping(value = "uploadForm" , method = RequestMethod.POST)
	public String uploadForm(HttpServletRequest request , MultipartFile file1 , Model model) throws IOException {
		// Servlet 상송 받지 못했을 때 realPath 불러 오는 방법
		String uploadPath = request.getSession().getServletContext().getRealPath("/upload/");
		
		log.info("originalName: " + file1.getOriginalFilename());
		log.info("size: " + file1.getSize());
		log.info("contentType: " + file1.getContentType());
		log.info("uploadPath: " + uploadPath);
		String savedName = uploadFile(file1.getOriginalFilename(), file1.getBytes(), uploadPath);
		log.info("savedName: " + savedName);
		model.addAttribute("savedName", savedName);
		
		return "uploadResult";
		
		
	}
	
	private String uploadFile(String originalName , byte[] fileData , String uploadPath) throws IOException {
		// Universally Unique IDentifier
		UUID uid = UUID.randomUUID();
		// requestPath = requestPath + "/resources/image";
		System.out.println("uploadPath -> " + uploadPath);
		// Directory 생성
		File fileDirectory = new File(uploadPath);
		if(!fileDirectory.exists()) {
			fileDirectory.mkdir();
			System.out.println("업로드용 폴더 생성: " + uploadPath);
		}
		
		String savedName = uid.toString() + "_" + originalName;
		log.info("savedName: " + savedName);
		File target = new File(uploadPath, savedName);
//		File target = new File(requestPath, savedName);
		// File Upload --> uploadPaht / UUID _ originalName
		FileCopyUtils.copy(fileData, target); // org.springframework.util.FileCopyUtils
		
		return savedName;
	}
	
	@RequestMapping(value = "uploadFileDelete" , method = RequestMethod.GET)
	public String uploadFileDelete(HttpServletRequest request, Model model) throws Exception {
		String uploadPath = request.getSession().getServletContext().getRealPath("/upload/");
		String deleteFile = uploadPath + "053ba1ff-c387-4822-9555-0c390aab8f25_화면 캡처 2022-09-20 184820.png";
		log.info("deleteFile: " + deleteFile);
		System.out.println("uploadFileDelete POST Start");
		int delResult = upFileDelete(deleteFile);
		log.info("deleteFile result -> " + delResult);
		model.addAttribute("deleteFile", deleteFile);
		model.addAttribute("delResult", delResult);
		return "uploadResult";
	}
	
	private int upFileDelete(String deleteFileName) throws Exception {
		int result = 0;
		log.info("upFileDelete result -> " + deleteFileName);
		File file = new File(deleteFileName);
		if(file.exists()) {
			if(file.delete()) {
				System.out.println("파일 삭제 성공");
				result = 1;
			}
			else {
				System.out.println("파일삭제 실패");
				result = 0;
			}
		}
		else {
			System.out.println("파일이 존재하지 않습니다.");
			result = -1;
		}
		return result;
	}
}
