package com.oracle.oBootJpa02.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.oracle.oBootJpa02.domain.Member;
import com.oracle.oBootJpa02.domain.Team;

@Repository
public class JpaMemberRepository implements MemberRepositroy {
	private final EntityManager em;
	@Autowired
	public JpaMemberRepository(EntityManager em) {
		this.em = em;
	}
	
	@Override
	public Member memberSave(Member member) {
		// 팀 저장
		Team team = new Team();
		team.setName(member.getTeamname());
		em.persist(team);
		// 회원 저장
		member.setTeam(team);
		em.persist(member);
		
		return member;
	}
	
	@Override
	public List<Member> findAll() {
		List<Member> memberList = em.createQuery("select m from Member m", Member.class).getResultList();
		return memberList;
	}
	
	@Override
	public Member findByMember(Long id) {
		Member member = em.find(Member.class, id);
//		Member member = em.createQuery("select m from Member m where id = :id", Member.class)
//							.setParameter("id", id)
//							.getSingleResult();
		return member;
	}
	
	@Override
	public int updateByMember(Member member) {
		int result = 0;
		Member member3 = em.find(Member.class, member.getId());
		if (member3 != null) {
			// 팀 저장
			Team team = em.find(Team.class, member.getTeamid());
			if (team != null) {
				team.setName(member.getTeamname());
				em.persist(team);
			}
			//회원저장
			member3.setTeam(team);             // 단방향 연관관계 설정, 참조 저장
			member3.setName(member.getName()); // 단방향 연관관계 설정, 참조 저장
			em.persist(member3);
			result = 1;
		} else {
			result = 0;
		}
		return result;
	}

	@Override
	public List<Member> findByNames(String searchName) {
		String pname = searchName + '%';
		List<Member> memberList = em.createQuery("select m from Member m where name Like :name" , Member.class)
									.setParameter("name", pname)
									.getResultList();
		return memberList;
	}

	// 검색조건 : 입력한 id보다 큰것, sal보다 큰것
	@Override
	public List<Member> findByMembers(Long id, Long sal) {
		List<Member> memberList2 = em.createQuery("select m from Member m where id > :id and sal > :sal" , Member.class)
									.setParameter("id", id)
									.setParameter("sal", sal)
									.getResultList();
		return memberList2;
	}
	
}
