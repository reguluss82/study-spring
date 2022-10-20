package sam03;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Ex03 {

	public static void main(String[] args) {
		// classic
		MessageBean cmb = new MessageBeanImpl("허유나", "전통적 안녕");
		cmb.sayHello();
		// DI(Dependency Injection - 의존성 주입) 호출 객체
		ApplicationContext ac = new ClassPathXmlApplicationContext("bean03.xml");
		MessageBean mb = (MessageBean) ac.getBean("mb3");
		mb.sayHello();

	}

}
