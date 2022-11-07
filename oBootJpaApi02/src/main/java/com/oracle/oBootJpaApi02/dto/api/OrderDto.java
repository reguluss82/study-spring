package com.oracle.oBootJpaApi02.dto.api;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.oracle.oBootJpaApi02.domain.Address;
import com.oracle.oBootJpaApi02.domain.Order;
import com.oracle.oBootJpaApi02.domain.OrderStatus;

import lombok.Data;

@Data
public class OrderDto {
	private Long               orderId;
	private String             name;
	private LocalDateTime      orderDate;
	private OrderStatus        orderStatus;
	private Address            address;
	// 1 : M 관계 추가
	private List<OrderItemDto> orderItems;
	
	public OrderDto(Order order) {
		orderId     = order.getId();
		name        = order.getMember().getName();
		orderDate   = order.getOrderDate();
		orderStatus = order.getStatus();
		address     = order.getDelivery().getAddress();
		orderItems  = order.getOrderItems().stream()
											.map(orderItem -> new OrderItemDto(orderItem))
											.collect(Collectors.toList());
	}
}
