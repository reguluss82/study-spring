package com.oracle.oBootMybatis01.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oracle.oBootMybatis01.model.Dept;
import com.oracle.oBootMybatis01.model.SampleVO;
import com.oracle.oBootMybatis01.service.EmpService;

import lombok.RequiredArgsConstructor;

// @Controller + @ResponseBody
@RestController
@RequiredArgsConstructor
public class EmpRestController {
	private final EmpService es;
	
	@RequestMapping(value = "/helloText")
	public String helloText() {
		String hello = "안녕";
		return hello;
	}
	
	// http://jsonviewer.stack.hu/
	@RequestMapping("/sample/sendVO2")
	public SampleVO sendVO2(int deptno) {
		SampleVO vo = new SampleVO();
		vo.setFirstName("길동");
		vo.setLastName("홍");
		vo.setMno(deptno);
		return vo;
	}
	
	@RequestMapping(value = "/sendVO3")
	public List<Dept> sendVO3() {
		List<Dept> deptList = es.deptSelect();
		return deptList;
	}
}
