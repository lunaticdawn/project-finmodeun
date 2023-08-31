package com.project.cmn.http.jwt;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Getter
@Setter
@ToString
@Component
@ConfigurationProperties(prefix = "project.jwt")
public class JwtConfig {
    /**
     * {@link Environment}에서 project.mybatis 설정을 가져와 {@link JwtConfig}로 변환한다.
     *
     * @param environment {@link Environment}
     * @return {@link JwtConfig}
     */
    public static JwtConfig init(Environment environment) {
        return Binder.get(environment).bindOrCreate("project.jwt", JwtConfig.class);
    }

    /**
     * 비밀 키
     */
    private String secretKey;

    /**
     * AccessToken 만료 시간을 나타내는 문자열
     */
    private String accessTokenExpire;

    /**
     * RefreshToken 만료 시간을 나타내는 문자열
     */
    private String refreshTokenExpire;

    /**
     * 전체 허용인 URI 들
     */
    private List<String> permitAllUris;

    /**
     * AccessToken 만료 시간. TimeUnit.MILLISECONDS
     */
    private long accessTokenExpireTime;

    /**
     * RefreshToken 만료 시간. TimeUnit.MILLISECONDS
     */
    private long refreshTokenExpireTime;

    public void setAccessTokenExpire(String accessTokenExpire) {
        this.accessTokenExpire = accessTokenExpire;
        this.accessTokenExpireTime = this.stringToTime(accessTokenExpire);
    }

    public void setRefreshTokenExpire(String refreshTokenExpire) {
        this.refreshTokenExpire = refreshTokenExpire;
        this.refreshTokenExpireTime = this.stringToTime(refreshTokenExpire);
    }

    /**
     * 시간을 나타내는 문자열을 TimeUnit.MILLISECONDS 로 바꾼다.
     *
     * @param timeStr 시간을 나타내는 문자열
     * @return TimeUnit.MILLISECONDS 로 바꾼 값
     */
    private long stringToTime(String timeStr) {
        long result = 0L;

        if (StringUtils.endsWithIgnoreCase(timeStr, "d")) {
            result = TimeUnit.MILLISECONDS.convert(Integer.parseInt(StringUtils.removeEndIgnoreCase(timeStr, "d")), TimeUnit.DAYS);
        } else if (StringUtils.endsWithIgnoreCase(timeStr, "m")) {
            result = TimeUnit.MILLISECONDS.convert(Integer.parseInt(StringUtils.removeEndIgnoreCase(timeStr, "m")), TimeUnit.MINUTES);
        } else if (StringUtils.endsWithIgnoreCase(timeStr, "s")) {
            result = TimeUnit.MILLISECONDS.convert(Integer.parseInt(StringUtils.removeEndIgnoreCase(timeStr, "s")), TimeUnit.SECONDS);
        }

        return result;
    }
}
