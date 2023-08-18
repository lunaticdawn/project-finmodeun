package kr.co.finmodeun.admin.code.controller;

import com.project.cmn.http.validate.groups.Create;
import com.project.cmn.http.validate.groups.Modify;
import com.project.cmn.http.validate.groups.Retrieve;
import com.project.cmn.http.validate.groups.RetrieveList;
import kr.co.finmodeun.admin.code.dto.CmnCodeInfoDto;
import kr.co.finmodeun.admin.code.service.CmnCodeInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CmnCodeInfoController {
    private final CmnCodeInfoService cmnCodeInfoService;

    /**
     * 코드 목록 조회
     *
     * @param param 조회 조건
     * @return 코드 목록
     */
    @PostMapping("/admin/code/retrieve/list")
    public List<CmnCodeInfoDto> codeRetrieveList(@RequestBody @Validated(RetrieveList.class) CmnCodeInfoDto param) {
        return cmnCodeInfoService.codeRetrieveList(param);
    }

    /**
     * 코드 상세 조회
     *
     * @param param 조회 조건
     * @return 코드 상세
     */
    @PostMapping("/admin/code/retrieve")
    public CmnCodeInfoDto codeRetrieve(@RequestBody @Validated(Retrieve.class) CmnCodeInfoDto param) {
        return cmnCodeInfoService.codeRetrieve(param);
    }

    /**
     * 코드 등록
     *
     * @param param 코드 내용
     * @return 등록한 row 수
     */
    @PostMapping("/admin/code/create")
    public int codeCreate(@RequestBody @Validated(Create.class) CmnCodeInfoDto param) {
        return cmnCodeInfoService.codeCreate(param);
    }

    /**
     * 코드 수정
     *
     * @param param 코드 내용
     * @return 수정한 row 수
     */
    @PostMapping("/admin/code/modify")
    public int codeModify(@RequestBody @Validated(Modify.class) CmnCodeInfoDto param) {
        return cmnCodeInfoService.codeModify(param);
    }
}