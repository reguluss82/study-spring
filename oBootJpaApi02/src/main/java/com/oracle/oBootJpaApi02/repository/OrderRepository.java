package com.oracle.oBootJpaApi02.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.oracle.oBootJpaApi02.domain.Order;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OrderRepository {
	private final EntityManager em;

	public List<Order> findAll() {
		return em.createQuery("select o from Order o" , Order.class)
				 .getResultList();
	}
	// Lazy JPA(fetch Join)
	// Query 1번만 실행됨 -> 성능 향상
	// 단점 : Paging 불가능 -> query dsl 또는 myBatis 사용
	// 객체관점으로 size가 결정됨.
	public List<Order> findAllWithItem() {
		return em.createQuery("select distinct o from Order o "
							+ " join fetch o.member m "
							+ " join fetch o.delivery d "
							+ " join fetch o.orderItems oi "
							+ " join fetch oi.item i ", Order.class).getResultList();
	}
}
