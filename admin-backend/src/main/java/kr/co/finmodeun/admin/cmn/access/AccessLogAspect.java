package kr.co.finmodeun.admin.cmn.access;


import com.project.cmn.http.accesslog.AccessLog;
import com.project.cmn.http.accesslog.AccessLogDto;
import com.project.cmn.http.accesslog.CmnStopWatch;
import com.project.cmn.http.dto.BaseDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * URL 호출 시 거치는 메소드에 대한 정보를 남기기 위한 AOP
 */
@Slf4j
@Aspect
@Configuration
public class AccessLogAspect {
    @Pointcut("execution(* com.project..*Controller.*(..)) || execution(* kr.co.finmodeun..*Controller.*(..))")
    public void pointcutController() {}

    @Pointcut("execution(* com.project..*Service.*(..)) || execution(* kr.co.finmodeun..*Service.*(..))")
    public void pointcutService() {}

    @Pointcut("execution(* com.project..*Mapper.*(..)) || execution(* kr.co.finmodeun..*Mapper.*(..))")
    public void pointcutMapper() {}

    /**
     * 각 메소드에 AOP 를 적용한다.
     *
     * @param jp {@link ProceedingJoinPoint}
     * @return 메소드 실행 결과
     * @throws Throwable 메소드를 실행할 때 발생하는 오류
     */
    @Around("pointcutController() || pointcutService() || pointcutMapper()")
    public Object around(ProceedingJoinPoint jp) throws Throwable {
        MethodSignature signature = (MethodSignature) jp.getSignature();
        this.startStopWatch(signature);
        this.setUserId(jp, signature);

        Object result = jp.proceed();

        this.setResCnt(signature, result);
        this.stopStopWatch();

        return result;
    }

    /**
     * StopWatch 를 시작한다.
     *
     * @param signature {@link MethodSignature}
     */
    private void startStopWatch(MethodSignature signature) {
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
        String userId = AccessLog.getAccessLogDto().getUserId();

        if (StringUtils.endsWith(signature.getDeclaringType().getSimpleName(), "Service")
                && StringUtils.isNotBlank(userId)) {
            Object[] args = jp.getArgs();

            if (args != null) {
                for (Object arg : args) {
                    if (arg instanceof BaseDto dto) {
                        dto.setCreId(userId);
                        dto.setModId(userId);
                    }
                }
            }
        }
    }

    /**
     * Mapper 의 메소드 실행 결과가 {@link List} 형인 경우, 그 결과의 갯수를 {@link AccessLogDto} 에 Set 한다.
     *
     * @param signature {@link MethodSignature}
     * @param result 메소드 실행 결과
     */
    private void setResCnt(MethodSignature signature, Object result) {
        if (StringUtils.endsWith(signature.getDeclaringType().getSimpleName(), "Mapper") && result instanceof List<?> resultList) {
            if (resultList.isEmpty()) {
                AccessLog.getAccessLogDto().setResCnt(0);
            } else {
                AccessLog.getAccessLogDto().setResCnt(resultList.size());
            }
        }
    }
}
