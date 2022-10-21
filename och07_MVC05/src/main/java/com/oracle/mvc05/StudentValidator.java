package com.oracle.mvc05;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.oracle.mvc05.dto.Student;

public class StudentValidator implements Validator {
	
	// 검증할 객체의 클래스 타입 정보
	@Override
	public boolean supports(Class<?> student) {
		return Student.class.isAssignableFrom(student);
	}
	
	// validate 함수
	@Override
	public void validate(Object target, Errors errors) {
		System.out.println("StudentValidator validate() start...");
		Student student = (Student) target;
		String studentName = student.getName();
		if (studentName == null || studentName.trim().isEmpty()) {
			System.out.println("studentName is null or empty");
			System.out.println("validate -> 회원 이름을 입력하세요...");
			errors.rejectValue("name", "회원 이름 공백 또는 NULL 오류");
		}
		String studentId = student.getStrId();
		if (studentId == null || studentId.trim().isEmpty()) {
			System.out.println("studentId is null or empty");
			System.out.println("validate -> strId를 입력하세요...");
			errors.rejectValue("strId", "strId 공백 또는 NULL 오류");
		}
	}

}
