package com.project.cmn.http.accesslog;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * # project.access.log.enabled 의 값이 true 이면 설정에 따라 접근 로그를 출력할 수 있는 설정을 적용한다.
 */
@Slf4j
@RequiredArgsConstructor
@Configuration
public class AccessLogConfiguration implements WebMvcConfigurer {
    private final AccessLogConfig accessLogConfig;

    /**
     * 접근 로그를 출력하기 위해 {@link AccessLogInterceptor} 를 추가
     *
     * @param registry {@link InterceptorRegistry} Interceptor 정보를 담고 있는 Registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        log.info("# Add Interceptor: AccessLogInterceptor");
        log.debug("# AccessLogConfig: {}", accessLogConfig);

        InterceptorRegistration interceptorRegistration = registry.addInterceptor(accessLogInterceptor());

        // AccessLogInterceptor 를 적용할 URL
        log.debug("# Path Patterns: {}", accessLogConfig.getPathPatterns());

        if (accessLogConfig.getPathPatterns() != null && !accessLogConfig.getPathPatterns().isEmpty()) {
            for (String pathPattern : accessLogConfig.getPathPatterns()) {
                interceptorRegistration.addPathPatterns(pathPattern);
            }
        }

        // AccessLogInterceptor 를 적용하지 않을 URL
        log.debug("# Exclude Path Patterns: {}", accessLogConfig.getExcludePathPatterns());

        if (accessLogConfig.getExcludePathPatterns() != null && !accessLogConfig.getExcludePathPatterns().isEmpty()) {
            interceptorRegistration.excludePathPatterns(accessLogConfig.getExcludePathPatterns());
        }
    }

    /**
     * {@link AccessLogInterceptor} 내에서 @Resource 를 사용하기 위해 Bean 을 생성한 후 등록하도록 함
     *
     * @return {@link AccessLogInterceptor}
     */
    @Bean
    public AccessLogInterceptor accessLogInterceptor() {
        return new AccessLogInterceptor();
    }

    /**
     * 접근 로그의 LOG_KEY 를 설정하고, Body 를 출력하기 위한 {@link AccessLogFilter} 를 등록한다.
     *
     * @return {@link FilterRegistrationBean<AccessLogFilter>}
     */
    @Bean
    public FilterRegistrationBean<AccessLogFilter> accessLogFilter() {
        log.info("# Register Filter: AccessLogFilter. Order: {}", accessLogConfig.getFilterOrder());

        FilterRegistrationBean<AccessLogFilter> filterRegBean = new FilterRegistrationBean<>();

        filterRegBean.setFilter(new AccessLogFilter());
        filterRegBean.setOrder(accessLogConfig.getFilterOrder());

        // AccessLogFilter 를 적용할 URL
        log.debug("# Url Patterns: {}", accessLogConfig.getUrlPatterns());

        if (accessLogConfig.getUrlPatterns() != null && !accessLogConfig.getUrlPatterns().isEmpty()) {
            for (String urlPattern : accessLogConfig.getUrlPatterns()) {
                filterRegBean.addUrlPatterns(urlPattern);
            }
        }

        return filterRegBean;
    }
}
