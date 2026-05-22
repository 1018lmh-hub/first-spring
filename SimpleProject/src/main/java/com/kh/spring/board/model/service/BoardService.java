package com.kh.spring.board.model.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.kh.spring.board.model.dao.BoardMapper;
import com.kh.spring.board.model.dto.BoardDto;
import com.kh.spring.exception.AuthorizationException;
import com.kh.spring.exception.InvalidParameterException;
import com.kh.spring.fileupload.FileUpload;
import com.kh.spring.member.model.dto.MemberDto;
import com.kh.spring.util.PageInfo;
import com.kh.spring.util.Pagenation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService {
	
	
	private final BoardMapper boardMapper;
	private final Pagenation pagenation;
	private final FileUpload fileUpload;
	
	
	/*
	 * 1. @Transactional은 public 메서드에서만 동작
	 * 
	 * 2. 같은 클래스 안에서 메서드끼리 호출하면 트랜젝션 안걸림
	 * 
	 * 3. CheckedException은 기본적으로 롤백 안됨(롤백 시키고 싶으면 RuntimeException)
	 * 
	 * 4. 의존성 이슈
	 * 
	 */
	
	// 안전장치 X => 성능 최적화 + 의도표시
	@Transactional(readOnly = true)// DB작업할 때 이 트랜젝션은 데이터 변경안합니다. => 힌트, 성능 향상
	// 근데 readOnly 달고 안에 DML 넣으면 버전마다 다르게 문제가 생긴다.
	public Map findAll(int page) {
		
		//유효성 검증
		if(page < 1) {
			throw new InvalidParameterException("잘못된 접근입니다.");
		}
		
		// 실질적인 비즈니스 로직 => 페이징 처리를 위한  PageInfo객체 생성 및 페이징 처리 후 게시글 조회
		
		int totalCount = boardMapper.selectTotalCount();
		
		//log.info("총 게시글 개수 : {}", totalCount);
		PageInfo pi = pagenation.getPageInfo(totalCount, page, 5, 3);
		
		RowBounds rb = new RowBounds((page - 1) * 5 , 5); // 실제로 페이징 처리 시에는 SQL문에서 해결(OFFSET문법)하는 것을 권장
		List<BoardDto> boards = boardMapper.findAll(rb);
		//log.info("게시글 목록 : {}", boards);
		
//		Map map = Map.of("boards", boards, "pi", pi);
//		return map;
		
		return Map.of("boards", boards, "pi", pi);
		
		
	}
	
	@Transactional // <tx:annotation-driven이 켜지면 스프링이 @Transactional이 붙은 메서드를 발견해서 프록시로 감쌈
	//프록시 객체 내부에서 save()메서드를 호출할 때 connection.setAutoCommit(false)로 돌리고 호출
	//그 후 메서드 종료시 commit()을 호출 예외 발생 시 rollback()
	public void save(BoardDto board, MultipartFile upfile, HttpSession session) {
		//1. 권한검증
		validateUser(board, session);
		
		//2. 유효성 검증(책임분리 시키기 숙)
		validateTitle(board);
		validateContent(board);
		
		//3. 파일이 있을 경우 이름을 바꿔서 서버에 업로드 => 파일의 정보를 board의 필드에 대입
		//(클래스로 분리쓰 숙)
		fileUpload.fileUpload(upfile, board,session);
		
		//4. 
		int result = boardMapper.save(board);
		
		if(result != 1) {
			throw new RuntimeException("관리자에게 문의하세요");
		}
		
		
	}

	private void validateUser(BoardDto board, HttpSession session) {
		String boardWriter =board.getBoardWriter();
		MemberDto userInfo =(MemberDto)session.getAttribute("userInfo");
		
		if(userInfo == null || !userInfo.getUserId().equals(boardWriter)) {
			throw new AuthorizationException("권한없는 요청입니다.");
		}
	}
	
	private void validateTitle(BoardDto board) {
		if(board.getBoardTitle().trim().isEmpty()) {
			throw new InvalidParameterException("유효하지 않은 요청입니다.");
		}
		String boardTitle = board.getBoardTitle().replaceAll("<", "&lt;").replaceAll("\n", "<br>");
		
		
		if(board.getBoardTitle().contains("?")) {
			boardTitle = board.getBoardTitle().replace("?", "오잉");
		}
		
		board.setBoardTitle(boardTitle);
		
		
	}
	
	private void validateContent(BoardDto board) {
		if(board.getBoardContent().trim().isEmpty()) {
			throw new InvalidParameterException("유효하지 않은 요청입니다.");
		}
	
		String boardContent = board.getBoardContent().replaceAll("<", "&lt;").replaceAll("\n", "<br>");
		
		if(board.getBoardTitle().contains("?")) {
			boardContent = board.getBoardContent().replace("?", "오잉");
		}
		
		
		board.setBoardContent(boardContent);
		
	}
	
	
	
	public BoardDto findByBoardNo(Long boardNo) {
		if(boardNo < 1 || boardNo == null) {
			throw new InvalidParameterException("유효하지 않은 값입니다.");
		}			  
		
		int result = boardMapper.increaseCount(boardNo);
		
		if(result != 1) {
			throw new InvalidParameterException("잘못된 요청입니다.");
		}
		
		BoardDto board = boardMapper.findByBoardNo(boardNo);
		
		if(board == null) {
			throw new InvalidParameterException("존재하지 않는 게시글입니다.");
		}
		
		return board;
	}
	
	public Map<String,Object> findByKeyword(String condition, String keyword, int page) {
		
		//사용자가 선택한 condition 과 사용자가 입력한 keyword를 가지고
		//DB창에서 조건을 걸어 검색해서 나온 조회 결과물을
		// 페이징 처리까지 끝내고 난 뒤 페이징 객체와 함께 응답할 것
		
		
		
		int searchedCount = boardMapper.findByKeywordCount(condition, keyword);
		
		//log.info("검색결과 개수 : {}", searchedCount);
		
		PageInfo pi =pagenation.getPageInfo(searchedCount, page, 3, 3);
		RowBounds rb = new RowBounds((page-1)*3, 3);
		
		List<BoardDto> boards = boardMapper.findByKeyword(condition, keyword, rb);
		//log.info("검색결과 : {}", boards);
		
		return Map.of("boards", boards, "pi", pi);
		
	}

	public int count() {
		int totalCount = boardMapper.selectTotalCount();
		return totalCount;
	}
	

}
