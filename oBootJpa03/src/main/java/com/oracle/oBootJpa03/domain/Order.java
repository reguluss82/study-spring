package com.oracle.oBootJpa03.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
	@Column(name = "order_id")
	private Long id;
	
	// Member Entity의 member_id에 의해 연결
	@ManyToOne
	@JoinColumn(name = "member_id")
	private Member member;
	
	// 배송정보
	// Delivery Entity의 delivery_id에 의해 연결
	@OneToOne
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
		member.getOrders().add(this);
	}
}
