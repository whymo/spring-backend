package org.zerock.myapp.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@NoArgsConstructor
@Log4j2

@Component // + Bean으로 자동 등록
@Aspect // + Aspect를 구현한 것이 Advice (****)
public class LogAdvice {
	
	// ==================================================================================
	// + Target(서비스) 객체의 JoinPoint(메소드)의 호출로그(Cross-Concern => Aspect)를 출력하는 Advice 구현
	// ==================================================================================
	// + 단, Advice의 종류는 "Before Advice"로 구현한다.
	// + 즉, Target 객체의 JoinPoint 호출 전에 수행되는 Advice를 구현할 것이다.
	// ==================================================================================
	// + @Aspect만으로는 자동으로 Bean Container에 Bean으로 등록할 수는 없다.
	// + 그렇기 때문에 stereotype 어노테이션이 필요하다. ( ex. @Component )
	// ==================================================================================
	
	// ==================================================================================
	// + Advice의 종류
	// ==================================================================================
	
//	@Before						public void before2() {;;}
//	@AfterThrowing				public void afterThrowing() {;;} : 예외가 발생한 뒤에 동작하는 코드
//	@AfterReturning				public void afterReturning() {;;} : 모든 실행이 정상적으로 일어났을때 수행
//	@After						public void after() {;;} : 예외 발생의 유무와 상관없이 수행
//	@Around						public void around() {;;} : 메소드의 실행 자체를 제어
	
	// ==================================================================================
	// + Advice는 value를 통해 advice를 어디에다가 바인딩 할 것인지 PointCut을 지정해줘야 한다.
	// ==================================================================================
	// + *Service이 Target, *(..)는 JoinPoint를 의미한다.
	// + 즉, org.zerock.myapp.service 밑의 모든 Service로 끝나는 파일 중에 모든 메소드를 선택한 것이다.
	// + (..)는 매개변수는 자유하는 의미이다.
	// + JoinPoint에 Advice를 적용하게 된다.
	// ==================================================================================
	// + Before Advice : (1) 매개변수가 없다. (2) 리턴타입이 void이다. (3) 메소드명은 자유 
	// ==================================================================================
	
	// ==================================================================================
	// + PointCut Expression을 @Before 어노테이션의 기본속성 값으로 설정해야 한다. (***)
	// + 1. 이때 AspctJ 언어가 제공하는 execution() 함수를 사용하여 pointcut expression을 설정
	// + 2. pointcut 표현식의 형태 : ( 접근제한자 패키지.클래스.메소드(매개변수) )
	// + 3. 위의 2번의 pointcut 표현식을 통해
	// +    (1) 어느 Target( 서비스 ) 객체에
	// +    (2) 어느 JoinPoint( 메소드 )에
	// +    (3) 어느 종류의 Advice( @Before )를 적용시킬지 설정한다.
	// ==================================================================================
	
	// ====================================================
	// + 1. Target 객체의 JoinPoint가 실행되기 직전에 수행
	// ====================================================
	@Before(value = "execution( * org.zerock.myapp.service.*Service.*(..) )")
	public void before() {
		log.info("*****************************");
		log.trace("<1> before() invoked.");
		log.info("*****************************");
	} // before
	
	// ====================================================
	// + 1-2. &&args(매개변수 이름)을 통해서 JoinPoint에 전달될 값을 미리 받아볼 수 있다.
	// ====================================================
	@Before(value = "execution( * org.zerock.myapp.service.SampleService.doAdd(..) ) && args(s1, s2)")
	public void before2(String s1, String s2) {
		log.info("*****************************");
		log.trace("<1-2> before2({}, {}) invoked.", s1, s2);
		log.info("*****************************");
	} // before2
	
	// ====================================================
	// + 2. Target 객체의 JoinPoint가 정상적으로 완료된 후에 수행
	// ====================================================
	// + @AfterReturning 결과값을 받아 볼 수 있다.
	// ====================================================
	@AfterReturning(pointcut = "execution( * org.zerock.myapp.service.*Service.*(..) )", returning = "result")
	public void afterReturning(Object result) {
		log.info("*****************************");
		log.trace("<2> afterReturning({}) invoked.", result);
		log.info("*****************************");
	} // afterReturning	
	
	// ====================================================
	// + 3. Target 객체의 JoinPoint의 예외가 발생한 후에 수행
	// ====================================================
	// + Log4j2에서 예외를 모두 StackTrace해버리기에 e가 찍히지는 않는다.
	// ====================================================
	@AfterThrowing(pointcut = "execution( * org.zerock.myapp.service.*Service.*(..) )", throwing = "e")
	public void afterThrowing(Exception e) {
		log.info("*****************************");
		log.trace("<3> afterThrowing({}) invoked.", e);
		log.info("*****************************");
	} // afterThrowing	
	
	// ====================================================
	// + 4. Target 객체의 JoinPoint가 완료된 후에 수행 ( 예외의 유무와 상관 x )
	// ====================================================
	@After(value = "execution( * org.zerock.myapp.service.*Service.*(..) )")
	public void after() {
		log.info("*****************************");
		log.trace("<4> after() invoked.");
		log.info("*****************************");
	} // after
	
	// ====================================================
	// + 5. 메소드의 실행 자체를 제어
	// ====================================================
	// + 반드시 매개변수로 ProceedingJoinPoint를 가져야 한다.
	// + joinPoint의 타입이 다양할 수 있기에 Object로 해야 한다.
	// ====================================================
	// + @Around가 pjp로 실행을 시켜야 다른 Advice가 수행가능하다. (***)
	// + @Around가 pjp로 실행하지 않으면, 다른 Advice는 수행되지 않는다.
	// + @Around가 가장 먼저 시작하게 된다.
	// ====================================================
	
	@Around(value = "execution( * org.zerock.myapp.service.*Service.*(..) )")
	public Object around(ProceedingJoinPoint pjp) throws Throwable {
		
		log.info("*****************************");
		log.trace("<5> around({}) invoked.", pjp);
		log.info("*****************************");
		
		log.info("\t + 1. type : {}", pjp.getClass().getName());
		log.info("\t + 2. target : {}", pjp.getTarget());
		log.info("\t + 3. args : {}", Arrays.toString(pjp.getArgs()));
		log.info("\t + 4. JoinPoint(메소드) : {}", pjp.getSignature());
		
		// ==============================================================
		// + 1 ) Target의 JoinPoint 실행소요시간 측정하기
		// ==============================================================
		
		// ==============================================================
		// + 1. 선처리 : 시작했을 때의 시간 구하기
		// ==============================================================
		// long start = System.currentTimeMillis();			// 1 / 1000 초 
		long start = System.nanoTime();						// 1 / 10억 초
		
		// ==============================================================
		// + 2. Proceeding Target's JoinPoint : 서비스의 메소드 직접 수행 (***)
		// ==============================================================
		
		// < ----- BEFORE ADVICE ----- >
		Object returnValue = pjp.proceed();
		// < ----- AFTER ADVICE ----- >
		
		// ==============================================================
		// + 3. 후처리 : 끝났을 때의 시간 구해, 실행소요 시간측정
		// ==============================================================
		long end = System.nanoTime();
		
		long temp = end - start;
		double elapsedTime = temp / Math.pow( 10.0 , 9.0 );
		// Math.pow( 10.0 , 9.0 )는 10.0의 아홉제곱이라는 의미이다.
		// 나노초를 기준으로 하여, 단위가 1 / 10억 초이기에 다시 10의 9제곱을 나눠줘야 한다.
		// 나노초가 초보다 작은 단위이기에 작은 단위에서 큰 단위로 바꾸기 위해서는 나눠야 한다. (**)
		// ex. 10억 나노초 / 10억 = 1초
		
		log.info("\t + elapsedTime : {}", elapsedTime);
		
		return returnValue;
		
	} // around

} // end class
