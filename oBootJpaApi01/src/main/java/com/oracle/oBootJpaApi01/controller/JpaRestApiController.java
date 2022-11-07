package com.oracle.oBootJpaApi01.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.oracle.oBootJpaApi01.domain.Member;
import com.oracle.oBootJpaApi01.service.MemberService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController	// Controller + responseBody -> RestApi와 Ajax 사용
@RequiredArgsConstructor
@Slf4j
public class JpaRestApiController {
	private final MemberService memberService;
	
	// postman --> Body --> raw --> JSON
	// @RequestBody Json(member)으로 온것을 --> Member member Setting
	@PostMapping("restApi/v1/memberSave")
	public CreateMemberResponse saveMemberV1(@RequestBody @Valid Member member) {
		Long id = memberService.saveMember(member);
		return new CreateMemberResponse(id);
	}
	
	// 목적 : Entity Member member --> 직접 화면이나 API 자체를 위한 entity setting 금지
	// 예시 : @NotEmpty --> @Column(name = "userName")
	@PostMapping("restApi/v2/memberSave")
	public CreateMemberResponse saveMemberV2(@RequestBody @Valid CreateMemberRequest cMember) {
		Member member = new Member();
		member.setName(cMember.getName());
		member.setSal(cMember.getSal());
		Long id = memberService.saveMember(member);
		return new CreateMemberResponse(id);
	}
	
	// inner Class
	@Data
	static class CreateMemberRequest {
		@NotEmpty
		private String name;
		private Long   sal;
	}
	
	// inner Class(이 class내에서 사용) 
	// 객체로 만들어 둔 이유 -> 하나의 값이더라도 객체로 보내주기위함
	@Data
	@RequiredArgsConstructor
	class CreateMemberResponse {
		private final Long id;
//		public CreateMemberResponse(Long id) {
//			this.id = id;
//		}
	}
	// Bad API
	@GetMapping("/restApi/v1/members")
	public List<Member> membersVer1() {
		List<Member> listMember = memberService.getListAllMember();
		return listMember; // view resolver X -> httpMessageConverter-JsonConverter(객체이므로)
		// Json 형식 key:value  배열이면 [] 로 묶어서
	}
	
	// Good API easy version
	// 목표 : 이름 & 급여만 전송
	@GetMapping("/restApi/v21/members")
	public Result membersVer21() {
		List<Member> findMembers = memberService.getListAllMember();
		List<MemberRtnDto> resultList = new ArrayList<MemberRtnDto>();
		for (Member member : findMembers) {
			MemberRtnDto memberRtnDto = new MemberRtnDto(member.getName(), member.getSal());
			resultList.add(memberRtnDto);
		}
		return new Result(resultList.size() , resultList);
	}
	
	// Good API lambda version
	// 자바 8에서 추가한 스트림(Streams)은 람다를 활용할 수 있는 기술 중 하나
	// 목표 : 이름 & 급여만 전송
	@GetMapping("/restApi/v22/members")
	public Result membersVer22() {
		List<Member> findMembers = memberService.getListAllMember();
		//for (Member member : findMembers) 의 member 가 m
		List<MemberRtnDto> memberCollect = findMembers.stream()
													  .map(m -> new MemberRtnDto(m.getName() , m.getSal()))
													  .collect(Collectors.toList());
		return new Result(memberCollect.size() , memberCollect);
	}
	
	// T는 인스턴스를 생설할 때 구체적인 타입으로 변경
	@Data
	@AllArgsConstructor
	class Result<T> { // <T> T 어떠한 객체도 들어갈수있다.
		private final int totCount; // 총 인원수 추가
		private final T data;
	}
	@Data
	@AllArgsConstructor
	class MemberRtnDto {
		private String name;
		private Long   sal;
	}
	
	// 수정 API
	// PUT 방식을사용했는데, PUT은 전체 업데이트를 할 때 사용
	// URI 상에서 '{ }' 로 감싸여있는 부분과 동일한 변수명을 사용하는 방법
	// 해당 데이터가 있으면 업데이트를 하기에 
	// PUT요청이 여러번 실행되어도 해당 데이터는 같은 상태이기에 "멱등"
	@PutMapping("/restApi/v21/members/{id}")
	public UpdateMemberResponse updateMemberV21(@PathVariable("id") Long id ,
												@RequestBody @Valid UpdateMemberRequest uMember) {
		memberService.updateMember(id, uMember.getName(), uMember.getSal());
		Member findMember = memberService.findByMember(id);
		return new UpdateMemberResponse(findMember.getId(), findMember.getName(), findMember.getSal());
	}
	
	@Data
	static class UpdateMemberRequest { // update할 field 명확하게 표현됨
		private String name;
		private Long   sal;
	}
	@Data
	@AllArgsConstructor
	class UpdateMemberResponse {
		private Long   id;
		private String name;
		private Long   sal;
	}

}
