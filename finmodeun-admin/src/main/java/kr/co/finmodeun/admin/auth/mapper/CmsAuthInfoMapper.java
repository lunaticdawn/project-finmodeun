package kr.co.finmodeun.admin.auth.mapper;

import kr.co.finmodeun.admin.auth.dto.CmsAuthInfoDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CmsAuthInfoMapper {
    /**
     * 권한 정보 목록을 조회한다.
     *
     * @param param 조회 조건
     * @return 권한 정보 목록
     */
    List<CmsAuthInfoDto> selectCmsAuthInfoList(CmsAuthInfoDto param);

    /**
     * 권한 정보를 등록한다.
     *
     * @param param 권한 정보
     * @return 등록한 row 수
     */
    int insertCmsAuthInfo(CmsAuthInfoDto param);

    /**
     * 권한 정보를 수정한다.
     *
     * @param param 권한 정보
     * @return 수정한 row 수
     */
    int updateCmsAuthInfo(CmsAuthInfoDto param);
}