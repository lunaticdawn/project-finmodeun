package kr.co.finmodeun.admin.auth.service;

import kr.co.finmodeun.admin.auth.dto.CmsAuthInfoDto;
import kr.co.finmodeun.admin.auth.mapper.CmsAuthInfoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CmsAuthInfoService {
    private final CmsAuthInfoMapper cmsAuthInfoMapper;

    /**
     * 권한 정보 목록을 조회한다.
     *
     * @param param 조회 조건
     * @return 권한 정보 목록
     */
    public List<CmsAuthInfoDto> cmsAuthInfoRetrieveList(CmsAuthInfoDto param) {
        return cmsAuthInfoMapper.selectCmsAuthInfoList(param);
    }

    /**
     * 권한 정보를 등록한다.
     *
     * @param param 권한 정보
     * @return 등록한 row 수
     */
    public int cmsAuthInfoCreate(CmsAuthInfoDto param) {
        return cmsAuthInfoMapper.insertCmsAuthInfo(param);
    }

    /**
     * 권한 정보를 수정한다.
     *
     * @param param 권한 정보
     * @return 수정한 row 수
     */
    public int cmsAuthInfoModify(CmsAuthInfoDto param) {
        return cmsAuthInfoMapper.updateCmsAuthInfo(param);
    }
}