package kr.co.finmodeun.admin.menu.controller;

import com.project.cmn.http.validate.groups.Create;
import com.project.cmn.http.validate.groups.Modify;
import kr.co.finmodeun.admin.menu.dto.CmsMenuInfoDto;
import kr.co.finmodeun.admin.menu.service.CmsMenuInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CmsMenuInfoController {
    private final CmsMenuInfoService cmsMenuInfoService;

    /**
     * 메뉴 목록을 Tree 형태로 조회한다.
     *
     * @param param 조회 조건
     * @return 메뉴 목록
     */
    @PostMapping("/admin/menu/retrieve/list")
    public List<CmsMenuInfoDto> cmsMenuInfoRetrieveList(@RequestBody CmsMenuInfoDto param) {
        return cmsMenuInfoService.cmsMenuInfoRetrieveList(param);
    }

    /**
     * 메뉴 정보를 등록한다.
     *
     * @param param 메뉴 정보
     * @return 등록한 row 수
     */
    @PostMapping("/admin/menu/create")
    public int cmsMenuInfoCreate(@RequestBody @Validated(Create.class) CmsMenuInfoDto param) {
        return cmsMenuInfoService.cmsMenuInfoCreate(param);
    }

    /**
     * 메뉴 정보를 수정한다.
     *
     * @param param 메뉴 정보
     * @return 수정한 row 수
     */
    @PostMapping("/admin/menu/modify")
    public int cmsMenuInfoModify(@RequestBody @Validated(Modify.class) CmsMenuInfoDto param) {
        return cmsMenuInfoService.cmsMenuInfoModify(param);
    }
}