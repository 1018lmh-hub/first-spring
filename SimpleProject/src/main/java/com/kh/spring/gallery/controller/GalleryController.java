package com.kh.spring.gallery.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kh.spring.gallery.model.service.GalleryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("galleries")
@RequiredArgsConstructor
public class GalleryController {
	
	private final GalleryService galleryService;
	
	@GetMapping
	public ModelAndView findAll(ModelAndView mv, @RequestParam(name="page", defaultValue="1") int page) {
		
		Map<String, Object> map = galleryService.findAll(page);
		
		mv.addObject("map",map).setViewName("gallery/galleries");
		return mv;
	}
	
	@GetMapping("/form")
	public String toForm() {
		return "gallery/form";
	}
	



}
