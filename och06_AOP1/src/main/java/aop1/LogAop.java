package aop1;

import org.aspectj.lang.ProceedingJoinPoint;

public class LogAop {
	// Around Advice에서 사용할 공통기능 메서드는
	// 대부분 파라미터로 전달받은 ProceedingJoinPoint의 proceed() 메서드만 호출
	public Object loggerAop(ProceedingJoinPoint joinPoint) throws Throwable {
		// 핵심관심사 Method
		String signatureStr = joinPoint.getSignature().toShortString();
		System.out.println(signatureStr + " is start..");
		// Returns the current time in milliseconds
		long startTime = System.currentTimeMillis();

		Object obj;
		try {
			// 핵심관심사 Method 수행
			obj = joinPoint.proceed();
			return obj;
		} finally {
			long endTime = System.currentTimeMillis();
			System.out.println(signatureStr + " is finished.");
			System.out.println(signatureStr + " 경과시간 : " + (endTime - startTime));
		}
		
	}
}
