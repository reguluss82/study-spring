package com.oracle.oBootJpaApi01.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Data;
// entity 는 순수 Table 관련 setting만 하는게 좋다.
@Entity
@Data
@Table(name = "member5")
@SequenceGenerator(name = "member5_seq_gen",
					sequenceName = "member5_seq_generator",
					initialValue = 1,
					allocationSize = 1)
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
					generator = "member5_seq_gen")
	@Column(name = "member_id")
	private Long   id;
//	@NotEmpty //직접 화면이나 API 자체를 위한 entity setting 금지
	@Column(name = "userName")
	private String name;
	private Long   sal;
//	@ManyToOne(fetch = FetchType.LAZY) //오류
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "team_id")
	private Team team;
	
}
