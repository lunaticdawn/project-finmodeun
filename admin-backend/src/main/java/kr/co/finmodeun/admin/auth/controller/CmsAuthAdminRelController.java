package kr.co.finmodeun.admin.auth.controller;

import kr.co.finmodeun.admin.auth.dto.CmsAuthAdminRelDto;
import kr.co.finmodeun.admin.auth.service.CmsAuthAdminRelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CmsAuthAdminRelController {
    private final CmsAuthAdminRelService cmsAuthAdminRelService;

    /**
     * 권한 코드와 관련된 어드민 목록을 조회한다.
     *
     * @param param 권한 코드
     * @return 권한 코드와 관련된 어드민 목록
     */
    @PostMapping("/admin/auth/admin/retrieve/list")
    public List<CmsAuthAdminRelDto> cmsAuthAdminRelRetrieveList(@RequestBody CmsAuthAdminRelDto param) {
        return cmsAuthAdminRelService.cmsAuthAdminRelRetrieveList(param);
    }

    /**
     * 권한-어드민 관계를 등록한다.
     *
     * @param param 권한-어드민 관계 정보
     * @return 등록한 row 수
     */
    @PostMapping("/admin/auth/admin/create")
    public int cmsAuthAdminRelsCreate(@RequestBody CmsAuthAdminRelDto param) {
        return cmsAuthAdminRelService.cmsAuthAdminRelsCreate(param);
    }
}