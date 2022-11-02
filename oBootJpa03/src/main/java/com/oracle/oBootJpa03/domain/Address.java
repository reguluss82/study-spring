package com.oracle.oBootJpa03.domain;

import javax.persistence.Embeddable;

import lombok.Getter;
/*
 *  1. 값 타입은 변경 불가능하게 설계.
 *  2. @Setter 를 제거하고, 생성자에서 값을 모두 초기화해서 변경 불가능한 클래스를 만들기 권장 
 */
import lombok.ToString;

@Getter
@ToString
@Embeddable  //값 타입을 정의하는 곳에서 표시
public class Address {
	private String city;
	private String street;
	private String zipcode;
	
	protected Address() { //기본생성자 함부로 생성하지 못하게
	}
	
	public Address(String city, String street, String zipcode) {
		this.city    = city;
		this.street  = street;
		this.zipcode = zipcode;
	}

}
