package com.oracle.oBootJpa02.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.oracle.oBootJpa02.domain.Member;
import com.oracle.oBootJpa02.repository.MemberRepositroy;

@Service
@Transactional
public class MemberService {
	private final MemberRepositroy memberRepositroy;
	@Autowired
	public MemberService(MemberRepositroy memberRepositroy) {
		this.memberRepositroy = memberRepositroy;
	}
	
	public Member memberSave(Member member) {
		memberRepositroy.memberSave(member);
		return member;
	}
	
	public List<Member> getListAllMember() {
		List<Member> memberList = memberRepositroy.findAll();
		return memberList;
	}
	
	public Member findByMember(Long id) {
		Member member = memberRepositroy.findByMember(id);
		return member;
	}
	
	public void memberUpdate(Member member) {
		memberRepositroy.updateByMember(member);
		return; // service return과 동시에 sql 실행
	}

	public List<Member> getListSearchMember(String searchName) {
		System.out.println("MemberService getListSearchMember Start...");
		// String pSearchName = searchName + '%';
		System.out.println("MemberService getListSearchMember searchName -> " + searchName);
		List<Member> listMember = memberRepositroy.findByNames(searchName);
		System.out.println("MemberService getListSearchMember listMember.size() -> " + listMember.size());
		return listMember;
	}

	public List<Member> getListFindByMembers(Member member) {
		List<Member> listMember = memberRepositroy.findByMembers(member.getId(), member.getSal());
		System.out.println("MemberService getListFindByMembers listMember.size() -> " + listMember.size());
		return listMember;
	}
}
