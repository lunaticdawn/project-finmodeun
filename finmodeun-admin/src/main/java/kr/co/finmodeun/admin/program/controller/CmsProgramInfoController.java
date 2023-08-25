package kr.co.finmodeun.admin.program.controller;

import com.project.cmn.http.validate.groups.Create;
import com.project.cmn.http.validate.groups.Modify;
import kr.co.finmodeun.admin.program.dto.CmsProgramInfoDto;
import kr.co.finmodeun.admin.program.service.CmsProgramInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CmsProgramInfoController {
    private final CmsProgramInfoService cmsProgramInfoService;

    /**
     * 프로그램 목록을 조회한다.
     *
     * @param param 조회 조건
     * @return 프로그램 목록 조회
     */
    @PostMapping("/admin/program/retrieve/list")
    public List<CmsProgramInfoDto> cmsProgramInfoRetrieveList(@RequestBody CmsProgramInfoDto param) {
        return cmsProgramInfoService.cmsProgramInfoRetrieveList(param);
    }

    /**
     * 프로그램 정보를 등록한다.
     *
     * @param param 프로그램 정보
     * @return 등록한 row 수
     */
    @PostMapping("/admin/program/create")
    public int cmsProgramInfoCreate(@RequestBody @Validated(Create.class) CmsProgramInfoDto param) {
        return cmsProgramInfoService.cmsProgramInfoCreate(param);
    }

    /**
     * 프로그램 정보를 수정한다.
     *
     * @param param 프로그램 정보
     * @return 수정한 row 수
     */
    @PostMapping("/admin/program/modify")
    public int cmsProgramInfoModify(@RequestBody @Validated(Modify.class) CmsProgramInfoDto param) {
        return cmsProgramInfoService.cmsProgramInfoModify(param);
    }
}