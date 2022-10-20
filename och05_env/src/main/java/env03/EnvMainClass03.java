package env03;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class EnvMainClass03 {

	public static void main(String[] args) {
		System.out.println("1 EnvMainClass03 Run...");
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		System.out.println("4 EnvMainClass03 adminConfig Before Run");
		AdminConnection connection = ctx.getBean("adminConfig", AdminConnection.class);
		System.out.println("5 EnvMainClass03 adminConfig After Run");
		
		System.out.println("connection.getAdminId() adminID : "         + connection.getAdminId());
		System.out.println("connection.getAdminPw() adminPW : "         + connection.getAdminPw());
		System.out.println("connection.getSub_adminId() sub_adminID : " + connection.getSub_adminId());
		System.out.println("connection.getSub_adminPw() sub_adminPW : " + connection.getSub_adminPw());
		ctx.close();
	}

}
