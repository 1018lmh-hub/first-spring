package com.kh.spring.fileupload;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.kh.spring.board.model.dto.BoardDto;

@Component
public class FileUpload {
	
	
	public void fileUpload(MultipartFile upfile, BoardDto board, HttpSession session) {
		if(!upfile.getOriginalFilename().isEmpty()) {
			//이름 바꾸기
			StringBuilder sb = new StringBuilder();
			sb.append("kh_");
			sb.append(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
			sb.append("_");
			sb.append((int)(Math.random()*900)+100);
			sb.append(upfile.getOriginalFilename().substring(upfile.getOriginalFilename().lastIndexOf(".")));
			
			//파일 업로드
			ServletContext application = session.getServletContext();
			String savePath = application.getRealPath("/resources/files/");
			
			try {
				upfile.transferTo(new File(savePath + sb.toString()));
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}

			//board setting
			board.setOriginName(upfile.getOriginalFilename());
			board.setChangeName("/spring/resources/files/" + sb.toString());
			
		}
	}

}
