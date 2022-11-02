package com.oracle.oBootJpa03.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oracle.oBootJpa03.domain.Delivery;
import com.oracle.oBootJpa03.domain.DeliveryStatus;
import com.oracle.oBootJpa03.domain.Member;
import com.oracle.oBootJpa03.domain.Order;
import com.oracle.oBootJpa03.domain.item.Item;
import com.oracle.oBootJpa03.domain.item.OrderItem;
import com.oracle.oBootJpa03.repository.InterItemRepository;
import com.oracle.oBootJpa03.repository.InterMemberRepository;
import com.oracle.oBootJpa03.repository.InterOrderRepository;
import com.oracle.oBootJpa03.repository.ItemRepository;
import com.oracle.oBootJpa03.repository.MemberRepository;
import com.oracle.oBootJpa03.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {
	private final OrderRepository  orderRepository;
	private final MemberRepository memberRepository;
	private final ItemRepository   itemRepository;
	private final InterMemberRepository interMemberRepository;
	private final InterItemRepository   interItemRepository;
	private final InterOrderRepository  interOrderRepository;
	
	// 1. Delivery Save
	// 2. Order Save
	// 3. OrderItem Save
	// 4. 재고 고려 안함, OrderItem은 하나만 처리
	@Transactional
	public void order(Long memberId, Long itemId, int count) {
		// Entity 조회
		//Member member = memberRepository.findOne(memberId);
		//Item item = itemRepository.findOne(itemId);
		Member member = interMemberRepository.findById(memberId).get(); //조회 하는순간 영속성관리에 들어간다.
		Item   item   = interItemRepository.findById(itemId).get();
		
		// 1. 배송정보 생성(Member 주소 배송 가정)
		Delivery delivery = new Delivery();
		delivery.setAddress(member.getAddress());
		delivery.setStatus(DeliveryStatus.READY);
		
		// 배송정보 저장
		//orderRepository.saveDelivery(delivery);
		interOrderRepository.save(delivery);
		
		// 2-1. 주문 생성
		Order order = Order.createOrder(member, delivery); // domain.Order 에서 static으로 선언한 method createOrder
		// 2-2. 주문 저장
		//orderRepository.saveOrder(order);
		interOrderRepository.save(order);
		
		// 3. 주문상품 생성
		//OrderItem orderItem = new OrderItem();
		//orderItem.set..........
		//연관관계 Mapping 처리 위해
		OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count); // DDD
		orderItem.setOrder(order); // DDD 가능하지만 꺼내서 보여준것.
		//orderRepository.saveOrderItem(orderItem);
		interOrderRepository.save(orderItem);
	}

	public List<Order> findOrders() {
		//return orderRepository.findAll();
		return interOrderRepository.findAll();
	}
}
