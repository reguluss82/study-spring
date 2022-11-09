package com.oracle.oBootMybatis01.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oracle.oBootMybatis01.model.Dept;
import com.oracle.oBootMybatis01.model.Emp;
import com.oracle.oBootMybatis01.service.EmpService;
import com.oracle.oBootMybatis01.service.Paging;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class EmpController {
	
	private final EmpService es;
	
	@RequestMapping(value = "listEmp")
	public String empList(Emp emp , String currentPage , Model model) {
		int totalEmp = es.totalEmp();
		
		// Paging 작업
		Paging page = new Paging(totalEmp, currentPage);
		// Parameter emp --> Page만 추가
		emp.setStart(page.getStart());
		emp.setEnd(page.getEnd());
		
		List<Emp> listEmp = es.listEmp(emp);
		
		model.addAttribute("totalEmp", totalEmp);
		model.addAttribute("listEmp", listEmp);
		model.addAttribute("page", page);
		
		return "list";
	}
	
	@GetMapping(value = "detailEmp")
	public String detailEmp(int empno , Model model) {
		Emp emp = es.detailEmp(empno);
		model.addAttribute("emp", emp);
		
		return "detailEmp";
	}
	
	@GetMapping(value = "updateFormEmp")
	public String updateFormEmp(int empno , Model model) {
		Emp emp = es.detailEmp(empno);
		// 문제
		// 1. DTO는 String hiredate
		// 2. Hire 단순조회 OK, JSP 에서 input type="date" 문제발생
		// 3. 해결책
		String hiredate = "";
		if (emp.getHiredate() != null) {
			hiredate = emp.getHiredate().substring(0, 10); // 연/월/일 로 자름
			emp.setHiredate(hiredate);
		}
		model.addAttribute("emp", emp);
		
		return "updateFormEmp";
	}
	
	// Mybatis SQL return값 확인 insert, update ,delete
	@PostMapping(value = "updateEmp")
	public String updateEmp(Emp emp , Model model) {
		es.updateEmp(emp);
		model.addAttribute("kk3", "Update Message");
//		return "redirect:listEmp"; // kk3 못받음
		return "forward:listEmp"; // kk3 받음
	}
	
	@RequestMapping(value = "writeFormEmp")
	public String wirteFormEmp(Model model) {
		List<Emp>  empList  = es.listManager();
		List<Dept> deptList = es.deptSelect();
		model.addAttribute("empMngList", empList);
		model.addAttribute("deptList", deptList);
		return "writeFormEmp";
	}
}
