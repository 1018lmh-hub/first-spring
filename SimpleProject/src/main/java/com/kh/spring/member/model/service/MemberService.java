package com.kh.spring.member.model.service;

import com.kh.spring.member.model.dto.MemberDto;

// 현업에서 많이 사용되는 안티패턴
//(이상적인 구조이나 실제로 현업에서 사용해봤을 때 서비스 단을 수정할 일이 거의 없다.
// 그런 일은 보통 새로운 프로젝트나 고도화 작업을 하기 때문에)
public interface MemberService {
	
	void login(MemberDto member);

}
