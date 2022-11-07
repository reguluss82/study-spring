package com.oracle.oBootJpaApi02.dto;

import java.time.LocalDateTime;

import com.oracle.oBootJpaApi02.domain.Address;
import com.oracle.oBootJpaApi02.domain.Order;
import com.oracle.oBootJpaApi02.domain.OrderStatus;

import lombok.Data;

@Data
public class SimpleOrderDto {
	private Long          orderId;
	private String        name;
	private LocalDateTime orderDate;
	private OrderStatus   orderStatus;
	private Address       address;
	
	public SimpleOrderDto(Order order) {
		orderId     = order.getId();
		name        = order.getMember().getName();
		orderDate   = order.getOrderDate();
		orderStatus = order.getStatus();
		address     = order.getDelivery().getAddress();
	}
}
