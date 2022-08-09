package com.example.springtest.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class LogAspect { // Aspect : 부가 기능 구현체들을 포함하고 있는 모듈
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    // PointCut : 적용할 지점 또는 범위 선택 // PointCut은 경로를 잘 지정해줘야된다.
    @Pointcut("execution(* com.example.springtest..controller.*.*(..)) && !@within(com.example.springtest.annotation.Test)")
    private void Target() {}

    @Pointcut("@annotation(com.example.springtest.annotation.Test) || @within(com.example.springtest.annotation.Test)")
    private void annotationTarget() { }

    // Advice : 실제 부가기능 구현부
    @Around("Target()")
    public Object calcPerformanceAdvice(ProceedingJoinPoint pjp) throws Throwable {
        logger.info("성능 측정을 시작합니다.");
        StopWatch sw = new StopWatch();
        sw.start();

        // 비즈니스 로직 (메인 로직)
        Object result = pjp.proceed();

        sw.stop();
        logger.info("성능 측정이 끝났습니다.");
        logger.info("AOP 관측");
        logger.info("걸린시간: {} ms", sw.getLastTaskTimeMillis());
        //logger.info(result.toString());
        return result;
    }

    @Around("annotationTarget()")
    public Object annotaionAdvice(ProceedingJoinPoint pjp) throws Throwable {
        logger.info("annotaionTest 진행중");
        Object result = pjp.proceed();
        logger.info(result.toString());
        return result;
    }
}
