package com.oracle.oBootJpa03.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.oracle.oBootJpa03.domain.Address;
import com.oracle.oBootJpa03.domain.Member;
import com.oracle.oBootJpa03.form.MemberForm;
import com.oracle.oBootJpa03.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MemberController {
	private final MemberService memberService;
//	public MemberController(MemberService memberService) {
//		this.memberService = memberService;
//	} //@RequiredArgsConstructor 
	
	@GetMapping(value = "/members/new")
	public String createForm(Model model) {
		log.info("members/new Slf4j..."); // lombok @Slf4j 사용
		model.addAttribute("memberForm", new MemberForm());
		return "members/createMemberForm";
	}
	
	@PostMapping(value = "/memberSave") // MVC05 참조.
	public String memberSave(@Valid MemberForm form , BindingResult result) {
		if(result.hasErrors()) {
			return "members/createMemberForm";
		}
		Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());
		Member member = new Member();
		member.setName(form.getName());
		member.setAddress(address);
		//memberService.memberSave(member);
		memberService.Save(member);
		return "redirect:/";
	}
//  entity manager 사용	
//	@GetMapping(value = "/members")
//	public String list(Model model) {
//		List<Member> members = memberService.findMembers();
//		model.addAttribute("members", members);
//		return "members/memberList";
//	}
	
	
	@GetMapping(value = "/members")
	public String list(Model model) {
		List<Member> members = memberService.findAll();
		model.addAttribute("members", members);
		return "members/memberList";
	}
}
