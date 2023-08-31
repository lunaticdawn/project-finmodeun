package kr.co.finmodeun.admin.cmn.interceptor;

import com.project.cmn.http.accesslog.AccessLog;
import com.project.cmn.http.jwt.JwtConfig;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.finmodeun.admin.cmn.service.SecurityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
public class SecurityInterceptor implements HandlerInterceptor {
    @Resource(name = "jwtConfig")
    private JwtConfig jwtConfig;

    @Resource(name = "securityService")
    private SecurityService securityService;

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) throws Exception {
        if (isCheckAuthUri()) {
            // 사용자 아이디를 저장한다.
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            AccessLog.getAccessLogDto().setUserId(userDetails.getUsername());

            // 권한을 체크한다.
            securityService.checkAuth(request);
        }

        return true;
    }

    /**
     * 권한을 체크해야 하는 URI 인지 확인한다.
     *
     * @return true: 권한을 체크해야 하는 URI, false: 접근을 허용하는 URI
     */
    private boolean isCheckAuthUri() {
        boolean isCheckAuthUrl = true;

        if (jwtConfig.getPermitAllUris() != null && !jwtConfig.getPermitAllUris().isEmpty()) {
            AntPathMatcher pathMatcher = new AntPathMatcher();
            String requestUri = AccessLog.getAccessLogDto().getRequestUri();

            for (String permitAllUri : jwtConfig.getPermitAllUris()) {
                if (pathMatcher.match(permitAllUri, requestUri)) {
                    isCheckAuthUrl = false;

                    break;
                }
            }
        }

        return isCheckAuthUrl;
    }
}
