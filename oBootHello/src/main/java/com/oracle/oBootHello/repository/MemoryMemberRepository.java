package com.oracle.oBootHello.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.oracle.oBootHello.domain.Member1;

// memory 에 저장하는 방법.
@Repository
public class MemoryMemberRepository implements MemberRepository {
	private static Map<Long, Member1> store = new HashMap<Long, Member1>();
	private static Long sequence = 0L; // 0L 0을 Long casting
	
	@Override
	public Member1 save(Member1 member1) {
		member1.setId(++sequence);
		store.put(member1.getId(), member1); //member1(객체) 로 value 잡아놓으면 확장성 용이
		System.out.println("MemoryMemberRepository sequence -> " + sequence);
		System.out.println("MemoryMemberRepository member1.getName() -> " + member1.getName());
		
		return member1;
	}

	@Override
	public List<Member1> findAll() {
		System.out.println("MemoryMemberRepository findAll start...");
		// store의 value(Member1)
		List<Member1> listMember = new ArrayList<>(store.values());
		System.out.println("MemoryMemberRepository findAll listMember.size() -> " + listMember.size());
		return listMember;
	}

}
