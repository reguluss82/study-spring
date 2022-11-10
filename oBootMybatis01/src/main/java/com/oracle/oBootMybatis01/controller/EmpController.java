package com.oracle.oBootMybatis01.controller;

import java.util.List;

import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import javax.servlet.http.HttpServletRequest;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oracle.oBootMybatis01.model.Dept;
import com.oracle.oBootMybatis01.model.DeptVO;
import com.oracle.oBootMybatis01.model.Emp;
import com.oracle.oBootMybatis01.model.EmpDept;
import com.oracle.oBootMybatis01.service.EmpService;
import com.oracle.oBootMybatis01.service.Paging;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class EmpController {
	
	private final EmpService es;
	private final JavaMailSender mailSender;
	
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
	
	// Mybatis SQL return값
	// insert 1(여러개인경우 1)
	// update 실행된 행의 개수 
	// delete 실행된 행의 개수
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
	
	@PostMapping(value = "writeEmp")
	public String writeEmp(Emp emp , Model model) {
		int result = es.insertEmp(emp);
		if (result > 0) {
			model.addAttribute("msg", "성공");
			return "forward:listEmp";
		}
		model.addAttribute("msg", "실패");
		return "forward:writeFormEmp";
	}
	
	@GetMapping(value = "confirm")
	public String confirm(int empno , Model model) {
		Emp emp = es.detailEmp(empno);
		model.addAttribute("empno", empno);
		if (emp != null) {
			model.addAttribute("msg", "중복된 사번입니다");
			return "forward:writeFormEmp";
		} else {
			model.addAttribute("msg" , "사용 가능한 사번입니다");
			return "forward:writeFormEmp";
		}
	}
	
	@GetMapping(value = "deleteEmp")
	public String deleteEmp(int empno , Model model) {
		int result = es.deleteEmp(empno);
		return "forward:listEmp";
	}
	
	@GetMapping(value = "listEmpDept")
	public String listEmpDept(Model model) {
		List<EmpDept> listEmpDept = es.listEmpDept();
		model.addAttribute("listEmpDept", listEmpDept);
		
		return "listEmpDept";
	}
	
	@RequestMapping(value = "mailTransport")
	public String mailTransport(HttpServletRequest request , Model model) {
		String tomail = "rylee6344@gmail.com";
		String setfrom = "leoteemo82@gmail.com";
		String title = "mailTransport 입니다";
		try {
			// Mime 전자우편 Internet 표준 Format
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
			messageHelper.setFrom(setfrom); // 보내는사람 생략하거나 하면 정상작동을 안함
			messageHelper.setTo(tomail);    // 받는사람 이메일
			messageHelper.setSubject(title);// 메일제목은 생략이 가능하다
			String tempPassword =(int) (Math.random() * 999999) + 1 + "";
			messageHelper.setText("임시 비밀번호입니다 : " + tempPassword); // 메일 내용
			DataSource dataSource = new FileDataSource("c:\\log\\hwa.png");
			messageHelper.addAttachment(MimeUtility.encodeText("hwa3.png", "UTF-8", "B"), dataSource);
			mailSender.send(message);
			model.addAttribute("check", 1); // 정상전달
			// DB tempPassword Logic 구성
		} catch (Exception e) {
			System.out.println("mailTransport err " + e.getMessage());
			model.addAttribute("check", 2); // 메일전달실패
		}
		return "mailResult";
	}
	
	// procedure Test 입력화면
	@RequestMapping(value = "writeDeptIn")
	public String writeDeptIn(Model model) {
		System.out.println("writeDeptIn Start..");
		return "writeDept3";
	}
	
	// Procedure 통한 Dept 입력후 VO 전달
	@PostMapping(value = "writeDept")
	public String writeDept(DeptVO deptVO , Model model) {
		es.insertDept(deptVO);
		if (deptVO == null) {
			System.out.println("deptVO null");
		} else {
			model.addAttribute("msg", "정상 입력 되었습니다");
			model.addAttribute("dept", deptVO);
		}
		return "writeDept3";
	}
}
