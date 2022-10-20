package aop3;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import aop3.buz.Student;
import aop3.buz.Worker;

public class AopMainClass03 {

	public static void main(String[] args) {
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX03.xml");
		Student student = ctx.getBean("student" , Student.class);
		student.getStudentInfo();
		student.get3StudentInfo();
		
		Worker worker = ctx.getBean("worker" , Worker.class);
		worker.getWorkerInfo();
		
		ctx.close();

	}

}
