package com.oracle.oBootHello.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.oracle.oBootHello.domain.Member1;
import com.oracle.oBootHello.service.MemberService;

@Controller
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	// 전통적
	// MemberService memberService = new MemberService();
	
	// DI 로 
	private final MemberService memberService; //final -> 생성자로만 만들어주겠다 의미
	
	@Autowired //bean으로 등록되어있는 class 불러온다.
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@GetMapping(value = "members/memberForm")
	public String memberForm() {
		System.out.println("MemberController /members/memberForm Start...");
		return "/members/memberForm";
	}
	
	@PostMapping(value = "/members/save")
	public String save(Member1 member1) { //form 에서 받은 name 을 member1(dto)에 받아서 가져오기 가능.
		System.out.println("MemeberController /members/save Start..");
		System.out.println("MemeberController /members/save member1.getName() -> " + member1.getName());
		Long id = memberService.memberSave(member1);
		System.out.println("MemeberController /members/save id -> " + id);
		return "redirect:/";
	}
	
	@GetMapping(value = "/members/memberList")
	public String memberList(Model model) {
		logger.info("memberList start...");
		List<Member1> memberLists = memberService.allMembers();
		model.addAttribute("memberLists", memberLists);
		logger.info("memberLists.size() -> " + memberLists.size());
		
		return "members/memberList";
	}
}
