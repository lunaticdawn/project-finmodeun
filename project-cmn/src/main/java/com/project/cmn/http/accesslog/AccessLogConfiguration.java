package com.project.cmn.http.accesslog;

import com.project.cmn.http.jwt.JwtConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * # project.access.log.enabled 의 값이 true 이면 설정에 따라 접근 로그를 출력할 수 있는 설정을 적용한다.
 */
@Slf4j
@Configuration
@ConditionalOnProperty(prefix = "project.access.log", value = "enabled", havingValue = "true")
public class AccessLogConfiguration implements WebMvcConfigurer, EnvironmentAware {
    private AccessLog accessLog;

    @Override
    public void setEnvironment(Environment environment) {
        AccessLogConfig accessLogConfig = AccessLogConfig.init(environment);
        JwtConfig jwtConfig = JwtConfig.init(environment);

        accessLog = new AccessLog(accessLogConfig, jwtConfig);
    }

    /**
     * 접근 로그를 출력하기 위해 {@link AccessLogInterceptor} 를 추가
     *
     * @param registry {@link InterceptorRegistry} Interceptor 정보를 담고 있는 Registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        log.info("# Add Interceptor: AccessLogInterceptor");
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(new AccessLogInterceptor(accessLog));

        // AccessLogInterceptor 를 적용할 URL
        if (accessLog.getAccessLogConfig().getPathPatterns() != null && !accessLog.getAccessLogConfig().getPathPatterns().isEmpty()) {
            log.debug("# Path Patterns: {}", accessLog.getAccessLogConfig().getPathPatterns());

            for (String pathPattern : accessLog.getAccessLogConfig().getPathPatterns()) {
                interceptorRegistration.addPathPatterns(pathPattern);
            }
        }

        // AccessLogInterceptor 를 적용하지 않을 URL
        if (accessLog.getAccessLogConfig().getExcludePathPatterns() != null && !accessLog.getAccessLogConfig().getExcludePathPatterns().isEmpty()) {
            log.debug("# Exclude Path Patterns: {}", accessLog.getAccessLogConfig().getExcludePathPatterns());

            interceptorRegistration.excludePathPatterns(accessLog.getAccessLogConfig().getExcludePathPatterns());
        }
    }

    /**
     * 접근 로그의 LOG_KEY 를 설정하고, Body 를 출력하기 위한 {@link AccessLogFilter} 를 등록한다.
     *
     * @return {@link FilterRegistrationBean<AccessLogFilter>}
     */
    @ConditionalOnProperty(prefix = "project.access.log", value = "filter", havingValue = "true")
    @Bean
    public FilterRegistrationBean<AccessLogFilter> accessLogFilter() {
        log.info("# Register Filter: AccessLogFilter. Order: {}", accessLog.getAccessLogConfig().getFilterOrder());

        FilterRegistrationBean<AccessLogFilter> filterRegBean = new FilterRegistrationBean<>();

        filterRegBean.setFilter(new AccessLogFilter());
        filterRegBean.setOrder(accessLog.getAccessLogConfig().getFilterOrder());

        // AccessLogFilter 를 적용할 URL
        if (accessLog.getAccessLogConfig().getUrlPatterns() != null && !accessLog.getAccessLogConfig().getUrlPatterns().isEmpty()) {
            log.debug("# Url Patterns: {}", accessLog.getAccessLogConfig().getUrlPatterns());

            for (String urlPattern : accessLog.getAccessLogConfig().getUrlPatterns()) {
                filterRegBean.addUrlPatterns(urlPattern);
            }
        }

        return filterRegBean;
    }
}
