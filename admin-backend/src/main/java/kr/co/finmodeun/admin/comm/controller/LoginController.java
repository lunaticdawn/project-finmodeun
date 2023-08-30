package kr.co.finmodeun.admin.comm.controller;

import com.project.cmn.http.exception.ServiceException;
import com.project.cmn.http.validate.groups.Login;
import com.project.cmn.http.validate.groups.Retrieve;
import kr.co.finmodeun.admin.admin.dto.CmsAdminInfoDto;
import kr.co.finmodeun.admin.admin.service.CmsAdminInfoService;
import kr.co.finmodeun.admin.comm.dto.TokenDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;

@Slf4j
@RequiredArgsConstructor
@RestController
public class LoginController {
    private final CmsAdminInfoService cmsAdminInfoService;

    /**
     * 로그인
     *
     * @param param 어드민 정보
     * @return {@link TokenDto}
     * @throws NoSuchAlgorithmException 어드민 비밀번호를 암호화 시 오류
     * @throws ServiceException 아이디 또는 비밀번호가 틀린 경우
     */
    @PostMapping("/admin/login")
    public TokenDto login(@RequestBody @Validated(Login.class) CmsAdminInfoDto param) throws ServiceException, NoSuchAlgorithmException {
        return cmsAdminInfoService.login(param);
    }

    /**
     * Refresh Token 을 이용하여 새로운 Access Token 을 만들어 반환한다.
     *
     * @param param {@link TokenDto}
     * @return 새로운 Access Token
     */
    @PostMapping("/admin/refresh")
    public TokenDto refresh(@RequestBody @Validated(Retrieve.class) TokenDto param) {
        return cmsAdminInfoService.refresh(param);
    }
}
