package com.project.cmn.http.accesslog;


import com.project.cmn.http.dto.BaseDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

/**
 * URL 호출 시 거치는 메소드에 대한 정보를 남기기 위한 AOP
 */
@Slf4j
@Aspect
@Configuration
@ConditionalOnProperty(prefix = "project.access.log", name = "aspect", havingValue = "true")
public class AccessLogAspect {
    @Pointcut("execution(* com.project..*Controller.*(..)) || execution(* kr.co.finmodeun..*Controller.*(..))")
    public void pointcutController() {}

    @Pointcut("execution(* com.project..*Service.*(..)) || execution(* kr.co.finmodeun..*Service.*(..))")
    public void pointcutService() {}

    @Pointcut("execution(* com.project..*Mapper.*(..)) || execution(* kr.co.finmodeun..*Mapper.*(..))")
    public void pointcutMapper() {}

    /**
     * 메소드 시작 전에 {@link org.springframework.util.StopWatch} 를 실행
     *
     * @param jp {@link JoinPoint}
     */
    @Before("pointcutController() || pointcutService() || pointcutMapper()")
    public void beforeMethod(JoinPoint jp) {
        MethodSignature signature = (MethodSignature) jp.getSignature();

        this.startStopWatch(jp, signature);
        this.setUserId(jp, signature);
    }

    /**
     * 메소드 종료 후에 {@link org.springframework.util.StopWatch} 를 중지
     *
     * @param jp {@link JoinPoint}
     */
    @After("pointcutController() || pointcutService() || pointcutMapper()")
    public void afterMethod(JoinPoint jp) {
        this.stopStopWatch();
    }

    /**
     * StopWatch 를 시작한다.
     *
     * @param jp {@link JoinPoint}
     * @param signature {@link MethodSignature}
     */
    private void startStopWatch(JoinPoint jp, MethodSignature signature) {
        String executeMethodName = signature.getMethod().getName();

        CmnStopWatch stopWatch = AccessLog.getAccessLogDto().getStopWatch();

        if (stopWatch != null) {
            if (stopWatch.isRunning()) {
                stopWatch.stop();
            }

            stopWatch.start(String.format("%s.%s", signature.getDeclaringType().getSimpleName(), executeMethodName));
        }
    }

    /**
     * StopWatch 를 멈춘다.
     */
    private void stopStopWatch() {
        CmnStopWatch stopWatch = AccessLog.getAccessLogDto().getStopWatch();

        if (stopWatch != null && stopWatch.isRunning()) {
            stopWatch.stop();
        }
    }

    /**
     * {@link BaseDto} 를 상속받은 객체의 경우 creId 와 modId 를 사용자 아이디로 set 한다.
     *
     * @param jp {@link JoinPoint}
     * @param signature {@link MethodSignature}
     */
    private void setUserId(JoinPoint jp, MethodSignature signature) {
        if (StringUtils.endsWith(signature.getDeclaringType().getSimpleName(), "Service")
                && StringUtils.isNotBlank(AccessLog.getAccessLogDto().getUserId())) {
            Object[] args = jp.getArgs();

            if (args != null && args.length > 0) {
                for (Object arg : args) {
                    if (arg instanceof BaseDto dto) {
                        dto.setCreId(AccessLog.getAccessLogDto().getUserId());
                        dto.setModId(AccessLog.getAccessLogDto().getUserId());
                    }
                }
            }
        }
    }
}