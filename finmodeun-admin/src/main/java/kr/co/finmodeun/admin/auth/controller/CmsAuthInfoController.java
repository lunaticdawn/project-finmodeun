package kr.co.finmodeun.admin.auth.controller;

import kr.co.finmodeun.admin.auth.dto.CmsAuthInfoDto;
import kr.co.finmodeun.admin.auth.service.CmsAuthInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CmsAuthInfoController {
    private final CmsAuthInfoService cmsAuthInfoService;

    /**
     * 권한 정보 목록을 조회한다.
     *
     * @param param 조회 조건
     * @return 권한 정보 목록
     */
    @PostMapping("/admin/auth/retrieve/list")
    public List<CmsAuthInfoDto> cmsAuthInfoRetrieveList(@RequestBody CmsAuthInfoDto param) {
        return cmsAuthInfoService.cmsAuthInfoRetrieveList(param);
    }

    /**
     * 권한 정보를 등록한다.
     *
     * @param param 권한 정보
     * @return 등록한 row 수
     */
    @PostMapping("/admin/auth/create")
    public int cmsAuthInfoCreate(@RequestBody CmsAuthInfoDto param) {
        return cmsAuthInfoService.cmsAuthInfoCreate(param);
    }

    /**
     * 권한 정보를 수정한다.
     *
     * @param param 권한 정보
     * @return 수정한 row 수
     */
    @PostMapping("/admin/auth/modify")
    public int cmsAuthInfoModify(@RequestBody CmsAuthInfoDto param) {
        return cmsAuthInfoService.cmsAuthInfoModify(param);
    }
}