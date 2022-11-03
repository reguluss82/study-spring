package com.oracle.oBootSecurity01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class SecurityController01 {
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@GetMapping("/user")
	public String user(String passwd) {
		System.out.println("SecurityController01 user start...");
		System.out.println("SecurityController01 user passwd -> " + passwd);
		String encPasswd = bCryptPasswordEncoder.encode(passwd);
		System.out.println("SecurityController01 user encPasswd -> " + encPasswd);
		return "user";
	}
	@GetMapping("/manager")
	public String manager() {
		System.out.println("SecurityController01 manager start...");
		return "manager";
	}
	@GetMapping("/admin")
	public String admin() {
		System.out.println("SecurityController01 admin start...");
		return "admin";
	}
}
