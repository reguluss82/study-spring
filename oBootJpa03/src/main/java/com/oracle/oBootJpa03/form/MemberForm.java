package com.oracle.oBootJpa03.form;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberForm {
	@NotEmpty // 화면단을 위해 분리해서 관리.
	private String name;
	private String city;
	private String street;
	private String zipcode;
}
