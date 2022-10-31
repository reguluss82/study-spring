package com.oracle.oBootJpa03.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "member3")
public class Member {
	@Id
	@Column(name = "member_id")
	private Long	id;
	private String	name;
	@Embedded
	private Address address;

	// Order Entity의 member field에 의해서 mapper 당함 --> 읽기 전용, DB에 반영되는것 아님. 버퍼에서 잠시 쓰는것
	@OneToMany(mappedBy = "member")
	private List<Order> orders = new ArrayList<>();

}
