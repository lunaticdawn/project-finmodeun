package kr.co.finmodeun.admin.auth.mapper;

import kr.co.finmodeun.admin.auth.dto.CmsAuthAdminRelDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CmsAuthAdminRelMapper {
    /**
     * 권한 코드와 관련된 어드민 목록을 조회한다.
     *
     * @param authCode 권한 코드
     * @return 권한 코드와 관련된 어드민 목록
     */
    List<CmsAuthAdminRelDto> selectCmsAuthAdminRelList(@Param("authCode") String authCode);

    /**
     * 권한-어드민 관계 목록을 등록한다.
     *
     * @param param 권한-어드민 관계 정보
     * @return 등록한 row 수
     */
    int insertCmsAuthAdminRels(CmsAuthAdminRelDto param);

    /**
     * 권한 코드와 관련된 어드민 목록을 삭제한다.
     *
     * @param authCode 권한 코드
     * @return 삭제한 row 수
     */
    int deleteCmsAuthAdminRel(@Param("authCode") String authCode);
}