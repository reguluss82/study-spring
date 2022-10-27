package com.oracle.oBootDBConnect.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.oracle.oBootDBConnect.domain.Member1;
import com.oracle.oBootDBConnect.service.MemberService;

@Controller
public class MemberController {
	private final MemberService memberService;
	@Autowired
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@GetMapping(value = "/members/new")
	public String createForm() {
		System.out.println("MemberController /members/new start");
		
		return "members/createMemberForm";
	}
	
	@PostMapping(value = "/members/new")
	public String memberSave(Member1 member) {
		System.out.println("MemberController memberSave start...");
		memberService.memberSave(member);
		
		return "redirect:/";
	}
	
	@GetMapping(value = "/members")
	public String memberLists(Model model) {
		List<Member1> memberList = memberService.findMembers();
		model.addAttribute("members", memberList);
		
		return "members/memberList";
	}
}
