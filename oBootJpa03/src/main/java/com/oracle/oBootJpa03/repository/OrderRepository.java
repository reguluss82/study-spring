package com.oracle.oBootJpa03.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.oracle.oBootJpa03.domain.Delivery;
import com.oracle.oBootJpa03.domain.Order;
import com.oracle.oBootJpa03.domain.item.OrderItem;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OrderRepository {
	private final EntityManager em;

	public void saveDelivery(Delivery delivery) {
		em.persist(delivery);
	}

	public void saveOrder(Order order) {
		em.persist(order);
	}

	public void saveOrderItem(OrderItem orderItem) {
		em.persist(orderItem);
		
	}

	public List<Order> findAll() {
		return em.createQuery("select o from Order o", Order.class)
				.getResultList();
	}
}
