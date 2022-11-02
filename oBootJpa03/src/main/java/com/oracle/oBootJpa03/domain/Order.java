package com.oracle.oBootJpa03.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "orders")
@Data
public class Order {
	@Id
	@GeneratedValue //따로 세팅하지 않으면 hibernate_seq 가 부여됨 
	@Column(name = "order_id")
	private Long id;
	
	// Member Entity의 member_id에 의해 연결
	// LAZY <- order table만 가져올때 order것만 가져온다. 
	// 연결된 객체(Member, delivery)까지 끌고 오지 않으므로 성능개선, 거의 대부분의 경우 LAZY
	// EAGER <- 같이 동시에 조회해야하는 경우에만 사용 거의 안쓴다고 봐도 무방
	@ManyToOne(fetch = FetchType.LAZY) 
	@JoinColumn(name = "member_id")
	private Member member;
	
	// 배송정보
	// Delivery Entity의 delivery_id에 의해 연결
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "delivery_id")
	private Delivery delivery;
	
	// 주문시간
	private LocalDateTime orderDate;
	
	// 주문상태[ORDER , CANCLE]
	@Enumerated(EnumType.STRING)
	private OrderStatus status;
	
	//==연관관계 메서드==//
	public void setMember(Member member) {
		this.member = member;
		member.getOrders().add(this); // member의 getter를 통해 (member 의 mappedBy 확인)
	}
	
	// DDD
	public static Order createOrder(Member member, Delivery delivery) {
		Order order = new Order();
		order.setMember(member);
		order.setDelivery(delivery);
		order.setStatus(OrderStatus.ORDER);
		order.setOrderDate(LocalDateTime.now());
		return order;
	}
}
