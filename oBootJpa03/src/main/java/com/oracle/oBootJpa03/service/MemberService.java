package com.oracle.oBootJpa03.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oracle.oBootJpa03.domain.Member;
import com.oracle.oBootJpa03.repository.InterMemberRepository;
import com.oracle.oBootJpa03.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
	private final MemberRepository memberRepository;
	private final InterMemberRepository interMemberRepository;
//	@Autowired // 생성자 Injection 많이 사용, 생성자가 하나면 생략 가능
//	public MemberService(MemberRepository memberRepository) {
//		this.memberRepository = memberRepository;
//	}
	
	// Entity Manager 사용
	public Long memberSave(Member member) {
		memberRepository.memberSave(member);
		return member.getId();
	}
	// JpaRepository 활용
	public Long Save(Member member) {
		interMemberRepository.save(member);
		return member.getId();
	}
	// Entity Manager 사용
	public List<Member> findMembers() {
		List<Member> memberList = memberRepository.findAll();
		return memberList;
	}
	
	// JpaRepository 활용
	public List<Member> findAll() {
		List<Member> listMember = interMemberRepository.findAll();
		return listMember;
	}
}
