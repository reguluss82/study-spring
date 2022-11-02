package com.oracle.oBootJpa03.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.oracle.oBootJpa03.domain.Member;

@Repository
public class MemberRepository{
	private final EntityManager em;
	@Autowired
	public MemberRepository(EntityManager em) {
		this.em = em;
	}
	
	public void memberSave(Member member) {
		em.persist(member);
	}
	
	// 현실
	public List<Member> findAll() {
		return em.createQuery("select m from Member m", Member.class).getResultList();
	}
	
	// 기초
	public List<Member> findAll2() {
		List<Member> memberList = em.createQuery("select m from Member m", Member.class).getResultList();
		return memberList;
	}
	
	public Member findOne(Long id) {
		return em.find(Member.class, id);
	}
}
