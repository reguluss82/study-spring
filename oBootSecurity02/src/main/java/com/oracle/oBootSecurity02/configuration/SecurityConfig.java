package com.oracle.oBootSecurity02.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration //IoC bean 등록
@EnableWebSecurity // 필터 체인 관리 시작 어노테이션
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true) // 특정 주소 접근시 권한 및
public class SecurityConfig {
	@Bean
	public BCryptPasswordEncoder encodedPwd() {
		return new BCryptPasswordEncoder();
	}
	
	// WebSecurityConfigurerAdapter deprecated
	@Bean
	protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.authorizeRequests()
			.antMatchers("/user/**").authenticated()
			//.antMatchers("/manager/**").authenticated()
			//.antMatchers("/admin/**").authenticated()
			.and()
			.formLogin()
			.loginPage("/loginForm")
			.loginProcessingUrl("/login")
			.failureUrl("/loginFail")
			.defaultSuccessUrl("/")
			;
		return http.build();
	}
}
