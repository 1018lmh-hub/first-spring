package com.kh.spring.member.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.spring.member.model.dto.MemberDto;

@Repository// 이거 근데 저장소면 vo,dto 쪽이 좀 더 맞는 거 아닌가 저장소랑 연결되어있다는 뜻인가
public class MemberDao {
	
	public MemberDto login(SqlSessionTemplate sqlSession, MemberDto member) {
		return sqlSession.selectOne("memberMapper.login", member);
	}

	public void signup(SqlSessionTemplate sqlSession, MemberDto member) {
		sqlSession.insert("memberMapper.signup", member);
	}
	

}
