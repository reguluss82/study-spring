package com.oracle.oBootSecurity02.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Entity
@SequenceGenerator(name = "user_seq_gen",
					sequenceName = "user_seq_generator",
					initialValue = 1,
					allocationSize = 1)
@Data
@Table(name = "user01")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
					generator = "user_seq_gen") // generator 설정한 객체를 지정해야한다.
	private int       id;
	private String    username;
	private String    password;
	private String    email;
	private String    role; // ROLE_USER, ROLE_ADMIN, ROLE_MANAGER
	@CreationTimestamp
	private Timestamp createdate;
	//private Timestamp logindate; //로그인시간 저장하여 인증관리하기위함
}
