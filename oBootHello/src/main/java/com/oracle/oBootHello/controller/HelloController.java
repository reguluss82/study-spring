package com.oracle.oBootHello.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oracle.oBootHello.domain.Emp;

@Controller
public class HelloController {
	private static final Logger logger = LoggerFactory.getLogger(HelloController.class);
	
	// Prefix -> templates
	// suffix -> .html
	@RequestMapping("hello")
	public String hello(Model model) {
		logger.info("start...");
		model.addAttribute("parameter", "boot start...");
		return "hello";
	}
	
	@ResponseBody //호출한 곳의 body로 response
	@GetMapping("ajaxString")
	public String ajaxString(@RequestParam("ajaxName") String aName) {
		System.out.println("HelloController ajaxString aName -> " + aName);
		return aName;
	}
	
	@ResponseBody
	@GetMapping("ajaxEmp")
	public Emp ajaxEmp(@RequestParam("empno") String empno , @RequestParam("ename") String ename) {
		System.out.println("HelloController ajaxEmp empno -> " + empno);
		logger.info("ename -> {}" , ename);
		Emp emp = new Emp();
		emp.setEmpno(empno);
		emp.setEname(ename);
		
		return emp;
	}
	
	
}
