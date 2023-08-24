package kr.co.finmodeun.admin.admin.mapper;

import kr.co.finmodeun.admin.admin.dto.CmsAdminInfoDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CmsAdminInfoMapper {
    /**
     * 어드민 목록을 조회한다.
     *
     * @param param 조회 조건
     * @return 어드민 목록
     */
    List<CmsAdminInfoDto> selectCmsAdminInfoList(CmsAdminInfoDto param);

    /**
     * 어드민 상세를 조회한다.
     *
     * @param adminId 어드민 아이디
     * @return 어드민 상세
     */
    CmsAdminInfoDto selectCmsAdminInfo(@Param("adminId") String adminId);

    /**
     * 어드민 정보를 등록한다.
     *
     * @param param 어드민 정보
     * @return 등록한 row 수
     */
    int insertCmsAdminInfo(CmsAdminInfoDto param);

    /**
     * 어드민 정보를 수정한다.
     *
     * @param param 어드민 정보
     * @return 수정한 row 수
     */
    int updateCmsAdminInfo(CmsAdminInfoDto param);

    /**
     * 비밀번호를 수정한다.
     *
     * @param param 어드민 비밀번호 정보
     * @return 수정한 row 수
     */
    int updateAdminPwd(CmsAdminInfoDto param);

    /**
     * 로그인 일시를 수정한다.
     *
     * @param adminId 어드민 아이디
     * @return 수정한 row 수
     */
    int updateLoginDt(@Param("adminId") String adminId);

    /**
     * 로그인 실패 횟수를 수정한다.
     *
     * @param adminId 어드민 아이디
     * @return 수정한 row 수
     */
    int updateLoginFailuerCnt(@Param("adminId") String adminId);
}