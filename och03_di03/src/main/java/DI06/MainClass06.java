package DI06;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass06 {

	public static void main(String[] args) {
		String configLocation1 = "classpath:applicationCTX610.xml";
		String configLocation2 = "classpath:applicationCTX611.xml";
		AbstractApplicationContext ctx = new GenericXmlApplicationContext(configLocation1 , configLocation2);
		Student student1 = ctx.getBean("student1" , Student.class);
		System.out.println(student1.getName());		// 선덕여왕
		System.out.println(student1.getHobbys());	// 승마, 정치
		
		StudentInfo studentInfo = ctx.getBean("studentInfo1" , StudentInfo.class);
		Student student2 = studentInfo.getStudent();
		System.out.println(student2.getName());
		System.out.println(student2.getHobbys());
		
		if (student1.equals(student2)) {
			System.out.println("student1 == student2");
		}
		
		Student student3 = ctx.getBean("student3" , Student.class);
		Student student4 = ctx.getBean("student3" , Student.class);
		System.out.println(student3.getName());	// 이순신
		System.out.println(student4.getName()); // 이순신
		
		if (student1.equals(student3)) {
			System.out.println("student1 == student3");
		} else {
			System.out.println("student1 != student3");
		}
		if (student3.equals(student4)) {
			System.out.println("student3 == student4"); // bean scope singleton (default)
		} else {
			System.out.println("student3 != student4"); // bean scope prototype
		}
		ctx.close();
	}

}
