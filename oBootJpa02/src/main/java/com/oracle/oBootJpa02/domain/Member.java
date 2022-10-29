package com.oracle.oBootJpa02.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

@Entity
@Data
@SequenceGenerator(name = "member_seq_gen",                // Object SEQ
					sequenceName = "member_seq_generator", // DB SEQ
					initialValue = 1,
					allocationSize = 1
					)
@Table(name = "member2")
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
					generator = "member_seq_gen"
					)
	@Column(name = "member_id", precision = 10) //DB 는 대소문자 구별없으므로 "_"로 단어구분
	private Long   id; //seq 쓸 생각이면 Long
	@Column(name = "username", length = 50)
	private String name;
	private Long   sal;
	
	// 관계 설정
	@ManyToOne
	@JoinColumn(name = "team_id")
	private Team team;
	
	@Transient // 특정 필드를 컬럼에 매핑하지 않음(매핑 무시)
	private String teamname; // 실제 Column 아님. buffer 저장 용도

	@Transient // 특정 필드를 컬럼에 매핑하지 않음(매핑 무시)
	private Long teamid; // 실제 Column 아님. buffer 저장 용도
	
}
