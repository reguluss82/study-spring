package DI01;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass01 {

	public static void main(String[] args) {
		String configLocation = "classpath:applicationCTX01.xml";
		AbstractApplicationContext ctx = new GenericXmlApplicationContext(configLocation);
		MyCalculator myCalculator = ctx.getBean("myCalculator", MyCalculator.class);
		myCalculator.add();
		myCalculator.sub();
		myCalculator.mul();
		myCalculator.div();
		
		System.out.println("=========== Classic ===========");
		// Classic
		MyCalculator myCalculator03 = new MyCalculator();
		Calculator   calculator03   = new Calculator();
		myCalculator03.setCalculator(calculator03);
		myCalculator03.setFirstNum(20);
		myCalculator03.setSecondNum(2);
		myCalculator03.add();
		myCalculator03.sub();
		myCalculator03.mul();
		myCalculator03.div();
	}

}
