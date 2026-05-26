package com.kh.spring.gallery.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.kh.spring.gallery.model.dto.GalleryDto;

//@Mapper
public class GalleryMapper {

	public int selectTotalCount() {
		return 0;
	}
	

	public List<GalleryDto> findAll(RowBounds rb) {
		return null;
	}




}
