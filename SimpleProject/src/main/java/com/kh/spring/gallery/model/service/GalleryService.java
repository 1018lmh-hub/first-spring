package com.kh.spring.gallery.model.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.spring.exception.InvalidParameterException;
import com.kh.spring.fileupload.FileUpload;
import com.kh.spring.gallery.model.dao.GalleryMapper;
import com.kh.spring.gallery.model.dto.GalleryDto;
import com.kh.spring.util.PageInfo;
import com.kh.spring.util.Pagenation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
//@Service
@RequiredArgsConstructor
public class GalleryService {
	
	private final GalleryMapper galleryMapper;
	private final Pagenation pagenation;
//	private final FileUpload fileUpload;
	

	@Transactional(readOnly = true)
	public Map findAll(int page) {
		
		
		if(page < 1) {
			throw new InvalidParameterException("잘못된 접근입니다.");
		}
		
		int totalCount = galleryMapper.selectTotalCount();
		
		
		PageInfo pi = pagenation.getPageInfo(totalCount, page, 5, 3);
		
		RowBounds rb = new RowBounds((page - 1) * 5 , 5);
		List<GalleryDto> galleries = galleryMapper.findAll(rb);
		
		
		return Map.of("galleries", galleries, "pi", pi);
		
		
	}
	
	

}
