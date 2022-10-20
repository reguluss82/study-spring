package sdlc02;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass02 {

	public static void main(String[] args) {
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX02.xml");
		
		Student student1 = ctx.getBean("student", Student.class);
		System.out.println("student1 이름 : " + student1.getName());
		System.out.println("student1 나이 : " + student1.getAge());
		System.out.println("========================================");
		
		Student student2 = ctx.getBean("student", Student.class);
		student2.setName("강유");
		student2.setAge(55);
		System.out.println("student2 이름 : " + student2.getName());
		System.out.println("student2 나이 : " + student2.getAge());
		System.out.println("========================================");
		
		System.out.println("student1 이름 : " + student1.getName());
		System.out.println("student1 나이 : " + student1.getAge());
		//singleton 이므로 	
		if (student1.equals(student2)) {
			System.out.println("student1 == student2");	// 같은 주소를 참조한다
		} else {
			System.out.println("student1 != student2"); // prototype
		}
		ctx.close();
	}

}
