package com.oracle.oBootJpa02;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.oracle.oBootJpa02.domain.Member;
import com.oracle.oBootJpa02.repository.MemberRepositroy;
import com.oracle.oBootJpa02.service.MemberService;
// @SpringBootTest : 스프링 부트 띄우고 테스트(이게 없으면 @Autowired 다 실패)
//반복 가능한 테스트 지원, 각각의 테스트를 실행할 때마다 트랜잭션을 시작하고 
//*      테스트가 끝나면 트랜잭션을 강제로 롤백 (이 어노테이션이 테스트 케이스에서 사용될 때만 롤백)
//module별로 Test 가능
@SpringBootTest
@Transactional
public class MemberServiceTest {
	@Autowired //field injection
	MemberService memberService;
	@Autowired
	MemberRepositroy memberRepositroy;
	@BeforeEach
	public void before1() {
		System.out.println("Test ");
	}
	@Test
	@Rollback(value = false)
	public void memberSave() {
		// 조건
		// 회원저장
		Member member = new Member();
		member.setTeamname("고려");
		member.setName("강감찬");
		// 수행
		Member member3 = memberService.memberSave(member);
		// 결과
		System.out.println("MemberServiceTest memberSave member3.getId() -> " + member3.getId());
		System.out.println("MemberServiceTest memberSave member3.getName() -> " + member3.getName());
		System.out.println("MemberServiceTest memberSave member3.getTeam().getName() -> " + member3.getTeam().getName());
	}

	@Test
	@Rollback(value = false)
	public void memberFind() {
		// 조건
		// 회원조회 --> 이순신
		Long findId = 2L;
		// 수행
		Member member = memberService.findByMember(findId);
		// 결과
		System.out.println("MemberServiceTest memberSave member.getName() -> " + member.getName());
	}
}
