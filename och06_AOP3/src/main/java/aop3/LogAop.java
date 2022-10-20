package aop3;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LogAop {
	// aop3.buz 패키지 안에 있는 모든 메소드
	@Pointcut("within(aop3.buz.*)")
	private void pointcutMethod() {
	}
	
	@Around("pointcutMethod()")
	public Object loggerAop(ProceedingJoinPoint joinPoint) throws Throwable {
		String signatureStr = joinPoint.getSignature().toShortString();
		System.out.println(signatureStr + " is start");
		long st = System.currentTimeMillis();
		
		try {
			Object obj = joinPoint.proceed();
			return obj;
		} finally {
			long et = System.currentTimeMillis();
			System.out.println(signatureStr + " is finished");
			System.out.println(signatureStr + " 경과시간 : " + (et - st));
		}
	}
	
	@Before("within(aop3.buz.*)")
	public void beforeAdvice() {
		System.out.println("beforeAdvice()");
	}
	
	@After("pointcutMethod()")
	public void afterAdvice() {
		System.out.println("afterAdvice()");
	}
}
