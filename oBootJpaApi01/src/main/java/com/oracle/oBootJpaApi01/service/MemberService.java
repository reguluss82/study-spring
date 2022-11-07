package com.oracle.oBootJpaApi01.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oracle.oBootJpaApi01.domain.Member;
import com.oracle.oBootJpaApi01.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
	private final MemberRepository memberRepository;
	
	// 전체회원 조회 API
	public List<Member> getListAllMember() {
		return memberRepository.findAll();
	}
	
	// 회원가입 API
	public Long saveMember(@Valid Member member) {
		Long id = memberRepository.save(member);
		return id;
	}
	
	// 회원 수정
	public void updateMember(Long id , String name , Long sal) {
		Member member = new Member();
		member.setId(id);
		member.setName(name);
		member.setSal(sal);
		memberRepository.updateByMember(member);
		return;
	}
	
	// 회원 조회
	public Member findByMember(Long memberId) {
		return memberRepository.findByMember(memberId);
	}
}
