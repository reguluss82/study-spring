package sam05;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Ex05 {

	public static void main(String[] args) {
//		MessageBean mb5 = new MessageBeanImpl();
//		mb5.setGreet("Good Bye");
//		mb5.setName("한창훈");
		ApplicationContext ac = new ClassPathXmlApplicationContext("bean05.xml"); //classPath는 src/main/resources 를 가리킨다
		MessageBean mb = (MessageBean) ac.getBean("mb5");
		mb.sayHello();
	}
}
