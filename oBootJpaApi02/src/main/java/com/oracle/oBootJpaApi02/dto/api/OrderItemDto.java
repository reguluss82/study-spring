package com.oracle.oBootJpaApi02.dto.api;

import com.oracle.oBootJpaApi02.domain.item.OrderItem;

import lombok.Data;
@Data
public class OrderItemDto {
	private String itemName;
	private int orderPrice;	
	private int count;
	
	public OrderItemDto(OrderItem orderItem) {
		itemName   = orderItem.getItem().getName();
		orderPrice = orderItem.getOrderPrice();
		count      = orderItem.getCount();
	}
}
