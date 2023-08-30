package kr.co.finmodeun.admin.config.security;

import com.project.cmn.http.jwt.JwtConfig;
import com.project.cmn.http.jwt.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import static com.project.cmn.http.WebCmnConstants.HttpHeaderKeys;

@Slf4j
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {
    private final JwtConfig jwtConfig;

    private static final String BEARER_PREFIX = "Bearer ";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = resolveToken(request);

        if (StringUtils.isNotBlank(token)) {
            this.setAuthentication(token);
        }

        filterChain.doFilter(request, response);
    }

    /**
     * 사용자 권한 정보를 {@link SecurityContextHolder} 에 담는다.
     *
     * @param token 발급받은 Access Token
     */
    private void setAuthentication(String token) {
        Claims claims = JwtUtils.getBody(jwtConfig, token);

        log.debug("### claims: {}", claims);

        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(HttpHeaderKeys.AUTHORITIES.code()).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .toList();

        UserDetails principal = new User((String) claims.get("id"), "", authorities);
        Authentication authentication = new UsernamePasswordAuthenticationToken(principal, "", authorities);

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    /**
     * {@link HttpServletRequest} 에서 Access Token 을 가져온다.
     *
     * @param request {@link HttpServletRequest}
     * @return Access Token
     */
    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(HttpHeaderKeys.AUTHORIZATION.code());

        if (StringUtils.isNotBlank(bearerToken) && StringUtils.startsWith(bearerToken, BEARER_PREFIX)) {
            return StringUtils.substring(bearerToken, BEARER_PREFIX.length());
        }

        return null;
    }
}
