package com.project.cmn.http.security;

import com.project.cmn.http.security.jwt.JwtConfig;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
@ToString
@Component
@ConfigurationProperties(prefix = "project.security")
public class SecurityConfig {
    /**
     * 전체 허용인 URI 들
     */
    private List<String> permitAllUris;

    /**
     * JWT 설정들
     */
    private JwtConfig jwt;
}
