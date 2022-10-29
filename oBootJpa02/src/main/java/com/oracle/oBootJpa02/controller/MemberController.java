package com.oracle.oBootJpa02.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.oracle.oBootJpa02.domain.Member;
import com.oracle.oBootJpa02.service.MemberService;

@Controller
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	private final MemberService memberService;
	@Autowired
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@GetMapping(value = "/members/new")
	public String createForm() {
		return "members/createMemberForm";
	}
	
	@PostMapping(value = "/memberSave")
	public String memberSave(Member member) {
		memberService.memberSave(member);
		return "redirect:/";
	}
	
	@GetMapping("/members")
	public String listMember(Model model) {
		List<Member> memberList = memberService.getListAllMember();
		System.out.println(memberList.get(0).getTeam().getName()); //방향성을 타고 그래프순회 타고타고타고
		model.addAttribute("memberList", memberList);
		return "members/memberList";
	}
	
	@GetMapping(value = "/memberModifyForm")
	public String memberModify(Long id, Model model) {
		Member member = memberService.findByMember(id);
		model.addAttribute("member", member);
		return "members/memberModify";
	}
	
	@PostMapping(value = "/members/memberUpdate")
	public String memberUpdate(Member member, Model model) {
		memberService.memberUpdate(member);
		return "redirect:/members";
	}
}
