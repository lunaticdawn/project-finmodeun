package com.project.cmn.http.jwt;

import com.project.cmn.http.WebCmnConstants;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class JwtUtils {
    public static final String BEARER_PREFIX = "Bearer ";

    private JwtUtils() {}

    /**
     * 알고리즘에 맞는 JWT 용 Secret Key 를 생성한다.
     *
     * @param signatureAlgorithmName {@link SignatureAlgorithm}
     * @return 알고리즘에 맞는 JWT 용 Secret Key
     */
    public static String getSecretKey(String signatureAlgorithmName) {
        SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.forName(signatureAlgorithmName));

        return Encoders.BASE64.encode(key.getEncoded());
    }

    /**
     * Access Token 을 생성한다.
     *
     * @param jwtConfig {@link JwtConfig}
     * @param claims Access Token 에 담을 정보들
     * @return 생성한 Access Token
     */
    public static String getAccessToken(JwtConfig jwtConfig, Map<String, Object> claims) {
        Key secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtConfig.getSecretKey()));
        Date expireDate = new Date(Calendar.getInstance().getTimeInMillis() + jwtConfig.getAccessTokenExpireTime());

        return genJwt(secretKey, claims, expireDate);
    }

    /**
     * Refresh Token 을 생성한다.
     *
     * @param jwtConfig {@link JwtConfig}
     * @param claims Refresh Token 에 담을 정보들
     * @return 생성한 Refresh Token
     */
    public static String getRefreshToken(JwtConfig jwtConfig, Map<String, Object> claims) {
        Key secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtConfig.getSecretKey()));
        Date expireDate = new Date(Calendar.getInstance().getTimeInMillis() + jwtConfig.getRefreshTokenExpireTime());

        return genJwt(secretKey, claims, expireDate);
    }

    /**
     * JWT 를 생성한다.
     *
     * @param secretKey 비밀 키
     * @param claims JWT 에 담을 내용
     * @param expireDate JWT 만료일시
     * @return 생성한 JWT
     */
    public static String genJwt(Key secretKey, Map<String, Object> claims, Date expireDate) {
        return Jwts.builder().addClaims(claims).signWith(secretKey, SignatureAlgorithm.HS512).setIssuedAt(new Date()).setExpiration(expireDate).compact();
    }

    /**
     * JWT 를 파싱한다.
     *
     * @param jwtConfig {@link JwtConfig}
     * @param token 파싱할 Token
     * @return 파싱 결과
     */
    public static Jws<Claims> parseClaimsJws(JwtConfig jwtConfig, String token) {
        Key secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtConfig.getSecretKey()));

        return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
    }

    /**
     * JWT 를 파싱한 후 {@link JwsHeader} 를 반환한다.
     *
     * @param jwtConfig {@link JwtConfig}
     * @param token 파싱할 Token
     * @return {@link JwsHeader}
     */
    public static JwsHeader getHeader(JwtConfig jwtConfig, String token) {
        return parseClaimsJws(jwtConfig, token).getHeader();
    }

    /**
     * JWT 를 파싱한 후 {@link Claims} 를 반환한다.
     *
     * @param jwtConfig {@link JwtConfig}
     * @param token 파싱할 Token
     * @return {@link Claims}
     */
    public static Claims getBody(JwtConfig jwtConfig, String token) {
        return parseClaimsJws(jwtConfig, token).getBody();
    }

    /**
     * {@link HttpServletRequest} 에서 Access Token 을 가져온다.
     *
     * @param request {@link HttpServletRequest}
     * @return Access Token
     */
    public static String getAccessToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(WebCmnConstants.HttpHeaderKeys.AUTHORIZATION.code());

        if (StringUtils.isNotBlank(bearerToken) && StringUtils.startsWith(bearerToken, BEARER_PREFIX)) {
            return StringUtils.substring(bearerToken, BEARER_PREFIX.length());
        }

        return null;
    }
}
