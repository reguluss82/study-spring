package com.oracle.oBootMybatis01.controller;

import java.util.HashMap;
import java.util.List;

import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oracle.oBootMybatis01.model.Dept;
import com.oracle.oBootMybatis01.model.DeptVO;
import com.oracle.oBootMybatis01.model.Emp;
import com.oracle.oBootMybatis01.model.EmpDept;
import com.oracle.oBootMybatis01.model.Member1;
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
	
	@RequestMapping(value = "listSearch3")
	public String listSearch3(Emp emp , String currentPage , Model model) {
		int totalEmp = es.totalEmp();
		
		// Paging 작업
		Paging page = new Paging(totalEmp, currentPage);
		// Parameter emp --> Page만 추가
		emp.setStart(page.getStart());
		emp.setEnd(page.getEnd());
		
		List<Emp> listSearchEmp = es.listSearchEmp(emp);
		
		model.addAttribute("totalEmp", totalEmp);
		model.addAttribute("listEmp", listSearchEmp);
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
//		return "writeFormEmp";
		return "writeFormEmp3"; //validation
	}
	
	@PostMapping(value = "writeEmp")
	public String writeEmp(Emp emp , Model model) {
		int insertResult = es.insertEmp(emp);
		if (insertResult > 0) {
			model.addAttribute("msg", "성공");
			return "forward:listEmp";
		}
		model.addAttribute("msg", "실패");
		return "forward:writeFormEmp";
	}
	
	//Validation check
	@PostMapping(value = "writeEmp3")
	public String writeEmp3(@ModelAttribute("emp") @Valid Emp emp , BindingResult result , Model model) {
		// Validation err -> return "forward:writeFormEmp"
		if (result.hasErrors()) {
			System.out.println("EmpController writeEmp3 hasErrors...");
			model.addAttribute("msg", "BindingResult  입력 실패");
			return "forward:writeFormEmp";
		}
		int insertResult = es.insertEmp(emp);
		if (insertResult > 0) {
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
	
//	                    DTO                           MAP
//	1. 정의             Class로 선 정의               즉흥적 구성
//	2. Parameter 전달   DTO -> Setter Or 생성자       map.put
//	3. 사용용도         명확한 정의                   시간부족/대화부족 
//	4. 장점             유지보수 용이                 기능개발 시간 절감

	@GetMapping(value = "/writeDeptCursor")
	public String writeDeptCursor(Model model) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("sDeptno", 30);
		map.put("eDeptno", 60);
		es.selListDept(map);
		List<Dept> deptList = (List<Dept>) map.get("dept");
		for (Dept dept : deptList) {
			System.out.println("dept.getDname() -> " + dept.getDname());
			System.out.println("dept.getLoc() -> "+ dept.getLoc());
		}
		model.addAttribute("deptList", deptList);
		return "writeDeptCursor";
	}
	
	// interCeptor 시작화면
	@RequestMapping(value = "interCeptorForm")
	public String interCeptorForm(Model model) {
		return "interCeptorForm";
	}
	
	@RequestMapping(value = "interCeptor")
	public String interCeptor(String id, Model model) {
		int memCnt = es.memCount(id);
		model.addAttribute("id", id);
		model.addAttribute("memCnt", memCnt);
		return "interCeptor";
	}
	// SampleInterceptor 내용을 받아 처리
	@RequestMapping(value = "doMemberWrite", method = RequestMethod.GET)
	public String doMemberWrite( Model model , HttpServletRequest request) {
		String ID = (String) request.getSession().getAttribute("ID");
		model.addAttribute("id", ID);
		return "doMemberWrite";
	}
	
	// interCeptor 진행 Test
	@RequestMapping(value = "doMemberList")
	public String doMemberList(Model model , HttpServletRequest request) {
		String ID = (String) request.getSession().getAttribute("ID");
		Member1 member1 = null;
		//Member1 List Get Service
		List<Member1> listMem = es.listMem(member1);
		model.addAttribute("ID", ID);
		model.addAttribute("listMem", listMem);
		return "doMemberList"; // User 존재하면 User 이용 조회 Page
		
	}
	
	// ajaxForm Test 입력화면
	@RequestMapping(value = "ajaxForm")
	public String ajaxForm(Model model) {
		return "ajaxForm";
	}
	
	@ResponseBody
	@RequestMapping(value = "getDeptName")
	public String getDeptName(int deptno, Model model) {
		String deptName = es.deptName(deptno);
		return deptName;
	}
	
	@RequestMapping(value = "listEmpAjaxForm")
	public String listEmpAjaxForm(Model model) {
		Emp emp = new Emp();
		emp.setStart(1);
		emp.setEnd(10);
		List<Emp> listEmp = es.listEmp(emp);
		model.addAttribute("result", "kkk");
		model.addAttribute("listEmp", listEmp);
		return "listEmpAjaxForm";
	}
	
	@RequestMapping(value = "listEmpAjaxForm2")
	public String listEmpAjaxForm2(Model model) {
		System.out.println("listEmpAjaxForm2 Start");
		Emp emp = new Emp();
		System.out.println("Ajax List Test Start");
		emp.setStart(1);
		emp.setEnd(15);
		List<Emp> listEmp = es.listEmp(emp);
		model.addAttribute("listEmp", listEmp);
		return "listEmpAjaxForm2";
	}
}
