package kr.co.finmodeun.admin.admin.controller;

import com.github.pagehelper.PageInfo;
import com.project.cmn.http.exception.ServiceException;
import com.project.cmn.http.util.PasswdCheckUtils;
import com.project.cmn.http.validate.groups.ChangePwd;
import com.project.cmn.http.validate.groups.Create;
import com.project.cmn.http.validate.groups.Modify;
import com.project.cmn.http.validate.groups.Retrieve;
import kr.co.finmodeun.admin.admin.dto.CmsAdminInfoDto;
import kr.co.finmodeun.admin.admin.service.CmsAdminInfoService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class CmsAdminInfoController {
    private final CmsAdminInfoService cmsAdminInfoService;

    /**
     * 어드민 목록을 조회한다.
     *
     * @param param 조회 조건
     * @return 어드민 목록
     */
    @PostMapping("/admin/admin/retrieve/list")
    public Object adminInfoRetrieveList(@RequestBody CmsAdminInfoDto param) {
        List<CmsAdminInfoDto> resultList = cmsAdminInfoService.adminInfoRetrieveList(param);

        if (param.getPageNum() != null && param.getPageNum() > 0) {
            return PageInfo.of(resultList);
        } else {
            return resultList;
        }
    }

    /**
     * 어드민 상세를 조회한다.
     *
     * @param param 조회 조건
     * @return 어드민 상세
     */
    @PostMapping("/admin/admin/retrieve")
    public CmsAdminInfoDto adminInfoRetrieve(@RequestBody @Validated(Retrieve.class) CmsAdminInfoDto param) {
        return cmsAdminInfoService.adminInfoRetrieve(param);
    }

    /**
     * 어드민 정보를 등록한다.
     *
     * @param param 어드민 정보
     * @return 등록한 row 수
     * @throws NoSuchAlgorithmException 어드민 비밀번호를 암호화 시 오류
     * @throws ServiceException 비밀번호화 비밀번호 확인이 서로 다른 경우 이거나 형식이 맞지 않는 경우
     */
    @PostMapping("/admin/admin/create")
    public int adminInfoCreate(@RequestBody @Validated(Create.class) CmsAdminInfoDto param) throws NoSuchAlgorithmException, ServiceException {
        if (!StringUtils.equals(param.getAdminPwd(), param.getAdminPwdConfirm())) {
            throw new ServiceException("비밀번호와 비밀번호 확인이 서로 다릅니다.");
        }

        if (!PasswdCheckUtils.checkPasswd(param.getAdminPwd(), 3, 3, 3)) {
            throw new ServiceException("비밀번호의 형식이 맞지 않습니다.");
        }

        return cmsAdminInfoService.adminInfoCreate(param);
    }

    /**
     * 어드민 정보를 수정한다.
     *
     * @param param 어드민 정보
     * @return 수정한 row 수
     */
    @PostMapping("/admin/admin/modify")
    public int adminInfoModify(@RequestBody @Validated(Modify.class) CmsAdminInfoDto param) {
        return cmsAdminInfoService.adminInfoModify(param);
    }

    /**
     * 어드민 비밀번호를 수정한다.
     *
     * @param param 어드민 정보
     * @return 수정한 row 수
     * @throws NoSuchAlgorithmException 어드민 비밀번호를 암호화 시 오류
     * @throws ServiceException 비밀번호화 비밀번호 확인이 서로 다른 경우 이거나 형식이 맞지 않는 경우, 기존 비밀번호가 틀린 경우
     */
    @PostMapping("/admin/admin/pwd/modify")
    public int adminPwdModify(@RequestBody @Validated(ChangePwd.class) CmsAdminInfoDto param) throws NoSuchAlgorithmException, ServiceException {
        if (!StringUtils.equals(param.getAdminPwd(), param.getAdminPwdConfirm())) {
            throw new ServiceException("비밀번호와 비밀번호 확인이 서로 다릅니다.");
        }

        if (!PasswdCheckUtils.checkPasswd(param.getAdminPwd(), 3, 3, 3)) {
            throw new ServiceException("비밀번호의 형식이 맞지 않습니다.");
        }

        return cmsAdminInfoService.adminPwdModify(param);
    }
}
