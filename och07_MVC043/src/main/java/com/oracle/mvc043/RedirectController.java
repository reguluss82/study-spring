package com.oracle.mvc043;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RedirectController {
	
	private static final Logger logger = LoggerFactory.getLogger(RedirectController.class);
	
	@RequestMapping("studentConfirm")
	public String studentRedirect(HttpServletRequest httpServletRequest, Model model) {
		
		logger.info("studentRedirect Start...");
		
		String id = httpServletRequest.getParameter("id");
		
		logger.info("studentConfirm id -> {}", id);
		
		String pw = "1234";
		
		model.addAttribute("id", id);
		model.addAttribute("pw", pw);
		// 성공이라고 가정 --> 같은 Controller내 method로 이동
		if (id.equals("abc")) {
			return "redirect:studentSuccess"; // redirect -> 같은 controller 내에 studentSuccess로
		}
		// 아니면 실패
		return "redirect:studentError";
	}
	
	@RequestMapping("studentSuccess")
	public String studentSuccess(HttpServletRequest request, Model model) {
		logger.info("studentSuccess Start...");
		String id       = request.getParameter("id");
		String password = request.getParameter("pw");
		logger.info("studentConfirm id -> {}"       + id);
		logger.info("studentConfirm password -> {}" + password);
		model.addAttribute("id",       id);
		model.addAttribute("password", password);
		
		return "student/studentSuccess";
	}
	
	@RequestMapping("studentError")
	public String studentError(Model model) {
	       logger.info("studentError Start...");
	       return "student/studentError";
	}
}
