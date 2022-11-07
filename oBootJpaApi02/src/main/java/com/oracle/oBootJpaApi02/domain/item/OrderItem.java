package com.oracle.oBootJpaApi02.domain.item;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.oracle.oBootJpaApi02.domain.Order;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "order_item")
@SequenceGenerator(name = "order_item_seq",
					sequenceName = "order_item_sequence",
					initialValue = 1,
					allocationSize = 1)
public class OrderItem {
	protected OrderItem() {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
					generator = "order_item_seq")
	@Column(name = "order_item_id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;
	
	@ManyToOne
	@JoinColumn(name = "item_id")
	private Item item;
	
	private int orderPrice;
	
	private int count;
	
	// 생성 메서드( createOrderItem() ): 주문 상품, 가격, 수량 정보를 사용해서 주문상품 엔티티를 생성
	public static OrderItem createOrderItem(Item item , int orderPrice , int count) {
		OrderItem orderItem = new OrderItem();
		orderItem.setItem(item);
		orderItem.setOrderPrice(orderPrice);
		orderItem.setCount(count);
		// <- 필요시 재고 처리 부분
		// item.removeStock(count)
		return orderItem;
	}

}
