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
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true) // 특정 주소 접근시 권한 및 인증을 위한 @secured 활성화
public class SecurityConfig {
	@Bean
	public BCryptPasswordEncoder encodedPwd() {
		return new BCryptPasswordEncoder();
	}
//	1.Form Login을 사용하게 된다면 인증 필터인 UsernamePasswordAuthenticationFilter가 실행되게 됩니다.
//	2.AntPathRequestMatcher는 요청 정보의 url이 해당 값으로 시작되는지 체크를 하는 하며 요청 정보가 일치하지 않는다면 2-1인 Filter로 이동한다.
//	※ url의 값은 .loginProcessingUrl(“/login")의 값 변경에 따라 변경됩니다.
//	3.요청 정보가 일치하면 Username과 Password정보가 담긴 Authentication 객체를 생성하여 AuthenticationManager에 넘깁니다.
//	4.AuthenticationManager는 이전 과정에서 받은 Authenticaton객체를 AuthenticationProvider에 넘겨주어 인증을 체크하도록 합니다.
//	5.AuthenticationProvider는 실질적으로 인증을 체크하는 역할을 합니다. 인증을 성공한다면 최종적인 Authenticatoin객체를 생성하여 Authentication객체를 넘기며 인증을 실패한다면 5-1의 AuthenticationException을 호출하여 UsernamePasswordAuthenticationFilter가 시작된 초기 부분으로 이동하게 합니다.
//	6.AuthenticationManager는 AuthenticationProvider로부터 받은 최종 Authentication객체를 다음 과정으로 넘겨줍니다.
//	7.최종 Authenticatoin 객체는 Security Context에 저장됩니다.
//	8.Security Context에 저장된 후에는 SuccessHandler를 호출하여 실행하게 됩니다.
//	이 후에는 SecurityContextHolder.getContext().getAuthentication()코드를 통해 인증 객체를 꺼내서 쓸 수 있습니다.
	// WebSecurityConfigurerAdapter deprecated
	@Bean
	protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.authorizeRequests()
			.antMatchers("/user/**").authenticated() //authenticated() 사용자 인증만 된상태면 접근가능
			.antMatchers("/manager/**").access("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
			.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
			.and()
			.formLogin()
			.loginPage("/loginForm")
			.loginProcessingUrl("/login") //form action url
			.failureUrl("/loginFail")
			.defaultSuccessUrl("/main")
			//.usernameParameter("usernameParameter")
			//.passwordParameter("passwordParameter")
			//.successHandler(successHandler) //AuthenticationSuccessHandler interface구현
			//.failureHandler(authenticationFailureHandler) //AuthenticationFailureHandler interface 구현
			.permitAll() // loginPage 접근 가능하도록 
			;
		return http.build();
	}
}
