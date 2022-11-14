package com.oracle.oBootMybatis01.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.oracle.oBootMybatis01.domain.Member;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MemberJpaRepositoryImpl implements MemberJpaRepository {
	private final EntityManager em;
	
	@Override
	public Member save(Member member) {
		em.persist(member);
		return member;
	}

	@Override
	public List<Member> findAll() {
		return em.createQuery("select m from Member m", Member.class)
				 .getResultList();
	}

	@Override
	public Optional<Member> findById(Long id) {
		return Optional.ofNullable(em.find(Member.class, id));
	}

	@Override
	public void updateByMember(Member member) {
		// 1. 영속성 관리 X -> setter 저장 불가, merge사용해야함
		// 2. merge         -> merge 할때 setting된 값만 수정, 나머지는 null
		em.merge(member);
	}

}
