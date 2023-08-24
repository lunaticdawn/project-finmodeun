package kr.co.finmodeun.admin.comm.controller;

import com.project.cmn.http.exception.ServiceException;
import com.project.cmn.http.validate.groups.Login;
import kr.co.finmodeun.admin.admin.dto.CmsAdminInfoDto;
import kr.co.finmodeun.admin.admin.service.CmsAdminInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;

@RequiredArgsConstructor
@RestController
public class LoginController {
    private final CmsAdminInfoService cmsAdminInfoService;

    /**
     * 로그인
     *
     * @param param 어드민 정보
     * @throws NoSuchAlgorithmException 어드민 비밀번호를 암호화 시 오류
     * @throws ServiceException 아이디 또는 비밀번호가 틀린 경우
     */
    @PostMapping("/login")
    public void login(@RequestBody @Validated(Login.class) CmsAdminInfoDto param) throws ServiceException, NoSuchAlgorithmException {
        cmsAdminInfoService.login(param);
    }
}