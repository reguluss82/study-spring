package com.oracle.oBootJpa03.domain;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Delivery {
	@Id
	@Column(name = "delivery_id")
	@GeneratedValue
	private Long id;
	@Embedded  //값 타입을 사용하는 곳에서 표시
	private Address address;
	@Enumerated(EnumType.STRING)   // Must STRING
	private DeliveryStatus status; // READY(준비), COMPLETE(배송)
}
