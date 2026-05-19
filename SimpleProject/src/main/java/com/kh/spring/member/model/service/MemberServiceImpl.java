package com.kh.spring.member.model.service;

import org.springframework.stereotype.Service;

import com.kh.spring.exception.InvalidParameterException;
import com.kh.spring.exception.TooLargeValueException;
import com.kh.spring.member.model.dao.MemberMapper;
import com.kh.spring.member.model.dto.MemberDto;
import com.kh.spring.member.model.vo.Member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl {

	//private static final Logger log = LoggerFactory.getLogger(MemberServiceImpl.class);
	// @Slf4j 에노테이션을 달면 로깅 기능 수행, 위 코드를 수행함.
	
	//private final MemberDao memberDao;
	//private final SqlSessionTemplate sqlSession;
	
	private final MemberMapper memberMapper;
	private final PasswordEncoder passwordEncoder;

	/*
	@Autowired
	public MemberServiceImpl(MemberDao memberDao, SqlSessionTemplate sqlSession) {
		this.memberDao = memberDao;
		this.sqlSession = sqlSession;
	}
	*/
	/*
	 * 스프링의 거의 모든 기능은
	 * 
	 * "개발자가 직접하던 귀찮을 일을 컨테이너가 대신 해준다."
	 */
	

	
	
	

	public MemberDto login(MemberDto member) {
		
		MemberDto userInfo = memberMapper.login(member);
		
		
		//TRACE, DEBUG, INFO, WARN, ERROR
		//System.out.println("MemberServiceImpl");
		//log.info("인포 메소드로 출력 {}, {}", member, member);
		//Login -> ver_1
		/*
		 * SqlSession session = Template.getSqlSession();
		 * MemberDto loginMember = new MemberDao().login(Session,member);
		 * session.close();
		 * return loginMember;
		 * 
		 */
		
		//return memberDao.login(sqlSession, member);
		//return memberMapper.login(member);
		
		
		
		
		
		
		/*
		if(userInfo == null) {
			throw new InvalidParameterException("아이디 또는 비밀번호가 틀림");
		}
		//1절
		
		//log.info("사용자가 입력한 비밀번호 평문 : {}", member.getUserPwd());
		//log.info("DB에 저장된 암호화된 비밀번호 : {}", userInfo.getUserPwd());
		
		if(passwordEncoder.matches(member.getUserPwd(), userInfo.getUserPwd())) {
			return userInfo;
		}
		
		return null;
		*/
		
		return validateLoginMember(userInfo, member.getUserPwd());
	}
	
	private MemberDto validateLoginMember(MemberDto userInfo, String userPwd) {
		
		if(userInfo == null) {
			throw new InvalidParameterException("아이디 또는 비밀번호가 틀림");
		}
		//1절
		
		//log.info("사용자가 입력한 비밀번호 평문 : {}", member.getUserPwd());
		//log.info("DB에 저장된 암호화된 비밀번호 : {}", userInfo.getUserPwd());
		
		if(passwordEncoder.matches(member.getUserPwd(), userInfo.getUserPwd())) {
			return userInfo;
		}
		
		return null;
	}
	
	public void signup(MemberDto member) {
		
		validateMember(member);
		//memberDao.signup(sqlSession, member);
		/*
		if(member.getUserId().length() >30) {
			throw new TooLargeValueException("아이디 값이 너무 깁니다.");
		}
		if(member.getUserId() == null ||
		   member.getUserId().trim().isEmpty()||
		   member.getUserName() == null ||
		   member.getUserName().trim().isEmpty()|| 
		   member.getUserPwd() == null ||
		   member.getUserPwd().trim().isEmpty()) {
			throw new InvalidParameterException("유효하지 않은 값입니다.");
		}
		*/
		
		// 정보에 따른 암호화 분류체계
		
		// 비밀번호 => 반드시 암호화					  => 단방향 암호화
		//	주민등록번호, 계좌번호, 카드번호 => 반드시 암호화  => 양방향 암호화 => 주민등록번호 특히 규제가 아주아주 강력함
		// 이름, 이메일, 전화번호 => 그때 그때 달라요		  => 보통 안함 => 출력할 때 마스킹
		// 
		String plainPwd = member.getUserPwd();
		String encPwd = passwordEncoder.encode(plainPwd);
		
		
		
		//Member encMember = new Member(member.getUserId(), encPwd, member.getUserName(), member.getEmail(), null, null, null);
		Member encMember = Member.builder()
								 .userId(member.getUserId())
								 .userPwd(encPwd)
								 .userName(member.getUserName())
								 .email(member.getEmail())
								 .build();
		
		//log.info("{}의 암호문 : {}", plainPwd, encPwd);
//		memberMapper.signup(member);
		memberMapper.signup(encMember);
	}
	
	/*
	 * SRP(Single Responsibility Principle)
	 * 단일 책임 원칙
	 * 
	 * 하나의 클래스(메소드)는 하나의 책임만을 가져야한다. == 얘가 수정되는 이유는 오로지 딱 한가지여야함
	 */
	
	private void validateMember(MemberDto member) {
		checkLength(member);
		checkBlank(member);
	}
	private void checkLength(MemberDto member) {
		if(member.getUserId().length() >30) {
			throw new TooLargeValueException("아이디 값이 너무 깁니다.");
		}
	}
	
	private void checkBlank(MemberDto member) {
		if(member.getUserId() == null ||
				   member.getUserId().trim().isEmpty()||
				   member.getUserName() == null ||
				   member.getUserName().trim().isEmpty()|| 
				   member.getUserPwd() == null ||
				   member.getUserPwd().trim().isEmpty()) {
					throw new InvalidParameterException("유효하지 않은 값입니다.");
				}
	}
	
	
}
