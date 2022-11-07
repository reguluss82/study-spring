package com.oracle.oBootJpaApi02.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oracle.oBootJpaApi02.dto.SimpleOrderDto;
import com.oracle.oBootJpaApi02.dto.api.OrderDto;
import com.oracle.oBootJpaApi02.service.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class OrderRestApiController {
	
	private final OrderService orderService;
	
	// M : 1 Entity를 조회해서 DTO로 변환(fetch join 사용X)
	// 단점 : 관련 쿼리 N번 호출
	@GetMapping("/restApi/ordersV21")
	public List<SimpleOrderDto> ordersV21() {
		return orderService.serviceOrdersV21();
	}
	
	//M:1 & 1:M Entity 포함, 조회해서 DTO변환
	@GetMapping("/restApi/ordersV22")
	public List<OrderDto> ordersV22() {
		return orderService.serviceOrdersV22();
	}
	
	@GetMapping("/restApi/ordersV31")
	public List<SimpleOrderDto> ordersV31() {
		return orderService.serviceOrdersV31();
	}

	@GetMapping("/restApi/ordersV32")
	public List<OrderDto> ordersV32() {
		return orderService.serviceOrdersV32();
	}
	
	
}
