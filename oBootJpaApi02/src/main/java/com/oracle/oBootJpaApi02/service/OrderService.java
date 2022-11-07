package com.oracle.oBootJpaApi02.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oracle.oBootJpaApi02.domain.Order;
import com.oracle.oBootJpaApi02.dto.SimpleOrderDto;
import com.oracle.oBootJpaApi02.dto.api.OrderDto;
import com.oracle.oBootJpaApi02.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {
	private final OrderRepository orderRepository;

	public List<SimpleOrderDto> serviceOrdersV21() {
		List<Order> orders = orderRepository.findAll();
		List<SimpleOrderDto> result = orders.stream()
											.map(o -> new SimpleOrderDto(o))
											.collect(Collectors.toList());
		return result;
	}

	public List<OrderDto> serviceOrdersV22() {
		List<Order> orders = orderRepository.findAll();
		List<OrderDto> result = orders.stream()
										.map(o -> new OrderDto(o))
										.collect(Collectors.toList());
		return result;
	}
	
	// Entity를 조회해서 DTO로 변환(fetch join 사용O)
	// fetch join으로 쿼리 1번 호출
	// 많은 Query로 인ㅇ한 성능 저하 개선
	public List<SimpleOrderDto> serviceOrdersV31() {
		List<Order> orders = orderRepository.findAllWithItem();
		return orders.stream().map(o -> new SimpleOrderDto(o))
					          .collect(Collectors.toList());
	}

	public List<OrderDto> serviceOrdersV32() {
		List<Order> orders = orderRepository.findAllWithItem();
		return orders.stream().map(o -> new OrderDto(o))
					 		  .collect(Collectors.toList());
	}

}
