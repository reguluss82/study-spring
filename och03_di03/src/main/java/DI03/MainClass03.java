package DI03;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass03 {
	
	public static void main(String[] args) {
		String configLocation = "classpath:applicationCTX03.xml";
		AbstractApplicationContext ctx = new GenericXmlApplicationContext(configLocation);
		StudentInfo studentInfo = ctx.getBean("studentInfo" , StudentInfo.class);
		studentInfo.getStudentInfo();
		
		Student student2 = ctx.getBean("student2" , Student.class);
		studentInfo.setStudent(student2);
		studentInfo.getStudentInfo();
		System.out.println("student2.getAge() --> " + student2.getAge());
		System.out.println(student2.getAge() + 5);
		ctx.close();
		// Classic
		Student student3 = new Student("김유신3" , 23 , "3학년" , "3번");
		student3.setAge(32);
		studentInfo.setStudent(student3);
		studentInfo.getStudentInfo();
		System.out.println("Classic student3.getAge() --> " + student3.getAge());
	}

}
