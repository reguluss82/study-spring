package com.oracle.oBootMybatis01.model;

import lombok.Data;

// Procedure VO
@Data
public class DeptVO {
	// 입력
	private int    deptno;
	private String dname;
	private String loc;
	// 출력
	private int    odeptno;
	private String odname;
	private String oloc;
}
