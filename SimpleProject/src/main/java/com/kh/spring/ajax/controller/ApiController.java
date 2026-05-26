package com.kh.spring.ajax.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.spring.ajax.model.service.ApiService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

//@Controller
@RestController //@Controller 와 @ResponseBody 를 동시에 포함
@Slf4j
@RequestMapping(value="api", produces="application/json; charset=UTF-8")
@RequiredArgsConstructor
public class ApiController {

	private final ApiService apiService;
	
	//@ResponseBody
	//@GetMapping(value="api/blog", produces="application/json; charset=UTF-8")
	@GetMapping("/blog")
	public String searchBlog(@RequestParam(value="query") String query) {
		String res = apiService.request(query);
		return res;
	}
	//@ResponseBody
	//@GetMapping(value="api/foods", produces="application/json; charset=UTF-8")
	@GetMapping("/foods")
	public String searchFoods(@RequestParam(value="line") String line,
			                  @RequestParam(value="page") String page) {
		String res = apiService.searchFoods(line, page);
		return res;
	}
	
	@GetMapping("/service")
	public String searchServiceArea(@RequestParam(value="service") String service) {
		return apiService.searchServiceArea(service);
	}
	
	/*
	 * REST(REpresentaional State Transfer)
	 * 
	 * HTTP프로토콜을 활용한 구조(아키텍쳐) 스타일 중 하나 => 굉장히 잘나감
	 * 
	 * 자원(Resource)중심의 URL구조 + 상태없음 (Stateless)통신
	 * 
	 * GET		/boards					=>게시글목록 조회
	 * GET		/boards/19				=> 게시글들 중 19번 게시글 조회
	 * GET		/boards/photo/19		=> 게시글들 중 사진게시글들 중 19번째 게시글 조회
	 * 
	 * POST     /boards        			=> 새 게시글 작성
	 * PUT		/boards/19				=> 19번 게시글 전체 수정
	 * DATCH	/boards/19				=> 19번 게시글 부분수정
	 * DELETE	/boards19				=> 19번 게시글 삭제
	 * 
	 * HTTP 상태코드
	 * 
	 * 200 OK					=>요청이 성공적으로 잘 이루어졌음
	 * 201 Created				=>요청에 의해 데이터가 잘 생성됨
	 * 400 Bad Request			=> 잘못된 요청
	 * 401 Unauthorized			=> 인증 실패(로그인 안됨 / 서비스키가 없다.)
	 * 404 Not Found			=> 없음
	 * 405 Method Not Allowed	=> Method 잘못보냄
	 * 500 Internal Error		=> 서버터짐
	 */
	
}
