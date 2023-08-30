package kr.co.finmodeun.admin.config.security;

import com.project.cmn.http.util.ResponseUtils;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
public class JwtExceptionFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (JwtException e) {
            log.error(e.getMessage(), e);

            if (e instanceof ExpiredJwtException) {
                ResponseUtils.sendResponse(response, HttpStatus.UNAUTHORIZED, 602);
            } else {
                ResponseUtils.sendResponse(response, HttpStatus.UNAUTHORIZED, 601);
            }
        }
    }
}
