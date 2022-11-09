package com.oracle.oBootMybatis01.model;

import lombok.Data;

@Data
public class Emp {
	private int empno;
	private String ename;
	private String job;
	private String hiredate;
	private int sal;
	private int comm;
	private int mgr;
	private String deptno;
	
	// 조회용
	private String search;
	private String pageNum;
	private String keyword;
	private int start;
	private int end;
}
