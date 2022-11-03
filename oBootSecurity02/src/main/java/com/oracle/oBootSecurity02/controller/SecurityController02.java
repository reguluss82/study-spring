package com.oracle.oBootSecurity02.controller;

import java.util.Iterator;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.oracle.oBootSecurity02.configuration.auth.PrincipalDetails;
import com.oracle.oBootSecurity02.entity.User;
import com.oracle.oBootSecurity02.service.SecurityService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class SecurityController02 {
	private final SecurityService securityService;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@GetMapping("/user")
	public String user(@AuthenticationPrincipal PrincipalDetails principal) {
		System.out.println("SecurityController02 user start...");
		System.out.println("SecurityController02 user principal -> " + principal);
		Iterator<? extends GrantedAuthority> iter = principal.getAuthorities().iterator();
		while (iter.hasNext()) {
			GrantedAuthority authority = iter.next();
			System.out.println("authority.getAuthority() -> " + authority.getAuthority());
			
		}
		
		return "user";
	}
	@GetMapping("/user/2")
	public String user2() {
		System.out.println("SecurityController02 user2 start...");
		return "user";
	}
	@GetMapping("/manager")
	public String manager() {
		System.out.println("SecurityController02 manager start...");
		return "manager";
	}
	@GetMapping("/manager/2")
	public String manager2() {
		System.out.println("SecurityController02 manager2 start...");
		return "manager";
	}
	@GetMapping("/admin")
	public String admin() {
		System.out.println("SecurityController02 admin start...");
		return "admin";
	}
	@GetMapping("/admin/2")
	public String admin2() {
		System.out.println("SecurityController02 admin2 start...");
		return "admin";
	}
	@GetMapping("/loginFail")
	public String loginFail() {
		System.out.println("loginFail start");
		return "loginFail";
	}
	@GetMapping("/loginForm")
	public String loginForm() {
		return "loginForm";
	}
	@GetMapping("/joinForm")
	public String joinForm() {
		return "joinForm";
	}
	@PostMapping("/joinProc")
	public String joinProc(User user) {
		String encPasswd = bCryptPasswordEncoder.encode(user.getPassword()); // encode
		user.setPassword(encPasswd);
		securityService.save(user);
		return "redirect:/loginForm";
	}
}
