package kr.co.finmodeun.admin.auth.service;

import kr.co.finmodeun.admin.auth.dto.CmsAuthAdminRelDto;
import kr.co.finmodeun.admin.auth.mapper.CmsAuthAdminRelMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CmsAuthAdminRelService {
    private final CmsAuthAdminRelMapper cmsAuthAdminRelMapper;

    /**
     * 권한 코드와 관련된 어드민 목록을 조회한다.
     *
     * @param param 권한 코드
     * @return 권한 코드와 관련된 어드민 목록
     */
    public List<CmsAuthAdminRelDto> cmsAuthAdminRelRetrieveList(CmsAuthAdminRelDto param) {
        return cmsAuthAdminRelMapper.selectCmsAuthAdminRelList(param.getAuthCode());
    }

    /**
     * 권한-어드민 관계를 등록한다.
     *
     * @param param 권한-어드민 관계 정보
     * @return 등록한 row 수
     */
    public int cmsAuthAdminRelsCreate(CmsAuthAdminRelDto param) {
        int cnt = 0;

        cmsAuthAdminRelMapper.deleteCmsAuthAdminRel(param.getAuthCode());

        if (param.getAdminIds() != null && !param.getAdminIds().isEmpty()) {
            cnt = cmsAuthAdminRelMapper.insertCmsAuthAdminRels(param);
        }

        return cnt;
    }
}