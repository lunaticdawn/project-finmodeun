package kr.co.finmodeun.admin.cmn.security;

import com.project.cmn.http.security.SecurityConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Slf4j
@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class JwtSecurityConfig {
    private final SecurityConfig securityConfig;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.cors(AbstractHttpConfigurer::disable);

        http.headers(httpSecurityHeadersConfigurer -> httpSecurityHeadersConfigurer.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable).disable());

        http.authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> {
            if (securityConfig.getPermitAllUris() != null && !securityConfig.getPermitAllUris().isEmpty()) {
                for (String permitAllUri : securityConfig.getPermitAllUris()) {
                    authorizationManagerRequestMatcherRegistry.requestMatchers(permitAllUri).permitAll();
                }
            }

            authorizationManagerRequestMatcherRegistry.anyRequest().authenticated();
        });

        http.exceptionHandling(httpSecurityExceptionHandlingConfigurer -> {
            httpSecurityExceptionHandlingConfigurer.authenticationEntryPoint(new CustomAuthenticationEntryPoint());
            httpSecurityExceptionHandlingConfigurer.accessDeniedHandler(new CustomAccessDeniedHandler());
        });

        http.addFilterBefore(new JwtSecurityFilter(securityConfig), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(new JwtExceptionFilter(), JwtSecurityFilter.class);

        return http.build();
    }
}
