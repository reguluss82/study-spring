package com.oracle.oBootJpa03.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oracle.oBootJpa03.domain.Delivery;
import com.oracle.oBootJpa03.domain.Order;
import com.oracle.oBootJpa03.domain.item.OrderItem;

public interface InterOrderRepository extends JpaRepository<Order, Long>{

	void save(Delivery delivery);

	void save(OrderItem orderItem);

}
