package com.oracle.oBootJpa03.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oracle.oBootJpa03.domain.Member;

public interface InterMemberRepository extends JpaRepository<Member, Long>{
	List<Member> findAll();
	
}
