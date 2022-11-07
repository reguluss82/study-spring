package com.oracle.oBootJpaApi01.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.oracle.oBootJpaApi01.domain.Member;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class JpaMemberRepository implements MemberRepository {
	private final EntityManager em;
	
	@Override
	public Long save(Member member) {
		em.persist(member);
		return member.getId();
	}

	@Override
	public List<Member> findAll() {
		return em.createQuery("select m from Member m", Member.class).getResultList();
	}

	@Override
	public int updateByMember(Member member) {
		int result = 0;
		Member member3 = em.find(Member.class, member.getId());
		if (member3 != null) { //영속성관리에 의해 update된다.
			member3.setName(member.getName());
			member3.setSal(member.getSal());
			result = 1;
		} else {
			result = 0;
			System.out.println("JpaMemberRepository updateByMember No Exist.");
		}
		return result;
	}

	@Override
	public Member findByMember(Long memberId) {
		return em.find(Member.class, memberId);
	}
	

}
