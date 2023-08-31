package kr.co.finmodeun.admin.cmn.service;

import com.project.cmn.http.exception.ServiceException;
import com.project.cmn.http.jwt.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import kr.co.finmodeun.admin.admin.mapper.CmsAdminInfoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class SecurityService {
    private final CmsAdminInfoMapper cmsAdminInfoMapper;

    /**
     * 발급한 Access Token 인지 확인한다.
     *
     * @param request {@link HttpServletRequest}
     */
    public void checkAuth(HttpServletRequest request) {
        String accessToken = JwtUtils.getAccessToken(request);

        // 발급한 Access Token 인지 확인
        // 새로 Access Token 을 발급 받았는데, 이전에 발급한 Access Token 을 사용하는 경우
        if (cmsAdminInfoMapper.selectCountByAccessToken(accessToken) == 0) {
            throw new ServiceException(HttpStatus.UNAUTHORIZED, 603);
        }
    }
}
