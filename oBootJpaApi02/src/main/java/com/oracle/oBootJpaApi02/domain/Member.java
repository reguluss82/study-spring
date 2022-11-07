package com.oracle.oBootJpaApi02.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@SequenceGenerator(name =  "member3_seq_gen", 
					sequenceName = "member3_seq_generator", 
					initialValue = 1, 
					allocationSize = 1)
@Table(name = "member3")
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, 
					generator = "member3_seq_gen")
	@Column(name = "member_id")
	private Long	id;
	private String	name;
	@Embedded
	private Address address;

	// Order Entity의 member field에 의해서 mapper 당함 --> 읽기 전용, DB에 반영되는것 아님. (List)버퍼에서 잠시 쓰는것 , 조회하기 편하게
	@OneToMany(mappedBy = "member")
	private List<Order> orders = new ArrayList<>();

}
