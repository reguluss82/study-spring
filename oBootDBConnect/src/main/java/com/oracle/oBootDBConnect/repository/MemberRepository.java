package com.oracle.oBootDBConnect.repository;

import java.util.List;

import com.oracle.oBootDBConnect.domain.Member1;

// interface를 만들어서 구현체들 관리 , 구현체에 @repository 추가,삭제를 통해 부품화
public interface MemberRepository {
	Member1 save(Member1 member1);
	List<Member1> findAll();
}
