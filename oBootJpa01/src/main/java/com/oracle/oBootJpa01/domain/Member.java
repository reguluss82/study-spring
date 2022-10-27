package com.oracle.oBootJpa01.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "member1")
public class Member {
	@Id // (identifier)PK 지정해준다.
	private Long   id;
	private String name;
}
